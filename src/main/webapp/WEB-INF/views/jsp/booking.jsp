<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<spring:url var="bookingUrl" value="/booking" htmlEscape="true"/>

<tags:page>
    <jsp:body>
        <form:form action="${bookingUrl}?${_csrf.parameterName}=${_csrf.token}" method="post" modelAttribute="bookingForm">
            <table>
                <tr>
                    <td colspan="2">
                        <form:label path="eventName">Event name</form:label>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <form:input path="eventName" readonly="true"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <form:label path="eventDateTime">Event date</form:label>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <form:input path="eventDateTime" readonly="true"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <form:label path="auditoriumName">Auditorium name</form:label>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <form:input path="auditoriumName" readonly="true"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <form:label path="userEmail">Email</form:label>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <form:input path="userEmail"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <form:label path="seats">Seats (example: 1,2,3). Seats in auditorium = ${seatsNumber}. VIP seats = ${vipSeats}</form:label>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <form:input path="seats"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Submit"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </jsp:body>
</tags:page>
