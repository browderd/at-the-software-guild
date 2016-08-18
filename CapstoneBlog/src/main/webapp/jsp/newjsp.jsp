<%-- 
    Document   : home
    Created on : 27-Jul-2016, 9:54:47 AM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
        <title>Blog</title>
        <!--<title>jQuery UI Datepicker - Default functionality</title>-->
         
       
    </head>
    <body>
        <div class="container-fluid"  id="dots">

            <div class="container-fluid" id="contain">


                <nav class="navbar navbar-default navbar-fixed-top" id="blogNav">
                    <div class="container">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand"><img id="t" src="img/ts.png"></a>
                            <!--  <div id="ts"></div>-->
                            <a class="navbar-brand" id="trench" href="#">TS EXPLORER</a>
                        </div>
                        <div id="navbar" class="navbar-collapse collapse">
                            <ul class="nav navbar-nav" id="navLinks">
                                <li class="active"><a href="${pageContext.request.contextPath}/publicView">Pub</a></li>
                                <li class=""><a href="${pageContext.request.contextPath}/home">Admin Home</a></li>
                                <li class=""><a href="${pageContext.request.contextPath}/displayUserList">User Management</a></li>
                                <li role="presentation"><a href="${pageContext.request.contextPath}/j_spring_security_logout">Log Out</a>
                    </li> 
                                
                            </ul>
                        
                        </div><!--/.nav-collapse -->
                    </div>
                </nav>
                                   
                <!-- <div class="navbar-fixed-top">
                 </div> -->

                <div class="row noSideScroll" id="bottom">
  
                    <div class="col-sm-2 noSideScroll" id="left">
                        <div id="leftInner" class="transparent"> 
                            <div id="adminConsole">
                                <h1>ADMIN ACTIONS CONSOLE</h1>
                                <hr id="hh">
                                <a class="staticLink" class="staticNav" onclick="loadActiveArticles();">
                                    <div class="astaticPage noSideScroll">
                                        SHOW ACTIVE ARTICLES                               
                                    </div>
                                </a>
                                <a class="staticLink" class="staticNav" onClick="showInputForm();">
                                    <div class="astaticPage">
                                        SHOW CONTENT EDITOR                               
                                    </div>
                                </a>
                                </a>
                                <a class="staticLink" class="staticNav" onclick="hideInputForm();">
                                    <div class="astaticPage">
                                        HIDE CONTENT EDITOR                            
                                    </div>
                                </a>
                                <a class="staticLink" class="staticNav" onclick="loadArticlesForReview();">
                                    <div class="astaticPage">
                                        SHOW ARTICLES FOR REVIEW                             
                                    </div>
                                </a>
                                <a class="staticLink" class="staticNav" onclick="loadAllHashtags();">
                                    <div class="astaticPage">
                                        SHOW ALL HASHTAGS                             
                                    </div>
                                </a>
                         

                        
                            </div><!--staticPages-->
                          <div class="row">
                             
  <div id="allHashtags"></div>
</div>
                    
          

                        </div><!--leftInner-->
                    </div><!-- col sm 3 -->


                    <div class="col-sm-7" id="middle">

                        <div id="middleInner" class="transparent"><!--posts go in here-->

                            <div id="articleDiv"></div>
                            <!--<div class="bg"></div>--><!--bg-->
                            <div id="staticPage"><%@include file="staticPageTemplate.jsp" %></div>


                            <!--<div id="image"></div>-->

                        </div><!--middleInner-->
                    </div><!-- col-sm-6 -->




                    <div class="col-sm-3" id="right">
                        <div id="rightInner" class="transparent"><div id="reviewLabel">ARTICLES FOR REVIEW</div><hr id="h">
                            <div id="review"></div>
                            </div>
                    </div><!-- col sm 3 -->
                </div><!-- row-->


                <div class="row">
                  <!--  <div class="col-sm-3" id="left"></div>-->

                    <div class="col-sm-3" id="buttons">
<!--
                        <div class="button" id="hideDiv" onclick="hideInputForm();">Hide Form</div>
                        <div class="button" id="showDiv" onClick="showInputForm();">Show Form</div>
                        <div class="button" id="showArticles" onclick="loadActiveArticles();">Show Articles</div>


                        <div class="button" id="reviewButton" onclick="loadArticlesForReview();">Show Articles for Review</div>-->
                    </div><!-- col-sm-3-->
                    <br>
                    <br><br>
                    <div class="col-sm-7" id="theform">    <div id="inputDiv"><%@include file="inputFormFragment.jsp" %></div></div>

                </div><!-- row -->


            </div><!-- Container -->
<!--            // Enable local "abbr" plugin from /myplugins/abbr/ folder.
CKEDITOR.plugins.addExternal( 'abbr', '/myplugins/abbr/', 'plugin.js' );

// extraPlugins needs to be set too.
CKEDITOR.replace( 'editor1', {
    extraPlugins: 'abbr'
} );-->
            <!--<div id="staticPages">-->
            <!--
            <div class="astaticPage">a static page</div>-->
            <!--  <div class="staticLink">static link</div>-->
            <!--    <a  class="staticLink"><div class="astaticPage">staticLink</div></a>-->
            <!--  <a><div class="astaticPage">staticPageInsideaLink</div></a>-->
            <!--  <a class="staticLink"><div class="astaticPage"></div></a>-->
        </div>
                    <%@include file="ArticleDetailsModal.jsp" %>
        <%@include file="detailsEditModalFragment.jsp" %>
   <script>
       CKEDITOR.replace('editor1');
   </script>
   
<!--   // Enable local "abbr" plugin from /myplugins/abbr/ folder.
CKEDITOR.plugins.addExternal( 'abbr', '/myplugins/abbr/', 'plugin.js' );

// extraPlugins needs to be set too.
CKEDITOR.replace( 'editor1', {
    extraPlugins: 'abbr'
} );-->
       <!-- <script>
            CKEDITOR.replace('editor1');
        </script> -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/blog.js"></script>
    </div><!--dots-->
</body>
</html>