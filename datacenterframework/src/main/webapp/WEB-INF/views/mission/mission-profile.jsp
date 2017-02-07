<%@include file="../predefine/header.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	
<script type="text/javascript"
	src="<c:url value="/resources/js/autocomplete.js"/>">
</script>

<script>
	$(function() {
		$("#accordion").accordion({
			collapsible : true,
			heightStyle : "content"
		});
		
		$("#file-type").autocomplete({
			source:$('#collections').val().split(",")
		});
		  $( function() {
			    var dateFormat = "dd/mm/yy",
			      from = $( "#begin-date" )
			        .datepicker({
			          changeMonth: true,
			          changeYear:true,
			          numberOfMonths: 1,
			          dateFormat:dateFormat
			        })
			        .on( "change", function() {
			          to.datepicker( "option", "minDate", getDate( this ) );
			        }),
			      to = $( "#end-date" ).datepicker({
			        changeMonth: true,
			        changeYear:true,
			        numberOfMonths: 1,
			        dateFormat:dateFormat
			      })
			      .on( "change", function() {
			        from.datepicker( "option", "maxDate", getDate( this ) );
			      });
			 
			    function getDate( element ) {
			      var date;
			      try {
			        date = $.datepicker.parseDate( dateFormat, element.value );
			      } catch( error ) {
			        date = null;
			      }
			 
			      return date;
			    }
			  } );
	});

	
</script>

<title>Perfil ${instrument.code}</title>
</head>

<body>
	<div id="wrapper">
		<%@include file="../predefine/menu.jsp"%>
			<div class="container-fluid">
				<div class="conteudo">
					<div class="row ">
						<div class="col-lg-12">
							<h1 class="page-header"><center> ${mission.name} </center></h1>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-8">									
										<div class="panel panel-primary">
											<div class="panel-heading ">Resumo</div>
											<div class="panel-body">
											  <div class="col-xs-6 col-md-3">
											    <a href="#" class="thumbnail">
											      <img src="/datacenterframework/${mission.photoPath}" alt="" style="width: 256px">
											    </a>
											  </div>
												<h4> Objetivo da Missão </h4>
												<p>${mission.objective}</p>
												<br />
												<h4>
													<span class="label label-info">${mission.spaceCraft.category.name}</span>
													<span class="label label-info">${mission.spaceCraft.dimension}</span>
													<span class="label label-info">${mission.spaceCraft.weight}kg</span>
												</h4>
											</div>
											<a href="#">
												<div class="panel-footer">
													<span class="pull-right">Visualizar detalhes</span> <span
														class="pull-right"><i
														class="fa fa-arrow-circle-right"></i></span>
													<div class="clearfix"></div>
												</div>
											</a>
										</div>
								</div>
								
								<div class="col-lg-4">
									<div class="panel panel-primary">
									<div class="panel-heading">
										<div class="row">
											<div class="col-xs-3">
												<img alt="institutions"
													src="/datacenterframework/resources/icons/instrument-64.png" />
											</div>
											<div class="col-xs-9 text-right">
												<div class="huge">${fn:length(mission.payloads)}</div>
												<div>Payloads</div>
											</div>
										</div>
									</div>
									<c:forEach items="${mission.payloads}" var="p">
										<a href="/datacenterframework/instrument/profile/${p.code}">
											<div class="panel-footer">
												<span class="pull-left">${p.name}</span><br /> <span
													class="pull-right"> </span>
												<div class="clearfix"></div>
											</div>
										</a>
									</c:forEach>
								</div>
					<!--  CARD DE INSTITUÇÕES PARTICIPANTES  -->
								<div class="panel panel-primary">
									<div class="panel-heading">
										<div class="row">
											<div class="col-xs-3">
												<img alt="institutions"
													src="/datacenterframework/resources/icons/college-64.png" />
											</div>
											<div class="col-xs-9 text-right">
												<div class="huge">${fn:length(mission.institutions)}</div>
												<div>Instituições Participantes</div>
											</div>
										</div>
									</div>
									<c:forEach items="${mission.institutions}" var="i">
										<a href="${i.website}" target="_blank">
											<div class="panel-footer">
												<span class="pull-left">${i.name}</span><br /> <span
													class="pull-right"> </span>
												<div class="clearfix"></div>
											</div>
										</a>
									</c:forEach>
								</div>
							</div>
							</div>
<!--  COLUNA DE TELEMETRIAS -->
<h1 class="page-header"> <center> Telemetrias </center> </h1>
		<div class="row">							
						<div class="col-lg-12">
							<div class="panel panel-primary">
							<form:form servletRelativeAction="/mission/search" commandName="mission">
								<div class="panel-heading">Telemetrias
									<input type="button"  value="Upload de Telemetria" 
									class="addTelemetry btn btn-primary" style="margin-left: 20px"/>
								</div>
								<div class="panel-body">
								<div class="row">
								<div class="col-lg-4">
									<div class="row">
										<div class="col-lg-6">
										<label>Início</label>										
										<input name="startDate" type="date" class="form-control inputForm" id="begin-date" />
										</div>
										<div class="col-lg-6">
										<label>Fim</label>
										<input name="endDate" type="date" class="form-control inputForm"  id="end-date" />
										</div>
										
									</div>									
								</div>
								<div class="col-lg-3">
									<label>Arquivo</label>
									<select  name="typeTelemetry" id="typeTelemetry"  
										class="form-control inputForm" >
										<c:forEach var="c" items="${fileTypes}">
											<option value="${c}">${c}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-lg-3">
									<label>Expressão de Consulta</label>
									<input type="text"
												name="expressionQuery" id="expressionQuery" 
												class="form-control inputForm">
								<!-- <button onclick="telemetrySearch()" id="search" name="search">PESQUISAR </button>  -->
								</div>								
								<div class="col-lg-2">
									<br>
									<input type="submit"  value="Pesquisar" class="btn btn-primary" />
								</div>
								</div>
								</div>
								<div class="table-responsive " style="overflow-x:auto; overflow: auto; max-height: 300px" >
									<table class="table table-striped table-hover table-condensed table-bordered table-sm" style="max-height: 300px">
									<thead class="thead-inverse">
									<tr>									
										<c:forEach var="c" items="${telemetry.getAllColumns()}" varStatus="i">
											<th class="head">${c}</th>
										</c:forEach>
									</tr>
									</thead>
									<c:forEach var="row" items="${telemetry.data}"  >
											<tr>
												<c:forEach var="c" items="${telemetry.getAllColumns()}" varStatus="i">
															<td <c:if test="${i.index eq 0 }">class="col-md-1"</c:if> >${row.getColumnValue(c)}</td>
												</c:forEach>
											</tr>
										</c:forEach>
									</table>
								</div>
							</form:form>
							</div>
						</div>
						</div>		
							
					</div>
<!--  Fim da página-->
						
					</div>
				</div>
		

<!--  UPLOAD DE TELEMETRIAS -->
<div id="dialog-telemetry" class="dialog-telemetry" title="Upload de Telemetria" style="overflow-x: hidden;">
		<p class="validateTips">Upload de Telemetria</p>
		<form>
			<fieldset>
			<div class="row">				
					<div class="form-group col-md-12">
						<label> Tipo de Arquivo</label>
					</div>					
			</div>
			<div class="row">				
					<div class="form-group col-md-12">
					<input type="text"
					name="file-type" id="file-type" 
					class="form-control inputForm">
			</div>					
			</div>
			<div class="row">
				<div class="form-group col-md-12" id="file-upload-div">				
					<input name="file2" id="file2" type="file" />
				</div>
			</div>
			<input type="hidden" id="collections" name="collections"  value="${collections}"/>	
			<input type="hidden" id="mission-code" name="mission-code"  value="${mission.code}"/>
				<!-- Allow form submission with keyboard without duplicating the dialog button -->
				<input type="submit" tabindex="-1"
					style="position: absolute; top: -1000px">
			</fieldset>
		</form>
	</div>

		<div class="alert alert-info success-message" role="alert" id="success-alert" >			 	
  				<center> <h4>Telemetria importada com sucesso</h4> </center> 
		</div>

<%@include file="../predefine/footer.jsp" %>
</body>
</html>