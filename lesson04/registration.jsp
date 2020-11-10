<%@ page import="java.util.regex.Matcher"%>
<%@ page import="java.util.regex.Pattern"%>
<%@ page import="itea_lesson04_web.DBwork"%>
<%@ include file="includes/menu.jsp" %>
<%
boolean showForm = true;
boolean isError = false;
Pattern pattern = null;
Matcher matcher = null;
StringBuilder errorText = new StringBuilder();
errorText.append("<ul>");

String login = request.getParameter("login");
String password = request.getParameter("password");
String rePassword = request.getParameter("rePassword");
String name = request.getParameter("name");
String gender = request.getParameter("gender");
String address = request.getParameter("address");
String comment = request.getParameter("comment");
String agree = request.getParameter("agree");

if(login!=null){
	if(login.isEmpty()){
		errorText.append("<li>The 'login' is empty.</li>");
		isError = true;
	}else{
		pattern = Pattern.compile("^[\\w\\.-]+@[a-zA-Z0-9-]+\\.([a-z]{2}|com|org|net|edu|gov|info)$");
        matcher = pattern.matcher(login);
        if(!matcher.matches()){
			errorText.append("<li>The 'login' must be an email address.</li>");
			isError = true;
		}
	}
	if(password.isEmpty()){
		errorText.append("<li>The 'password' is empty.</li>");
		isError = true;
	}
	if(rePassword.isEmpty()){
		errorText.append("<li>The 'rePassword' is empty.</li>");
		isError = true;
	}else{
		if(!password.isEmpty()){
			if(!password.equals(rePassword)){
				errorText.append("<li>The 'password' and 'rePassword' must be the same.</li>");
				isError = true;
			}else{
				pattern = Pattern.compile("^(?=.*[A-Z])[a-zA-Z0-9]{8,}$");
				matcher = pattern.matcher(password);
				if(!matcher.matches()){
					errorText.append("<li>The 'password' must consist of numbers and letters<br>(at least one must be capital).<br>Length must be at least 8 characters long.</li>");
					isError = true;
				}
			}
		}
	}
	if(name.isEmpty()){
		errorText.append("<li>The 'name' is empty.</li>");
		isError = true;
	}
	if(gender==null){
		errorText.append("<li>The 'gender' is empty.</li>");
		isError = true;
	}
	if(address.isEmpty()){
		errorText.append("<li>The 'address' is empty.</li>");
		isError = true;
	}
	if(comment.isEmpty()){
		errorText.append("<li>The 'comment' is empty.</li>");
		isError = true;
	}
	if(agree == null){
		errorText.append("<li>The 'agree' is empty.</li>");
		isError = true;
	}
	
	
	
	if(!isError){
	out.write("Registration sucsesfull!");
	showForm = false;
	DBwork dbWork = new DBwork();
	dbWork.insertUser(login,password,name);
	
	}
}
errorText.append("</ul>");

if(showForm){%>

<body>
<form id="loginForm" action="" method="post">
<div style='color:red'>
		<div class="input"><%out.write(errorText.toString());%></div>
</div>
<div class="field">
		<label>login:</label>
		<div class="input"><input type="text" name="login" value="<%=(login!=null?login:"")%>"></div>
</div>
<div class="field">
		<label>password:</label>
		<div class="input"><input type="password" name="password" value="<%=(password!=null?password:"")%>"></div>
</div>
<div class="field">
		<label>retype password:</label>
		<div class="input"><input type="password" name="rePassword" value="<%=(rePassword!=null?rePassword:"")%>"></div>
</div>
<div class="field">
		<label>name:</label>
		<div class="input"><input type="text" name="name" value="<%=(name!=null?name:"")%>"></div>
</div>
<div>
		<div class="input">gender: M<input type="radio" name="gender" value="male" <%=(gender!=null && gender.equals("male")?"checked":"")%>>F<input type="radio" name="gender" value="female" <%=(gender!=null && gender.equals("female")?"checked":"")%>></div>
</div>
<div>
		<div class="input">address: <select name ="address"><option value="lnr"<%=(address!=null && address.equals("lnr")?"selected":"")%>>LRN</option><option value="dnr"<%=(address!=null && address.equals("dnr")?"selected":"")%>>DNR</option><option value="crimea"<%=(address!=null && address.equals("crimea")?"selected":"")%>>Crimea</option></select></div>
</div>
<div class="field">
		<label>comment:</label>
		<div class="input"><textarea name = "comment" cols = "40" rows="10" value="<%=(comment!=null?comment:"-")%>"></textarea></div>
</div>
<div>
		<div class="input">I agree: <input type="checkbox" name = "agree"></div>
</div>
<div class="submit">
		<button value="send" type="submit">Enter</button>
</div>
</form>
</body>

<%}%>