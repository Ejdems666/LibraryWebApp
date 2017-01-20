<%@ page import="app.model.entity.Category" %>
<%@ page import="java.util.Collection" %>
<form method="POST">
    <label for="name">Name</label>
    <input type="text" id="name" name="name">

    <label for="description">Description</label>
    <input type="text" id="description" name="description">

    <label for="category">Category</label>
    <select id="category" name="category">
        <% for (Category category : ((Collection<Category>) request.getAttribute("categories"))) { %>
            <option value="<%=category.getId()%>"><%=category.getName()%></option>
        <% } %>
    </select>
    <input type="submit" name="add">
</form>
