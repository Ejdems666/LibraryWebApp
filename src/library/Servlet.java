package library;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by adam on 12/01/2017.
 */
public abstract class Servlet extends HttpServlet {
    protected void renderTemplate(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        renderTemplate(this.getServletName().toLowerCase(),request,response);
    }

    protected void renderTemplate(String template,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setAttribute("template",template);
        if(request.getAttribute("title") == null) {
            request.setAttribute("title",template);
        }
        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
    }
}
