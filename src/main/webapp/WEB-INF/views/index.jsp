<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FreeDeal</title>
    <link href="css/styleindex.css" rel="stylesheet" type="text/css">
</head>
<body>
    <header class="header">
        <h1 class="logo"><i><strong>Free Deal</strong></i></h1>
        <div class="header-banner">
            <img src="css/pictures/fd_header1.jpg" alt="banner" width="1356">
        </div>

<sec:authorize access="isAnonymous()">
    <form action="/loginProcessing" method="post">
        <span id="error">${error}</span>
        <div class="username">
            <input type="text" name="username" placeholder="username">
        </div>
        <div class="password">
            <input type="password" name="password" placeholder="password">
        </div>
        <div class="login-button">
            <input type="submit" value="Login">
        </div>
        <div class="link-registration">
            <a href="/registration">Create an account</a>
        </div>
    </form>
</sec:authorize>
    </header>
<sec:authorize access="isAuthenticated()">
    <div class="profile-button">
        <a href="/profile">
            <button>My profile</button></a>
    </div>
    <div class="logout-button">
        <a href="/logout">
            <button>Logout</button>
        </a>
    </div>
    <div class="order-button">
        <a href="/orderPage">
            <button>Order</button>
        </a>
    </div>
    <div class="tables-position">
        <table border="1" width="20%" cellpadding="2" class="table-users">
            <tr>
                <th>Picture</th>
                <th><a href="/index?page=${param.get("page")}&sort=username">Username</a> </th>
                <th><a href="/index?page=${param.get("page")}&sort=email">Email</a> </th>
            </tr>
        <c:forEach items="${users}" var="u">
            <tr>
                <td><img src="${u.profilepicture}" alt="Profile Picture" height="100" width="100"></td>
                <td>${u.username}</td>
                <td>${u.email}</td>
            </tr>
        </c:forEach>
        </table>
        <table border="1" width="50%" cellpadding="2" class="table-orders">
            <tr>
                <th>Picture</th>
                <th><a href="/index?page=${param.get("page")}&sort=ordername">Ordername</a> </th>
                <th><a href="/index?page=${param.get("page")}&sort=payment">Payment</a> </th>
                <th><a href="/index?page=${param.get("page")}&sort=dayamount">Days</a> </th>
                <th><a href="/index?page=${param.get("page")}&sort=workersamount">Workers</a> </th>
            </tr>
            <c:forEach items="${orders}" var="o">
                <tr>
                    <td><img src="${o.workpicture}" alt="Work Picture" height="100" width="100"></td>
                    <td>${o.ordername}</td>
                    <td>${o.payment}</td>
                    <td>${o.dayamount}</td>
                    <td>${o.workersamount}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</sec:authorize>
<%--    <script src="css/javascript/indexJS.js"></script>--%>
</body>
</html>
