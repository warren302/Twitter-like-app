<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Logging in page Twitter</title>
</head>
<body>
  <form action="login" method="post">
    <div>
      Login: <input type="text" name="username"/>
    </div>
    <div>
      Password: <input type="password" name="password"/>
    </div>
    <div>
      <input type="submit" value="Log in"/>
    </div>
    <div>
      Don't you have an account yet? <a href="register"><strong>Register</strong></a>
    </div>  
  </form>
</body>
</html>