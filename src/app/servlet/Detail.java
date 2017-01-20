package app.servlet;

import app.model.Model;
import app.model.entity.Item;
import app.model.repository.ItemRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by adam on 19/01/2017.
 */
public class Detail extends Servlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Model model = Model.getInstance();
            ItemRepository itemRepository = model.getItemRepository();
            Item item = itemRepository.getById(id);
            if(item != null) {
                request.setAttribute("item", item);
                renderTemplate(request,response);
            } else {
                renderError(request,response);
            }
        } catch (Exception e) {
            renderError(request,response);
        }
    }

    private void renderError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("alert", "Item with this id doesn't exist");
        renderTemplate("404",request,response);
    }
}
