var stompClient = null;

function connect() {
    var socket = new SockJS('/chat-messaging');
    stompClient = Stomp.over(socket);
    stompClient.debug = null;
    stompClient.connect({}, function (frame) {
        // setConnected(true);
        console.log('connected: ' + frame);
        stompClient.subscribe('/chat/messages', function (undto) {
            console.log("received undto data");
            console.log(undto);
            let data = JSON.parse(undto.body);
            console.log('data');
            console.log(data);
            console.log(data.amount);
            console.log(data.capacity);
            updateFillbar(data.amount, data.capacity);
        });
    });
}

function disconnect() {
    stompClient.disconnect();
    // setConnected(false);
    console.log('disconnected');
}

// send example
function send() {

    stompClient.send("localhost:8080/app/message", {}, 'msg');
}

/*
function getData() {
    $.ajax({
        url: '/un',
        type: 'post',
        /!*headers: {
            'Auth': 'Bearer ' + sessionStorage.getItem('juniorAuthToken')
        },*!/
        success: function (r) {
            console.log(r);
            updateFillbar(r.amount, r.capacity);
        },
        error: function () {
            console.log("error getData() response");
            // window.location.href = '/';
        }
    });
}*/

function updateFillbar(amount, capacity) {
    let $resAmount = $('.res_amount');
    $resAmount.css('width', (amount / capacity * 100) + '%');
    $resAmount.text(amount + ' / ' + capacity);
}

let changeIncomeTypes = ['inc', 'dec'];

function changeIncome(type) {
    if (changeIncomeTypes.indexOf(type) === -1) {
        console.log("wrong change income type value");
        return;
    }

    $.ajax({
        url: '/un/' + type,
        type: 'get',
        success: function (r) {
            console.log(r);
        },
        error: function (r) {
            console.log("error: " + r);
        }
    });
}