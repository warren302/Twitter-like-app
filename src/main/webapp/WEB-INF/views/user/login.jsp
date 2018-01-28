<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Logging in page Twitter</title>
</head>
<body>
  <f:form action="login" method="post" modelAttribute="user">
  	
    <div>
      Login: <f:input type="text" path="username"/><f:errors path="username" cssClass="error" element="div"/>
    </div>
    <div>
      Password: <f:input type="password" path="password"/><f:errors path="password" cssClass="error" element="div"/>
    </div>
 	 <f:errors path="*" element="div"/>
    <div>
      <input type="submit" value="Log in"/>
    </div>
    <div>
      Don't you have an account yet? <a href="register"><strong>Register</strong></a>
    </div>  
  </f:form>
</body>
</html>