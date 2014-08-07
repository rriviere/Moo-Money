<%@ page language="java" contentType="text/json; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<json:array >
<c:forEach items="${tags}" var="tag" varStatus="counter">
	
	<json:object >
		<json:property name="id">
			<c:out value="${tag.id}" />
		</json:property>
		<json:property name="itemCode">
			<c:out value="${tag.itemCode}"  />
		</json:property>
		<json:property name="itemDescription">
		<c:out value="${tag.itemCode}-${tag.itemDescription}" escapeXml="false" />
		</json:property>
		<json:property name="buttonType">
			<c:out value="${tag.buttonType }" escapeXml="false" />
		</json:property>
		
	</json:object>
</c:forEach>
</json:array>

