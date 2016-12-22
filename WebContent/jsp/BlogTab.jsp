<br>
<center>
	<input type="button" id="nBlog" name="nBlog" value="new blog" onclick="nBlog()">
	<input type="button" id="pubBlog" name="pubBlog" value="my blogs" onclick="pubBlog()">
</center>
<div id="blog_p1" style="display:block;">
	<form id="newBlogForm"  method="post" action="/myblog/blog.do">
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
	
<div id="blog_p2" style="display:block;">
	<div id="myBlog"></div>
	<br>
	<p style="margin:0 auto;text-align:center;">
	<input type="button" id="pre" name="pre" value="pre" onclick="prePage()">
	<input type="button" id="cur" name="cur" value="1" >
	<input type="button" id="next" name="next" value="next" onclick="nextPage()">
	</p>
</div>
<script type="text/javascript">
	function nBlog(){
		var div1=document.getElementById("blog_p1");
		var div2=document.getElementById("blog_p2");div2.style.display="none";
		if(div1.style.display=="none"){
			div1.style.display="block";
			document.getElementById("title").value="";
			document.getElementById("content").value="";
			}
		else{
			div1.style.display="none";
		}
	}
	//submit a new blog
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
		newBlogState=false;
		subBlog_2();
		if(!newBlogState) alert("try again!");
	}
	function subBlog_2(){
		var xmlHttpBlog;
		var url="/myblog/blog.do";
		var formobj=document.getElementById("newBlogForm");
		var t=document.getElementById("title").value;
		var c=document.getElementById("content").value;
		var b=document.getElementById("blogType").value;
		var formdata="title="+t+"&content="+c+"&blogType="+b;
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
					alert("successfully!");
					newBlogState=true;
					document.getElementById("title").value="";
					document.getElementById("content").value="";
				}
				
				
			}
		}
		xmlHttpBlog.open("post",url,false);
		xmlHttpBlog.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlHttpBlog.send(formdata);
	}
	
	//display my published blog
		var pageNum=0;
		function prePage(){
			if(pageNum<=1)
				alert("This is already first page!");
			else{
				pageNum=pageNum-2;
				pubBlog_2();
			}
		}
		function nextPage(){
			pubBlog_2();
		}
		function pubBlog(){
		var div1=document.getElementById("blog_p1");div2.style.display="none";
		var div2=document.getElementById("blog_p2");
		if(div2.style.display=="none")
			div2.style.display="block";
		else
			div2.style.display="none";
		pageNum=0;
		pubBlog_2();
	}
	function pubBlog_2(){
		var xmlHttpBlog;
		var url="/myblog/blog.do";
		var num=pageNum+1;
		var data="blogType=myBlog&pageNum="+num;
		if(window.XMLHttpRequest){
			xmlHttpBlog=new XMLHttpRequest();
		}
		else{
			xmlHttpBlog=new ActiveXObject("Microsof.XMLHTTP");
		}
		xmlHttpBlog.onreadystatechange=function (){
			if(xmlHttpBlog.readyState==4 && xmlHttpBlog.status==200){
				var response=xmlHttpBlog.responseText;
				if(response.length==0) alert("last page already");
				else{
					var div=document.getElementById("myBlog");
					var cur=document.getELementById("cur");
					div.innerHTML=response;
					pageNum=pageNum+1;
					cur.value=pageNum;
				}
			}
		}
		xmlHttpBlog.open("post",url,false);
		xmlHttpBlog.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlHttpBlog.send(formdata);
	}
</script>
