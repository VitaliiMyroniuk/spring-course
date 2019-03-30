<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<spring:url var="bookingUrl" value="/booking" htmlEscape="true"/>

<tags:page>
    <jsp:body>
        List of events:

        <table border="1">
            <tr>
                <th width="100" align="left">Name</th>
                <th width="100" align="left">Rate</th>
                <th width="100" align="left">Price</th>
                <th width="150" align="left">Date</th>
                <th width="100" align="left">Auditorium</th>
                <th width="100" align="left">Book ticket</th>
            </tr>
            <c:forEach var="event" items="${events}">
                <tr>
                    <td>${event.name}</td>
                    <td>${event.rate}</td>
                    <td>${event.basePrice}</td>
                    <td>${event.dateTime}</td>
                    <td>${event.auditorium.name}</td>
                    <td>
                        <a href="${bookingUrl}?eventName=${event.name}&eventDateTime=${event.dateTime}&auditoriumName=${event.auditorium.name}">Book ticket</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>
</tags:page>
