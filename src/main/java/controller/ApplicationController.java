package controller;

import context.*;

public interface ApplicationController{
   RequestContext getRequestContext(Object request);
   ResponseContext handleRequest(RequestContext reqc);
   void handleResponse(RequestContext reqc, ResponseContext resc);
}
