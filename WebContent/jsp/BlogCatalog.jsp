<br>
<br>
==================Blog Catalog==================
<br>
<br>
	<div id="Blog"></div>
<br>
<input type="button" id="pre" name="pre" value="pre" onclick="prePage()">
<input type="button" id="cur" name="cur" value="1" >
<input type="button" id="next" name="next" value="next" onclick="nextPage()">

<script type="text/javascript" charset="UTF-8">
var blogType="daily";
var pageNum=0;
function prePage(){
	if(pageNum<=1)
		alert("This is already first page!");
	else{
		updateBlogCatalog();
	}
}
function nextPage(){
		updateBlogCatalog();
}
function updateBlogCatalog(){
	var num=pageNum+1;
	var url="./blog.do?blogType="+blogType+"&pageNum="+num;
	loadBlogCatalog(url);
}
function loadBlogCatalog(url){
	var xmlHttpBlog;
	if(window.XMLHttpRequest){
		xmlHttpBlog=new XMLHttpRequest();
	}
	else{
		xmlHttpBlog=new ActiveXObject("Microsof.XMLHTTP");
	}
	xmlHttpBlog.onreadystatechange=function (){
		if(xmlHttpBlog.readyState==4 && xmlHttpBlog.status==200){
			var con=eval("("+xmlHttpBlog.responseText+")");
			var txt="";
			var div=document.getElementById("Blog");
			var cur=document.getElementById("cur");
			var ref="<a href=\"blog.do?blogType=show&id=";
			if(con.length!=0){
				for(var js in con){
					txt=txt+ref+con[js].id+"\">"+con[js].title+"</a><br>author:"+con[js].author+"&nbsp;&nbsp;&nbsp;&nbsp;comments:"+con[js].cmtnum+"&nbsp;&nbsp;&nbsp;&nbsp;views:"+con[js].views+"<br>pubdate:"+con[js].pubDate+"&nbsp;&nbsp;&nbsp;&nbsp;revdate:"+con[js].revDate+"<br><br>";
				}
				div.innerHTML=txt;
				pageNum=pageNum+1;
				cur.value=pageNum;
			}
			else
				alert("last page already!");
		}
	}
	xmlHttpBlog.open("GET",url,true);
	xmlHttpBlog.send(null);
}
</script>
