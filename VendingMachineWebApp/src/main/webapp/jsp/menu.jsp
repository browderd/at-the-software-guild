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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
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
                    <li role="presentation"><a href="${pageContext.request.contextPath}/home"><span class="glyphicon glyphicon-home"></span></a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/updateBalance"><span id="currentBal" class="glyphicon glyphicon-usd"></span></a></li>
                </ul>    
            </div>
        
        <div class="row">
            <div id="works"  class="clearfix grid"> 
            </div>
        </div>
                </div>
        <%@include file="purchaseModalFragment.jsp" %>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/vendingMachine.js"></script>
        <script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
    </body>
</html>

