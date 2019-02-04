<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css">	
<title>All Items</title>
</head>
   <SCRIPT language="javascript">
	   $(function() {
		$("#selectall").click(function() {
			$('.case').attr('checked', this.checked);
		});
		$(".case").click(function() {
			if ($(".case").length == $(".case:checked").length) {
				$("#selectall").attr("checked", "checked");
			} else {
				$("#selectall").removeAttr("checked");
			}
		});
	});
</SCRIPT>
<body style="background-color: orange;">
<script type="text/javascript" charset="utf8"
		src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" charset="utf8"
		src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
	<script>
		$(function() {
			$("#example").dataTable();
		})
	</script>

	<h1>List Items</h1>
	<form action="allitemid.html">
		<c:if test="${!empty itemDtos}">
			<table id="example" align="center" border="3">
				<thead>
				<tr>
					<th>Sr Id</th>
					<th>Item Name</th>
					<th>Item price</th>
					<th>Item AvilabelStock</th>
					<th>select all<input type="checkbox" id="selectall"
						name="selectall" /></th>
					<c:if test="${showMenu}">
						<th>Action</th>
					</c:if>
				</tr>
			</thead>
				<tbody>
				<c:forEach items="${itemDtos}" var="item">
					<tr>
						<td><c:out value="${item.getId()}" /></td>
						<td><c:out value="${item.getItemname()}" /></td>
						<td><c:out value="${item.getPrice()}" /></td>
						<td><c:out value="${item.getAvilabelstock()}" /></td>
						<td align="center"><input type="checkbox" class="case"
							name="itemid" value="${item.id}" /></td>
						<c:if test="${showMenu}">
							<td align="center"><a href="addredirect.html">Add</a> <a
								href="editredirect.html?id=${item.getId()}&itemname=${item.getItemname()}&price=${item.getPrice()}&avilabelstock=${item.getAvilabelstock()}">Edit</a>
								<a href="deletedirect.html?id=${item.getId()}">Delete</a>
							<a href="viewcust.html">View Of Customer</a>
							<a href="viewhistory.html">View Of OrderDetails</a>
						    </td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
			</table>
		</c:if>
		<input type="submit" VALUE="SUBMIT HERE" />
	</form>
</body>
</html>
