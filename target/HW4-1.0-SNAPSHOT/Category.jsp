<%@ page import="model.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.DAO" %><%--
  Created by IntelliJ IDEA.
  User: shalev
  Date: 03/03/2022
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="Category.css">
    <link rel="stylesheet" type="text/css" href="Home.css">
    <title>Library - Category</title>
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
<h1 id="changing">Choose Your Category</h1>
<table>
    <tr>
        <th>
            Category
        </th>
    </tr>
    <%ArrayList<String> categories = DAO.getCategories();
    if(categories == null){
        request.setAttribute("error","no books in DB");
        request.getRequestDispatcher("error.jsp").forward(request,response);
        return;
    }
    for(String category : categories)
    {
    %>
    <tr>
    <td><%=category%></td>
    <td><a href="ShowBooksCatServlet?category=<%=category%>">show books</a></td>
    </tr>
    <%}
    %>


</table>
</div>
</body>
</html>
