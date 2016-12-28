<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><c:choose><c:when test="${requestScope.bList.size()==0}"></c:when><c:otherwise>
		<c:forEach items="${requestScope.bList}" var="blog">
			<div id="${blog.id}" style="display:block;">
			
			<br>
			<!-- it is weird! in this way null response's length is 0! important   -->
				${blog.title}
				<br>
				revDate:${blog.revDate}
				comments:${blog.cmt}
				<br>
				<input type="button" value="edit" onclick="edit(${blog.id})">
				<input type="button" value="delete" onclick="del(${blog.id})">
				<input type="button" value="read" onclick="window.location.href='/myblog/blog.do?blogType=show&id=${blog.id}'">
			</div>
			<br>
		</c:forEach>
</c:otherwise></c:choose>