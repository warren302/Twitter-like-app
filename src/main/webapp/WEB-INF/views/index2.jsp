<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Twitter</title>
</head>
<body>
	<h2>Strona startowa aplikacji Twitter</h2>
	<div>
		<span>Jesteś zalogowany jako: ${user.username}</span>
	</div>
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
					<td>${tweet.text }</td>
					<td>${tweet.user.username}</td>
					<td>${tweet.created}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>