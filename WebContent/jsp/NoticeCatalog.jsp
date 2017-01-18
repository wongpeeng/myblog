<br>
<br>
<center>
====================Notice====================
</center>
<br>
<br>
	<div id="Notice" style="margin:0 auto;width:500px;height:auto;word-wrap:break-word;border:1px solid #000;align=left;"></div>
<br><center>
<input type="button" id="before" name="before" value="pre" onclick="bPage()">
<input type="button" id="now" name="now" value="1" >
<input type="button" id="after" name="after" value="next" onclick="aPage()">
</center>
<script type="text/javascript" charset="UTF-8">
var noPage=0;
function bPage(){
	if(noPage<=1)
		alert("This is already first page!");
	else{
		noPage=noPage-2;
		updateNoticeCatalog();
	}
}
function aPage(){
		updateNoticeCatalog();
}
function updateNoticeCatalog(){
	var num=noPage+1;
	var url="/myblog/notice.do?nType=list&pageNum="+num;
	loadNoticeCatalog(url);
}
function loadNoticeCatalog(url){
	var xmlHttpNotice;
	if(window.XMLHttpRequest){
		xmlHttpNotice=new XMLHttpRequest();
	}
	else{
		xmlHttpNotice=new ActiveXObject("Microsof.XMLHTTP");
	}
	xmlHttpNotice.onreadystatechange=function (){
		if(xmlHttpNotice.readyState==4 && xmlHttpNotice.status==200){
			var con=eval("("+xmlHttpNotice.responseText+")");
			var txt="";
			var div=document.getElementById("Notice");
			var cur=document.getElementById("now");
			var ref="<a href=\"/myblog/notice.do?nType=show&id=";
			if(con.length!=0){
				for(var js in con){
					txt=txt+ref+con[js].id+"\">"+con[js].title+"</a><br>author:"+con[js].author+"&nbsp;&nbsp;&nbsp;&nbsp;Emergency Level:"+con[js].level+"<br>from:"+con[js].strDate+"&nbsp;&nbsp;&nbsp;&nbsp;to:"+con[js].endDate+"<br><br>";
				}
				div.innerHTML=txt;
				noPage=noPage+1;
				cur.value=noPage;
			}
			else
				alert("last page already!");
		}
	}
	xmlHttpNotice.open("GET",url,true);
	xmlHttpNotice.send(null);
}
	</script>
