<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Customer</title>
</head>
<body style="background-color: orange;">
	<h1>List Customers</h1>
		<c:if test="${!empty coustmerDtos}">
			<table align="left" border="1">
				<tr>
					<th>Customers Id</th>
					<th>Customers FirstName</th>
					<th>Customers LastName</th>
					<th>Customers Mob_no</th>
					<th>Customers Address</th>
					<th>Customers City</th>
					<th>Customers Email_Id</th>
					<th>Customers Password</th>
					<th>Action</th>
				</tr>

				<c:forEach items="${coustmerDtos}" var="coustmer">
					<tr>
						<td><c:out value="${coustmer.getId()}"/></td>
						<td><c:out value="${coustmer.getFirstname()}"/></td>
						<td><c:out value="${coustmer.getLastname()}"/></td>
						<td><c:out value="${coustmer.getMobno()}"/></td>
						<td><c:out value="${coustmer.getAddress()}"/></td>
						<td><c:out value="${coustmer.getCity()}"/></td>
						<td><c:out value="${coustmer.getEmail()}"/></td>
					    <td><c:out value="${coustmer.getPassw()}"/></td>
						<td align="center">
						<a href="cstdeletedirect.html?id=${coustmer.getId()}">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
</body>
</html>
