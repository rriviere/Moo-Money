<!DOCTYPE html>
<html lang="en">
<%@ include file="/WEB-INF/pages/common/headerInclude.jspf" %>
<head>
	<meta charset="utf-8"/>
	<title><spring:message code="title.reportingManager"/></title>
	<base href="<c:out value='${pageContext.request.scheme}' />://<c:out value='${pageContext.request.serverName}' />:<c:out value='${pageContext.request.serverPort}' /><c:out value='${pageContext.request.contextPath}' />/" />	
	<%@ include file="/WEB-INF/pages/common/headerCss.jspf" %>
	<%@ include file="/WEB-INF/pages/common/headerJs.jspf" %>  	
</head>
<body>
<div class="ym-wrapper">
	<div class="ym-wbox">	
		<main>			 		
			<div class="ym-column linearize-level-1">				
				<div class="ym-col3">
					<div class="ym-wbox">
						<section class="ym-grid linearize-level-2">
							<article class="ym-g80 ym-gl">
		 						<p class="dimmed">Thats an error.</p>
		 						<p>	
		 							<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>		 																     								
		 						</p>
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
