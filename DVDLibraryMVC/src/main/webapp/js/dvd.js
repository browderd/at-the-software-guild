// Document ready function
$(document).ready(function () {
    loadDVDLibrary();
    // on click for our add button
    $('#add-button').click(function (event) {
// we don’t want the button to actually submit
// we'll handle data submission via ajax
        event.preventDefault();
// Make an Ajax call to the server. HTTP verb = POST, URL = contact 
        $.ajax({
            type: 'POST',
            url: 'dvd',
// Build a JSON object from the data in the form 
            data: JSON.stringify({
                title: $('#add-title').val(),
                releaseDate: $('#add-releaseDate').val(),
                director: $('#add-director').val(),
                impaaRating: $('#add-impaaRating').val(),
                studio: $('#add-studio').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'}).success(function (data, status) {
// If the call succeeds, clear the form and reload the summary table
            $('#add-title').val('');
            $('#add-releaseDate').val('');
            $('#add-director').val('');
            $('#add-impaaRating').val('');
            $('#add-studio').val('');
            loadDVDLibrary();
            return false;
        });
    });

    $('#edit-button').click(function (event) {
        // prevent the button press from submitting the whole page
        event.preventDefault();

        // Ajax call -
        // Method - PUT
        // URL - contact/{id}
        // Just reload all of the Contacts upon success
        $.ajax({
            type: 'PUT',
            url: 'dvd/' + $('#edit-dvd-id').val(),
            data: JSON.stringify({
                idNumber: $('#edit-dvd-id').val(),
                title: $('#edit-title').val(),
                releaseDate: $('#edit-releaseDate').val(),
                director: $('#edit-director').val(),
                impaaRating: $('#edit-impaaRating').val(),
                studio: $('#edit-studio').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function () {
            loadDVDLibrary();
        });
    });

    $('#search-button').click(function (event) {
        // we don’t want the button to actually submit
        // we'll handle data submission via ajax
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'search/dvds',
            data: JSON.stringify({
                title: $('#search-title').val(),
                releaseDate: $('#search-releaseDate').val(),
                director: $('#search-director').val(),
                impaaRating: $('#search-impaaRating').val(),
                studio: $('#search-studio').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#search-title').val('');
            $('#search-releaseDate').val('');
            $('#search-director').val('');
            $('#search-impaaRating').val('');
            $('#search-studio').val('');
            fillDVDTable(data, status);
        });
    });
});
//==========
// FUNCTIONS
//==========
// Load contacts into the summary table
function loadDVDLibrary() {
// clear the previous list
    clearDVDTable();
// grab the tbody element that will hold the new list of contacts 
    var cTable = $('#contentRows');
// Make an Ajax GET call to the 'contacts' endpoint. Iterate through // each of the JSON objects that are returned and render them to the // summary table.
    $.ajax({
        url: "dvds"}).success(function (data, status) {
        fillDVDTable(data, status);
    });
}

function fillDVDTable(dvdList, status) {
    // clear the previous list
    clearDVDTable();
    // grab the tbody element that will hold the new list of contacts
    var cTable = $('#contentRows');

    // render the new contact data to the table
    $.each(dvdList, function (index, dvd) {
        cTable.append($('<tr>')
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-dvd-id': dvd.idNumber,
                                    'data-toggle': 'modal',
                                    'data-target': '#detailsModal'
                                })
                                .text(dvd.title)
                                ) // ends the <a> tag
                        ) // ends the <td> tag for the contact name
                .append($('<td>').text(dvd.releaseDate))
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-dvd-id': dvd.idNumber,
                                    'data-toggle': 'modal',
                                    'data-target': '#editModal'
                                })
                                .text('Edit')
                                ) // ends the <a> tag
                        ) // ends the <td> tag for Edit
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'onClick': 'deleteDVD' + dvd.idNumber + ')'
                                })
                                .text('Delete')
                                ) // ends the <a> tag
                        ) // ends the <td> tag for Delete
                ); // ends the <tr> for this Contact
    }); // ends the 'each' function
}

function deleteDVD(id) {
    var answer = confirm("Do you really want to delete this movie?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'dvd/' + id
        }).success(function () {
            loadDVDLibrary();
        });
    }
}
// Clear all content rows from the summary table
function clearDVDTable() {
    $('#contentRows').empty();
}

$('#detailsModal').on('show.bs.modal', function (event) {
// Get the element that triggered this event - in our case it is a contact
// name link in the summary table. This link has an attribute that contains
// the contactId for the given contact. We'll use that to retrieve the
// contact's details.
    var element = $(event.relatedTarget);
// grab the contact id
    var dvdID = element.data('dvd-id');
// PLACEHOLDER: Eventually we'll make an ajax call to the server to get the
// details for this contact but for now we'll load the dummy
// data
    var modal = $(this);
    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdID
    }).success(function (dvd) {
        modal.find('#dvd-id').text(dvd.idNumber);
        modal.find('#dvd-title').text(dvd.title);
        modal.find('#dvd-releaseDate').text(dvd.releaseDate);
        modal.find('#dvd-director').text(dvd.director);
        modal.find('#dvd-impaaRating').text(dvd.impaaRating);
        modal.find('#dvd-studio').text(dvd.studio);
    });
});

$('#editModal').on('show.bs.modal', function (event) {
// Get the element that triggered this event - in our case it is a contact
// name link in the summary table. This link has an attribute that contains
// the contactId for the given contact. We'll use that to retrieve the
// contact's details.
    var element = $(event.relatedTarget);
// Grab the contact id
    var dvdId = element.data('dvd-id');
// PLACEHOLDER: Eventually we'll make an ajax call to the server to get the
// details for this contact but for now we'll load the dummy
// data
    var modal = $(this);
    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId
    }).success(function (dvd) {
        modal.find('#dvd-id').text(dvd.idNumber);
        modal.find('#edit-dvd-id').val(dvd.idNumber);
        modal.find('#edit-title').val(dvd.title);
        modal.find('#edit-releaseDate').val(dvd.releaseDate);
        modal.find('#edit-director').val(dvd.director);
        modal.find('#edit-impaaRating').val(dvd.impaaRating);
        modal.find('#edit-studio').val(dvd.studio);
    });
});




