<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Blog Main Page</title>
</head>
<body onload="updateBlogCatalog();updateNoticeCatalog();">
	<center>
	<%@ include file="PersonInfo.jsp" %><br>
	<br>
	<%@	include file="FuncModule.jsp" %>
	<br>
	<%@ include file="NoticeCatalog.jsp" %>
	<%@ include file="BlogCatalog.jsp" %>
	<%@ include file="ExtraInfo.jsp"%>
	</center>
	</body>
</html>