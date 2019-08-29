<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
    <link href="css/styleOrder.css" rel="stylesheet" type="text/css">
</head>
<body>
    <section class="order-new">
        <h1>New Order: ${ordername}</h1>
        <form:form action="/order" method="post" modelAttribute="order">
            <div>
                <form:label path="ordername"><pre>Ordername: </pre></form:label>
                <form:input path="ordername" placeholder="${order.ordername}"/>
            </div>
            <div>
                <form:label path="date"><pre>Date: </pre></form:label>
                <input type="date" name="date" value="${order.date}">
            </div>
            <div>
                <form:label path="payment"><pre>Payment: </pre></form:label>
                <form:input path="payment" placeholder="${order.payment}"/>
            </div>
            <div>
                <form:label path="workersamount"><pre>Workers amount required: </pre></form:label>
                <form:input path="workersamount" placeholder="${order.workersamount}"/>
            </div>
            <div>
                <form:label path="dayamount"><pre>Days amount required: </pre></form:label>
                <form:input path="dayamount" placeholder="${order.dayamount}"/>
                <form:input path="id" value="${order.id}" type="hidden"/>
            </div>
            <div>
                <form:label path="description"><pre>Description: </pre></form:label>
                <form:input path="description" placeholder="${order.description}"/>
            </div>
            <br>
            <div>
                <form:label path="workpicture"><pre>Work Picture:</pre></form:label>
                <img src="${order.workpicture}" alt="Work Picture" width="300"/>
                <form:input path="workpicture" value="${order.workpicture}" type="hidden"/>
            </div>
            <br>
            <div>
                <input type="submit" value=Register>
            </div>
            <br>
            <div>
                <a href="/index">Back to main page</a>
            </div>
        </form:form>
    </section>
</body>
</html>
