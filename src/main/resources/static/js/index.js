function check_authority() {
    $.ajax({
            url: '/index',
            type: 'post',
            headers: {
                'Auth': 'Bearer ' + sessionStorage.getItem('juniorAuthToken')
            },
            success: function (r) {
                $('#main_wrapper').html(r);
            },
            error: function () {
                window.location.href = '/';
            }
        }
    )
}