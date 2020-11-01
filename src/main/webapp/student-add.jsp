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
			<div class="col-5">
				<h3 class="mt-3">${not empty student ? "Edit Student" : "Add New Student" }</h3>
			</div>
		</div>
	</div>
	<div class="container ">
		<div class="row pt-3">
		<c:url var="save" value="/student-add"></c:url>
			<form action="${save }" class="form col-6" method="post">
			<input type="hidden" name="studentid" value="${student.id  }"/>
				<div class="form-group">
					<label for="">Name</label> 
					<input type="text" name="studentname" class="form-control" required="required" value="${student.name }">
				</div>
				<div class="form-group">
					<label for="">Email</label> 
					<input type="email" name="email" class="form-control" required="required" value="${student.email }">
				</div>
				<div class="form-group">
					<label for="">Phone</label> 
					<input type="text" name="phone" class="form-control" required="required" value="${student.phno }">
				</div>
				
				<button type="submit" class="btn btn-primary">${not empty student ? 'Update' : 'Save' }</button>
				<button type="reset" class="btn btn-danger">Clear</button>

			</form>
		</div>
	</div>

</body>
</html>