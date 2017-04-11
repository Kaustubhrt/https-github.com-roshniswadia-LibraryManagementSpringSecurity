<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	<a
					href="javascript:formSubmit()"> Logout</a>
	<div align="center">
		<table>
			
			<tr>
				<td><a
					href="viewAllUser">View
						All User</a></td>
			</tr>
			<tr>
				<td><a
					href="viewAllBooks">View
						All Available books</a></td>
			</tr>
			<tr>
				<td><a
					href="addBook">Add
						a book</a></td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
	</div>
</sec:authorize>
</body>
</html>