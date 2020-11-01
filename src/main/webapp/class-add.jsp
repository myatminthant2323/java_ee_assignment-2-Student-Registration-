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
		<h3 class="mb-4">${not empty classes ? 'Edit Class' : 'Add New Class' }</h3>
		
		<c:url value="/class-add" var="save"></c:url>
		<form action="${save }" method="post">
			
			<input type="hidden" name="classid" value="${classes.id }"/>
			
			<div class="form-group">
				<label for="courseid">Course</label>
				<select name="courseid" id="courseid" class="form-control">
					
					<c:forEach items="${courses }" var="c">
						<option value="${c.id }" ${classes.course.id == c.id ? 'selected' : '' }>${c.name }</option>
					</c:forEach>
				
				</select>
			</div>
			
			<div class="form-group">
				<label for="name">Name</label>
				<input type="text" name="name" value="${classes.name }" id="name" class="form-control" required placeholder="Enter Class Name"/>
			</div>
			
			<div class="form-group">
				<label for="start_date">Start Date</label>
				<div class="input-group date" data-provide="datepicker" data-date-format="yyyy-mm-dd">
					<input type="text" class="form-control" name="start_date" value="${classes.start_date }">
					<div class="input-group-addon">
						<span class="glyphicon glyphicon-th"></span>
					</div>
				</div>
			</div>
			
			
			
			<div class="form-group">
				<button class="btn btn-primary" type="submit">${not empty classes ? 'Update' : 'Save' }</button>
				<button class="btn btn-danger" type="reset">Clear</button>
			</div>
			
		</form>
	
	</div>
	
</body>
</html>