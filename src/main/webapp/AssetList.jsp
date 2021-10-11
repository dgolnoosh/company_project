<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<header>
    <jsp:include page="header.html"/>
</header>
<main>
    <c:if test="${param.resultdelete == 1}">
        <div style="color: GREEN"><h2>Removal operation completed successfully.</h2></div>
    </c:if>
    <c:if test="${param.resultdelete == 0}">
        <div style="color: RED"><h2>Deletion failed. Please try again</h2></div>
    </c:if>
    <c:if test="${param.done == 1}">
        <div style="color: GREEN"><h2>done successfully</h2></div>
    </c:if>
    <c:if test="${param.done == 0}">
        <div style="color: RED"><h2>Unregister please try again</h2></div>
    </c:if>
<form action="ServletAssetList" method="get">
    <fieldset>
        <legend>Assets:</legend>
        <br/><br/>
        <h3 style="color: blue">name and family:</h3>
        <div style="color: blue">
<%--        <c:out value="${param.name}"/>--%>
<%--        <c:out value="${param.family}"/>--%>
    <c:out value="${requestScope.employeename}"/>
    <c:out value="${requestScope.employeefamily}"/>
        </div>
        <br/><br/>
        <input type="button" value="AddAsset" onclick="location.href='ServletAddAsset?employeeid=${param.employeeid}'">
<%--        <input type="hidden" name="eid" value="${requestScope.employeeid}">--%>
        <input type="button" value="back" onclick="goBack()">
        <input type="hidden" name="eid" value="${param.employeeid}">
    </fieldset>
</form>
</div>
<div>
    </br></br>
    <h2>List of assets</h2>
    <table border="4">
        <tr>
            <td><h3>AssetID</h3></td>
            <td><h3>Color</h3></td>
            <td><h3>Title</h3></td>
        </tr>
        <c:forEach items="${requestScope.list_asset}" var="p">
            <tr id="asset${p.assetId}">
                <td><c:out value="${p.assetId}"/></td>
                <td><c:out value="${p.color}"/></td>
                <td><c:out value="${p.title}"/></td>
                <td>
<%--                    <button onclick="location.href='ServletDeleteAsset?assetid=${p.assetId}'">Delete</button>--%>
                         <button onclick="loadDoc(${p.assetId})">Delete</button>
                </td>
<td>
    <button onclick="location.href='ServletEditAsset?assetid=${p.assetId}'">edit</button>
</td>
            </tr>
        </c:forEach>
    </table>
</div>
</main>
<script>
    function goBack() {
        location.href="employee";
    }
    function loadDoc(ID){
        f=confirm("are you sure?");
        if (f){
            removeByAjax(ID);
        }else {}
    }
    function removeByAjax(ID){
        const xhttp=new XMLHttpRequest();
        xhttp.onload=function (){
            document.getElementById("asset"+ID).style.display='none';
        }
        xhttp.open("GET","http://localhost:8080/company_war_exploded/ServletDeleteAsset?assetid="+ID);
        xhttp.send();
    }
</script>
</body>
</html>
