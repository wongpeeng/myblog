<center>
	<c:choose>
		<c:when test="${sessionScope.user.name==null}">
			Welcome,guest!
			Please login!
		</c:when>
		<c:otherwise>
			Welcome,${sessionScope.user.getName()}!
		</c:otherwise>
	</c:choose>
</center>
