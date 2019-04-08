<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<spring:url var="loginUrl" value="/login" htmlEscape="true"/>

<tags:page>
    <jsp:body>
        <h2>Spring Security Custom Login Form</h2>

        <c:if test="${not empty loginErrorMsg}">
            <div class="login-error-message">${loginErrorMsg}</div>
        </c:if>
        <c:if test="${not empty logoutMsg}">
            <div class="logout-message">${logoutMsg}</div>
        </c:if>

        <form action="${loginUrl}" method="post">
            <table>
                <tr>
                    <td><label for="email">Email</label></td>
                    <td><input id="email" type="text" name="email"/></td>
                </tr>
                <tr>
                    <td><label for="password">Password</label></td>
                    <td><input id="password" type="password" name="password"/></td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="submit" value="Login"/>
                    </td>
                </tr>
            </table>
        </form>
    </jsp:body>
</tags:page>
