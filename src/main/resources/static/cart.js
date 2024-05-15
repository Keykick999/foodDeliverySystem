// 페이지 로드 시 장바구니에 있는 상품 표시
document.addEventListener('DOMContentLoaded', function() {
    displayCartItems();
});

// 쿠키에서 값 가져오기
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1, c.length);
        }
        if (c.indexOf(nameEQ) == 0) {
            return c.substring(nameEQ.length, c.length);
        }
    }
    return null;
}

// 쿠키에 값 저장하기
function setCookie(name, value, days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "") + expires + "; path=/";
}

// 쿠키 삭제 함수
function deleteCookie(name) {
    document.cookie = name + '=; path=/; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

// 메뉴 상세 정보를 가져오는 함수
function fetchMenuDetail(menuId) {
    return fetch(`/api/menus/${menuId}`)
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('NetworkError');
        });
}

// 페이지 로드 시 장바구니에 있는 상품 표시
function displayCartItems() {
    var menuIds = JSON.parse(getCookie('menuIds') || '[]');
    var quantities = JSON.parse(getCookie('quantities') || '[]');

    var cartItemsDiv = document.getElementById('cartItems');
    cartItemsDiv.innerHTML = '';

    if (menuIds.length > 0 && quantities.length > 0) {
        var fetchPromises = menuIds.map(menuId => fetchMenuDetail(menuId));

        Promise.all(fetchPromises)
            .then(menus => {
                menus.forEach((menu, index) => {
                    cartItemsDiv.innerHTML += `
                        <p>메뉴 이름: ${menu.menuName}</p>
                        <p>주문 수량: ${quantities[index]}</p>
                        <button onclick="deleteMenu(${menuIds[index]})">장바구니에서 삭제</button>
                    `;
                });
            })
            .catch(error => {
                console.error('fetch error', error);
            });
    } else {
        cartItemsDiv.innerHTML = `<p>장바구니에 상품이 없습니다.</p>`;
    }
}

// 메뉴 장바구니에서 삭제
function deleteMenu(menuId) {
    var menuIds = JSON.parse(getCookie('menuIds') || '[]');
    var quantities = JSON.parse(getCookie('quantities') || '[]');

    // 메뉴 ID와 수량 배열에서 해당 메뉴 삭제
    var index = menuIds.indexOf(menuId.toString()); // 메뉴 ID를 문자열로 변환하여 비교
    if (index > -1) {
        menuIds.splice(index, 1);
        quantities.splice(index, 1);
    }

    // 변경된 배열을 다시 쿠키에 저장
    setCookie('menuIds', JSON.stringify(menuIds), 7);
    setCookie('quantities', JSON.stringify(quantities), 7);

    // 장바구니 아이템 다시 표시
    displayCartItems();
}

// 주문하기 버튼 클릭 시 주문 처리
function checkout() {
    var menuIds = JSON.parse(getCookie('menuIds') || '[]');
    var quantities = JSON.parse(getCookie('quantities') || '[]');
    var orderData = {};

    menuIds.forEach((id, index) => {
        orderData[id] = quantities[index];
    });

    fetch('/api/menus/order', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify(orderData)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data.message);
        alert("주문 완료");
        deleteCookie('menuIds');
        deleteCookie('quantities');
        displayCartItems();
    })
    .catch(error => {
        console.error('Error:', error);
        alert('주문 처리 중 오류가 발생했습니다.');
    });
}

// 메뉴 상세 정보를 가져와서 화면에 표시하는 함수
function displayMenuDetail() {
    var url = '/api/menus/' + menuId; // Thymeleaf로부터 가져온 menuId 사용
    fetch(url)
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('NetworkError');
        })
        .then(item => {
            const menuDiv = document.getElementById('menuDiv');
            menuDiv.innerHTML = `
                <p>menuName: ${item.menuName}</p>
                <p>price: ${item.price}</p>
            `;
            document.getElementById('menuIdInput').value = item.menuId;
        })
        .catch(error => {
            console.error('fetch error', error);
        });
}

// 주문 목록에 추가하는 함수
function addToCart() {
    var menuId = document.getElementById('menuIdInput').value;
    var quantity = parseInt(document.getElementById('quantityInput').value);

    // 기존 쿠키 값 가져오기
    var existingMenuIds = getCookie('menuIds');
    var existingQuantities = getCookie('quantities');

    // 기존 쿠키 값이 없을 경우 빈 배열로 초기화
    existingMenuIds = existingMenuIds ? JSON.parse(existingMenuIds) : [];
    existingQuantities = existingQuantities ? JSON.parse(existingQuantities) : [];

    // 메뉴 ID가 이미 존재하는지 확인하고 수량 업데이트
    var index = existingMenuIds.indexOf(menuId);
    if (index !== -1) {
        // 이미 존재하는 메뉴 ID의 수량 증가
        existingQuantities[index] += quantity;
    } else {
        // 새로운 메뉴 정보 추가
        existingMenuIds.push(menuId);
        existingQuantities.push(quantity);
    }

    // 쿠키에 저장
    setCookie('menuIds', JSON.stringify(existingMenuIds), 7);
    setCookie('quantities', JSON.stringify(existingQuantities), 7);

    alert('주문 목록에 추가되었습니다!');
}
