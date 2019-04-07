<%@ tag language="java" pageEncoding="UTF-8" %>

<div class="header">
    <div class="tabs">
        <button class="tabs__home" onclick="window.location='home';">Home</button>
        <button class="tabs__users" onclick="window.location='users';">Users</button>
        <button class="tabs__sign-up" onclick="window.location='register';">Sign up</button>
        <button class="tabs__logout">Logout</button>
    </div>
</div>

<form class="logout-form" action="#" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
