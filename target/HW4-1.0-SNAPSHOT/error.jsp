<%@ page import="model.Student" %><%--
  Created by IntelliJ IDEA.
  User: shalev
  Date: 03/03/2022
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean scope="request" id="error" type="java.lang.String"></jsp:useBean>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="Home.css">
</head>
<body>
<%--<link href="errorCss.css" type="text/css" rel='stylesheet'>--%>
<nav>
    <div id="navDiv" class="navPadding">
        <a href="Home.jsp">Home</a>
        <a href="Category.jsp">Category</a>
        <a href="Student.jsp">Student</a>
        <a class="login" href="index.jsp">Login</a>
    </div>

</nav>
<h1 class="biggest"><%=error%></h1>
<a id="blueOption" href="index.jsp">return Login</a>
</body>
</html>
