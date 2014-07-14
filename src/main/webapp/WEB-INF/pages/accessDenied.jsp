<!DOCTYPE html>
<html lang="en">
<%@ include file="/WEB-INF/pages/common/headerInclude.jspf" %>
<head>
	<meta charset="utf-8"/>
	<title><spring:message code="title.reportingManager"/></title>

	<!-- Mobile viewport optimisation -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<base href="<c:out value='${pageContext.request.scheme}' />://<c:out value='${pageContext.request.serverName}' />:<c:out value='${pageContext.request.serverPort}' /><c:out value='${pageContext.request.contextPath}' />/" />	
	<%@ include file="/WEB-INF/pages/common/headerCss.jspf" %>
	<%@ include file="/WEB-INF/pages/common/headerJs.jspf" %>  
	<script type="text/javascript" src="<c:url value="resources/js/pages/login.js" />"></script>
</head>
<body>
<div class="ym-wrapper">
	<div class="ym-wbox">	
		<header>
			<h2 id="reportingHeader"><spring:message code="title.reportingManager"/></h2>
		</header>
		<main>
			 
			<c:if test="${not empty error}">
				<div class="box error">
					<spring:message code="error.login"/><c:out value="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}" />
				</div>
			</c:if>
			 		
			<div class="ym-column linearize-level-1">				
				<div class="ym-col3">
					<div class="ym-wbox">
						<section class="ym-grid linearize-level-2">
							<article class="ym-g80 ym-gl">
		 						<p>403. <span class="dimmed">Thats an error.</span></p>	
		 						<p class="dimmed">Your client does not have permission to access this content from the server.</p>
							</article>
						</section>
					</div>
				</div>
			</div>
		</main>
	</div>
</div>

<!-- full skip link functionality in webkit browsers -->
<script src="resources/css/yaml/yaml/core/js/yaml-focusfix.js"></script>
</body>
</html>
