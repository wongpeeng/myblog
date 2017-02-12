<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administration Center</title>
</head>
<body>
<br><br>
<div id="function" style="margin:0 auto;width:500px;height:auto;display:block;align=center;">
	<input type="button" value="user admin" onclick="userAdmin()">
	<input type="button" value="blog admin" onclick="blogAdmin()">
	<input type="button" value="comment admin" onclick="cmtAdmin()">
	<input type="button" value="person center" onclick="window.location.href='/myblog/jsp/PersonMainPage.jsp'">
</div>
<div id="userAdmin" style="margin:0 auto;width:500px;height:auto;display:block;align=left;">
	<br><br>
	<font color="red">change password</font>：<br><br>
	user name:<br><input type="text" id="pwdUser" name=“pwdUser”><br>
	new password:<br><input type="text" id ="newPwd" name="newPwd"><br>
	<input type="button" value="submit" onclick="newPwd()">
	<br><br>
<font color="red">close account</font>：<br><br>
	user name:<br><input type="text" id ="delUser"><br>
	<input type="button" value="submit" onclick="delUser()">
</div>
<div id="blogAdmin" style="margin:0 auto;width:500px;height:auto;display:none;align=left;">
	<br><br>
	<font color="red">delete blog</font>：<br><br>
	blog id:<br><input type="text" id ="delBlog"><br>
	<input type="button" value="submit" onclick="delBlog()">
</div>
<div id="cmtAdmin" style="margin:0 auto;width:500px;height:auto;display:none;align=left;">
	<br><br>
	<font color="red">delete comment</font>：<br><br>
	comment id:<br><input type="text" id ="delCmt"><br>
	<input type="button" value="submit" onclick="delCmt()">
</div>
<script>
function userAdmin(){
	document.getElementById("userAdmin").style.display="block";
	document.getElementById("blogAdmin").style.display="none";
	document.getElementById("cmtAdmin").style.display="none";
}
function blogAdmin(){
	document.getElementById("userAdmin").style.display="none";
	document.getElementById("blogAdmin").style.display="block";
	document.getElementById("cmtAdmin").style.display="none";
}
function cmtAdmin(){
	document.getElementById("userAdmin").style.display="none";
	document.getElementById("blogAdmin").style.display="none";
	document.getElementById("cmtAdmin").style.display="block";
}
var status="false";
function newPwd(){
	var div1=document.getElementById("pwdUser");
	var div2=document.getElementById("newPwd");
	var user=div1.value;
	var newPwd=div2.value;
	data="mType=pwd&user="+user+"&newPwd="+newPwd;alert(data);
	if(!checkUser(user)) return;
	if(!checkPwd(newPwd)) return;
	alert("ok");
	req(data);
	if(status=="true"){
		div1.value="";
		div2.value="";
		alert("successful!");
	}else alert("fail to change password");
	status="false";	
}
function delUser(){
	var div=document.getElementById("delUser");
	var user=div.value;
	if(!checkUser(user)) return;
	data="mType=close&user="+user;
	req(data);
	if(status=="true"){
		div.value="";
		alert("successful!");
	}else alert("fail to delete user!");
	status="false";
}
function delBlog(){
	var div=document.getElementById("delBlog");
	var blog=div.value;
	if(!checkId(blog)) return;
	data="mType=blog&blog="+blog;
	req(data);
	if(status=="true"){
		div.value="";
		alert("successful!");
	}else alert("fail to delete blog!");
	status="false";
}
function delCmt(){
	var div=document.getElementById("delCmt");
	var cmt=div.value;
	if(!checkId(cmt)) return;
	data="mType=cmt&cmt="+cmt;
	req(data);
	if(status=="true"){
		div.value="";
		alert("successful!");
	}else alert("fail to delete comment!");
	status="false";
}
function req(data){
	var xmlHttp;
	if(window.XMLHttpRequest){
		xmlHttp=new XMLHttpRequest();
	}
	else{
		xmlHttpNotice=new ActiveXObject("Microsof.XMLHTTP");
	}
	xmlHttp.onreadystatechange=function(){
		if(xmlHttp.readyState==4&&xmlHttp.status==200){
			var rs=eval("("+xmlHttp.responseText+")");
			if(rs.status == "true")status="true";
		}
	}
	xmlHttp.open("post","/myblog/manage.do",false);
	xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlHttp.send(data);
}
function checkUser(user){
	var reg=/[^A-Za-z0-9_]/g;
	if(reg.test(user)){
		alert("illegal user name!")
		return false;
	}
	if(user.length>20||user.length<6){
		alert("illegal user name!");
		return false;
	}
	return true;
}
function checkPwd(pwd){
	var reg=/[^A-Za-z0-9_]/g;
	if(reg.test(pwd)){
		window.alert("illegal passwprd, use characters: _,0~9,a~z,A~Z!");
		return false;
	}
	if(pwd.length<6||pwd.length>20){
		window.alert("illegal password,length must >=6 and <=20!");
		return false;
	}
	return true;
}
function checkId(id){
	var reg=/[^0-9]/g;
	if(reg.test(id)){
		window.alert("illegal id, use characters:0~9!");
		return false;
	}
	return true;
}
</script>
</body>
</html>