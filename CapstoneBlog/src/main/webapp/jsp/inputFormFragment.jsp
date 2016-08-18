<%-- 
    Document   : inputFormFragment
    Created on : 1-Aug-2016, 7:37:12 PM
    Author     : apprentice
--%>
<!--
<form>
    Add a hashtag (one at a time):<br>
    <input type="text" name="hashtag" id="hashtag">    
    <button id="hashtagButton">Add</button>
</form>-->

<form id="inputForm" role="form" class="form-horizontal">
    <div class="row">
    <textarea id="editor1" name="editor1"></textarea><br>
    
<!--   <div id="leftOptions">
    
    Enter a heading for the article: <br><input type="text" id="articleHead" name="articleHead"><br>
    This entry is meant to be a new:<br>
    <input class="form-horizontal" type="radio" name="staticPage" value="false">Blog Post<br>
    <input type="radio" name="staticPage" value="true">Static Page<br>
    
   
    Pick the Active Status of the Article:
    <br>
    <input type="radio" name="activeStatus" value="3" checked="checked">Queued for Approval<br>
    <input type="radio" name="activeStatus" value="2">Approved, Awaiting Post Date<br>
    <input type="radio" name="activeStatus" value="1">Active<br>

        <input type="hidden" id="activeStatus">
        <input type="hidden" id="theDate">
    
Select posting date:<br>
    <input type="date" name="postDate" id="postDate"/><br>
    Select take-down date:<br>
    <input type="date" name="takeDown" id="takeDown"/>-->
  

    <!--<br>-->
  <!--  <div class="row">-->
  <a type="submit" class="button" data-toggle="modal" onclick="loadBody();" data-target="#postModal" data-article-body="CKEDITOR.instances.editor1.getData()">Post</a>
    <div id="buttonDiv">
   <!-- <button type="submit" data-toggle="modal" data-target="#postModal" id="postButton">Submit</button>-->
</div><!--buttonDiv-->
</div><!--leftOptions-->
</div><!--row-->
</form>

