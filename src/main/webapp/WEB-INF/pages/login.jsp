  <%@ include file="/WEB-INF/pages/common/headerLogin.jspf" %>
  <body>
    <!-- Docs page layout -->
    <div class="bs-docs-header" id="content">
      <div class="container">
        <h1>Moo Money</h1>
        <p>Helping you keep track of your finanaces by storing receipts and forecasting expenditure.</p>
      </div>
    </div>

    <div class="container">
      <c:url value="${pageContext.request.contextPath}/j_spring_security_check" var="loginUrl" />						    
      <form:form class="form-signin" role="form" name="loginForm" commandName="loginForm" action="${loginUrl}" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>        
        <form:input path="j_username" class="form-control" placeholder="Username" maxlength="30"/>		  
        <form:input path="j_password" class="form-control" type="password" placeholder="Password" maxlength="30"/>                
   		<form:select path="project" class="form-control">
			<form:options items="${projects}" />
		</form:select>					
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> Remember me
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form:form>
		<c:choose>
		    <c:when test="${not empty error}">
				<div class="login-error">
					<strong>Login error! </strong><c:out value="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}" />  				  
				</div> 
		    </c:when>
		    <c:when test="${not empty sessionManagementErrorMessage}">
				<div class="login-error">  
				  <strong>Login error! </strong><c:out value="${sessionManagementErrorMessage}" />  
				</div> 
		    </c:when>
		    <c:otherwise>
				<div></div>	
		    </c:otherwise>
		</c:choose>			 	
    </div> <!-- /container -->

  </body>
</html>
