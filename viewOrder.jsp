<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
</head>
<body style="background-color: orange;">
<div>Welcome To User:: ${firstname}</div>
<h4 style="color:red">Order Details</h4>
	
<form action="viewhistory.html">
<div>
	<c:if test="${!empty selItems}">
		<table align="left" border="1">
			<tr>
				<th>Sr. No</th>
				<th>Item Buy_event</th>
				<th>Item Name</th>
				<th>Item Price</th>
				<th>Item ItemId</th>
				<th>Item Coustmer_id</th>
			</tr>
			<c:forEach items="${selItems}" var="selecteItem">
				<tr>
				    <td><c:out value="${selecteItem.getId()}"/></td>
					<td><c:out value="${selecteItem.getBuy_event()}"/></td>
 				    <td><c:out value="${selecteItem.getItemname()}"/></td>
					<td><c:out value="${selecteItem.getPrice()}"/></td>
					<td><c:out value="${selecteItem.getItemId()}"/></td>
					<td><c:out value="${selecteItem.getCoustmer_id()}"/></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	</div>
</form>
</body>
</html>
