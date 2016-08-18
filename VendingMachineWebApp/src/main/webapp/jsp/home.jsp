<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/VendingMachine.css" />
        <link href='https://fonts.googleapis.com/css?family=Bitter:400,700' rel='stylesheet' type='text/css'>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
        <!-- animate.css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/set.css" />        
    </head>
    <body>
        <div class="container">
            <div class="navbar navbar-inverse">
                <div class="navbar-header">
            <h4 id="landingTitle">Dustin's Home Movie Vending Machine</h4>
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/login"><span class="glyphicon glyphicon-lock">Login</span></a></li>
                </ul>    
            </div>
        <div class="container">
            <form class="form-horizontal"
                     role="form"
                     action="addBalance"
                     method="POST">
                <div class="form-group">
                    <label for="add-balanceStr" class="col-md-4 control-label" style="color:white;">How much would you like to spend?</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="add-balance" name="balanceStr" placeholder="$$$$$$$"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <button type="submit" id="add-button" class="btn btn-default">Go!</button>
                    </div>
                </div>
            </form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <!-- wow script -->
        <script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
    </body>
</html>

