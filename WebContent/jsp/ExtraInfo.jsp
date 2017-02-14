<%@ page import="blog.listener.SessionListener"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body onload="ctime()">
	<br>
	<br>
	<div id="extraInfo" style="margin: 0 auto; width: 500px;">
	current online user number:
	<%=blog.listener.SessionListener.getUserNum() %>!<br>
	current time:
	<div id="ctime" style="margin: 0 auto; width: 500px;"></div>
	</div>
</body>
<script type="text/javascript">
function ctime() {
  var show = document.getElementById("ctime");
  setInterval(function() {
   var time = new Date();
   var m = time.getMonth() + 1;
   var t = time.getFullYear() + "-" + m + "-"
     + time.getDate() + " " + time.getHours() + ":"
     + time.getMinutes() + ":" + time.getSeconds();
   show.innerHTML = t;
  }, 1000);
 };
</script>
</html>