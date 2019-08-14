<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
    <h1>Order: ${ordername}</h1>
    <form:form action="/order" method="post" modelAttribute="orderDTO">
        <div>
            <form:input path="ordername" placeholder="Oder Name"/>
        </div>
        <div>
            <form:input path="payment" placeholder="Payment"/>
        </div>
        <div>
            <form:input path="workersamount" placeholder="Warkers amount required"/>
        </div>
        <div>
            <form:input path="dayamount" placeholder="Day Amount"/>
        </div>
        <div>
            <input type="submit" value=Register>
        </div>
        <div>
            <a href="/index">Back to main page</a>
        </div>
    </form:form>
</body>
</html>
