<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Twitter</title>
</head>
<body>
	<h2>Twitter welcome page</h2>
	<div>
		<span>Have you got an account already? <a href="<c:url value="/user/login"/>"><strong>Log in</strong></a></span>
	</div>
	<div>
		<span>New to this site? <a href="<c:url value="/user/register"/>"><strong>Register</strong></a></span>
	</div>
</body>
</html>