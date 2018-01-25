<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logging out page Twitter</title>
</head>
<body>
	<div>
		<span>You are logged in as: ${user.username}</span>
	</div>

	<f:form action="logout" method="post">
		You are logging out, confirm!
		<button type="submit" name="button" value="Confirm">Confirm</button>
		<button type="submit" name="button" value="No">No</button>

	</f:form>
</body>
</html>