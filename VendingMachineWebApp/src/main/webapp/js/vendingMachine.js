/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Document ready function
$(document).ready(function () {
    loadMenu();
    $('[data-toggle="popover"]').popover();
    $(document).on("click", ".click_me", function () {
        $(this).popover({
            placement: 'right',
            title: 'Nutrition Facts',
            html: true,
            type: 'button'
        }).popover('toggle');
    });
    $('#buy-button').click(function () {
        $.ajax({
            type: 'POST',
            url: 'item/' + $('#purchase-item-id').val()
        }).success(function (data, status) {
            puchaseItem(data);
            $('#currentBal').empty();
        });
    });
    displayBalance();
});
//==========
// FUNCTIONS
//==========

function loadMenu() {
    clearTable();
    var cTable = $('#works');
    $.ajax({
        url: "popMenu"}).success(function (data, status) {
        fillMenu(data, status);
    });
}

function fillMenu(itemList, status) {
    clearTable();
    var cTable = $('#works');
    $.each(itemList, function (index, item) {
        cTable.append($('<figure>')
                .attr({
                    'class': 'effect-oscar  wowload fadeInUp'
                })
                .append($('<img>')
                        .attr({
                            'src': 'img/' + item.name + '.jpg',
                            'alt': 'picture of ' + item.name,
                            'class': 'img-responsive',
                            'style': 'width:338px;height:292px;'
                        }))
                .append($('<figcaption>')
                        .append($('<h2>')
                                .attr({
                                    'style': 'text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;'
                                })
                                .text(item.name))
                        .append($('<br>'))
                        .append($('<p>')
                                .append($('<div>')
                                        .attr({
                                            'class': 'btn-group-vertical'
                                        })
                                        .append($('<button>')
                                                .attr({
                                                    'type': 'button',
                                                    'class': 'btn btn-primary',
                                                    'data-itemid': item.itemID,
                                                    'data-toggle': 'modal',
                                                    'data-target': '#purchaseModal'
                                                })
                                                .text('Buy for $' + item.cost)
                                                )
                                        .append($('<button>')
                                                .attr({
                                                    'class': 'btn btn-info click_me',
                                                    'data-toggle': 'popover',
                                                    'data-content': item.info
                                                })
                                                .text('More Info')
                                                )))
                        )
                );
    });
}

function puchaseItem(change) {
    if (change.isEnough === false) {
        alert('Please add additional funds in order to purchase this item.');
    } else {
        alert('Thank you for your purchase! Your change is as follows:'
                + change.quarters + ' quarters, ' + change.dimes + ' dimes, '
                + change.nickels + ' nickels, and ' + change.pennies + ' pennies.');
    }

}

function displayBalance() {
    $.ajax({
        type: 'GET',
        url: 'balance'
    }).success(function (data, status) {
        $('#currentBal').append($('<big>').text(data));
    });
}

function clearTable() {
    $('#works').empty();
}

$('#purchaseModal').on('show.bs.modal', function (event) {
    var modal = $(this);
    var element = $(event.relatedTarget);

    var itemID = element.data('itemid');
    $.ajax({
        type: 'GET',
        url: 'item/' + itemID
    }).success(function (item) {
        modal.find('#question').html('Are you certain you would like to buy ' +
                item.name + ' for $' + item.cost + '?');
        modal.find('#purchase-item-id').val(item.itemID);
    });
});







