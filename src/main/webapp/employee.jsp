<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>employee</title>
    <style>
        .green {
            color: GREEN;
        }

        .red {
            color: RED;
        }
        .palevioletred {
            color: palevioletred;
        }
    </style>
</head>
<body>
<header>
    <jsp:include page="header.html"/>
</header>
<main>
    <div style="color: GREEN">${param.ResultDelete}</div>
    <p id="demo"></p>
    <c:if test="${param.resultdelete == 1}">
        <div style="color: GREEN"><h2>Removal operation completed successfully.</h2></div>
    </c:if>
    <c:if test="${param.resultdelete == 0}">
        <div style="color: RED"><h2>Deletion failed. Please try again</h2></div>
    </c:if>
    <c:if test="${param.resultdelete == 2}">
        <div style="color: BLUE"><h2>Can not remove amployees who have assets</h2></div>
    </c:if>

    <c:if test="${param.done == 1}">
        <div style="color: GREEN"><h2>done successfully</h2></div>
            </c:if>
    <c:if test="${param.done == 0}">
        <div style="color: RED"><h2>Unregister please try again</h2></div>
    </c:if>

    <div>

    <form action="employee" method="get">
        <fieldset>
            <legend>Employee filter:</legend>
            </br></br>
        <label for="Name">First name:</label>
        <input type="text" id="Name" name="Name" value="${param.Name}">
        <label for="Family">Last name:</label>
        <input type="text" id="Family" name="Family" value="${param.Family}">
        <br/><br/>
        <label for="Date1">data from:</label>
        <input type="text" id="Date1" name="Date1" value="${param.Date1}">
        <label for="Name"> to:</label>
        <input type="text" id="Date2" name="Date2" value="${param.Date2}">

            <br/><br/>
            <label for="Level">Level:</label>
            <select id="Level" name="Level">
                <option value="">select...</option>
            <c:forEach items="${requestScope.list_level}" var="l">
                <option value="${l.levelId}" ${l.levelId == param.Level ? "selected" : ""}><c:out value="${l.levelTitle}"/></option>
            </c:forEach>
            </select>
            <br/><br/>
            <input type="submit" value="Search">
            <input type="button" value="AddEmployee" onclick="location.href='ServletAddEmployee'">
    </fieldset>
    </form>
    </div>
<div>
</br></br>
    <table border="4">
        <tr>
            <td><h3>EmployeeID</h3></td>
            <td><h3>Name</h3></td>
            <td><h3>Family</h3></td>
            <td><h3>HIRE DATE</h3></td>
            <td><h3>levelTitle</h3></td>
        </tr>
        <c:forEach items="${requestScope.list_employee}" var="p">
            <tr id="employee${p.employeeId}">
                <td><c:out value="${p.employeeId}"/></td>
                <td><c:out value="${p.fName}"/></td>
                <td><c:out value="${p.lName}"/></td>
                <td><c:out value="${fn:split(p.hireDate,' ')[0]}"/></td>
                <td><c:out value="${p.levelDTO.levelTitle}"/></td>
                <td>
                    <input type="button" name="Edit" value="edit"  onclick="location.href='ServletEditEmployee?employeeid=${p.employeeId}'">
<%--                    <input type="button" name="Edit" value="edit"  onclick="location.href='ServletEditEmployee?employeeid=${p.employeeId}&Name=${p.fName}&Family=${p.lName}&Hire_date= ${p.hireDate}&Level=${p.levelDTO.levelId}'">--%>
                </td>
                <td>
<%--                    <button type="button"  onclick="loadDoc(${p.employeeId})">Delete</button>--%>
<%--                    <button type="button" id="ajaxBtn" >Delete</button>--%>
                    <input type="button" name="delete" value="delete"  onclick="loadDoc(${p.employeeId})">
                </td>
                <td>
                    <button onclick="location.href='ServletAssetList?employeeid=${p.employeeId}'">Assets</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</main>
<footer></footer>

<meta name="viewport" content="width=device-width" />
<script type="text/javascript"
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">
</script>
<script type="text/javascript">
    function loadDoc(ID){
            f=confirm("are you sure?");
            if (f){
                removeByAjax(ID);
            }else {}
       }
        function removeByAjax(ID) {
            $.ajax({
                url: "ServletDeleteEmployee?employeeid=" + ID,
                cache: false,
                // success: function (text) {
                //     // $("p").innerHTML="";
                //     if (text === '1') {
                //         $("p").addClass("green")
                //         $("p").append("Removal operation completed successfully.");
                //         document.getElementById("employee"+ID).style.display='none';
                //         return;
                //     } else if (text === '0') {
                //         $("p").addClass("red")
                //         $("p").append("Deletion failed. Please try again");
                //         return;
                //     } else if (text === '2') {
                //         $("p").addClass("palevioletred")
                //         $("p").append("Can not remove amployees who have assets");
                //         return;
                //     }
                // }
                success: function (text) {
                    // $("p").innerHTML="";
                    if (text === '1') {
                        $("p").addClass("green")
                        document.getElementById("employee"+ID).style.display='none';
                        $("p").append("Removal operation completed successfully.");
                        setTimeout(function(){
                            //  $("p").innerHTML="";
                            $("p").text("");
                        } , 3000);
                        return;
                    } else if (text === '0') {
                        $("p").addClass("red")
                        $("p").append("Deletion failed. Please try again");
                        setTimeout(function(){
                            //  $("p").innerHTML="";
                            $("p").text("");
                        } , 3000);
                        return;
                    } else if (text === '2') {
                        $("p").addClass("palevioletred")
                        $("p").append("Can not remove amployees who have assets");
                         setTimeout(function(){
                        //  $("p").innerHTML="";
                        $("p").text("");
                         } , 3000);
                        return;
                    }
                }
                // timeout: 3000

            });
        }
                    </script>


                    <%--<script>--%>
<%--    function loadDoc(ID){--%>
<%--        f=confirm("are you sure?");--%>
<%--        if (f){--%>
<%--            removeByAjax(ID);--%>
<%--        }else {}--%>
<%--    }--%>
<%--    function removeByAjax(ID){--%>
<%--        const xhttp=new XMLHttpRequest();--%>
<%--        xhttp.onload=function (){--%>
<%--            document.getElementById("employee"+ID).style.display='none';--%>
<%--        }--%>
<%--        xhttp.open("GET","http://localhost:8080/company_war_exploded/ServletDeleteEmployee?employeeid="+ID);--%>
<%--        xhttp.send();--%>
<%--    }--%>
<%--</script>--%>
</body>
</html>