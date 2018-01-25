<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Twitter</title>
</head>
<body>
	<div>
		<div>
			<span>You are logged in as: ${user.username}</span> <a
				href="<c:url value="/user/logout" />">Log out</a>
		</div>
		<div>
			<strong><c:out value="${tweet.user.username}"></c:out></strong>
			<small><c:out value="${tweet.created}"></c:out></small><br>
			<h4><c:out value="${tweet.text}" /></h4>
			
		</div>
		<hr>
		<div>
			<c:forEach items="${tweet.comments}" var="comment">
				<strong><c:out value="${comment.user.username }" /></strong>
				<small><c:out value="${comment.created }" /></small>
				<br>
				<h4><c:out value="${comment.text }" /></h4>
				<br>
			</c:forEach>
		</div>
		<div>
			<f:form action="" method="post" modelAttribute="comment">
				<div>
					Add comment:
					<f:input type="text" path="text" />
					<input type="submit" value="Send" />
				</div>
			</f:form>
		</div>


		<div>
			<a href="<c:url value="/tweet/list" />">Back to Tweets' list </a>
		</div>
	</div>
</body>
</html>