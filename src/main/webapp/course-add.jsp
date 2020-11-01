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
				<h3 class="mt-3">${not empty course ? "Edit Course" : "Add New Course" }</h3>
			</div>
		</div>
	</div>
	<div class="container ">
		<div class="row pt-3">
		<c:url var="save" value="/course-add"></c:url>
			<form action="${save }" class="form col-6" method="post">
			<input type="hidden" name="courseid" value="${course.id  }"/>
				<div class="form-group">
					<label for="">Name</label> 
					<input type="text" name="coursename" class="form-control" required="required" value="${course.name }">
				</div>
				<div class="form-group">
						<label for="">Level</label>
						<select name="level" id="" class="form-control">
							<option value="Basic" ${course.level  == "Basic" ? 'selected' : '' }>Basic</option>
							<option value="Intermediate" ${course.level  == "Intermediate" ? 'selected' : '' }>Intermediate</option>
							<option value="Advanced" ${course.level  == "Advanced" ? 'selected' : '' }>Advanced</option>
						</select>
					</div>
				<div class="form-group">
					<label for="">Fees</label> 
					<input type="number" name="fees" class="form-control" required="required" value="${course.fees }">
				</div>
				<div class="form-group">
					<label for="">Duration <span class="text-danger">(months)</span></label> 
					<input type="number" name="duration" class="form-control" required="required" value="${course.duration }">
				</div>
				<button type="submit" class="btn btn-primary">${not empty course ? 'Update' : 'Save' }</button>
				<button type="reset" class="btn btn-danger">Clear</button>

			</form>
		</div>
	</div>

</body>
</html>