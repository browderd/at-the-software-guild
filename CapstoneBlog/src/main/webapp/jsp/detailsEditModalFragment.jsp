<%-- 
    Document   : detailsEditModalFragment
    Created on : Jul 19, 2016, 8:58:51 AM
    Author     : mgaffney
--%>

<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
     aria-labelledby="detailsModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="detailsModalLabel">Edit Article</h4>
            </div>
            <div class="modal-body">
                <h3 id="article-id"></h3>

                <form>
                  
                  <!--  <span  id="editArticleBodyLabel"> Edit Article Body</span>--><br><br>
                  <textarea id="editor2" name="editor2"></textarea><br>
                    <script>
                        CKEDITOR.replace('editor2');
                    </script>
                    <br>
                    Edit Article Heading: <br><input type="text" id="edit-article-head"><br>
                    <br>

                    
                    <br>
                    Edit posting date:
                    <br>
                    <input type="date" name="edit-article-posting-date" id="edit-article-posting-date">
                  
                    <br>
                    Edit take-down date:
                    <br>
                   <!--  <input type="text" id="edit-article-posting-date"> -->
                    <input type="date" name="edit-article-take-down-date" id="edit-article-take-down-date">
                   
<!--                    <input type="text" id="edit-article-static-page">-->
            Pick the Active Status of the Article:
    <br>
    <input type="radio" name="activeStatus" value="3">Queued for Approval<br>
    <input type="radio" name="activeStatus" value="2">Approved, Awaiting Post Date<br>
    <input type="radio" name="activeStatus" value="1"  checked="checked">Active<br>
    <br>
         This entry is meant to be a:<br>
    <input type="radio" name="staticPage" value="false" checked="checked">Blog Post<br>
    <input type="radio" name="staticPage" value="true">Static Page<br>
                  
                    <input type="hidden" id="edit-article-id">
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button type="submit" id="edit-button" class="btn btn-default" data-dismiss="modal">
                                Edit
                            </button>
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">
                                Cancel
                            </button>
                        
                        </div>
                    </div>
                </form>
                <br>
                <br>
            </div>
        </div>
    </div>
</div>
