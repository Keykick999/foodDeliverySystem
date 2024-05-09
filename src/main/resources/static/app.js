function loadMenus() {
    $.ajax({
        url: '/api/menus',
        type: 'GET',
        dataType: 'json',
        success: function(menus) {
            var menuListHtml = menus.map(menu => `<div>${menu.name} - ${menu.price}</div>`).join('');
            $('#menuList').html(menuListHtml);
        },
        error: function() {
            $('#menuList').html('<p>Error loading menus.</p>');
        }
    });
}

$('#orderForm').submit(function(event) {
    event.preventDefault();
    var orderRequest = {
        memberId: $('#memberId').val(),
        items: [{
            menuId: $('#menuId').val(),
            quantity: $('#quantity').val()
        }]
    };
    $.ajax({
        url: '/api/orders',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(orderRequest),
        success: function() {
            alert('Order placed successfully');
        },
        error: function() {
            alert('Error placing order');
        }
    });
});

function cancelOrder() {
    var orderId = $('#cancelOrderId').val();
    $.ajax({
        url: `/api/orders/${orderId}`,
        type: 'POST',
        success: function() {
            alert('Order canceled successfully');
        },
        error: function() {
            alert('Error canceling order');
        }
    });
}
