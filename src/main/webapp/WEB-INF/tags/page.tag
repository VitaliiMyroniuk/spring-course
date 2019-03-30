<%@ tag language="java" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/main.css"/>
    </head>
    <body>
        <tags:header/>
        <div class="content">
            <jsp:doBody/>
        </div>
    </body>
</html>
