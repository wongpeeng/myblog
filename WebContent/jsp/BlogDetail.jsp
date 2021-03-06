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
		<input type="button" value="mainpage" onclick="window.location.href='jsp/ShowContent.jsp'">
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
					<div id="${cmt.id}" style="display:block">
						${cmt.critic}&nbsp;&nbsp;&nbsp;&nbsp;
						pubDate:${cmt.pubDate}<br>
						<c:if test="${cmt.ctype ne 'c' }">
							to:${cmt.toPerson}<br>
						</c:if>
						${cmt.content}<br>
						<input type="hidden" id="critic${cmt.id}"  value="${cmt.critic}">
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
		var blogId="${requestScope.blog.id}";
		var user="${sessionScope.user.name}";
		var toPerson_default="${requestScope.blog.author}";
		var toPerson_real=toPerson_default;
		var t="c";
		var status="false";
		var newCmtId="-1";
		var pubDate="";
		function newComment(){
			data=document.getElementById("newComment").value;
			if(!check(data))
					return false;
			content="cType=newCmt&content="+data+"&blogId="+blogId+"&toPerson="+toPerson_real+"&t="+t;
			req(content);
			if(status=="false"){alert("fail to comment!") ;return;}
			window.location.reload(true);
			t="c";
		}
		function pros(cid){
			var content="cType=prosCmt&cid="+cid;
			req(content);
			if(status=="true"){
				var bId="pros"+cid;
				var button=document.getElementById(bId);
				var s=button.value;
				s=s.replace("pros:","");
				s=parseInt(s)+1;
				s="pros:"+s;
				button.value=s;
			}else{alert("fail to pros comment!");}
			status="false";
		}
		function cons(cid){
			var content="cType=consCmt&cid="+cid;
			req(content);
			if(status=="true"){
				var bId="cons"+cid;
				var button=document.getElementById(bId);
				var s=button.value;
				s=s.replace("cons:","");
				s=parseInt(s)+1;
				s="cons:"+s;
				button.value=s;
			}else{alert("fail to pros comment!");}
			status="false";
		}
		function re(cid){
			t="r";
			p="critic"+cid;
			toPerson_real=document.getElementById(p).value;
			alert("now type characters below!");
		}
		function del(cid){
			data="cType=delCmt&cid="+cid;
			req(data);
			if(status=="true"){
				div=document.getElementById(cid);
				div.style.display="none";
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
					if(rs.status=="false");
					else
						status="true";
				}
			}
			xmlHttp.open("post","/myblog/comment.do",false);
			xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xmlHttp.send(data);
		}
		function check(data){
			var reg=/[^\u0020-\u007e\u4e00-\u9fa5\ufe30-\uffa0\u3000-\u303f\u2018\u2019\u2014\u2026\u2013\u201c\u201d\r\n]/g;
			if(reg.test(data)){
				window.alert("illegal characters!");
				return false;
			}
			if(data.length>500||data.length<10){
				alert("10<=content length<=500!");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>