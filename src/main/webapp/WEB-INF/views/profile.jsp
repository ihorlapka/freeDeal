<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link href="css/styleprofile.css" rel="stylesheet" type="text/css">
</head>
<body>
    <header>
        <h1>My ${user.username}  profile</h1>
        <div>
            <a href="/">
                <button><-Back to index</button>
            </a>
        </div>
        <br>
    </header>
    <img src="${user.profilepicture}" width="200px" height="200px">
    <div><strong>Change your profile picture</strong></div>
    <form:form modelAttribute="user" method="post" action="/updateUser">
        <div>
            <form:label path="firstname"><pre>First Name: </pre></form:label>
            <form:input path="firstname" placeholder="${user.firstname}"/>
        </div>
        <div>
            <form:label path="secondname"><pre>Second Name:</pre></form:label>
            <form:input path="secondname" value="${user.secondname}"/>
        </div>
        <div>
            <form:label path="phone"><pre>Phone:      </pre></form:label>
            <form:input path="phone" value="${user.phone}"/>
        </div>
        <div>
            <form:label path="age"><pre>Age:        </pre></form:label>
            <form:input path="age" value="${user.age}"/>
        </div>
        <div>
            <form:label path="profession"><pre>Profession: </pre></form:label>
            <form:input path="profession" value="${user.profession}"/>
        </div>
        <div>
            <form:label path="hobbies"><pre>Hobbies:    </pre></form:label>
            <form:input path="hobbies" value="${user.hobbies}"/>
        </div>
        <div>
            <form:label path="email"><pre>Email:      </pre></form:label>
            <form:input path="email" value="${user.email}"/>
        </div>
        <div>
            <form:label path="password"><pre>Password:   </pre></form:label>
            <form:input path="password" value="${user.password}"/>
        </div>
        <br>
        <div>
            <input type="submit" value="Update">
        </div>
    </form:form>

    <section class="multipart">
        <div c:if="${message}">${message}</div>
        <div>
            <form method="post" action="/upload" enctype="multipart/form-data">
                <label for="file"><strong>Upload your file:</strong></label>
                <input type="file" id="file" name="file">
                <br><br>
                <input type="submit" value="Upload">
                <br>
            </form>
        </div>
        <div>
            <div>
                <strong>My files:</strong>
                <br>
            </div>
            <ul>
                <c:forEach items="${files}" var="file">
                    <li><a href="${file}">${file}</a></li>
                </c:forEach>
            </ul>
        </div>
    </section>
    <div>
        <table border="1" width="40%" cellpadding="2" class="table-profile">
            <div class="new-order-button">
                <a href="/orderPage">
                    <button>New Order</button>
                </a>
            </div>
            <h2><strong>My orders</strong></h2>
            <tr>
                <th>Picture</th>
                <th><a href="/profile?page=${param.get("page")}&sort=ordername">Ordername</a></th>
                <th><a href="/profile?page=${param.get("page")}&sort=payment">Payment</a></th>
                <th><a href="/profile?page=${param.get("page")}&sort=workersamount">Workers</a></th>
                <th><a href="/profile?page=${param.get("page")}&sort=dayamount">Days</a></th>
            </tr>
            <c:forEach items="${user.orders}" var="uo">
                <tr>
                    <td><img src="${uo.workpicture}" alt="Work picture" height="100" width="100"></td>
                    <td>${uo.ordername}
                        <br>
                        <a href="/orderPage?id=${uo.id}">
                            <button>edit</button>
                        </a>
                        <br>
                        <a href="/deleteOrder?id=${uo.id}">
                            <button>delete</button>
                        </a>
                    </td>
                    <td>${uo.payment}</td>
                    <td>${uo.workersamount}</td>
                    <td>${uo.dayamount}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div>
        <table border="1" width="25%" cellpadding="2" class="friends-profile">
            <h3>Friends</h3>
            <tr>
                <th>Photo</th>
                <th>Username</th>
            </tr>
            <c:forEach items="${user.friends}" var="f">
                <tr>
                    <td><img src="${f.profilepicture}" alt="friend" height="100" width="100"></td>
                    <td>${f.username}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>
