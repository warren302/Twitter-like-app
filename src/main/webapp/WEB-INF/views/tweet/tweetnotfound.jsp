<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Twitter</title>
</head>
<body>
<div>
	<div>
		<span>You are logged in as: ${user.username}</span>
		<a href="<c:url value="/user/logout" />" >Log out</a>
	</div>
	<h3>Tweet not found</h3>
	<div>
		<a href="<c:url value="/tweet/list" />" >Back to Tweets' list </a>
	</div>
</div>
</body>
</html>