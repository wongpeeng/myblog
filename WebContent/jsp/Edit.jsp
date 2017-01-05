<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>edit ${requestScope.type}!</title>
</head>
<body>
<div id="${requestScope.type}" style="margin:0 auto;width:500px;">
	<form>
		title
		<br>
		<textarea id ="title" name="title" style="height:32px;width:500px;">${requestScope.blog.title} </textarea>
		<br>
		content
		<br>
		<textarea id="content" name="content" style="height:384px;width:500px;">${requestScope.blog.content}</textarea>
		<br>
	</form>
</div>
</body>
</html>