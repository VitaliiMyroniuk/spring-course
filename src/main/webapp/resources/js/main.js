var Main = Main || {};

Main = {

    bindLogoutButtonClick: function () {
        $('.tabs__logout').click(function (event) {
            event.preventDefault();
            $.ajax({
                type: 'POST',
                url: '/logout',
                data: $('.logout-form').serialize(),
                success: function () {
                    window.location.href = 'home';
                }
            });
        });
    }

};

$(document).ready(function () {
    Main.bindLogoutButtonClick();
});
