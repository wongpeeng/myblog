<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to ${sessionScope.user.name}'s main page!</title>
</head>
<body>

<center><h2>Welcome back home,${sessionScope.user.name}!</h2></center>
<br>
<center>
	<input type="button" id="myset" 	name="set" 			value="personal settings" 	onclick="myset()">
	<input type="button" id="myblog" 	name="myblog" 		value="blogs" 				onclick="myblog()">
	<input type="button" id="mycomment" name="mycomment" 	value="comments" 			onclick="mycomment()">
</center>
<br>

<div id="setting" style="margin:0 auto;width:500px;height:auto;display:block;align=left;border:1px solid #000">
<pre>
here is setting center!
</pre>
</div>


<div id="blog" style="margin:0 auto;width:500px;height:auto;display:none;align=left;border:1px solid #000;word-wrap:break-word;">
<%@ include file="BlogTab.jsp" %>
</div>


<div id="comment" style="margin:0 auto;height:auto;display:none;align=left;border:1px solid #000">
<pre>
here is comments center!
</pre>
</div>

<script type="text/javascript">
function myset(){
	document.getElementById("setting").style.display="block";
	document.getElementById("blog").style.display="none";
	document.getElementById("comment").style.display="none";
}
function myblog(){
	document.getElementById("setting").style.display="none";
	document.getElementById("blog").style.display="block";
	document.getElementById("comment").style.display="none";
}
function mycomment(){
	document.getElementById("setting").style.display="none";
	document.getElementById("blog").style.display="none";
	document.getElementById("comment").style.display="block";
}

</script>
</body>
</html>