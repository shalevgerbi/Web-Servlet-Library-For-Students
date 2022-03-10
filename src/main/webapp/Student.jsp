<%@ page import="model.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.DAO" %>
<%@ page import="model.Book" %><%--
  Created by IntelliJ IDEA.
  User: shalev
  Date: 03/03/2022
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="Home.css">
    <title>Title</title>
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
<%String message="";
message = (String) request.getSession().getAttribute("message");
if(message ==null)
    message = "hi "+s.getFirstName();
%>
<h1><%=message%></h1>

<h1 id="studentPage">This is the student page</h1>
<table>
    <tr>
        <th>Book's ISBN</th>
        <th>Book's Name</th>
        <th>Book's Author</th>
        <th>Category</th>
        <th>Release Year</th>
    </tr>

    <%ArrayList<Book> books = DAO.getBooks();
        for(Book b : books){
    %>

    <tr>
        <td><%=b.getIsbn()%></td>
        <td><%=b.getBookName()%></td>
        <td><%=b.getAuthorName()%></td>
        <td><%=b.getCategory()%></td>
        <td><%=b.getReleaseYear()%></td>
        <td><a href="BorrowServlet?isbn=<%=b.getIsbn()%>">Borrow</a></td>
    </tr>
    <%}%>
</table>
</body>
</html>
