$(document).ready(function () {
    $(document).keyup(function (e) {
        if (e.which === 13) {
            loginFunc();
            return true;
        }
    });
});

let loginFunc = function () {
    const usernameField = $("#usernameField")[0].value;
    const passwordField = $("#passwordField")[0].value;
    const authenticationDto = {username: usernameField, password: passwordField};

    $.ajax({
            url: '/auth',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(authenticationDto),
            success: function (r) {
                sessionStorage.setItem('juniorAuthToken', r.token);
                successLoginAnimation();
                // indexGet();
            },
            error: function () {
                // $("#usernameField").val('');
                $("#passwordField").val('');
                let $msg = $('#info_message');
                $msg.animate({opacity: 0}, 300, 'swing', function () {
                    $msg.text('Wrong username or password').animate({opacity: 1}, 500, 'swing', function () {
                        $msg.delay(1200)
                            .animate({opacity: 0}, 300, 'swing', function () {
                                $msg.text('Log in here');
                                $msg.animate({opacity: 1}, 500, 'swing');
                            })
                    })
                });
            }
        }
    );
};

function successLoginAnimation() {
    $('body').animate({opacity: 0}, 500, 'swing', function () {
        indexGet();
    });
};

function indexGet() {

    $.ajax({
            url: '/index',
            type: 'post',
            headers: {
                'Auth': 'Bearer ' + sessionStorage.getItem('juniorAuthToken')
            },
            success: function (r) {
                $('#main_wrapper').html(r);

                $('head').append('<link href="/css/index.css" rel="stylesheet" type="text/css">');
                let link = $('#loginCss');
                link.remove();
                $('body').delay(300).animate({opacity: 1}, 800, 'linear', function () {
                    getContentData();
                });
            },
            error: function () {
                window.location.href = '/';
            }
        }
    )
};

function getContentData() {
    $.ajax({
        url: '/contentdata',
        type: 'get',
        headers: {
            'Auth': 'Bearer ' + sessionStorage.getItem('juniorAuthToken')
        },
        success: function (r) {
            console.log(r);
            $('.body_content pre').html(r);
        },
        error: function () {

        }
    });
}

