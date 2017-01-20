<%@ page import="app.model.entity.Category" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="assets/style.css">
    <title>${title}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="assets/bootstrap.min.css">
    <script src="assets/jquery-3.0.0.min.js"></script>
    <script src="assets/bootstrap.min.js"></script>
</head>
<body>
    <% if (request.getAttribute("alert") != null) {%>
    ${alert}
    <% } %>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div>
                <img src="assets/img/Library.jpeg" alt="Just a minute" style="width:250px;height:60px;" >
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/"><span class="glyphicon glyphicon-home"></span> Homepage </a></li>
                    <li><a href="/add"><span class="glyphicon glyphicon-plus"></span> ADD </a></li>
                    <form method="GET" action="/" id="search-form">
                        <div class="search-control">
                            <label for="search">Search:</label>
                            <input type="search" id="search" name="search">
                        </div>
                        <div class="search-control">
                            <label for="order">Order By name</label>
                            <input value="order" id="order" name="order" type="checkbox">
                        </div>
                        <div class="search-control">
                            <label for="category">Category</label>
                            <select id="category" name="category">
                                <option value="all">all</option>
                                <% for (Category category : ((Collection<Category>) request.getAttribute("categories"))) { %>
                                <option value="<%=category.getId()%>"><%=category.getName()%></option>
                                <% } %>
                            </select>
                        </div>
                        <button  class="search-control" name="go" value="true">Go</button>
                    </form>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container">
        <jsp:include page="templates/${template}.jsp"/>
    </div>
    <footer class="container-fluid text-center">
        <p>Online Library Copyright</p>
    </footer>
</body>
</html>
