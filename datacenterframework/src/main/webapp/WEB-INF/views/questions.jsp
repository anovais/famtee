<%@include file="predefine/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>
	<div class="container" >
		<div class="conteudo" >
			<h1>NOVA QUESTÃO</h1>
			
			<div class="form-group">
			<form:form method="POST" servletRelativeAction="/question/form"			
				commandName="question" class="form">
				<div class="form-group">
					<label>Questão</label>
					<form:input path="description" class="form-control inputForm" placeholder="Descricão da questão"/>
					<form:errors path="description" class="form-control " />
				</div>
				
				<div class="form-group">
					<label>Tipo</label>
					<form:select path="type" class="form-control" items="${mapTypes}" itemLabel="name" itemValue="code" />
					<form:errors path="type" />
				</div>
				<div class="form-group">
					<label>Level</label>
					<form:select path="level" class="form-control" items="${levels}" itemLabel="level" itemValue="level"/>
					<form:errors path="level" />
				</div>				
				<input type="submit" value="Adicionar" class="btn btn-primary" />
			</form:form>
			</div>
		</div>
	</div>

</body>
</html>