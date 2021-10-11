<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: golnoosh
  Date: 9/28/2021
  Time: 1:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
<style>
    div.contain {
        border-radius: 40px;
        background-color: #f2f2f2;
        padding: 20px;
        display: block;
        margin-left: auto;
        margin-right: auto;
        width: 85%;

    }
    input[type=text], select {
        width: 60%;
        padding: 12px 20px;
        margin: 4px 0;
        border: 1px solid #ccc;
        border-radius: 20px;
        box-sizing: border-box;
    }
    label{
        color: black;
        float: left;
        width: 7em;
    }
    input[type=submit] {
        width: 40%;
        background-color: #04AA6D;
        color: snow;
        padding: 14px 20px;
        margin: 2px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    input[type=button] {
        width: 40%;
        background-color: #04AA6D;
        color: snow;
        padding: 14px 20px;
        margin: 2px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    .center{
        margin-left: auto;
        margin-right: auto;
        width: 50%;
    }
</style>

</head>
<body>
<header>
    <jsp:include page="header.html"/>
</header>
<div style="color: RED">${requestScope.errorMessage}</div>
<%--    </c:if>--%>
<br/><br/>
<div class="contain">
    <c:set var="asset" value="${requestScope.asset}"/>
    <form  action="ServletEditAsset" method="post">
        <input type="hidden" name="assetid" value="${asset.assetId}">
        <label for="Color" >Color:</label>
        <input type="text" id="Color" name="Color" value="${asset.color}" placeholder="enter a Color...">
        <br/><br/>
        <label for="Title" >Title:</label>
        <input type="text" id="Title" name="Title" value="${asset.title}" placeholder="enter a Title...">
        <br/><br/>
        <br/><br/>

        <div class="center">
            <input type="submit" name="Submit" value="register">
            <input type="button" value="cancel" onclick="goBack()">
        </div>
        <br/><br/>
    </form>
</div>
</body>
</html>
