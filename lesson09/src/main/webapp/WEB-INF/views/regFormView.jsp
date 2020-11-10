<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@include file="/source/includes/header.jsp"%>

<body>
	<c:choose>
		<c:when test="${sessionScope.userTemp!=null}">
Registration Successful, ${user.name}!
		</c:when>
		<c:otherwise>
			<form id="loginForm" action="./registration" method="post">
				<div style="color: red">
					<div class="input">${errorText}</div>
				</div>
				<div class="field">
					<label>Login:</label>
					<div class="input">
						<input type="text" name="login" value="${reg.login}">
					</div>
				</div>
				<div class="field">
					<label>Password:</label>
					<div class="input">
						<input type="password" name="password" value="${reg.password}">
					</div>
				</div>
				<div class="field">
					<label>Retype password:</label>
					<div class="input">
						<input type="password" name="rePassword" value="${reg.rePassword}">
					</div>
				</div>
				<div class="field">
					<label>Name:</label>
					<div class="input">
						<input type="text" name="name" value="${reg.name}">
					</div>
				</div>
				<div>
					<label>Gender:</label>
					<div class="input">
						Male<input type="radio" name="gender" value="male"
							<c:if test = "${reg.gender eq 'male'}">checked</c:if>>
						Female<input type="radio" name="gender" value="female"
							<c:if test = "${reg.gender eq 'female'}">checked</c:if> )>
					</div>
				</div>
				<div>
					<label>Address:</label>
					<div class="input">
						<select name="address">
							<option value="lnr"
								<c:if test = "${reg.address eq 'lnr'}">selected</c:if>>LRN</option>
							<option value="dnr"
								<c:if test = "${reg.address eq 'dnr'}">selected</c:if>>DNR</option>
							<option value="crimea"
								<c:if test = "${reg.address eq 'crimea'}">selected</c:if>>Crimea</option>
						</select>
					</div>
				</div>
				<div class="field">
					<label>Comment:</label>
					<div class="input">
						<textarea name="comment" cols="20" rows="10")">${reg.comment}</textarea>
					</div>
				</div>
				<div>
					<div class="input">
						I agree: <input type="checkbox" name="agree">
					</div>
				</div>
				<div class="submit">
					<button value="send" type="submit">Enter</button>
				</div>
			</form>
		</c:otherwise>
	</c:choose>
</body>

<%@include file="/source/includes/footer.jsp"%>