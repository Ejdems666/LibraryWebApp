<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>
    <% if (request.getAttribute("alert") != null) {%>
    ${alert}
    <% } %>
    <jsp:include page="templates/${template}.jsp"/>
</body>
</html>
