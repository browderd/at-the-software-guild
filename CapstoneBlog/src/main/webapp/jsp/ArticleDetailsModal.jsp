<%-- 
    Document   : ArticleDetailsModal
    Created on : 12-Aug-2016, 3:22:58 PM
    Author     : apprentice
--%>

<div class="modal fade" id="postModal" tabindex="-1" role="dialog"
     aria-labelledby="detailsModalLabel" aria-hidden="true">
     <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="postModalLabel">Post Details</h4>
            </div>
           
            <div class="modal-body">
                <div id="bodyTest"></div>
<form role="form" id="detailsForm">
<input type="hidden" id="body">
    Enter a heading for the article: <br><input type="text" id="articleHead" name="articleHead"><br>
    This entry is meant to be a new:<br>
    <input class="form-horizontal" type="radio" name="staticPage" value="false">Blog Post<br>
    <input type="radio" name="staticPage" value="true">Static Page<br>
    
   
    Pick the Active Status of the Article:
    <br>
    <input type="radio" name="activeStatus" value="3" checked="checked">Queued for Approval<br>
    <input type="radio" name="activeStatus" value="2">Approved, Awaiting Post Date<br>
    <input type="radio" name="activeStatus" value="1">Active<br>

        
    
Select posting date:<br>
    <input type="date" name="postDate" id="postDate"/><br>
    Select take-down date:<br>
    <input type="date" name="takeDown" id="takeDown"/>
  

    <br>
  <!--  <div class="row">-->
    <div id="buttonDiv">
    <button  type="submit" id="postButton2" class="btn btn-default"
                                    data-dismiss="modal">Submit</button>
</div><!--buttonDiv-->


    
</form>
    </div><!--modal div-->
    </div></div></div>