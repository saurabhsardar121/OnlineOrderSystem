<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-color: orange;">
<div align ="center">
<form action="coustomer.html">
First_Name<input type = "text"  name = "firstname" required>
</br>
</br>
Last_Name <input type = "text"  name = "lastname" required>
</br>
</br>
Mob_No &nbsp&nbsp&nbsp<input type = "text"  name = "mobno" required>
</br>
</br>
Address &nbsp&nbsp&nbsp&nbsp<input type  = "text" name = "address" required>
</br>
</br>
City &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type  = "text" name = "city" required>
</br>
</br>
Email_Id &nbsp&nbsp<input type  = "text" name = "email" required>
</br>
</br>
Password &nbsp&nbsp<input type  = "password" name = "Passw"  required>
</br>
<input type  = "hidden" name = "userType"  value="customer">
</br>
<input type ="submit" >
</form>
</div>
</body>
</html>