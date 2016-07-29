<%--
  Created by IntelliJ IDEA.
  User: Tomas
  Date: 28/07/2016
  Time: 10:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<c:forEach items="${songs}" var="song">
    <h4>${song.id}</h4>
    <h4>${song.name}</h4>
    <h4>${song.art}</h4>
    <br>
</c:forEach>
</body>
</html>