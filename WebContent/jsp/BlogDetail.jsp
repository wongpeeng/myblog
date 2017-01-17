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
			<c:when test="${requestScope.blog!=null}">
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
				<c:when test="${requestScope.cList.size()!=0}">
					<c:forEach items="${requestScope.cList}" var="cmt">
					<div id="${cmt.id}">
						<c:if test="${cmt.ctype ne 'c' }">
							to:${cmt.toPerson}<br>
						</c:if>
						pubDate:${cmt.pubDate}<br>
						${cmt.content}<br>
						<input type="button" id="pros${cmt.id}" value="pros:${cmt.pros}" onclick="pros(${cmt.id})">
						<input type="button" id="cons${cmt.id}" value="cons:${cmt.cons}" onclick="cons(${cmt.id})">
						<input type="button" id="reply${cmt.id}" value="reply" onclick="re(${cmt.id})">
						<c:if test="${cmt.critic eq sessionScope.user.name }">
								<input type="button" value="delete" onclick="del(${cmt.id})">
						</c:if>
					</div>
					<br>
					</c:forEach>
				</c:when>
				<c:otherwise>
				no comments!
			</c:otherwise>
			</c:choose>
		</div>
	</div>
	<br>
	<br>
	<c:if test="${requestScope.blog!=null}">
		<div id="new commnet"
			style="margin: 0 auto; width: 500px; word-wrap: break-word;">
			<font color="red">new comment:</font>
			<textarea id="newComment" style="height: 200px; width: 500px;"></textarea>
			<br> <input type="button" id="submit" name="submit"
				value="submit" onclick="newComment(${requestScope.blog.id})">
		</div>
	</c:if>
	<script type="text/javascript">
		var blogId=requestScope.blog.id;
		var status=false;
		function newComment(){
			var divSon=document.createElement("div");
			content=document.getElementById("newComment").value;
			divSon.innerHTML=content;
			if(content.length==0){
				alert("no content");
				return;
			}
			var divFather=document.getElementById("comments_p2");
			if(divFather.children.length!=0){
				divFather.insertBefore(divSon,divFather.firstChild);
			}else{
				divFather.innerHTML="";
				divFather.appendChild(divSon);
			}
			
		}
		function pros(cid){
			var content="cType=prosCmt&cid="+cid;
			req(content);
			if(status){
				var bId="pros"+cid;
				var button=document.getElementById(bId);
				var s=button.value;
				s=s.replace("pros:","");
				s=parseInt(s)+1;
				s="pros:"+s;
				button.value=s;
			}else{alert("fail to pros comment!");}
			status=false;
		}
		function cons(id){
			alert("cons");
		}
		function re(id){
			alert("reply");
		}
		function del(id){
			alert("del");
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
					if(rs.status=="true")status=true;
				}
			}
			xmlHttp.open("post","/myblog/comment.do",false);
			xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xmlHttp.send(data);
		}
	</script>
</body>
</html>