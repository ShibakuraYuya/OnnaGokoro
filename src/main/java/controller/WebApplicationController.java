package controller;

import context.*;
import factory.*;
import command.*;
import controller.ApplicationController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

public class WebApplicationController implements ApplicationController{
   public RequestContext getRequestContext(Object request){
      RequestContext reqc = new WebRequestContext();
      reqc.setRequest(request);
      return reqc;
   }

   public ResponseContext handleRequest(RequestContext reqc){
      AbstractCommand command = CommandFactory2.getCommand(reqc);
      command.init(reqc);

      ResponseContext resc = command.execute(new WebResponseContext());

      return resc;
   }

   public void handleResponse(RequestContext reqc, ResponseContext resc){
      HttpServletRequest req = (HttpServletRequest)reqc.getRequest();
      HttpServletResponse res = (HttpServletResponse)resc.getResponse();

      req.setAttribute("data",resc.getResult());

      RequestDispatcher dispatcher = req.getRequestDispatcher(resc.getTarget());

      try{
         dispatcher.forward(req,res);
      }catch(ServletException e){
         e.printStackTrace();
      }catch(IOException e){
         e.printStackTrace();
      }
   }
}
