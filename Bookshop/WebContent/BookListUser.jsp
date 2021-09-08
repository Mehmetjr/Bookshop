<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%
    session=request.getSession(false);
    if(session==null)
    {
        response.sendRedirect("login.jsp");
    }

%> 
<head>
    <title>Books Store Application</title>
</head>
<body>
    <center>
        <h1>${user.cart}</h1>
        <h2>
			<form action="ControllerServlet" method="get">
			             <select name="GenreFilter" id="GenreFilter">
            			 		<option value="all">all</option>
   						 		<option value="Action and Adventure">Action and Adventure</option>
   						 		<option value="Classics">Classics</option>
 						 		<option value="Comic Book or Graphic Novel">Comic Book or Graphic Novel</option>
   						 		<option value="Detective and Mystery">Detective and Mystery</option>
   						 		<option value="Fantasy">Fantasy</option>
   						 		<option value="Historical Fiction">Historical Fiction</option>
 								<option value="Horror">Horror</option>
   						 		<option value="Literary Fiction">Literary Fiction</option>
						 </select>
				  &nbsp;&nbsp;&nbsp;
             	 <input type="date" id="DateFilter" name="DateFilter">
             	  &nbsp;&nbsp;&nbsp;
				 <input type="submit" value="Filter">
				  &nbsp;&nbsp;&nbsp;
				 <a href="cart">Go to cart</a>
				 &nbsp;&nbsp;&nbsp;
				 <a href="logout">Logout</a>
			</form>
			
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5" id="table">
            <caption><h2>List of Books</h2></caption>
            <tr>
                <th>Title</th>
                <th>Author</th>
                <th>Genre</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="book" items="${listBook}">
                <tr>
                    <td><c:out value="${book.title}" /></td>
                    <td><c:out value="${book.author}" /></td>
                    <td><c:out value="${book.genre}" /></td>
                    <td>
                       <a href="Info?id=<c:out value='${book.id}' />">Full Information</a> 
                       &nbsp;&nbsp;&nbsp;&nbsp;
                       <a href="add?id=<c:out value='${book.id}' />">Add To Chart</a>                   
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>

</html>