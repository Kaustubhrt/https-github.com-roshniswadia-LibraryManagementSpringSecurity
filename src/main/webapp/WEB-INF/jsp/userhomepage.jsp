<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<sec:authorize access="hasRole('ROLE_USER')">
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
		<div align="center">
			<table>
				<tr>
					<td><div style="color: Blue">${message}</div></td>
				</tr>
				<tr>
					<td><a
						href="viewAvailableBooks?userId=<%=request.getAttribute("userId")%>">View
							All Available Books</a></td>
				</tr>
				<tr>
					<td><a
						href="viewAllIssuedBooks?userId=<%=request.getAttribute("userId")%>">View
							All Issued Books</a></td>
				</tr>
			</table>

		</div>
		</sec:authorize>
		
</body>
</html>