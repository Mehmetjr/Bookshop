<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books Store Application</title>
</head>
<body>
    <center>
        <h1>Books Management</h1>
        <h2>
            <c:if test="${selecteduser != null}">
           		<a href="newUser">Add New User</a>
        	</c:if>
            &nbsp;&nbsp;&nbsp;
            <a href="users">List All Users</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${selecteduser != null}">
            <form action="updateUser" method="post">
        </c:if>
        <c:if test="${selecteduser == null}">
            <form action="addUser" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${selecteduser != null}">
                        Edit User
                    </c:if>
                    <c:if test="${selecteduser == null}">
                        Add New User
                    </c:if>
                </h2>
            </caption>
                <c:if test="${selecteduser != null}">
                    <input type="hidden" name="id" value="<c:out value='${selecteduser.id}' />" />
                </c:if>           
            <tr>
                <th>email: </th>
                <td>
                    <input type="text" name="email" size="45"
                            value="<c:out value='${selecteduser.email}' />"
                        />
                </td>
            </tr>
             <tr>
                <th>password: </th>
                <td>
                    <input type="text" name="password" size="45"
                            value="<c:out value='${selecteduser.password}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>fullname: </th>
                <td>
                    <input type="text" name="fullname" size="90"
                            value="<c:out value='${selecteduser.fullname}' />"
                        />
                </td>
                
            </tr>
            <tr>
                <th>role: </th>
                <td>
                      <select name="role" id="role">
   						 <option value="admin">admin</option>
   						 <option value="user">user</option>
					  </select>
                </td>
                
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>

</html>