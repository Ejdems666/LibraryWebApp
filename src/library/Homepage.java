package library;

import model.Connector;
import model.repository.ItemRepository;
import model.repository.UserRepository;

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
        request.setAttribute("title","title");
        this.renderTemplate(request,response);
        try {
            Connector connector = new Connector();
            UserRepository userRepository = new UserRepository(connector);
            System.out.println(userRepository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
