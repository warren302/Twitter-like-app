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
			href="<c:url value="/user/mailbox" />">MessageBox</a> <a
			href="<c:url value="/user/logout" />">Log out</a>
	</div>
	<div class="container">
		<h3>User Details</h3>
		<br />

		<div class="row">
			<label class="col-sm-2">ID</label>
			<div class="col-sm-10">${showUser.id}</div>
		</div>
		<div class="row">
			<label class="col-sm-2">Username</label>
			<div class="col-sm-10">${showUser.username}</div>
		</div>
		<div class="row">
			<label class="col-sm-2">E-mail</label>
			<div class="col-sm-10">${showUser.email}</div>
		</div>
		<div class="row">
			<label class="col-sm-2">No of tweets</label>
			<div class="col-sm-10">${twCounter}</div>
		</div>
		<div class="row">
			<label class="col-sm-2">No of comments</label>
			<div class="col-sm-10">${comCounter}</div>
		</div>
	</div>
	<br>
	<c:choose>
		<c:when test="${user.id ne showUser.id}">
			<c:url value="/message/newmessage/${showUser.id }" var="linkMessage" />
			<f:form action="${linkMessage}" method="get" modelAttribute="message">
				<input type="submit" value="send message to ${showUser.username }" />
			</f:form>
		</c:when>
	</c:choose>


	<%-- alternatively
		<f:form action="${pageContext.request.contextPath}/message/newmessage/${showUser.id}"
			method="get">
			<input type="submit" value="send message to ${showUser.username }" />
		</f:form>
 --%>

	<div>
		<table>
			<tr>
				<th>No</th>
				<th>Text</th>
				<th>Author</th>
				<th>created</th>
			</tr>
			<c:forEach items="${showUser.tweets}" var="tweet"
				varStatus="LoopStatus">
				<tr>
					<td>${LoopStatus.count}</td>
					<td><a href="<c:url value="/tweet/details/${tweet.id}" />">${tweet.text}</a></td>
					<td><a href="<c:url value="/user/details/${tweet.user.id}" />">${tweet.user.username}</a></td>
					<td>${tweet.created}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div>
		<a href="<c:url value="/tweet/list" />">Back to tweets' list </a>
	</div>
</body>
</html>