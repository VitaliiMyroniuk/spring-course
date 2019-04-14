<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<spring:url var="refillAccountUrl" value="/refill-account" htmlEscape="true"/>

<tags:page>
    <jsp:body>
        <b>Email:</b> ${userEmail}
        <br>
        <b>Current balance:</b> ${balance}
        <br><br>
        <form action="${refillAccountUrl}" method="post">
            <table>
                <tr>
                    <td><label for="amount">Amount</label></td>
                    <td><input id="amount" type="number" step="0.01" name="amount"/></td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="userEmail" value="${userEmail}"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="submit" value="Refill"/>
                    </td>
                </tr>
            </table>
        </form>
    </jsp:body>
</tags:page>
