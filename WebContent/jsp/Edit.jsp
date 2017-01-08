<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>edit ${requestScope.type}!</title>
</head>
<body>
	<div id="${requestScope.type}" style="margin: 0 auto; width: 500px;">
		<form>
			title <br>
			<textarea id="title" name="title" style="height: 32px; width: 500px;">${requestScope.blog.title} </textarea>
			<br> content <br>
			<textarea id="content" name="content"
				style="height: 384px; width: 500px;">${requestScope.blog.content}</textarea>
			<br> <input type="button" id="submit" name="submit" value="submit"
				onclick="update(${requestScope.blog.id})">
				<input type="button" value="back" onclick="window.history.go(-1)">
		</form>
	</div>
	<script type="text/javascript">
	//submit a new blog
	function update(id){
		var t1=document.getElementById("title");
		var t2=document.getElementById("content");
		var reg=/[^\u0020-\u007e\u4e00-\u9fa5\ufe30-\uffa0\u3000-\u303f\u2018\u2019\u2014\u2026\u2013\u201c\u201d\r\n]/g;
		if(t1.value.length>200||t1.value.length<10||t2.value.length>2000||t2.value.length<10){
			alert("10<=title character<=200!\n10<=content length<=2000!");
			return false;
		}
		if(reg.test(t1.value)){
			alert("illegal characters in title!");
			return false;
		}
		if(reg.test(t2.value)){
			alert("illegal characters in content!");
			return false;
		}
		updateBlogState=false;
		updateBlog_2(id);
		if(!updateBlogState) alert("try again!");
		else {
			alert("successfully!");
			url="/myblog/blog.do?blogType=show&id="+id;
			window.location.href=url;
		}
	}
	function updateBlog_2(id){
		var xmlHttpBlog;
		var url="/myblog/blog.do";
		var t=document.getElementById("title").value;
		var c=document.getElementById("content").value;
		var formdata="title="+t+"&content="+c+"&blogType=updBlog&id="+id;
		if(window.XMLHttpRequest){
			xmlHttpBlog=new XMLHttpRequest();
		}
		else{
			xmlHttpBlog=new ActiveXObject("Microsof.XMLHTTP");
		}
		xmlHttpBlog.onreadystatechange=function (){
			if(xmlHttpBlog.readyState==4 && xmlHttpBlog.status==200){
				var rs=eval("("+xmlHttpBlog.responseText+")");
				if(rs.status=="true") {
					updateBlogState=true;
				}
			}
		}
		xmlHttpBlog.open("post",url,false);
		xmlHttpBlog.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlHttpBlog.send(formdata);
	}
	
</script>
</body>
</html>