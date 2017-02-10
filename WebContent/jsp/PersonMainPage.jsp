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
</center>
<br>

<div id="setting" style="margin:0 auto;width:500px;height:auto;display:block;align=left;">
<font color="red">change password</font>：<br><br>
	old password:<input type="password" id="oldPwd" name=“oldPwd”><br>
	new password:<input type="password" id ="newPwd" name="newPwd"><br>
	<input type="button" value="submit" onclick="newPwd()">


</div>


<div id="blog" style="margin:0 auto;width:500px;height:auto;display:none;align=left;word-wrap:break-word;">
<%@ include file="BlogTab.jsp" %>
</div>



<script type="text/javascript">
function myset(){
	document.getElementById("setting").style.display="block";
	document.getElementById("blog").style.display="none";
}
function myblog(){
	document.getElementById("setting").style.display="none";
	document.getElementById("blog").style.display="block";
}

//////////////setting////////
status="false";
user="${sessionScope.user.name}"
function newPwd(){
	np=document.getElementById("newPwd").value;
	op=document.getElementById("oldPwd").value;
	if(!check(op)){alert("check old password!");return;}
	if(!check(np)){alert("check new password!");return;}
	data="click=pwd&oldPwd="+op+"&newPwd="+np+"&user="+user;
	req(data);
	if(status == "true"){
		alert("successful!");
		document.getElementById("newPwd").value="";
		document.getElementById("oldPwd").value="";
	}else{
		alert("try again!");
	}
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
function check(data){
	var reg=/[^A-Za-z0-9_]/g;
	if(reg.test(data)){
		alert("don't use characters except _,0~9,a~z,A~Z!")
		return false;
	}
	if(data.length>20||data.length<6){
		alert("length must >=6 and <=20!");
		return false;
	}
	return true;
}

</script>
</body>
</html>