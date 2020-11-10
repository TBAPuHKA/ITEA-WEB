<%@ page isELIgnored = "false" %>
Hello, ${auth.name}!
<form action="./authorization" method="post">
	<input type="hidden" name="logOut" value = "logOut"/>
	<input type="submit" value="Log Out"/>
</form>