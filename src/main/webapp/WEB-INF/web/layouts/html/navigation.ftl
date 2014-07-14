<#assign home_url><@spring.url relativeUrl="${servletPath}/"/></#assign>
<#assign company_url><@spring.messageText code="company.url" text=companyUrl!"http://www.springsource.com"/></#assign>
<#assign company_name><@spring.messageText code="company.name" text=companyName!"SpringSource"/></#assign>
<#assign product_url><@spring.messageText code="product.url" text=productUrl!"http://static.springframework.org/spring-batch"/></#assign>
<#assign product_name><@spring.messageText code="product.name" text=productName!"Spring Batch"/></#assign>
<div id="primary-navigation">
	<div id="primary-left">
		<ul>
			<#list menuManager.menus as menu>
			<#assign menu_url><@spring.url relativeUrl="${menu.url}"/></#assign>
			<#if menu.label == "Home">
				<li><a href="${menu_url}/home">${menu.label}</a></li>
			<#else>
				<li><a href="${menu_url}">${menu.label}</a></li>
			</#if>
			</#list>
		</ul>
	</div>
	<div id="primary-right">
		<ul>
			<li><a href="${company_url}">${company_name}</a></li>
			<li><a href="${product_url}">${product_name}</a></li>
		</ul>
	</div>
</div><!-- /primary-navigation -->