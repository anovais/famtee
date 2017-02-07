<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"><base>


<title>Insert title here</title>
</head>
<body>

<div class="container">
<form:form servletRelativeAction="/register"  method="POST" commandName="user">
	<div>		
		<label>Nome</label> <input name="name"/>
	</div>
	<div>		
		<label>Lattes</label> <input name="lattes"/>
	</div>	
	<div>		
		<label>Email</label> <input name="email"/>
	</div>
	
	<div>		
		<label>Senha</label> <input name="password" type="password"/>
	</div>
	<input type="submit" value="Cadastrar-se" />
</form:form>
	
</div>
</body>
</html>