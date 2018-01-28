<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<span>You are logged in as: ${user.username}</span> <a
			href="<c:url value="/user/logout" />">Log out</a>
	</div>
	<hr>
	<h4>Messages</h4>
	<c:forEach items="${messages}" var="message">
	From: ${message.sender.username} To: ${message.addressee.username} 
	<small>${message.created}</small>
		<br>
		<c:choose>
			<c:when test="${message.sender.id == user.id }">
				<a href="<c:url value="/message/details/${message.id}"/>">
					${message.heading}</a>
			</c:when>
			<c:when test="${message.read}">
				<a href="<c:url value="/message/details/${message.id}"/>">
					${message.heading}</a>
			</c:when>
			<c:otherwise>
				<b><a href="<c:url value="/message/details/${message.id}"/>">
						${message.heading}</a></b>
			</c:otherwise>
		</c:choose>
		<br>
	</c:forEach>
	<f:form action="" method="post" modelAttribute="message">
		<div>
			<label>SendTo:</label>
			<f:select items="${receivers}" path="addressee" itemLabel="username"
				itemValue="id" />
		</div>
		<f:textarea rows="10" columns="100" path="text" />
		<div>
			<input type="submit" value="Send" />
		</div>
	</f:form>

	<div>
		<a href="<c:url value="/tweet/list" />">Back to tweets' list </a>
	</div>

</body>
</html>