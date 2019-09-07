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
                        <button type="submit">+ friend</button>
                    </form>
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
                <th>Status</th>
            </tr>
            <c:forEach items="${orders}" var="o">
                <tr>
                    <td><img src="${o.workpicture}" alt="Work Picture" height="100" width="100"></td>
                    <td>${o.ordername}
                        <form method="post" action="/executeOrder/${o.id}">
                            <button type="submit">Commit</button>
                        </form>
                    </td>
                    <td>${o.payment}</td>
                    <td>${o.dayamount}</td>
                    <td>${o.workersamount}</td>
                    <td>${o.status}</td>
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
        <div class="avatar-properties">
            <table border="1" width="25%" cellpadding="2">
                <tr>
                    <td><strong>Username:</strong></td>
                    <td><strong>${user.username}</strong></td>
                </tr>
                <tr>
                    <td><strong>Age:</strong></td>
                    <td><strong>${user.age}</strong></td>
                </tr>
                <tr>
                    <td><strong>Profession:</strong></td>
                    <td><strong>${user.profession}</strong></td>
                </tr>
                <tr>
                    <td><strong>Orders:</strong></td>
                    <td><strong>${user.ordersAmount()}</strong></td>
                </tr>
                <tr>
                    <td><strong>Friends:</strong></td>
                    <td><strong>${user.friendsAmount()}</strong></td>
                </tr>
            </table>
        </div>
    </div>
    <div id="main-content" class="container">
        <div class="row">
            <div class="col-md-6">
                <form class="form-inline">
                    <div class="form-group">
                        <label for="connect">WebSocket connection:</label>
                        <button id="connect" class="btn btn-default" type="submit">Connect</button>
                        <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">Disconnect
                        </button>
                    </div>
                </form>
            </div>
            <div class="col-md-6">
                <form class="form-inline">
                    <div class="form-group">
                        <label for="name">What would you like to write?</label>
                        <input type="text" id="name" class="form-control" placeholder="type here...">
                    </div>
                    <button id="send" class="btn btn-default" type="submit">Send</button>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table id="conversation" class="table table-striped">
                    <thead>
                    <tr>
                        <th>Chat</th>
                    </tr>
                    </thead>
                    <tbody id="greetings">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</sec:authorize>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
    <script src="javascript/ws.js"></script>
</body>
</html>
