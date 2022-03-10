<%@ page import="model.Student" %><%--
  Created by IntelliJ IDEA.
  User: shalev
  Date: 03/03/2022
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Library - Home</title>
    <link rel="stylesheet" type="text/css" href="Home.css">
</head>
<body>
<%    Student s = (Student) session.getAttribute("loggedInUser");
    if (s == null){
        request.setAttribute("error","Only Logged in users can see this page");
        request.getRequestDispatcher("error.jsp").forward(request,response);
        return;
    }
%>
<nav>
    <div id="navDiv" class="navPadding">
        <a href="Home.jsp">Home</a>
        <a href="Category.jsp">Category</a>
        <a href="Student.jsp">Student</a>
        <a class="login" href="index.jsp">hello <%=s.getFirstName()%></a>
    </div>

</nav>

<h1>Library</h1>
<div class="content">
    <p style="text-align: center; font-family: sans-serif" ><em><strong>Welcome to the Library</strong></em><br />
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
    </p>

</div>
<div class="center">
    <label class="freeText">Write To Us
        <textarea  class="freeText"></textarea>
    </label>
</div>
<footer>
    <h4 id="timer">timer</h4>


    made by Shalev Gerbi
</footer>

</body>
</html>
