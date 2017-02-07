<%@include file="../predefine/header.jsp" %>
<title>Cadastro de Tecnologia</title>
</head>
<body>
<%@include file="../predefine/menu.jsp" %>
	<div class="container" >
		<div class="conteudo">		
			<h1>NOVA TECNOLOGIA</h1>			
			<div class="form-group" >
			<div class="row">
			<div class="col-md-6">
			<form:form method="POST" servletRelativeAction="/instrument/form"
				commandName="instrument" enctype="multipart/form-data" class="form">
				<div class="form-group">
					<label>Tecnologia</label>
					<form:input path="name" class="form-control inputForm" placeholder="Nome da tecnologia"/>
					<form:errors path="name" class="form-control " />
				</div>
				<div class="row">				
					<div class="form-group col-md-6">
						<label>Tipo</label>
					</div>
					<div class="form-group col-md-6">
						<label>Identificador</label>
					</div>					
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<form:select path="type" class="form-control inputForm" items="${mapTypes}" itemLabel="name" />
						<form:errors path="type" />
					</div>
					<div class="form-group col-md-6">
						<form:input path="code" class="form-control inputForm" placeholder="Código Identificador"/>
						<form:errors path="code" />
					</div>
				</div>
				
				<div class="form-group">
					<label>Objetivo</label>
					<form:input path="objective" class="form-control inputForm" placeholder="Objetivo principal"/>
					<form:errors path="objective" />
				</div>
				<div class="form-group"> 
					<label>Descrição</label>
					<form:textarea path="description" class="form-control inputForm" placeholder="Uma breve descrição" />
					<form:errors path="description" />
				</div >
			
				<div class="row">				
					<div class="form-group col-md-6">
						<label>Data de inicio do projeto</label>
					</div>
					<div class="form-group col-md-6">
						<label>Foto</label>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<form:input type="date" path="initDate" class="form-control inputForm" />
						<form:errors path="initDate" />	
					</div>
					<div class="form-group col-md-6">
						<input type="file" name="photo" class="form-control file" />
						<form:errors path="photoPath" />
					</div>
				</div>
				<input type="submit" value="Adicionar" class="btn btn-primary" />
			</form:form>
			</div>
			</div>
			</div>
		</div>
	</div>
</body>
</html>