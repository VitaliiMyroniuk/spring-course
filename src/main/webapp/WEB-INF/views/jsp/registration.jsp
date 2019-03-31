<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<spring:url var="registerUrl" value="/register" htmlEscape="true"/>

<tags:page>
    <jsp:body>
        <form:form action="${registerUrl}" method="post" modelAttribute="userForm">
            <table>
                <tr>
                    <td colspan="2">
                        <form:label path="email">Email</form:label>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <form:input path="email"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <form:label path="name">Name</form:label>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <form:input path="name"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <form:label path="birthday">Birthday</form:label>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <form:input path="birthday"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <input type="submit" value="Submit"/>
                    </td>
                </tr>
            </table>
        </form:form>

        Register more users by uploading JSON file:
        <form:form action="/upload-users" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>
                        <input type="file" name="file"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Upload"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </jsp:body>
</tags:page>
