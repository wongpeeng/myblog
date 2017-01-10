<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${requestScope.blog.title}</title>
</head>
<body>
	<center>
		<input type="button" value="mainpage">
		<c:if test="${sessionScope.user!=null}">
			<input type="button" value="person center"
				onclick="window.location.href='/myblog/jsp/PersonMainPage.jsp' ">
		</c:if>
		<input type="button" value="back" onclick="window.history.go(-1)">
	</center>
	<br>
	<div id="blog"
		style="margin: 0 auto; width: 500px; word-wrap: break-word;">
		<c:choose>
			<c:when test="${requestScope.blog.title!=null}">
				<font color="red">title:</font>
				<br>${requestScope.blog.title}<br>
				<br>
				<font color="red">content:</font>
				<br>${requestScope.blog.content}<br>
			</c:when>
			<c:otherwise>
				failed to get the blog or be deleted!
			</c:otherwise>
		</c:choose>
	</div>
	<br>
	<br>
	<div id="comments"
		style="margin: 0 auto; width: 500px; word-wrap: break-word;">
		<font color="red">comments:</font><br>
		<div id="comments_p2">
		<c:choose>
			<c:when test="${requestScope.cmtList.size()==0}">
				<font color="red">title:</font>
				<br>${requestScope.blog.title}<br>
				<br>
				<font color="red">content:</font>
				<br>${requestScope.blog.content}<br>
			</c:when>
			<c:otherwise>
				no comments!
			</c:otherwise>
		</c:choose>
		</div>
	</div>
	<br>
	<br>
	<c:if test="${requestScope.blog.title!=null}">
		<div id="new commnet"
			style="margin: 0 auto; width: 500px; word-wrap: break-word;">
			<font color="red">new comment:</font>
			<textarea id="newComment" style="height: 200px; width: 500px;"></textarea>
			<br> <input type="button" id="submit" name="submit"
				value="submit" onclick="newComment(${requestScope.blog.id})">
		</div>
	</c:if>
	<script type="text/javascript">
		function newComment(){
			var divSon=document.createElement("div");
			content=document.getElementById("newComment").value;
			divSon.innerHTML=content;
			var divFather=document.getElementById("comments_p2");
			if(divFather.children.length==0)divFather.innerHTML="";
			divFather.appendChild(divSon);
		}
	</script>
</body>
</html>