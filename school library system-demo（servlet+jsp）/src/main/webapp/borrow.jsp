<%--
  Created by IntelliJ IDEA.
  User: mayitbe
  Date: 2021/7/21
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>
<head>
    <script type="text/javascript">
         function next(){
             var page = parseInt(document.getElementById("currentPage").innerHTML);
             if(page==parseInt(document.getElementById("pages").innerHTML)){
                 return
             }else{
                 page++;
             }
             location.href="/book?method=findAllBorrow&page="+page;
         }

         function previous(){
             var page = parseInt(document.getElementById("currentPage").innerHTML);
             if(page==1){
                 return;
             }
             page--;
             location.href="/book?method=findAllBorrow&page="+page;
         }

         function first(){
             location.href="/book?method=findAllBorrow&page=1";
         }

         function last(){
             var pages =parseInt(document.getElementById("pages").innerHTML);
             location.href="/book?method=findAllBorrow&page="+pages;
         }
    </script>
</head>

<body>
<table class="table">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>author</td>
        <td>readername</td>
        <td>tel</td>
        <td>borrowtime</td>
        <td>returntime</td>
        <td>state</td>
    </tr>
    <c:forEach items="${list}" var="borrow">
        <tr>
            <td>${borrow.id}</td>
            <td>${borrow.book.name}</td>
            <td>${borrow.book.author}</td>
            <td>${borrow.reader.name}</td>
            <td>${borrow.reader.tel}</td>
            <td>${borrow.borrowTime}</td>
            <td>${borrow.returnTime}</td>
            <td>
                <c:if test="${borrow.state==0}"><font color="#7cfc00">not reviewed</font></c:if>
                <c:if test="${borrow.state==1}"><font color="black"> passed</font></c:if>
                <c:if test="${borrow.state==2}"><font color="#dc143c">not passed</font></c:if>
                <c:if test="${borrow.state==3}"><font color="#a52a2a">returned</font></c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<div id="first" onclick="first()">first page</div>
<div id="previous" onclick="previous()">previous page</div>
<div id="currentPage">${currentPage}</div>/<div id="pages">${pages}</div>
<div id="next" onclick="next()">next page</div>
<div id="last" onclick="last()">last page</div>
<div id="dataPerPage">${dataPerpage}  books per page </div>
</body>
</html>
