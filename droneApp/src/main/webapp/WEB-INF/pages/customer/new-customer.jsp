<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/includes/taglibs.jsp"%>

<html>
<head>
<title>Create CRUD Spring JPA with PostgreSQL - Show up Data From
	Database</title>
</head>
<body>
	<div class="judul">
		<h1>Create CRUD Spring JPA with PostgreSQL</h1>
		<h2>List Data From Database</h2>
	</div>

	<div>
		<strong style="color: blue;">${message}</strong>
	</div>
	
	<br />
	<a href="/">Show Data</a>

	<br/>
	<h3>Add New Customer</h3>
	<form id="addNew"action="/addCustomer/new" method="post">		
		<table>
			<tr>
				<td>First Name</td>
				<td><input type="text" name="firstName"></td>					
			</tr>	
			<tr>
				<td>Last Name</td>
				<td><input type="text" name="lastName"></td>					
			</tr>	
			<tr>
				<td></td>
				<td><input type="submit" value="Save" onclick="return confirm('Do you want save?');return false;"></td>					
			</tr>				
		</table>
		&copy; Developer <!-- &copy; ndms.arifin@gmail.com -->
	</form>
</body>

</html>
<script type="text/javascript" >

</script>        