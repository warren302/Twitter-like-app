<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>message details</title>
</head>
<body>
	<div>
		<span>You are logged in as: ${user.username}</span> <a
			href="<c:url value="/user/logout" />">Log out</a>
	</div>
	<hr>
	<h5>Messages</h5>
	<div>
		From: ${message.sender.username} To: ${message.addressee.username} <small>${message.created}</small>
		<br>
		<c:out value="${message.text}"></c:out>
		<br>
	</div>
	
	<div>
		<f:form action="" method="post" modelAttribute="message">
			<div>
				<input type="submit" value="back to messagebox" />
			</div>
		</f:form>
	</div>

</body>
</html>