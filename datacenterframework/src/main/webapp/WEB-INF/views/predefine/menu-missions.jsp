
	 <!-- Sidebar -->
        <div id="sidebar-wrapper" style="background: white;">
            <ul class="sidebar-nav">
			<li> <h2> <span> Missões</span> </h3></li>
            <c:forEach items="#{list}" var="mission">                
                <li id="${mission.id}" >
                    <a href='<c:url value="/mission/profile/${mission.name}" />' >${mission.name}</a>
                </li>  
			</c:forEach>              
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->
