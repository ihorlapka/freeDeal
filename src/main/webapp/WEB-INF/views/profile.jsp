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
            <a href="/">Back to index</a>
        </div>
        <br>
    </header>
    <img src="${user.profilepicture}" width="100px" height="100px">
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
            <form:input path="age" value="${user.firstname}"/>
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

    <div c:if="${message}">${message}</div>
    <div>
        <form method="post" action="/upload" enctype="multipart/form-data">
            <label for="file">File:</label>
            <input type="file" id="file" name="file">
            <input type="submit" value="Upload">
        </form>
    </div>
    <div>
        <ul>
            <c:forEach items="${files}" var="file">
                <li><a href="${file}"${file}></a></li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>
