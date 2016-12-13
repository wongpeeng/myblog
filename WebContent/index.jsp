<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to wongpeeng's blog system</title>

</head>
<body>
    <center>
    <form name="loginform" method="post" action="login.do">
        <h1>USER LOGIN</h1>
        USER ID: <input type="text" id="userName" name="userName"><br>
        USER SN:<input type="password" id="userPwd" name="userPwd"><br>
		<input type="hidden" id="click" name="click" value="guest"><br>
        <input type="button" id="guest" name="guest" value="guest" onclick="guestj()">
        <input type="button" id="login" name="login" value="login" onclick="loginj()">
        <input type="button" id="register" name="register" value="register" onclick="registerj()">
    </form>
    </center>
    
<script type="text/javascript">
	function guestj(){
		document.getElementById("click").value="guest";
		document.loginform.submit();
	}
	function loginj(){
		if(!rule()){
			return;
		}
		document.getElementById("click").value="login";
		loginform.submit();
	}
	function registerj(){
		if(!rule()){
			return;
		}
		document.getElementById("click").value="register";
		document.loginform.submit();

	}
	//to judge that id and name only include 0~9 a~z A~Z '_'
	//and 6<=length<=20;
	function rule(){
		var reg=/[^A-Za-z0-9_]/g;
		var id=document.getElementById("userName").value;
		var pwd=document.getElementById("userPwd").value;
		if(reg.test(id)){
			window.alert("USER ID:don't use characters except _,0~9,a~z,A~Z!");
			return false;
		}
		if(id.length<6||id.length>20){
			window.alert("USER ID:length must >=6 and <=20!");
			return false;
		}
		if(reg.test(pwd)){
			window.alert("USER SN:don't use characters except _,0~9,a~z,A~Z!");
			return false;
		}
		if(pwd.length<6||pwd.length>20){
			window.alert("USER SN:length must >=6 and <=20!");
			return false;
		}
		
			return true;
		}
	var logErr="<%=request.getParameter("logErr")%>";
	var regErr="<%=request.getParameter("regErr")%>";
	var regOK="<%=request.getParameter("regOK")%>";
	if(logErr==="yes") alert("login failed!\n try again!");
	if(regErr=="yes") alert("register failed!\n try again or change another ID!");
	if(regOK=="yes") alert("register successfully!\n login please!");
</script>    
</body>
</html>


