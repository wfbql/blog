$(function () {
    $("#addBlog_btn").click(function () {
        var data1 = {
            "title": $("#title").val(),
            "des": $("#des").val(),
            "context":editor.txt.text()
        }
        $.ajax({
            url:"/admin/saveBlog",
            type:"POST",
            data:data1,
            dataType:"json",
            success:function (result) {
                if (result.status == 200){
                    alert("发布成功")
                    self.location='./blogList.html';
                }
            }
        });
    });
});