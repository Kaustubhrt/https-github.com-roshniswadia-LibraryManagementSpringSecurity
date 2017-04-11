<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<c:url value="j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
	<a href="javascript:formSubmit()"> Logout</a>
	<div align="center">

		<header> <big>Library Management System</big> </header>
		<nav> <a href="adminhomepage">Admin Home Page</a>

		<table cellpadding="10" border="1">
			<caption>
				<h1>List of All Books</h1>
			</caption>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Author Name</th>
				<th>Issue Status</th>
				<th>Price</th>
			</tr>
			<c:forEach var="bookList" items="${bookList}">
				<tr>
					<td><c:out value="${bookList.bookId}" /></td>
					<td><c:out value="${bookList.bookName}" /></td>
					<td><c:out value="${bookList.authorName}" /></td>
					<td><c:out value="${bookList.issueStatus}" /></td>
					<td><c:out value="${bookList.amount}" /></td>

					<td><a href="edit?bookId=<c:out value='${bookList.bookId}' />">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="delete?bookId=<c:out value='${bookList.bookId}' />">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div style="color: red">${error}</div>
		
		</sec:authorize>
</body>
</html>