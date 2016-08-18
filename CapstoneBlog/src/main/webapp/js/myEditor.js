/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    tinymce.init({
        selector: "textarea",
        statusbar: false,
        setup: function (editor) {
            editor.on('change', function () {
                tinymce.triggerSave();
            });}});});
        
   $(function () {
    tinymce.init({
        selector: "textarea",
        statusbar: false,
        setup: function (editor) {
            editor.on('change', function () {
                tinymce.triggerSave();
            });
        }
    });

    $('#showValues').click(function () {
        var msg = '';
        $('textarea').each(function (index, ta) {
            var $ta = $(ta);
            msg += '<div><strong>Textarea "' + $ta.attr('name') + '" value:</strong><br />' + $ta.val() + '</div>';
        });
        $('#values').html(msg);
    });
});
    
 $('#postButton').click(function (event) {
// we don’t want the button to actually submit
// we'll handle data submission via ajax
        event.preventDefault();
// Make an Ajax call to the server. HTTP verb = POST, URL = contact 
        $.ajax({
            type: 'POST',
            url: 'post/content',
// Build a JSON object from the data in the form 
            data: JSON.stringify({
                content: $('#myTextarea').val()
                            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'}).success(function (data, status) {
// If the call succeeds, clear the form and reload the summary table
            $('#content').val('');
            
           
            return false;
        });
    });
    
//    function ajaxSave() {
//
//    
//        var ed = tinyMCE.get('content');
//
//    // Do you ajax call here, window.setTimeout fakes ajax call
//    ed.setProgressState(1); // Show progress
//    window.setTimeout(function() {
//        ed.setProgressState(0); // Hide progress
//        alert(ed.getContent());
//    }, 3000);
//}
//    
//
// $(".submit").live("click", function () {
//            tinyMCE.activeEditor.getContent();
//            var f = $(this).parents("form");
//            var action = f.attr("action");
//            var serializedForm = f.serialize();
//            //tinyMCE.triggerSave(); also tried putting here
//            $.ajax({
//                 type: 'POST',
//                 url: 'post/content/',
//                data: JSON.stringify({
//                title: $('#content').val(),
//                 success: function (data, textStatus, request) {
//                     $(".divform").html(data);
//                 },
//                 error: function (req, status, error) {
//                     alert&("Error occurred!");
//                 }});
//              
//    return false;
// });
    
//    
//    function ajaxLoad() {
//    var ed = tinyMCE.get('content');
//
//    // Do you ajax call here, window.setTimeout fakes ajax call
//    ed.setProgressState(1); // Show progress
//    window.setTimeout(function() {
//        ed.setProgressState(0); // Hide progress
//        ed.setContent('HTML content that got passed from server.');
//    }, 3000);
//}
//
//function ajaxSave() {
//    var ed = tinyMCE.get('content');
//
//    // Do you ajax call here, window.setTimeout fakes ajax call
//    ed.setProgressState(1); // Show progress
//    window.setTimeout(function() {
//        ed.setProgressState(0); // Hide progress
//        alert(ed.getContent());
//    }, 3000);
