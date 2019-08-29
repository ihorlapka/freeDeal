<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link href="css/styleregistration.css" rel="stylesheet" type="text/css">
</head>
<body>
    <h1>Your Registration:</h1>
    <form:form action="/register" method="post" modelAttribute="userDTO">
        <div>
            <span class="error">${userDTO.alreadyExistsError}</span>
        </div>
        <br>
        <div>
            <form:input path="username" placeholder="Username"/>
            <span class="error">${userDTO.invalidUsername}</span>
        </div>
        <br>
        <div>
            <form:input path="email" placeholder="Email"/>
            <span class="error">${userDTO.invalidEmail}</span>
        </div>
        <br>
        <div>
            <form:password path="password" placeholder="Password"/>
            <span class="error">${userDTO.invalidPassword}</span>
        </div>
        <br>
        <div>
            <form:password path="repeatPassword" placeholder="Repeat Password"/>
            <span class="error">${userDTO.passwordsDoNotMatch}</span>
        </div>
        <br>
        <div>
            <input type="submit" value="Register">
        </div>
        <br>
        <div>
            <a href="/index">Back to home page</a>
        </div>

    </form:form>
</body>
</html>
