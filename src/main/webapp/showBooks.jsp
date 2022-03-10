<%@ page import="model.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Book" %><%--
  Created by IntelliJ IDEA.
  User: shalev
  Date: 06/03/2022
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="Home.css">
    <title>Title</title>
</head>
<body>
<nav id="nav1" class="navBar">

    <div id="navDiv" class="navPadding">
        <a href="Home.jsp">Home</a>
        <a href="Category.jsp">Category</a>
        <a href="Student.jsp">Student</a>
        <a id="login" class="login" href="index.jsp">Sign in</a>
    </div>

</nav>
<%    Student s = (Student) session.getAttribute("loggedInUser");
    if (s == null){
        request.setAttribute("error","Only Logged in users can see this page");
        request.getRequestDispatcher("error.jsp").forward(request,response);
        return;
    }
%>
<%
    ArrayList<Book> books = (ArrayList<Book>) session.getAttribute("bookList");
    if(books == null){
        request.setAttribute("error","No books found");
        request.getRequestDispatcher("error.jsp").forward(request,response);
        return;
    }

    for(Book b : books)
    {%>
<tr>
    <td><%=b.getIsbn()%></td>
    <td><%=b.getBookName()%></td>
    <td><%=b.getAuthorName()%></td>
    <td><%=b.getCategory()%></td>
    <td><%=b.getReleaseYear()%></td>
    <td><a href="BorrowServlet?isbn=<%=b.getIsbn()%>">Borrow</a></td>
</tr>
<%}%>

</body>
</html>
