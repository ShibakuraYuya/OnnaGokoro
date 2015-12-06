package context;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class WebRequestContext implements RequestContext{
   private Map parameters;
   private HttpServletRequest request;

   public WebRequestContext(){}

   public String getCommandPath(){
      String servletPath = request.getServletPath();
      System.out.println(servletPath);
      String commandPath = servletPath.substring(1);
      return commandPath;
   }

   public String[] getParameter(String key){
      return (String[])parameters.get(key);
   }

   public Object getRequest(){
      return request;
   }

   public void setRequest(Object request){
      this.request = (HttpServletRequest)request;
      parameters = this.request.getParameterMap();
   }
}
