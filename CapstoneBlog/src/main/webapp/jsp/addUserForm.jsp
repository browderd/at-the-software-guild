<%-- 
    Document   : addUserForm
    Created on : Jul 26, 2016, 9:17:28 AM
    Author     : mgaffney
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        

        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <!--^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^-->
        <!--^^^Just out of curiosity, what is this stylesheet responsible for? -Patrick^^^-->

      <!--  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>-->
<!--<script src="../ckeditor/ckeditor.js"></script>-->

       <script src="//cdn.ckeditor.com/4.5.10/full-all/ckeditor.js"></script>
       <script src="https://cdn.jsdelivr.net/ckfinder/2.3.1/ckfinder.js"></script>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/blogStyles.css" rel="stylesheet">
        <title>Add User</title>
        <!--<title>jQuery UI Datepicker - Default functionality</title>-->
         
       
    </head>
    <body>
        <h1>Add User Form</h1>
        <form method="POST" action="addUser">
            Username: <input type="text" name="username"/><br/>
            Password: <input type="password" name="password"/><br/>
            Admin User? <input type="checkbox" name="isAdmin" value="yes"/> <br/>
            <input type="submit" value="Add User"/>
            <br/>
        </form>
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/blog.js"></script>
    </body>
</html>
