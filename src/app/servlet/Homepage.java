package app.servlet;

import app.model.Connector;
import app.model.Model;
import app.model.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by adam on 11/01/2017.
 */
public class Homepage extends Servlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = Model.getInstance();
        UserRepository userRepository = model.getUserRepository();
        this.renderTemplate(request,response);
    }
}
