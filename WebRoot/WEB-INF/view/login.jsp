<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录页面demo</title>
</head>

<body>
<form action="/user/dologin.html" id="view_login_login" method="post">
<h2>登录
</h2>
<table width="309" border="0">
  <tr>
    <td width="124">username：</td>
    <td width="175"><label>
      <input type="text" name= "username" anne-node="username" />
    </label></td>
  </tr>
  <tr>
    <td>passwd：</td>
    <td><input type="password" name="passwd" anne-node="passwd" /></td>
  </tr>
</table>
<p>
     <input type="button" name="button" id="button" value="提交" anne-node="login" />
</p>
</form>
<script src="/conf/login.js"></script>
</body>
</html>
