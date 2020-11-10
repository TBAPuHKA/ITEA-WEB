<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false"%>
<%@include file="/source/includes/header.jsp" %>
<body>
<center>
	<c:forEach items="${products}" var="itms">	
	<table border="1">
	<tr><td width="300">${itms.name}</td><td width="300">Цена: ${itms.price} грн.</td></tr>
	<tr><td><img width="250" height="250" src="./source/images/${itms.id}.jpg"></td><td>${itms.description}</td></tr>
	<tr><td>
	<input type="hidden" name="prodId" value="${itms.id}"/>
	<img id="imgMinus" src="./source/images/minus.png" width="25" height = "25" onclick="minus(${itms.id})"/>
	<input type="text" id="qnt${itms.id}" value="1" size="2" />
	<img id="imgPlus" src="./source/images/plus.png" width="25" height = "25" onclick="plus(${itms.id})"/>
	</td><td>
	<input type="button" class="buyButton" value="Buy" onclick="show(${itms.id})"/>
	</table>
	<br/>
	<br/>
	
</c:forEach>
</center>
</body>
</html>
<%@include file="/source/includes/footer.jsp" %>

<script>
	function minus(numb){
		var qnt = document.getElementById("qnt"+numb);
		qnt.value = +qnt.value-1;
	}
	
	function plus(numb){
		var qnt = document.getElementById("qnt"+numb);
		qnt.value = +qnt.value+1;
	}
	
	function show(numb){
		var qnt = document.getElementById("qnt"+numb);
		alert("id: "+numb+" quantity: "+qnt.value);
	}
</script>