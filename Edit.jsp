 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action ="edit.html">

<input type="text" name="itemname" value="${itemdto.getItemname()}"/>
</br>
</br>
<input type="text" name="price" value="${itemdto.getPrice()}"  />
</br>
</br>
<input type="text" name="avilabelstock" value="${itemdto.getAvilabelstock()}"  />
</br>
</br>
<input type="hidden" name="id" value="${itemdto.getId()}" />
<input type="submit" VALUE="SUBMIT HERE" /> 
</form>
</body>
</html>