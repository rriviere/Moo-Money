  <%@ include file="/WEB-INF/pages/common/headerDashboard.jspf" %>
  <body>

    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Dashboard</a></li>
            <li><a href="#">Settings</a></li>
            <li><a href="#">Profile</a></li>
            <li><a href="<%=request.getContextPath()%>/j_spring_security_logout"><spring:message code="button.logout"/></a></li>
            
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </div>

	<form:form id="internetLoginForm" 
		action="https://ibanking.bankofmelbourne.com.au/ibank/loginPage.action" 
		target="internetBankingFrame" 
		method="post">								    
	    
	    <input type="hidden" name="user_name" value="${username}" />
				<input type="hidden" name="password" value="${credentials}" />
													
    </form:form>					  
	<!-- =======================	-->
	<!--    	iframe				-->
	<!-- =======================	-->	
    <div>			
	    <iframe id="internetBankingFrame" name="internetBankingFrame" class="internetBankingFrame"></iframe>			    										    																	    	
    </div>				  

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="resources/css/bootstrap-3.1.1-dist/js/bootstrap.min.js"></script>
    <script src="resources/css/bootstrap-3.1.1-dist/js/docs.min.js"></script>
  </body>
</html>
