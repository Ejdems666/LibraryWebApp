package app.servlet;

import app.model.Model;
import app.model.entity.User;
import app.model.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by adam on 19/01/2017.
 */
@WebServlet(name = "SignUp")
public class SignUp extends Servlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = Model.getInstance();
        UserRepository userRepository = model.getUserRepository();

        User user = new User();
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        userRepository.persistAndFlush(user);

        request.setAttribute("alert", "user with id "+user.getId()+" was added");
        renderTemplate(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        renderTemplate(request,response);
    }
}
