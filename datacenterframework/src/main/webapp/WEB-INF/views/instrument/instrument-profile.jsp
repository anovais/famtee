<%@include file="../predefine/header.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery/jquery-ui.css"/>">
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery.js"/>" > </script>
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-ui.js"/>" > </script>

<script>
  $( function() {
    $( "#accordion" ).accordion({
      collapsible: true,      
    	heightStyle: "content",
    	active: false
    });
    
  } );
  </script>

<title>Perfil ${instrument.code}</title>
</head>

<body >
<%@include file="../predefine/menu.jsp" %>
	<div class="container-fluid">
		<div class="conteudo">
			<div class="row ">			
				
				<div class="col-sm-4 ">
				<div class="row borda">
					<h2>${instrument.name}</h2>
					<h4>Objetivo</h4>										
					<label class="textoSuave">${instrument.objective}</label>
					<h4>Descrição</h4>
					<label class="textoSuave">${instrument.description}</label>
					</div>
					
<!--  CARD DE MISSOES -->
					<div class="row" style="margin-top: 20px;">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<div class="row">
											<div class="col-xs-3">
												<img alt="missions"
													src="/datacenterframework/resources/icons/instrument-64.png" />
											</div>
											<div class="col-xs-9 text-right">
												<div class="huge">${fn:length(instrument.missions)}</div>
												<div>Missões</div>
											</div>
										</div>
									</div>
									<c:forEach items="${instrument.missions}" var="p">
										<a href="/datacenterframework/mission/profile/${p.name}">
											<div class="panel-footer">
												<span class="pull-left">${p.name}</span><br /> <span
													class="pull-right"> </span>
												<div class="clearfix"></div>
											</div>
										</a>
									</c:forEach>
								</div>
							</div>


				</div>
<!-- ------- 	 AVALIAÇÃO TRL ---------	-->				
				<div class="col-sm-8">
<!-- 				<div class="row"> -->
<!-- 					<div class="col-lg-4"> -->
<!-- 						TRL -->
<!-- 					</div> -->
<!-- 					<div class="col-lg-8"> -->
					
<!-- 					<div class="progress" style="height: 50px; padding 10px;"> -->
<%-- 					<c:forEach items="${instrument.assessments}" var="assessment"> --%>
<!-- 						  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="1" style="min-width: 5em; width: 1%;"> -->
<!-- 						    <label style="margin-top: 10px"> TRL-1 </label> -->
<!-- 						  </div> -->
<%-- 					  </c:forEach>					   --%>
<!-- 					</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
				<div class="row">
				<div class="well well-sm">
					Legenda: &nbsp;&nbsp;
						<img style="max-width: 20px" src="<c:url value="/resources/icons/none.png"/>" /> Não Avaliado &nbsp;&nbsp;&nbsp;
						<img style="max-width: 20px" src="<c:url value="/resources/icons/red.png"/>" /> Não Atingido &nbsp;&nbsp;&nbsp;
						<img style="max-width: 20px" src="<c:url value="/resources/icons/yellow.png"/>" /> Parcialmente Atingido &nbsp;&nbsp;&nbsp;
						<img style="max-width: 20px" src="<c:url value="/resources/icons/green.png"/>" /> Atingido &nbsp;&nbsp;&nbsp;
						<a href="<c:url value="/assessment/evaluate/${instrument.code}"/>">Avaliar</a>
						 
				</div>
				<div style="margin-top: -20px"> 
					<div id="accordion">
						<c:forEach items="${instrument.assessments}" var="assessment">
							<h3>
								<img style="max-width: 24px;" src="<c:url value="/resources/icons/${assessment.progress.icon}"/>" />&nbsp;&nbsp; 
								TRL-${assessment.level.level} &nbsp; ${assessment.level.name}
							</h3>
							
							<div class="content">
								<p>${assessment.level.description}</p>
								<ul class="list-group" style="margin: 0 0 0 0;"> 
								<c:forEach items="${assessment.quizz}" var="answer">
									<li class="list-group-item item-lista" >
									<img style="max-width: 18px;" src="<c:url value="/resources/icons/${answer.progress.alternativeIcon}"/>" />&nbsp;&nbsp;
									${answer.question.description} 
									<c:if test="${answer.artefacts.isEmpty() eq false }">
									&nbsp;&nbsp;
								  	<a href="<c:url value="/${answer.artefacts[0].resourceURL}"/>" 
												download="${answer.artefacts[0].name}.${answer.artefacts[0].extension}" style="text-decoration: none">
										<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
									</a>
									</c:if>
									</li>
								</c:forEach>
								</ul>
						<c:if test="${assessment.artefacts.isEmpty() eq false}">
							<!--  TENTATIVA DE TABELA -->
							<div class="panel panel-default">
							<!-- Default panel contents -->
							  <div class="panel-heading text-center">
							  		Artefatos
								</div>							 
							  <!-- Table -->
							  <table class="table" id="artefactsTable${level.index+1}" style="border: 1px solid #fff;">
							    <c:forEach items="${assessment.artefacts}" var="artefact">
							    	<tr class="item-lista" artefactline="true" data-idartefact="${artefact}">
							    		<td>
							    		<c:choose>
											    <c:when test="${artefact.name.length() > 40}">
											        ${artefact.name.substring(0,40)}...
											    </c:when>    
											    <c:otherwise>
											        ${artefact.name}
											    </c:otherwise>
										</c:choose>
						    			</td>
							    		<td>
					    					<c:choose>
											    <c:when test="${artefact.description.length() > 40}">
											        ${artefact.description.substring(0,40)}...
											    </c:when>    
											    <c:otherwise>
											        ${artefact.description}
											    </c:otherwise>
											</c:choose>
										</td>
							    		<td> <fmt:formatDate pattern="dd/MM/yyyy" value="${artefact.uploadDate.time}" />   </td>
							    		<td style="width: 35px" ><a href="<c:url value="/${artefact.resourceURL}"/>" 
														download="${artefact.name}.${artefact.extension}" style="text-decoration: none">
														<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
														</a>
										</td>					
										
							    	</tr>
							    </c:forEach>
							  </table>
							</div>
							</c:if>
							</div>
						</c:forEach>
					</div>
				</div>
				</div>
				</div> <!-- LINHA DO ACORDION DE TRL -->
				</div>
			</div>
		</div>
		<!-- 		
			<div class="row-md-12 borda">
			
 PRIMEIRA COLUNA 
				<div class="col-md-4">
					<h1>${instrument.name}</h1>
					
					<div class="row-md-12">
						<h3>Objetivo</h3>
					</div>
					<div class="row">
						<label class="textoSuave">${instrument.objective}</label>
					</div>
					
					<div class="row-md-12">
						<label>Descrição</label>
					</div>
					<div class="row-md-12">
						<label>${instrument.description}</label>
					</div>
					
				</div>
	<!-- SEGUNDA COLUNA  
				<div class="col-md-6">
					<h1>${instrument.name}</h1>
				</div>
				
			</div>
			-->
	</div>
	</div>
	
</body>
</html>