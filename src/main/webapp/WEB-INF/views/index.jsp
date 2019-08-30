<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FreeDeal</title>
    <link href="css/styleindex.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/slick.css"/>
</head>
<body>
    <header class="header">
        <h1 class="logo"><i><strong>Free Deal</strong></i></h1>
        <div class="header-banner">
            <img src="pictures/fd_header1.jpg" alt="banner" width="1366">
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
            <button>New Order</button>
        </a>
    </div>
<%--    <div class="slider">--%>
<%--        <div><img src="slide/m1.jpg" width="500" height="350"></div>--%>
<%--        <div><img src="slide/m2.jpg" width="500" height="350"></div>--%>
<%--        <div><img src="slide/m3.jpg" width="500" height="350"></div>--%>
<%--        <div><img src="slide/m4.jpg" width="500" height="350"></div>--%>
<%--        <div><img src="slide/m5.jpg" width="500" height="350"></div>--%>
<%--        <div><img src="slide/m6.jpg" width="500" height="350"></div>--%>
<%--    </div>--%>
    <div class="tables-position">
        <table border="1" width="20%" cellpadding="2" class="table-users">
            <tr>
                <th>Picture</th>
                <th><a href="/index?userPage=${param.get("userPage")}&userSort=username">Username</a> </th>
                <th><a href="/index?userPage=${param.get("userPage")}&userSort=email">Email</a> </th>
            </tr>
        <c:forEach items="${users}" var="u">
            <tr>
                <td><img src="${u.profilepicture}" alt="Profile Picture" height="100" width="100"></td>
                <td>${u.username}
                    <form method="post" action="/addFriend/${u.id}">
                        <button type="submit">Add friend</button>
                    </form>
<%--                    <a href="/addFriend/${u.id}">--%>
<%--                        <button>+ friend</button>--%>
<%--                    </a>--%>
                </td>
                <td>${u.email}</td>
            </tr>
        </c:forEach>
        </table>
        <div class="userPage">
            <a href="/index?userPage=1&userSort=${param.get("userSort")}">1</a>
            <a href="/index?userPage=2&userSort=${param.get("userSort")}">2</a>
            <a href="/index?userPage=3&userSort=${param.get("userSort")}">3</a>
            <a href="/index?userPage=4&userSort=${param.get("userSort")}">4</a>
            <a href="/index?userPage=5&userSort=${param.get("userSort")}">5</a>
        </div>
        <table border="1" width="50%" cellpadding="2" class="table-orders">
            <tr>
                <th>Picture</th>
                <th><a href="/index?orderPage=${param.get("orderPage")}&orderSort=ordername">Ordername</a> </th>
                <th><a href="/index?orderPage=${param.get("orderPage")}&orderSort=payment">Payment</a> </th>
                <th><a href="/index?orderPage=${param.get("orderPage")}&orderSort=dayamount">Days</a> </th>
                <th><a href="/index?orderPage=${param.get("orderPage")}&orderSort=workersamount">Workers</a> </th>
            </tr>
            <c:forEach items="${orders}" var="o">
                <tr>
                    <td><img src="${o.workpicture}" alt="Work Picture" height="100" width="100"></td>
                    <td>${o.ordername}
                        <a href="/executeOrder/${o.id}">
                            <button>Execute</button>
                        </a>
                    </td>
                    <td>${o.payment}</td>
                    <td>${o.dayamount}</td>
                    <td>${o.workersamount}</td>
                </tr>
            </c:forEach>
        </table>
        <div class="orderPage" >
            <a href="/index?orderPage=1&orderSort=${param.get("orderSort")}">1</a>
            <a href="/index?orderPage=2&orderSort=${param.get("orderSort")}">2</a>
            <a href="/index?orderPage=3&orderSort=${param.get("orderSort")}">3</a>
            <a href="/index?orderPage=4&orderSort=${param.get("orderSort")}">4</a>
            <a href="/index?orderPage=5&orderSort=${param.get("orderSort")}">5</a>
        </div>
    </div>
    <div>
        <img src="${user.profilepicture}" width="150" height="150" alt="User Photo" class="avatar">
    </div>
</sec:authorize>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="javascript/slick.min.js"></script>
    <script src="javascript/indexJS.js"></script>
</body>
</html>
