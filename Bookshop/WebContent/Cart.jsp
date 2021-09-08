<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
    <center>
        <h1>${user.role}</h1>
        <h2>
            <a href="list">Go to home page</a>
			&nbsp;&nbsp;&nbsp;
			<a href="logout">Logout</a>            
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Cart</h2></caption>
            <tr>
            	<th>ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Genre</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="book" items="${listBook}">
                <tr>
               		<td><c:out value="${book.id}" /></td>
                    <td><c:out value="${book.title}" /></td>
                    <td><c:out value="${book.author}" /></td>
                    <td><c:out value="${book.genre}" /></td>
                    <td>
                     	<a href="Info?id=<c:out value='${book.id}' />">Full Information</a> 
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deletefromcart?id=<c:out value='${book.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>