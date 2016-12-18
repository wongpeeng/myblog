<br>
<center>
	<input type="button" id="nBlog" name="nBlog" value="new blog" onclick="nBlog()">
	<input type="button" id="pubBlog" name="pubBlog" value="my blogs" onclick="">
</center>
<div id="blog_p1" style="display:block;">
	<form name="newBlogForm" method="post" action="/myblog/blog.do">
		title
		<br>
		<textarea id ="title" name="title" style="height:32px;width:500px;"></textarea>
		<br>
		content
		<br>
		<textarea id="content" name="content" style="height:384px;width:500px;"></textarea>
		<br>
		<input type="hidden" id="blogType" name="blogType" value="newBlog">
		<p style="margin:0 auto;text-align:center;">
		<input type="button" id="sub" name="sub" value="submit" onclick="subBlog()">
		</p>
	</form>
</div>
<script type="text/javascript">
	function nBlog(){
		var div=document.getElementById("blog_p1");
		if(div.style.display=="none"){
			div.style.display="block";
			document.getElementById("title").value="";
			document.getElementById("content").value="";
			}
		else{
			div.style.display="none";
		}
	}

	function subBlog(){
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
		document.newBlogForm.submit();
	}
</script>
