<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link href="css/styleprofile.css" rel="stylesheet" type="text/css">
</head>
<body>
    <header>
        <h1>My ${username}  profile</h1>
        <div>
            <a href="/">Back to index</a>
        </div>
    </header>
<%--    <form:form action="/myProfile" method="post" modelAttribute="user">--%>
<%--        <div>--%>
<%--            <form:label path="username">Username:</form:label>--%>
<%--            <span>${user.username}</span>--%>
<%--        </div>--%>
<%--    </form:form>--%>

</body>
</html>
