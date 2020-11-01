<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-lg navbar-mainbg">
        <a class="navbar-brand navbar-logo" href="#">Student Registration</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <div class="hori-selector"><div class="left"></div><div class="right"></div></div>
                <li class="nav-item">
                    <a class="nav-link" href="javascript:void(0);">Home</a>
                </li>
                <% if (getServletContext().getAttribute("page") == "course") { %>
                <li class="nav-item active">
                <% } else { %>
                <li class="nav-item">
                <% } %>
                	<c:url value="/courses" var="courses"></c:url>
                    <a class="nav-link" href="${courses }">Course</a>
                </li>
                <% if (getServletContext().getAttribute("page") == "class") { %>
                <li class="nav-item active">
                <% } else { %>
                <li class="nav-item">
                <% } %>
                <c:url value="/classes" var="classes"></c:url>
                    <a class="nav-link" href="${classes }">Class</a>
                </li>
                <% if (getServletContext().getAttribute("page") == "student") { %>
                <li class="nav-item active">
                <% } else { %>
                <li class="nav-item">
                <% } %>
                <c:url value="/students" var="students"></c:url>
                    <a class="nav-link" href="${students }">Student</a>
                </li>
               <% if (getServletContext().getAttribute("page") == "registration") { %>
                <li class="nav-item active">
                <% } else { %>
                <li class="nav-item">
                <% } %>
                 <c:url value="/registrations" var="registrations"></c:url>
                    <a class="nav-link" href="${registrations }">Registration</a>
                </li>
            </ul>
        </div>
    </nav>