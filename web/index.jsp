<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="assets/style.css">
    <title>${title}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
                <img src="Library.gif" alt="Just a minute" style="width:250px;height:60px;" >
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="login.html"><span class="glyphicon glyphicon-user"></span> Log In</a></li>
                    <li><a href="signup.html"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                    <li><a href="add.html"><span class="glyphicon glyphicon-shopping-cart"></span> ADD </a></li>
                    <form action="search.html">
                        Search: <input type="search" id="mySearch" name="search">
                    </form>
                </ul>
            </div>
        </div>
    </nav>
    <jsp:include page="templates/${template}.jsp"/>
    <footer class="container-fluid text-center">
        <p>Online Library Copyright</p>
    </footer>
</body>
</html>
