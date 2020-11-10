<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false"%>
<%@include file="/source/includes/header.jsp" %>
<body>    
<center>
	<c:forEach items="${sessionScope.cart}" var="itms">	
	<table border="1">
	<tr><td width="300">${itms.key.name}</td><td width="300">Цена: ${itms.key.price} грн.</td></tr>
	<tr><td><img width="250" height="250" src="./source/images/${itms.key.id}.jpg"></td><td>Описание:<br/>${itms.key.description}</td></tr>
	<tr><td>Количество: ${itms.value}</td><td>
		<form action="./cart" method="post">
			<input type="hidden" name="prodIdDel" value = "${itms.key.id}"/>
			<input type="image" src="./source/images/delete.png" width="50" height = "50" alt="">
		</form>
	</td></tr>
	</table>
	<br/>
	<br/>
	
</c:forEach>
</center>
</body>
</html>
<%@include file="/source/includes/footer.jsp" %>