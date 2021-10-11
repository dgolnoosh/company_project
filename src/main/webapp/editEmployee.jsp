<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>edit Employee</title>
    <div><jsp:include page="header.html"/></div>
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
<main>
    <%--    <c:if test="${requestScope.errorMessage != null}">--%>
    <div style="color: RED">${requestScope.errorMessage}</div>
    <%--    </c:if>--%>
    <br/><br/>
    <div class="contain">

        <form  action="ServletEditEmployee" method="post">
<%--            <input type="text" id="Name" name="id" value="${param.Name}" placeholder="enter a first name...">--%>
            <input type="hidden" name="employeeid" value="${param.employeeid}">
            <label for="Name" >first name:</label>
            <input type="text" id="Name" name="Name" value="${requestScope.employee.fName}" placeholder="enter a first name...">
            <br/><br/>

            <label for="Family" >last name:</label>
            <input type="text" id="Family" name="Family" value="${requestScope.employee.lName}" placeholder="enter a last name...">
            <br/><br/>

            <label for="Hire_date" >Hire_date:</label>
<%--           <input type="text" id="Hire_date" name="Hire_date" value="${requestScope.employee.hireDate}" placeholder="yyyy-mm-dd">&ndash;%&gt;--%>
        <input type="text" id="Hire_date" name="Hire_date" value="${fn:split(requestScope.employee.hireDate,' ')[0]}" placeholder="yyyy-mm-dd">

    <br/><br/>

            <label for="Level" >Level:</label>
            <select id="Level" name="Level">
                <option value="">select...</option>
                <c:forEach items="${requestScope.list_level}" var="l">
                    <option value="${l.levelId}" ${l.levelId == requestScope.employee.levelDTO.levelId ? "selected" : ""}><c:out value="${l.levelTitle}" /></option>
                </c:forEach>
            </select>
            <br/><br/>

            <div class="center">
                <input type="submit" name="Submit" value="register">
                <input type="button" value="cancel" onclick="goBack()">
            </div>
            <br/><br/>
        </form>

    </div>
</main>
</body>
</html>
