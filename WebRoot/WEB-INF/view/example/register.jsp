<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册页面demo</title>
</head>

<body>
<form action="/user/register.html">
<h2>注册
  <label>
  <select name="type">
    	<option value="0">机构用户</option>
        <option value="1">学生用户</option>
    </select>
  </label>
</h2>
<table width="309" border="0">
  <tr>
    <td width="124">username：</td>
    <td width="175"><label>
      <input type="text" name="username" />
    </label></td>
  </tr>
  <tr>
    <td>passwd：</td>
    <td><input type="password" name="passwd" /></td>
  </tr>
  <tr>
    <td>re-passwd：</td>
    <td><input type="password" /></td>
  </tr>
</table>
<p>
     <input type="submit" name="button" id="button" value="提交">
</p>
</form>
</body>
</html>
