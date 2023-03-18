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

	<br />
	<a class="tombol" href="/addCustomer" >+ Add New Record</a>

	<h3>Data Customer</h3>
	<table border="1" class="table">
		<tr>
			<th>No</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th width="50%">Opsi</th>
		</tr>
		<c:forEach items="${listCustomer}" var="listCustomer" varStatus="theNumber">
			<tr align="center">
				<td>${theNumber.index+1}</td>
				<td>${listCustomer.firstName}</td>
				<td>${listCustomer.lastName}</td>
				<td width="50%">
				<%-- <form action="/deleteCustomer/${listCustomer.id}" method="post">
					<input type="hidden" name="id" value="${customer.id}">
					<input type="submit" value="Delete" onclick="return confirm('Do you want delete?');return false;">
				</form> --%>
				<form id="delete${listCustomer.id}" action="/deleteCustomer/${listCustomer.id}" method="post">
					<a class="edit" href="/editCustomer/${listCustomer.id}">Edit</a> | 
					<input type="hidden" name="id" value="${customer.id}">
					 <a href="#" onclick="validation(${listCustomer.id});">Delete</a>
				</form>
				 </td>
			</tr>
		</c:forEach>
	</table>
	&copy; Developer <!-- &copy; ndms.arifin@gmail.com -->
</body>
</html>
<script>
	function validation(id){
		var form = document.getElementById('delete'+id);
		var ask = confirm('Are you sure delete ? ');
		if(ask){
			form.submit();
			return true;
		}else{
			return false;
		}
	}
	function submitMe()
	{
		document.MyForm.submit();
	return;
	}
</script>