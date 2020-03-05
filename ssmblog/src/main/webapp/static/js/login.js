$(function () {

    $("#login_btn").click(function () {
        var data1 = {
            "username": $("#username").val(),
            "password": $("#password").val()
        }
        $.ajax({
            url: "/admin/login",
            data: data1,
            type: "GET",
            dataType:"json",
            success: function (result) {
                //表示成功
                if (result.status == 0) {
                    self.location='./main.html';
                }  else {
                    alert(result.msg)
                }
            }
        });
    });
});