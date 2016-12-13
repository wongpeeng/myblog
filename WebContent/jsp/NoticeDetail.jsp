<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${requestScope.notice.title}</title>
</head>
<body>
<center>
	<%@ include file="PersonInfo.jsp" %>
	<div id="noticeContent" style="width:500px;height:auto;border:1px solid #000">
		${requestScope.notice.content}
	</div>
	<%@ include file="ExtraInfo.jsp" %>
</center>
</body>
</html>