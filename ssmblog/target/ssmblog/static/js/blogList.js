$(function(){
    var totalRecord,currentPage;
    //1.显示所有数据
    to_page(1);

    /**
     * 1.显示所有博客列表
     * @param pn
     */
    function to_page(pn) {
       // alert("111")
        $.ajax({
            url: "/admin/getAllBlog",
            data:"pn="+pn,
            type: "GET",
            dataType: "json",
            success: function (result) {
                //alert(data.msg);
                //解析并显示员工数据表
                build_blog_table(result)

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
    function build_blog_table(result) {
        //清空table表格
        $("#blog_table tbody").empty();
        var blog = result.data.list;

        //遍历元素
        $.each(blog, function (index, item) {
            var checkBox=$("<td><input type='checkbox' class='check_item'/></td>");
            var id = $("<td></td>").append(item.id);
            var title = $("<td></td>").append(item.title);
            var des = $("<td></td>").append(item.des);

            var button1 = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn").append($("<span></span>").addClass("glyphicon glyphicon-pencil").attr("aria-hidden", true)).append("编辑");
            var button2 = $("<button></button>").addClass("tn btn-danger btn-sm delete_btn").append($("<span></span>").addClass("glyphicon glyphicon-trash").attr("aria-hidden", true)).append("删除");
            var td_btn = $("<td></td>").append(button1).append(" ").append(button2);
            $("<tr></tr>").append(checkBox).append(id).append(title).append(des).append(td_btn ).appendTo("#blog_table tbody");

        })
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
     * 删除博客
     */
    //给删除按钮绑定单机时间
    $(document).on("click",".delete_btn",function (){
        //1.弹出确认删除对话框
        var id=$(this).parents("tr").find("td:eq(1)").text();
        var data1={
            "id":id
        };
        if(confirm("确认删除吗？")){
            //确认，发送ajax请求删除
            $.ajax({
                url:"/admin/deleteBlog",
                data:data1,
                type:"GET",
                success:function (result) {
                    if (result.status == 0){
                        alert("删除成功");
                        to_page(currentPage);
                    }else {
                        alert("删除失败");
                    }

                }
            })
        }
    });
});