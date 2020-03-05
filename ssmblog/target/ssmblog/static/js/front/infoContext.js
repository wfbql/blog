$(function () {
    var loc = location.href;
    var n1 = loc.length;//地址的总长度
    var n2 = loc.indexOf("=");//取得=号的位置
    var id = decodeURI(loc.substr(n2+1, n1-n2));//从=号后面的内容
    var sb=[];
    $.ajax({
        url: "/blog/getById",
        data: "id="+id,
        type: "GET",
        dataType:"json",
        success: function (data) {
            //表示成功
            if (data.code == 200) {
                sb+="<div class=post-head>";
                sb+="<h1 class=post-title><a href=\"\">"+data.pageInfo.title+"</a></h1>";
                sb+="<div class=post-meta><span class=author>作者：<a href=https://www.bootcss.com/ target=_blank>姜飞祥</a></span> &bull;";
                sb+="<time class=post-date datetime=\"July 28, 2016 11:29 AM\" title=\"July 28, 2016 11:29 AM\">2016年07月28日</time>";
                sb+="</div>";
                sb+="</div>";
                sb+="<div class=featured-media><a href=/post/laracon-laravel-5-3-recap/>\n" +
                    "                            <img src=http://image.golaravel.com/5/1a/d0b8c085dec30605e2c3e9e773d00.png alt=\"图片显示异常\">\n" +
                    "                        </a>\n" +
                    "                        </div>";
                sb+="<div class=post-content>\n" +
                    "                            <p></p>\n" +
                    "                            <p>"+data.pageInfo.context+"</p>\n" +
                    "                            <p></p>\n" +
                    "                        </div>";
                $("#infoContext").html(sb);
            }  else {
                alert("失败")
            }
        }
    });

})