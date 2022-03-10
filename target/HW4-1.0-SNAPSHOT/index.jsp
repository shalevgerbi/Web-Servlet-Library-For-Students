<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Library - Login</title>
    <link rel="stylesheet" type="text/css" href="./Login.css">
    <%session.invalidate();%>
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
<h1>Sign in to our Library</h1>
<form id="form1" action="LoginServlet">
    <label for="email">E-Mail</label>
    <input name="emailTB" id="email" class="nameInput myInput" placeholder="please enter your E-Mail" type="text" value=""/>
    <label for="password">Password:</label>
    <input id="password" name="passTB" class="passInput myInput" placeholder="please enter your password" type="password" value=""/>
    <button type="submit">Submit</button>
</form>
</body>
</html>