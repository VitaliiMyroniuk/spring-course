<!doctype html>

<html lang="en">
    <head>
        <title>Users</title>
    </head>
    <body>
        <a href="/home">Home page</a>
        <br><br>
        <form action="/users-by-name" method="GET">
            <table>
                <tr>
                    <td>User name</td>
                    <td>
                        <input type="text" name="name"/>
                    </td>
                    <td>
                        <input type="submit" value="Search"/>
                    </td>
                </tr>
            </table>
        </form>
        <form action="/user-by-email" method="GET">
            <table>
                <tr>
                    <td>User email</td>
                    <td>
                        <input type="text" name="email"/>
                    </td>
                    <td>
                        <input type="submit" value="Search"/>
                    </td>
                </tr>
            </table>
        </form>
        <br>
        <table border="1">
            <tr>
                <th width="150" align="left">User name</th>
                <th width="150" align="left">User email</th>
                <th width="150" align="left">Birthday date</th>
            </tr>
            <#if users??>
                <#list users as user>
                    <tr>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <td>${user.birthday}</td>
                    </tr>
                </#list>
            </#if>
        </table>
    </body>
</html>
