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
             location.href="/admin?method=return&page="+page;
         }

         function previous(){
             var page = parseInt(document.getElementById("currentPage").innerHTML);
             if(page==1){
                 return;
             }
             page--;
             location.href="/admin?method=return&page="+page;
         }

         function first(){
             location.href="/admin?method=return&page=1";
         }

         function last(){
             var pages =parseInt(document.getElementById("pages").innerHTML);
             location.href="/admin?method=return&page="+pages;
         }
    </script>
</head>

<body>
<a href="/admin?page=1">handle borrow</a>
<table class="table">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>author</td>
        <td>readername</td>
        <td>tel</td>
        <td>borrowtime</td>
        <td>returntime</td>
        <td>action</td>
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
                <a href="/admin?method=handle&state=3&id=${borrow.id}">return</a>
            </td>
        </tr>
    </c:forEach>
</table>
<div id="first" onclick="first()">first page</div>
<div id="previous" onclick="previous()">previous page</div>
<div id="currentPage">${currentPage}</div>/<div id="pages">${pages}</div>
<div id="next" onclick="next()">next page</div>
<div id="last" onclick="last()">last page</div>
<div id="dataPerPage">${dataPerpage}books per page</div>
</body>
</html>
