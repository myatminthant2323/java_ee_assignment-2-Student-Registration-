<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
<jsp:include page="resources/common.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="resources/menu.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-12" style="margin-top: 20px;">
				<h3 class="d-inline">All Student</h3>
				<c:url value="/student-add.jsp" var="add"></c:url>
				<a href="${add }" class="btn btn-primary mt-1 float-right">Add
					New</a>
			</div>
		</div>
	</div>
	<div class="container ">
		<div class="row pt-3">
			<table class="table col-12 ">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Phone</th>
						<th colspan="2" class="text-center">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${students }" var="s">
						<tr>
							<td>${s.id }</td>
							<td>${s.name }</td>
							<td>${s.email }</td>
							<td>${s.phno }</td>
							<td><c:url value="/student-edit" var="edit">
									<c:param name="studentid">${s.id }</c:param>
								</c:url> <a href="${edit }" class="btn btn-outline-success">Edit</a></td>
							<td><c:url value="/student-remove" var="remove">
									<c:param name="studentid">${s.id }</c:param>
								</c:url> <a href="${remove }" class="btn btn-outline-danger">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>