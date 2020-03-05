$(function () {
    //1.显示所有数据
    to_page(1);

    /**
     * 显示所有博客列表
     */
    function to_page(pn) {
        $.ajax({
            url: "/admin/getAllBlog",
            data:"pn="+pn,
            type: "GET",
            dataType: "json",
            success: function (result) {
                //alert(data.msg);
                //解析并显示博客
                listBlog_show(result)

                //2.解析并显示分页信息
                build_page_info(result);

                //3.解析并显示分页条数据
                build_page_nav(result);
            }
        })
    }

    /**
     * 解析并显示博客数据表
     * @param data
     */
    function listBlog_show(result) {
        var blog = result.data.list;
        var sb=[];
        //遍历元素
        $.each(blog, function (index, item) {
           sb+="<div class=post-head>";
           sb+="<h1 class=post-title><a href=\"\">"+item.title+"</a></h1>";
            sb+="<div class=post-meta><span class=author>作者：<a href=https://www.bootcss.com/ target=_blank>姜飞祥</a></span> &bull;";
            sb+="<time class=post-date datetime=\"July 28, 2016 11:29 AM\" title=\"July 28, 2016 11:29 AM\">2016年07月28日</time>";
            sb+="</div>";
            sb+="</div>";
            sb+="<div class=featured-media><a href=/post/laracon-laravel-5-3-recap/>\n" +
                "                            <img src=http://image.golaravel.com/5/1a/d0b8c085dec30605e2c3e9e773d00.png alt=\"图片显示异常\">\n" +
                "                        </a>\n" +
                "                        </div>";
            sb+="<div class=post-content id" +
                "des>\n" +
                "                            <p></p>\n" +
                "                            <p>"+item.des+"</p>\n" +
                "                            <p></p>\n" +
                "                        </div>";
            sb+="<div class=post-content id='context'>\n" +
            "                            <p></p>\n" +
            "                            <p>"+item.context+"</p>\n" +
            "                            <p></p>\n" +
            "                        </div>";
            sb+=" <div class=post-permalink id=\"contextshow\" ><button class=\"btn btn-default btn-fullContext\" data-id="+item.id+">阅读全文</button>\n" +
                "                        </div>\n" +
                "                        <footer class=\"post-footer clearfix\"></footer>";

        })
        $("#blogshow_list").html(sb);
    }

    /**
     * 解析显示分页信息
     * @param data
     */
    function build_page_info(result) {
        $("#page_info_area").empty();
        $("#page_info_area").append("当前" + result.data.pageNum + "页,总共" + result.data.pages +
            "页，总共" + result.data.total + "条记录");
        totalRecord = result.data.total;
        currentPage=result.data.pageNum;
    }

    /**
     * 解析显示分页导航条
     * @param data
     */
    function build_page_nav(result) {
        $("#page_nav_area").empty();
        var ul = $("<ul></ul>>").addClass("pagination");
        var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
        var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;").attr("href", "#"));
        var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;").attr("href", "#"));
        var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
        //如果没有前一页，前一页和首页就不能点
        if (result.data.hasPreviousPage == false) {
            firstPageLi.addClass("disabled");
            prePageLi.addClass("disabled");
        } else {
            //首页
            firstPageLi.click(function () {
                to_page(1);
            });
            prePageLi.click(function () {
                to_page(result.data.pageNum - 1);
            });
        }
        if (result.data.hasNextPage == false) {
            nextPageLi.addClass("disabled");
            lastPageLi.addClass("disabled");
        } else {
            //构建点击事件
            nextPageLi.click(function () {
                to_page(result.data.pageNum + 1);
            });
            lastPageLi.click(function () {
                to_page(result.data.lastPage);
            })
        }
        //添加首页和前一页
        ul.append(firstPageLi).append(prePageLi);
        //遍历添加页码
        $.each(result.data.navigatepageNums, function (index, item) {
            var numLi = $("<li></li>").append($("<a></a>").append(item).attr("href", "#"));
            //如果是当前选中页面，添加active标识
            if (result.data.pageNum == item) {
                numLi.addClass("active");
            }
            //给每个页码添加点击就跳转
            numLi.click(function () {
                to_page(item);
            });
            ul.append(numLi);
        });
        //添加下一页和末页
        ul.append(nextPageLi).append(lastPageLi);
        var navEle = $("<nav></nav>").append(ul);
        navEle.appendTo("#page_nav_area");
    }

    /**
     * 阅读全文
     */
    $(document).on("click", "#contextshow", function () {
        //var id = $(this).data("id");
        $("#context").hide();
    });
    $(document).on("click", "#contextshow", function () {
        //var id = $(this).data("id");
        $("#context").show();
    });
});
