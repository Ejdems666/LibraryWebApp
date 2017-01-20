<%@ page import="app.model.entity.Item" %>
<%@ page import="java.util.Collection" %>
<%
    Collection<Item> items = ((Collection<Item>) request.getAttribute("items"));
%>
<div class="row">
    <%
        for (Item item : items) {
    %>
    <div class="col-lg-4 col-md-6">
        <div class="thumbnail item">
            <a href="/detail?id=<%= item.getId() %>" target="_blank">
                <img src="assets/img/<%= item.getImg() %>" alt="<%= item.getName() %>">
                <div class="caption">
                    <p><%= item.getName() %>
                    </p>
                </div>
            </a>
        </div>
    </div>
    <%
        }
    %>
</div>