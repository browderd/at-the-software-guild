<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DVD Library </title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>Your Collection</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/viewCollection">View Collection</a></li>
                </ul>    
            </div>
            <div>
                <a href="addDVD">Add a DVD</a><br>
                <hr>               
                <c:forEach var="dvd" items="${library}">
                    <s:url value="removeDVD" var="deleteDVD_url">
                        <s:param name="idNumber" value="${dvd.idNumber}"/>
                    </s:url>
                    <s:url value="editDVDForm" var="editDVD_url">
                        <s:param name="idNumber" value="${dvd.idNumber}"/>
                    </s:url>
                    Title: ${dvd.title} <sub>${dvd.releaseDate}</sub> | 
                    <a href="${deleteDVD_url}">Delete</a> | <a href="${editDVD_url}">Edit</a><br>
                    Director: ${dvd.director}<br>
                    Studio: ${dvd.studio}<br>
                    IMPAA Rating: ${dvd.impaaRating}<br>
                    User Ratings:<br><br>
                    <c:forEach var="rating" items="${dvd.userRating}">
                        ${rating}<br>
                    </c:forEach>
                    <br><hr><br>
                </c:forEach>
            </div>      
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

