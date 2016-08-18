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
            <h1 id="landingTitle">Dustin's Home Movie Vending Machine</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/home"><span class="glyphicon glyphicon-home"></span></a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/updateBalance"><span class="glyphicon glyphicon-usd">$ ${balance}</span></a></li>
                </ul>    
            </div>
        </div>
        <div class="container">        
            <h2 style="color: white;">Sign in to get some grub!</h2>
            <c:if test="${param.login_error == 1}">
                <h3>Wrong id or password!</h3>
            </c:if>
            <form method="post" class="signin" action="j_spring_security_check">
                <fieldset>
                    <table cellspacing="0">
                        <tr>
                            <th>
                                <label for="username">Username
                                </label>
                            </th>

                            <td><input id="username_or_email"
                                       name="j_username"
                                       type="text" />
                            </td>
                        </tr>
                        <tr>
                            <th><label for="password">Password</label></th>
                            <td><input id="password"
                                       name="j_password"
                                       type="password" />
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td><input name="commit" type="submit" value="Sign In" /></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <!-- wow script -->
        <script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
    </body>
</html>

