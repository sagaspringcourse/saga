<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Example</title>
</head>
<body>

<% 

	try {
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/saga", "root", "root");

	PreparedStatement statement = connection.prepareStatement(
			"select id, first_name, last_name, age from s_player where id = ?");
	statement.setInt(1, Integer.valueOf(request.getParameter("id")));
	
	ResultSet resultSet = statement.executeQuery();
	resultSet.next();
	
	request.setAttribute("firstName", resultSet.getString("first_name"));
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	
%>	

<h1>Hello ${requestScope.firstName}</h1>

</body>
</html>