<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<title>All Selected  Items</title>
</head>
<body style="background-color: orange;">
<h3>Welcome To User:: ${firstname}</h3>
<h1>Selected Items List</h1>
<h4 style="color:red">Order Details</h4>
<div>
	<c:if test="${!empty selItems}">
		<table align="left" border="1">
			<tr>
				<th>Sr. No</th>
				<th>Item Name</th>
				<th>Item Price</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${selItems}" var="selecteItem">
				<tr>
				<td><c:out value="${selecteItem.getId()}" /></td>
 				<td><c:out value="${selecteItem.getItemname()}" /></td>
					<td><c:out value="${selecteItem.getPrice()}" /></td>
					<td align="center">
					<a href="seleteddelete.html?id=${item.getId()}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
		<div>Final Total Amount:: ${totalprice}</div>
	</c:if>
<a href="order.html?cid=${cid}">Confirm Order </a></td>
	</div>
</body>
</html>
