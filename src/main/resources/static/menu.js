document.addEventListener('DOMContentLoaded', function() {
    fetch('/api/menus')
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Network error!');
        })
        .then(data => {
            const menuList = document.getElementById('menuList');
            data.forEach(item => {
                const menu = document.createElement('a');
                menu.textContent = `category: ${item.category.categoryName}, menuName: ${item.menuName}, price:${item.price}`;
                menu.href = `/menus/${item.menuId}`;
                menuList.appendChild(menu);
            });
        })
        .catch(error => {
            console.error('Failed to fetch data:', error);
        });
});
