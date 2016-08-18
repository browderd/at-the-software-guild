/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


///////////////////////
//show post modal
///////////////////////
$('#postModal').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var articleID = element.data('article-id');
    var modal = $(this);
    var C = CKEDITOR.instances.editor1.getData();
    alert(C);
    modal.find('#body2').val(C);
    alert($('#body2').val());
});

//////////////////////////////
//dedailsPostButton onclick function
//////////////////////////////


$('#postButton2').click(function (event) {
    event.preventDefault();
    var pDate = $("#postDate2").val();
    var tDate = $("#takeDown2").val();
    var static = $("input[type='radio'][name='staticPage2']:checked").val();
    var head = $('#articleHead2').val();
    var status = $("input[type='radio'][name='activeStatus2']:checked").val();
    var body = $('#body2').val();
    $('#articleHead').val(head);
    alert($('#articleHead').val());
    $.ajax({
        type: 'POST',
        url: 'article',
        data: JSON.stringify({
            articleBody: body,
            staticPage: static /*$("input[type='radio'][name='staticPage']:checked").val()*/,
            activeStatus: status /*$("input[type='radio'][name='activeStatus']:checked").val()*/,
            articleHead: head /*$('#articleHead').val()*/,
            postingDate: pDate /*$("#postDate").val()*/,
            takeDownDate: tDate /*$("#takeDown").val()*/
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'}).success(function (data, status) {
        $('textarea.editor1').val('');
        CKEDITOR.instances.editor1.setData("");
        loadActiveStaticPages();
        loadArticlesForReview();
        loadActiveArticles();
        return false;
    });
});