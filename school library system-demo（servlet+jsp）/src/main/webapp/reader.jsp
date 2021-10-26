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
             location.href="/book?page="+page;
         }

         function previous(){
             var page = parseInt(document.getElementById("currentPage").innerHTML);
             if(page==1){
                 return;
             }
             page--;
             location.href="/book?page="+page;
         }

         function first(){
             location.href="/book?page=1";
         }

         function last(){
             var pages =parseInt(document.getElementById("pages").innerHTML);
             location.href="/book?page="+pages;
         }
    </script>
</head>

<body>
Current page: <a href="/book?page=1">first page</a>
Welcome back!：<a href="/book?method=findAllBorrow&page=1">${reader.name}</a>
<a href="/logout">logout</a>

<table class="table">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>author</td>
        <td>bookcase</td>
        <td>action</td>
    </tr>
    <c:forEach items="${list}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>${book.bookcase.name}</td>
            <td>
                <a href="/book?method=borrow&bookid=${book.id}">borrow</a>
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
