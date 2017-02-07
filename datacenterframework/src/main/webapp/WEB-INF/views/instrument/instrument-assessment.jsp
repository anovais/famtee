<%@include file="../predefine/header.jsp"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" type="text/css" 	href="<c:url value="/resources/jquery/jquery-ui.css"/>">
<script type="text/javascript" 	src="<c:url value="/resources/jquery/jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/resources/jquery/jquery-ui.js"/>"></script>

<script type="text/javascript" 	src="<c:url value="/resources/js/assessment.js"/>"></script>

<title>Perfil ${instrument.code}</title>
</head>

<body>
	<%@include file="../predefine/menu.jsp"%>


	<div class="container-fluid">
		<div class="conteudo">
			<div class="row ">
				<div class="col-lg-12 borda" align="center">
					<h2>${instrument.name}</h2>
				</div>
				<div class="row ">
					<div class="col-lg-12">
						<div class="well well-sm">
							Legenda: &nbsp;&nbsp; <img style="max-width: 20px"
								src="<c:url value="/resources/icons/none.png"/>" /> Não
							Avaliado &nbsp;&nbsp;&nbsp; <img style="max-width: 20px"
								src="<c:url value="/resources/icons/red.png"/>" /> Não Atingido
							&nbsp;&nbsp;&nbsp; <img style="max-width: 20px"
								src="<c:url value="/resources/icons/yellow.png"/>" />
							Parcialmente Atingido &nbsp;&nbsp;&nbsp; <img
								style="max-width: 20px"
								src="<c:url value="/resources/icons/green.png"/>" /> Atingido
							&nbsp;&nbsp;&nbsp;
						</div>

						<div>
							<div>
<form action="/datacenterframework/assessment/submit" onsubmit="return confirm('Tem certeza que deseja registrar a avaliação?')"
	method="POST" >
						<div id="tabs" class="nav nav-tabs nav-justified">
						<ul>
							<c:forEach items="${instrument.assessments}" var="assessment" varStatus="level">
							<li>
								<a href="#tabs-${assessment.level.level}"> 
								<img	style="max-width: 24px;"
											src="<c:url value="/resources/icons/${assessment.progress.icon}"/>" />
											TRL-${assessment.level.level}
								</a>
							</li>
							</c:forEach>
							<li>
							<input type="submit" value="Confirmar Avaliação" 
									class="btn btn-primary" /></li>
							</ul>
							
							<!--  PARA CADA AVALIAÇÃO -->
							<c:forEach items="${instrument.assessments}" var="assessment"
								varStatus="level">
								<div id="tabs-${assessment.level.level}" data-assessmentlevel="${assessment.level.level}" >
									<ul class="list-group" style="margin: 0 0 0 0;">
										<c:forEach items="${assessment.quizz}" var="answer"
											varStatus="answerStatus">
											<div class="input-group"  data-answerid="${answer.id}" data-value="${answer.progress.value}">
												<span class="input-group-addon" style="min-width: 18px;"> 
													<img class="answering" style="max-width: 15px;" src="<c:url value="/resources/icons/${answer.progress.alternativeIcon}"/>" />
													&nbsp;&nbsp;
												</span>
												<span class="input-group-addon" style="min-width: 18px;">
													<a href="#" id="${answer.id}" class="addFile4Answer">
														<img style="max-width: 15px;" alt="anexao documento"
														title="anexar documento"
														src="<c:url value="/resources/icons/paperclip.png"/>" />
														</a>
												</span>														
												<label class="form-control">
												
													${answer.question.description} &nbsp;&nbsp;
												
													<span id="artefactsArea${answer.id}">
													<c:forEach items="${answer.artefacts}" var="artefact">
													<span  class="tag label label-primary" style="padding:5px 15px;" artefactline="true" data-idartefact="${artefact.code}">
														<a href="/datacenterframework/${artefact.resourceURL}" download="${artefact.name}.${artefact.extension}" style="text-decoration: none">													 
															<span style="color: #fff;">	${artefact.name} </span>
														</a>
													  <a href="#" onclick="removeArtefact(this)"><i class="remove glyphicon glyphicon-remove-sign glyphicon-white" style="width: 2px;"></i></a> 
													</span>
													</c:forEach>
													</span>
												</label>
											</div>

										</c:forEach>
									</ul>
		<br/>
		
		
		<!--  TENTATIVA DE TABELA -->
		<div class="panel panel-primary">
		  <!-- Default panel contents -->
		  <div class="panel-heading text-center">
		  		Artefatos &nbsp;&nbsp;&nbsp; <a href="#" class="addFile" id="${level.index+1}"> <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> novo </a>
			</div>
		
		  <!-- Table -->
		  <table class="table" id="artefactsTable${level.index+1}" style="border: 1px solid #fff;">
		    <c:forEach items="${assessment.artefacts}" var="artefact">
		    	<tr class="item-lista" artefactline="true" data-idartefact="${artefact.code}">
		    		<td>
		    		<c:choose>
						    <c:when test="${artefact.name.length() > 50}">
						        ${artefact.name.substring(0,50)}...
						    </c:when>    
						    <c:otherwise>
						        ${artefact.name}
						    </c:otherwise>
					</c:choose>
	    			</td>
		    		<td>
    					<c:choose>
						    <c:when test="${artefact.description.length() > 50}">
						        ${artefact.description.substring(0,50)}...
						    </c:when>    
						    <c:otherwise>
						        ${artefact.description}
						    </c:otherwise>
						</c:choose>
					</td>
		    		<td> <fmt:formatDate pattern="dd/MM/yyyy" value="${artefact.uploadDate.time}" />   </td>
		    		<td style="width: 35px">
		    		<a href="<c:url value="/${artefact.resourceURL}"/>" 
														download="${artefact.name}.${artefact.extension}" style="text-decoration: none">
									<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
									</a>
					</td>					
					<td style="width: 35px">
					<a href="#" class="deletelink" onclick="removeArtefact(this)"
									style="text-decoration: none">
									<span class="glyphicon glyphicon-remove" aria-hidden="true" ></span>
									</a>
					</td>
		    	</tr>
		    </c:forEach>
		  </table>
		</div>
		</div>
		
							</c:forEach>
						</div>
<!-- FIM  PARA CADA AVALIAÇÃO -->
</form>
<!--  FORMULARIO PARA UPLOAD DE ARTEFATO -->
<div id="dialog-form" class="dialog-form" title="Upload de Artefato" style="overflow-x: hidden;">
		<p class="validateTips">Descrição do Artefato</p>
		<form>
			<fieldset>
			<div class="row">				
					<div class="form-group col-md-12">
						<label> Nome </label>
					</div>					
			</div>
			<div class="row">				
					<div class="form-group col-md-12">
					<input type="text"
					name="name" id="name" 
					class="form-control inputForm">
			</div>					
			</div>	
			<div class="row">				
					<div class="form-group col-md-12">
						<label> Descrição </label>
					</div>					
			</div>
			<div class="row">				
					<div class="form-group col-md-12">
					<input type="text"
					name="description" id="description" 
					class="form-control inputForm">
			</div>					
			</div>	
			<div class="row">
				<div class="form-group col-md-12" id="file-upload-div">				
					<input name="file2" id="file2" type="file" />
				</div>
			</div>
			<input type="hidden" id="dialog-level" name="dialog-level" />
			<input type="hidden" id="dialog-type" name="dialog-type" />
				<!-- Allow form submission with keyboard without duplicating the dialog button -->
				<input type="submit" tabindex="-1"
					style="position: absolute; top: -1000px">
			</fieldset>
		</form>
	</div>
</div>

<!--  FIM FORMULARIO PARA UPLOAD DE ARTEFATO -->								

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@include file="../predefine/footer.jsp" %>
</body>
</html>