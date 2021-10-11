<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<table border="4" id="myTable">
    <tr>
        <th>employeeid</th>
    </tr>
    <tr>
        <c:forEach var = "i" begin = "1" end = "5">
    <tr>
        <td> <c:out value = "${i}"/></td>
        <td><button onclick="myFunction()">delete</button></td>
    </tr>
    </c:forEach>
</table>
<p id="demo"></p>

<script>
    function myFunction() {
        var txt;
        if (confirm("DO YOU WANT TO DELETE?!")) {
            deleterow();
            txt = "successful!";
        } else {
            txt = "You pressed Cancel!";
        }
        document.getElementById("demo").innerHTML = txt;
    }
    function deleterow(){
        document.getElementById("myTable").deleteRow(2);
    }
</script>

</body>
</html>