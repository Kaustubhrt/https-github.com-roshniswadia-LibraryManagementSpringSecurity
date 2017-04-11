<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.User"%>
<%@ page import="bean.Book"%>
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

	<header> <big>Library Management System</big> </header>
	<nav> <a
		href="adminhomepage">Admin
		Home Page</a> <nav> 

	<table cellpadding="10" border="1">
		<caption>
			<h1>List of All Users</h1>
		</caption>
		<tr>
			<th>User ID</th>
			<th>User Name</th>
			<th>Books Issued</th>
		</tr>
		<%-- <c:forEach var="userList" items="${userList}">
			<tr>
				<td><c:out value="${userList.bookId}" /></td>
				<td><c:out value="${userList.bookName}" /></td>
				<td><c:out value="${userList.authorName}" /></td>
				<td><c:out value="${userList.issueStatus}" /></td>
				<td><c:out value="${userList.amount}" /></td>

				<td><a href="edit?bookId=<c:out value='${bookList.bookId}' />">Edit</a>
					&nbsp;&nbsp;&nbsp;&nbsp; <a
					href="delete?bookId=<c:out value='${bookList.bookId}' />">Delete</a>
				</td>
				</c:forEach>
			</tr> --%>

		<%
			List<User> userList = (List<User>) request.getAttribute("userList");

			for (Iterator<User> iterator = userList.iterator(); iterator
					.hasNext();) {
				User listUser = (User) iterator.next();
		%>
		<tr>
			<td><%=listUser.getUserId()%></td>
			<td><%=listUser.getUserLoginName()%></td>

			<td>
				<table cellpadding="10" border="1">
					<th>Book Name</th>
					<th>Author Name</th>
					<th>Price</th>

					<%
						Iterator it = listUser.getBookSet().iterator();
							while (it.hasNext()) {
								Book booklist = (Book) it.next();
					%>
					<tr>
						<td><%=booklist.getBookName()%></td>
						<td><%=booklist.getAuthorName()%></td>
						<td><%=booklist.getAmount()%></td>
					</tr>


					<%
						}
					%>
					</tr>
				</table>
			</td>
			<%
				}
			%>
		
	</table>
	<div style="color: red">${error}</div>
	</sec:authorize>
</body>
</html>