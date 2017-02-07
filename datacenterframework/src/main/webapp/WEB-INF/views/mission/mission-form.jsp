<%@include file="../predefine/header.jsp" %>
<script>
	$(function(){
		$( ".date" )
        .datepicker({dateFormat:'dd/mm/yyyy', showOtherMonths: true,
            selectOtherMonths: true});
	});
</script>
</head>
<body>
  <div id="wrapper">
<%@include file="../predefine/menu.jsp" %>

	<div class="container-fluid">
		<div class="conteudo">		
			<h1>Nova Miss�o Espacial</h1>			
			<div class="form-group" >
			<div class="row">
			<div class="col-md-6">
			
			<form:form method="POST" servletRelativeAction="/mission/form" commandName="mission" class="form">
				<div class="form-group">
					<label>Miss�o</label>
					<form:input path="name" class="form-control inputForm" placeholder="Codinome da Miss�o"/>
					<form:errors path="name" class="form-control " />
				</div>
				<div class="row">
					<div class="form-group col-md-3">
						<label>Categoria</label>					
					</div>									
					<div class="form-group col-md-6">
						<label>Dimens�o</label>
					</div>
					<div class="form-group col-md-3">
						<label>Peso</label>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-3">
								<form:select path="spaceCraft.category.id" class="form-control" items="${spaceCategoryList}" itemLabel="name" itemValue="id" />
					</div>
						<div class="form-group col-md-2">
							<form:input path="spaceCraft.dimension.height" class="form-control inputForm" placeholder="X"/>
						</div><div class="form-group col-md-2">
							<form:input path="spaceCraft.dimension.width" class="form-control inputForm" placeholder="Y"/>
						</div><div class="form-group col-md-2">
							<form:input path="spaceCraft.dimension.depth" class="form-control inputForm" placeholder="Z"/>
						</div>
				
						<div class="form-group col-md-3">
						<form:input path="spaceCraft.weight" class="form-control inputForm" placeholder="Kg" />
						</div>
				</div>
				
				<div class="form-group">
					<label>Objetivo</label>
					<form:textarea path="objective" class="form-control inputForm" placeholder="Objetivo principal"/>
					<form:errors path="objective" />
				</div>
				<div class="form-group"> 
					<label>Descri��o</label>
					<form:textarea path="description" class="form-control inputForm" placeholder="Uma breve descri��o" />
					<form:errors path="description" />
				</div >
			
				<div class="row">				
					<div class="form-group col-md-6">
						<label>Data de inicio do projeto</label>
					</div>
					<div class="form-group col-md-6">
						<label>Data Lan�amento</label>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<form:input type="date" path="period.start" class="form-control inputForm date" />
						<form:errors path="period.start" />	
					</div>
					<div class="form-group col-md-6">
						<form:input type="date" path="launchDate" class="form-control inputForm date" />
						<form:errors path="launchDate" />	
					</div>
				</div>				
				<input type="submit"  value="Adicionar" class="btn btn-primary" />
			</form:form>
			
			</div>
			</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>