<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false"%>
<%@include file="/source/includes/header.jsp" %>
<body>    
<center>
	<c:forEach items="${products}" var="itms">	
	<table border="1">
	<tr><td width="300">${itms.name}</td><td width="300"></td></tr>
	<tr><td><img width="250" height="250" src="./source/images/${itms.id}.jpg"></td><td>${itms.description}</td></tr>
	<tr><td>${itms.price}</td><td><input type="button" value="Buy"/></td></tr>
	
	</table>
	<br/>
	<br/>
	
</c:forEach>
</center>
</body>
</html>
<%@include file="/source/includes/footer.jsp" %>