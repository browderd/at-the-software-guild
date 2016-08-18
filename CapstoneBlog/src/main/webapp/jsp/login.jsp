<%-- 
    Document   : login
    Created on : Jul 25, 2016, 9:42:14 AM
    Author     : mgaffney
--%>

<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

   <!-- <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="search-title" class="col-md-4 control-label">
                                Title:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="search-title"
                                       placeholder="Title"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="search-director" class="col-md-4 control-label">
                                Director:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="search-director"
                                       placeholder="Director"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="search-year" class="col-md-4 control-label">Year:</label>
                            <div class="col-md-8">
                                <input type="number" class="form-control" id="search-year"
                                       placeholder="Year"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="search-rating" class="col-md-4 control-label">Rating:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="search-rating"
                                       placeholder="Rating"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="search-studio" class="col-md-4 control-label">Studio:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="search-studio"
                                       placeholder="Studio"/>
                            </div>
                        </div>
                      
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <button type="submit" id="search-button" class="btn btn-default">
                                    Search
                                </button>
                             
      
                 <!--           </div>
                 <!--       </div>
                 <!--   </form>-->


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/site-styles.css" rel="stylesheet">
        <!-- SWC Icon -->
        <title>DVD Library</title>
    </head>
    <body>
        <div class="formDiv">
            <h2 id="signIn">Login</h2>
            <!-- #1 - If login_error == 1 then there was a failed login attempt -->
            <!-- so display an error message -->
            <c:if test="${param.login_error == 1}">
                <h3>Wrong id or password!</h3>
            </c:if>
            <!-- #2 - Post to Spring security to check our authentication -->
            <form method="post" class="signin form-horizontal" action="j_spring_security_check">
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
                            <!-- #2b - must be j_password for Spring -->
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
        
            <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/dvdLibrary.js"></script>

    </body>
</html>
