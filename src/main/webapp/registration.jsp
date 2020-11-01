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
				<h3 class="d-inline">All Student Registration</h3>
				<c:url value="/registration-add" var="add"></c:url>
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
						<th>Student Name</th>
						<th>Class Name</th>
						<th>Registration Date</th>
						<th>Paid Amount</th>
						<th colspan="2" class="text-center">Action</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items ="${registrations }" var="r">
					<tr>
						<td>${r.id }</td>
						<td>${r.student.name }</td>
						<td>${r.classes.name }</td>
						<td>${r.registrationDate }</td>
						<td>${r.paidAmount }</td>
						<td>
							<c:url value="/registration-edit" var="edit">
								<c:param name="regid">${r.id }</c:param>
							</c:url>
							<a href="${edit }" class="btn btn-outline-success"><span class="text-center">Edit</span></a>
						</td>
						<td>
						<c:url value="/registration-remove" var="remove">
							<c:param name="regid">${r.id }</c:param>
						</c:url>
							<a href="${remove }" class="btn btn-outline-danger"><span class="text-center">Delete</span></a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>