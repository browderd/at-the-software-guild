<%-- 
    Document   : displayUserList
    Created on : Jul 26, 2016, 9:12:40 AM
    Author     : mgaffney
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <!--^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^-->
        <!--^^^Just out of curiosity, what is this stylesheet responsible for? -Patrick^^^-->
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
      <!--  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>-->
<!--<script src="../ckeditor/ckeditor.js"></script>-->

       <script src="//cdn.ckeditor.com/4.5.10/full-all/ckeditor.js"></script>
       <script src="https://cdn.jsdelivr.net/ckfinder/2.3.1/ckfinder.js"></script>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/blogStyles.css" rel="stylesheet">
        <title>Users</title>
        <!--<title>jQuery UI Datepicker - Default functionality</title>-->
         
       
    </head>
    <body>
        <h1>Users</h1>
        <a href="displayUserForm">Add a User</a><br/>
        <hr/>
        <c:forEach var="user" items="${users}">
            <c:out value="${user.username}"/> |
            <a href="deleteUser?username=${user.username}">Delete</a><br/><br/>
        </c:forEach>
            
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/blog.js"></script>

    </body>
</html>