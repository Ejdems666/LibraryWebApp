<%@ page import="app.model.entity.Item" %>
<% Item item = ((Item) request.getAttribute("item")); %>
<header>
    <h1><%= item.getName() %></h1>
</header>
<div>
    <div class="row">
        <div class="col-md-4">
            <img src="assets/img/<%=item.getImg()%>" width="400" height="600" alt="<%= item.getName() %>"
                 align="left">
        </div>
    </div>
</div>
<article>
    <h1>DESCRIPTION:</h1>
    <p>
        <%= item.getDescription() %>
    </p>
</article>
