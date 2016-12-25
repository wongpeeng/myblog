<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
//<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:choose>
	<c:when test="${requestScope.bList.size()==0}">
		no blog to display!
	</c:when>
	<c:otherwise>
		<c:forEach items="${requestScope.bList}" var="blog">
			<div id="${blog.id}" style="display:block;">
				${blog.title}
				<input type="button" value="edit" onclick="edit(blog.id)">
				<input type="button" value="delete" onclick="delete(blog.id)">
				<input type="button" value="read" onclick="read(blog.id)">
				<br>
				${blog.revDate }
				${blog.cmt}
			</div>
			<br>
		</c:forEach>
	</c:otherwise>
</c:choose>