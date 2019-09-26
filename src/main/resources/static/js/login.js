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
            window.location.replace('/index');
        },
        error: function () {
            $("#usernameField").val('');
            $("#passwordField").val('');
            $('#info_message').text('WRONG USERNAME OR PASSWORD').css('opacity', '1').css('display', 'block').delay(1000).fadeOut({duration: 2000});
        }
    });


};