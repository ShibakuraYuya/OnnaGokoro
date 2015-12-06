import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import controller.*;
import context.*;

public class FrontServlet extends HttpServlet{
   public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws IOException, ServletException{
         doPost(req, res);
   }

  public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws IOException, ServletException{
      req.setCharacterEncoding("utf-8");

      // ApplicationControllerの実装クラスのインスタンスを取得
      ApplicationController app = new WebApplicationController();

      // ファクトリーメソッドを呼びだして、具象クラスのインスタンスを取得
      RequestContext reqc = app.getRequestContext(req);

      // ファクトリーメソッドを呼び出し、RequestContextを渡して、ResponseContextを取得
      ResponseContext resc = app.handleRequest(reqc);

      // ResponseContextにHttpServletResponseインターフェイスを実装するクラスのインスタンスを格納
      resc.setResponse(res);

      // VIEWの選択と転送処理を行う
      app.handleResponse(reqc,resc);
    }
}
