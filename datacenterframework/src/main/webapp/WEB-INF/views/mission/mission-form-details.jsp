<%@include file="../predefine/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery/jquery-ui.css"/>">
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery.js"/>" > </script>
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-3.1.1.min.js"/>" > </script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js"/>" > </script>
<script type="text/javascript" src="<c:url value="/resources/js/pickList.js"/>" > </script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>" > </script>
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-ui.js"/>" > </script>
<title>Insert title here</title>
</head>
<body>
  <div id="wrapper">
<%@include file="../predefine/menu.jsp" %>

	<div class="container-fluid">
		<div class="conteudo">		
			<h1>Nova Missão Espacial</h1>			
			<div class="form-group" >
			<div class="row">
			<div class="col-md-6">
			
			<form:form method="POST" servletRelativeAction="/mission/form-details" commandName="details" class="form">
				<div class="form-group">
				
				<div class="row">
				</div>
				<div class="row">
					<div class='col-lg-5'>
						<label>Payloads</label>
					</div>
					<div class='col-lg-1'> 
                  	</div>
					<div class='col-lg-5'>
						<label>Instituições</label>
					</div>
				</div>
				 <div class='row'> 
                   <div class='col-lg-5'> 
                 	 <form:select class='form-control pickListSelect pickData' multiple="true" path="payloads" >
                 	 	<form:options items="${payloads}" itemLabel="name" itemValue="id" />
                 	 </form:select> 
                  </div> 
                  <div class='col-lg-1'> 
                  </div> 
                  	<form:select class='form-control pickListSelect pickData' multiple="true" path="institutions" >
                 	 	<form:options items="${institutions}" itemLabel="name" itemValue="id" />
                 	 </form:select>  
                 </div>
				<input type="submit" value="Adicionar" class="btn btn-primary" />
				
				</div>
			</form:form>
			
			</div>
			</div>
			</div>
		</div>
	</div>
	</div>
	
	
	   
      
      <script>

//          var val = {
//             01: {id: 01, text: 'Isis'},
//             02: {id: 02, text: 'Sophia'},
//             03: {id: 03, text: 'Alice'},
//             04: {id: 04, text: 'Isabella'},
//             05: {id: 05, text: 'Manuela'},
//             06: {id: 06, text: 'Laura'},
//             07: {id: 07, text: 'Luiza'},
//             08: {id: 08, text: 'Valentina'},
//             09: {id: 09, text: 'Giovanna'},
//             10: {id: 10, text: 'Maria Eduarda'},
//             11: {id: 11, text: 'Helena'},
//             12: {id: 12, text: 'Beatriz'},
//             13: {id: 13, text: 'Maria Luiza'},
//             14: {id: 14, text: 'Lara'},
//             15: {id: 15, text: 'Julia'}
//          };

//          var pick = $("#pickList").pickList({data: val});

//          $("#getSelected").click(function () {
//             console.log(pick.getValues());
//          });

      </script>
</body>
</html>