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
	
	<div class="container py-5">
		<h3 class="mb-4">${not empty registration ? 'Edit Student Registration' : 'Add New Student Registration' }</h3>
		
		<c:url value="/registration-add" var="save"></c:url>
		<form action="${save }" method="post">
			
			<input type="hidden" name="regid" value="${registration.id }"/>
			
			<div class="form-group">
				<label for="stdid">Student</label>
				<select name="stdid" id="stdid" class="form-control">
					
					<c:forEach items="${students }" var="s">
						<option value="${s.id }" ${registration.student.id == s.id ? 'selected' : '' }>${s.name }</option>
					</c:forEach>
				
				</select>
			</div>
			
			<div class="form-group">
				<label for="classid">Class</label>
				<select name="classid" id="classid" class="form-control">
					
					<c:forEach items="${classes }" var="c">
						<option value="${c.id }" ${registration.classes.id == c.id ? 'selected' : '' }>${c.name }</option>
					</c:forEach>
				
				</select>
			</div>
			
			
			<div class="form-group">
				<label for="paid_amount">Paid Amount</label>
				<input type="number" name="paid_amount" value="${registration.paidAmount }" id="name" class="form-control" required placeholder="Enter Paid Amount"/>
			</div>
			
			<div class="form-group">
				<button class="btn btn-primary" type="submit">${not empty registration ? 'Update' : 'Save' }</button>
				<button class="btn btn-danger" type="reset">Clear</button>
			</div>
			
		</form>
	
	</div>
	
</body>
</html>