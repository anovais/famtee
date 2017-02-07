<%@include file="predefine/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>
<%@include file="predefine/menu.jsp" %>
	<div class="container">
	<div class="conteudo">
		<ul>
			<li class="list-item-group"> <a href="<c:url value="/initAllModel"/>"> <h3>Inserir Definições</h3> </a> </li>
			
		</ul>
	
			<div>
			<h1>${title}</h1>
			<div class="alert-success"> ${message} </div>
			<div class="card card-block">
				<ul class="list-group" style="width:600px;" align="center">
					<c:forEach items="#{list}" var="obj">
						<li class="list-group-item">
						<!-- <img src="<c:url value="/${tec.photoPath}"/>" /> <label> -->
						<a href="<c:url value="/${type}/profile?id=${obj.id}"/>">
									${obj.description} 
						</a>
						</label></li>
					</c:forEach>
				</ul>
			</div>
			
		</div>
		</div>
	</div>
</body>
</html>