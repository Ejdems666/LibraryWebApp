package app.servlet;

import app.ItemNameComparator;
import app.model.Model;
import app.model.entity.Item;
import app.model.repository.CategoryRepository;
import app.model.repository.ItemRepository;
import hyggedb.select.Condition;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by adam on 11/01/2017.
 */
public class Homepage extends Servlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = Model.getInstance();
        processSearchAndFilterForm(request, model);

        CategoryRepository categoryRepository = model.getCategoryRepository();
        request.setAttribute("categories", categoryRepository.findAll());
        this.renderTemplate(request, response);
    }

    private void processSearchAndFilterForm(HttpServletRequest request, Model model) {
        ItemRepository itemRepository = model.getItemRepository();
        if (request.getParameter("go") != null) {
            String search = request.getParameter("search");
            Condition condition = new Condition("", "item", "name LIKE ?", "%" + search + "%");
            String category = request.getParameter("category");
            if (category != null && !category.equals("all")) {
                condition.and("category_id=?", Integer.parseInt(category));
            }
            ArrayList<Item> items = new ArrayList<>(itemRepository.findBy(condition));
            if (request.getParameter("order") != null) {
                items.sort(new ItemNameComparator());
            }
            request.setAttribute("items", items);
        } else {
            request.setAttribute("items", itemRepository.findAll());
        }
    }
}
