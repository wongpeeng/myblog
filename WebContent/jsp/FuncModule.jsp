<center>
	<c:choose>
		<c:when test="${sessionScope.user.name==null}">
		<input type="button" value="login" name="login" value="login" onclick="window.location.href='index.jsp'">
		</c:when>
		<c:otherwise>
			<input type="button" value="Personal Center" name="pc" id="pc" onclick="">
		</c:otherwise>
	</c:choose>
	<input type="button" id="da" name="da"  value="Daily Articles" onclick="da()">
	<input type="button" id="wh" name="wh"  value="Weekly Hot"     onclick="wh()">
	<form>
		<br>
		Key Words:<input type="text" id="seacrhContent" name="searchContent">
		<input type="button" id="search" name="search" value="search" onclick="">
	</form>
</center>
<script type="text/javascript">
function da(){
	blogType="daily";
	pageNum=0;
	updateBlogCatalog();
}
function wh(){
	blogType="weekly";
	pageNum=0;
	updateBlogCatalog();
}
</script>