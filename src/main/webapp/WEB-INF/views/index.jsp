<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FreeDeal</title>
    <link href="css/styleindex.css" rel="stylesheet" type="text/css">

</head>
<body class="back">
    <header>
        <h1 class="welcome back">Free Deal</h1>
    </header>

<sec:authorize access="isAnonymous()">
    <form action="/loginProcessing" method="post">
        <span id="error">${error}</span>
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
    </form>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <div class="welcome">
        <a href="/profile">My profile</a>
    </div>
    <div class="welcome">
        <a href="/logout">Logout</a>
    </div>
    <div class="welcome">
        <a href="/orderPage">
            <button>Order</button>
        </a>
    </div>
        <table border="1" width="30%" cellpadding="2" class="table-users">
            <tr>
                <th><a href="/index?page=${param.get("page")}&sort=username">Username</a> </th>
                <th><a href="/index?page=${param.get("page")}&sort=email">Email</a> </th>
                <th>Picture</th>
            </tr>
        <c:forEach items="${users}" var="u">
            <tr>
                <td>${u.username}</td>
                <td>${u.email}</td>
                <td>${u.profilepicture}</td>
            </tr>
        </c:forEach>
        </table>
        <table border="1" width="40%" cellpadding="2" class="table-orders">
            <tr>
                <th><a href="/index?page=${param.get("page")}&sort=ordername">Ordername</a> </th>
                <th><a href="/index?page=${param.get("page")}&sort=payment">Payment</a> </th>
                <th><a href="/index?page=${param.get("page")}&sort=dayamount">Days</a> </th>
                <th><a href="/index?page=${param.get("page")}&sort=workersamount">Workers</a> </th>
            </tr>
            <c:forEach items="${orders}" var="o">
                <tr>
                    <td>${o.ordername}</td>
                    <td>${o.payment}</td>
                    <td>${o.dayamount}</td>
                    <td>${o.workersamount}</td>
                </tr>
            </c:forEach>
        </table>
</sec:authorize>

<%--    <script src="css/javascript/indexJS.js"></script>--%>
</body>
</html>
