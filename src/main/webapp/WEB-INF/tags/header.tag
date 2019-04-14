<%@ tag language="java" pageEncoding="UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="logoutUrl" value="/logout" htmlEscape="true"/>

<div class="header">
    <div class="tabs">
        <button class="tabs__home" onclick="window.location='home';">Home</button>
        <button class="tabs__events" onclick="window.location='events';">Events</button>
        <button class="tabs__users" onclick="window.location='users';">Users</button>
        <button class="tabs__sign-up" onclick="window.location='register';">Sign up</button>
        <button class="tabs__logout" type="submit" form="logout-form">Logout</button>
    </div>
</div>

<form id="logout-form" class="logout-form" action="${logoutUrl}" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
