<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Twitter</title>
</head>
<body>
	<div>
		<span>You are logged in as: ${user.username}</span> <a
			href="<c:url value="/user/logout" />">Log out</a>
	</div>
	<div>
		<table>
			<tr>
				<th>No</th>
				<th>Text</th>
				<th>Author</th>
				<th>created</th>
			</tr>
			<c:forEach items="${tweets}" var="tweet" varStatus="LoopStatus">

				<tr>
					<td>${LoopStatus.count}</td>
					<td><a href="<c:url value="/tweet/details/${tweet.id}" />">${tweet.text}</a></td>
					<td><a href="<c:url value="/user/details/${tweet.user.id}" />">${tweet.user.username}</a></td>
					<td><small>${tweet.created}</small></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div>
		<f:form action="list" method="post" modelAttribute="tweet">
			<div>
				Text:
				<f:input type="text" path="text" />
				<input type="submit" value="tweet it" />
			</div>
		</f:form>
	</div>

</body>
</html>