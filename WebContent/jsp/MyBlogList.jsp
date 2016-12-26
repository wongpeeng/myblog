<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
	<c:when test="${requestScope.bList.size()==0}">
		no blog to display!
	</c:when>
	<c:otherwise>
		<c:forEach items="${requestScope.bList}" var="blog">
			<div id="${blog.id}" style="display:block;">
			=======================================================
			<br>
				title:${blog.title}
				<br>
				revDate:${blog.revDate}
				comments:${blog.cmt}
				<input type="button" value="edit" onclick="edit(blog.id)">
				<input type="button" value="delete" onclick="delete(blog.id)">
				<input type="button" value="read" onclick="read(blog.id)">
			</div>
			<br>
		</c:forEach>
	</c:otherwise>
</c:choose>