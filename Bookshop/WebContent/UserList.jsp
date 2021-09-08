<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
    <center>
        <h2>
       		<a href="newUser">Add New User</a>
			&nbsp;&nbsp;&nbsp;
            <a href="list">Go to home page</a>
			&nbsp;&nbsp;&nbsp;
			<a href="logout">Logout</a>            
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Users</h2></caption>
            <tr>
            	<th>ID</th>
                <th>Fullname</th>
                <th>Email</th>
                <th>Password</th>
                <th>Role</th>
                <th>Cart</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="u" items="${listUser}">
                <tr>
               		<td><c:out value="${u.id}" /></td>
                    <td><c:out value="${u.fullname}" /></td>
                    <td><c:out value="${u.email}" /></td>
                    <td><c:out value="${u.password}" /></td>
                    <td><c:out value="${u.role}" /></td>
                    <td><c:out value="${u.cart}" /></td>
                    <td>
                     	<a href="userUpdate?id=<c:out value='${u.id}' />">Update User</a> 
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deletefromusers?id=<c:out value='${u.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>