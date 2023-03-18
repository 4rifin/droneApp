<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Membuat CRUD Dengan PHP Dan MySQL - Menampilkan data dari
	database</title>
</head>
<body>
	<div class="judul">
		<h1>Membuat CRUD Dengan JAVA Dan PostgreSQL</h1>
		<h2>Menampilkan data dari database</h2>
	</div>

	<br />
	<a class="tombol" href="/addCustomer" target="_blank">+ Tambah Data Baru</a>

	<h3>Data Customer</h3>
	<table border="1" class="table">
		<tr>
			<th>No</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Opsi</th>
		</tr>
		<%-- <c:forEach items="${listCustomer}" var="listCustomer" varStatus="theNumber">
			<tr>
				<td>${theNumber.index+1}</td>
				<td>${listCustomer.firstName}</td>
				<td>${listCustomer.lastName}</td>
				<td><a class="edit" href="#" target="_blank">Edit</a> | <a class="hapus" href="#" target="_blank">Hapus</a></td>
			</tr>
		</c:forEach> --%>
	</table>
</body>
</html>