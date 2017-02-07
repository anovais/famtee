<%@include file="predefine/header.jsp" %>
<title>Lista dos instrumentos</title>
</head>
<body>
<%@include file="predefine/menu.jsp" %>
	<div class="container">
		<div class="conteudo" align="center">
			<h1>${title}</h1>
			<div class="card card-block">
				<ul class="list-group" style="width:600px;" align="center">
					<c:forEach items="#{list}" var="obj">
						<li class="list-group-item">
						<!-- <img src="<c:url value="/${tec.photoPath}"/>" /> <label> -->
						<a href="<c:url value="/${type}/profile/${obj.code}"/>">
									${obj.name} 
						</a>
						</label></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>