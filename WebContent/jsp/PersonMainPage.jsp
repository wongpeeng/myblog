<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to ${sessionScope.user.name}'s main page!</title>
</head>
<body>
<center><h1>Welcome back home,${sessionScope.user.name}!</h1></center>
<div id="setting" style="margin:0 auto;width:500px;height:auto;display:block;align=left;border:1px solid #000">
<pre>
here is setting center!<br>&nbsp; hello!
</pre>
</div>
<br>
<div id="blog" style="margin:0 auto;width:500px;height:auto;display:none;align=left;border:1px solid #000">
<pre>
here is blog center!
</pre>
</div>
<br>
<div id="comments" style="margin:0 auto;width:500px;height:auto;display:none;align=left;border:1px solid #000">
<pre>
here is comments center!
</pre>
</div>
</body>
</html>