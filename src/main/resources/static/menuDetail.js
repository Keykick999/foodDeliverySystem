// 메뉴 상세 정보를 가져와서 화면에 표시하는 함수
function displayMenuDetail() {
  var url = '/api/menus/' + menuId; // Thymeleaf로부터 가져온 menuId 사용
  fetch(url)
    .then(response => {
      if(response.ok){
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



// 쿠키 설정 함수
function setCookie(name, value, days) {
  var expires = "";
  if (days) {
      var date = new Date();
      date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
      expires = "; expires=" + date.toUTCString();
  }
  document.cookie = name + "=" + (value || "") + expires + "; path=/";
}

// 쿠키 가져오는 함수
function getCookie(name) {
  var nameEQ = name + "=";
  var ca = document.cookie.split(';');
  for(var i = 0; i < ca.length; i++) {
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

// 페이지 로드 시 메뉴 상세 정보 표시
document.addEventListener('DOMContentLoaded', function() {
  displayMenuDetail();
});
