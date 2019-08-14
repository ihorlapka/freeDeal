<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link href="css/styleindex.css" rel="stylesheet" type="text/css">

</head>
<body>
    <h1 class="welcome">Welcome</h1>
    <form action="/loginProcessing" method="post">
        <span id="error">${error}</span>

<sec:authorize access="isAnonymous()">
    <div>
        <input type="text" name="username" id="username" placeholder="username">
    </div>
    <div>
        <input type="password" name="password" id="password" placeholder="password">
    </div>
    <div>
        <input type="submit" value="Login" class="login">
    </div>
    <div>
        <a href="/registration" class="login">Create an account</a>
    </div>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <div>
        <a href="/profile">My profile</a>
    </div>
    <div>
        <a href="/logout">Logout</a>
    </div>
    <div>
        <a href="/orderPage">
            <button>Order</button>
        </a>
    </div>
</sec:authorize>
    </form>
</body>
</html>
