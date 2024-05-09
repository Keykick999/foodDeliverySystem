    $(document).ready(function(){
        // 전체 메뉴 조회
        $.ajax({
            url: "/api/menus",
            type: "GET",
            dataType: "json",
            success: function(data) {
                // 서버에서 받은 데이터를 처리하여 화면에 표시
                var menuListHtml = '';
                $.each(data, function(index, menu) {
                    menuListHtml += '<div>' + menu.menuName + ' - ' + menu.price + '</div>';
                });
                $('#menuList').html(menuListHtml);
            },
            error: function(xhr, status, error) {
                console.error("Error: " + status);
            }
        });
    });
