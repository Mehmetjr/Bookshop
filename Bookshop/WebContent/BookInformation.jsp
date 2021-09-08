<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%
    session=request.getSession(false);
    if(session==null)
    {
        response.sendRedirect("login.jsp");
    }

%> 
<head>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
</head>
<body>
 <center>
 	<h2>${book.title}</h2>
 	                    <a href="list">Go to home page</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="edit?id=<c:out value='${book.id}' />">Update</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${book.id}' />">Delete</a>      
 </center>
  <div align="center">
        <table border="1" cellpadding="5">
            <img src="${book.img}">
            <tr>
                <th>Title</th>
                <td>${book.title}</td>
            </tr>
            <tr>
                <th>Author</th>
                <td>${book.author}</td>                
            </tr>
            <tr>
            	<th>Genre</th>
                <td>${book.genre}</td>
            </tr>
            <tr>
            	<th>Year of Publishing</th>
            	<td>${book.date}</td>
            </tr>
            <tr>
            	<th>Information</th>
            	<td>${book.info}</td>
            </tr>		
        </table>
    </div>   
</body>
</html>
