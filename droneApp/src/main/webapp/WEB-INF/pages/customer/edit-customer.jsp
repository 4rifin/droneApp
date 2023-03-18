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
	<h3>Edit Customer</h3>
	<form action="/editCustomer/${customer.id}" method="post">		
		<table>
			<tr>
				<td>id</td>
				<td><input type="text" name="id" value="${customer.id}" disabled="disabled"><input type="hidden" name="id" value="${customer.id}"></td>					
			</tr>
			<tr>
				<td>firstName</td>
				<td><input type="text" name="firstName" value="${customer.firstName}"></td>					
			</tr>	
			<tr>
				<td>LastName</td>
				<td><input type="text" name="lastName" value="${customer.lastName}"></td>					
			</tr>	
			<tr>
				<td></td>
				<td><input type="submit" value="Simpan" onclick="return confirm('Do you want edit?');return false;"></td>					
			</tr>				
		</table>
	</form>
</body>
&copy; Developer <!-- &copy; ndms.arifin@gmail.com -->
</html>