$(function () {

    var keyword = $("i#keyword").text();
    var pageIndex = $("i#pageIndex").text();

    $.ajax({
        type: "GET",
        url: "getSearchPagingBaiduYunSources?keyword=" + keyword+"&pageIndex="+pageIndex,
        dataType: "json",
        success: function (data) {
            //显示页码
            var pages = $("ul.list");
            var sPageHtml = "";
            if (data.length > 0){
                for (var i=0;i<data[0]["pageCount"];i++){
                    if (i == pageIndex - 1){
                        sPageHtml += "<li class='current'><a href=\"m/baiduyunwangpanSearchResult?keyword="+keyword+"&pageIndex="+(i+1)+"\">"+(i+1)+"</a></li>";
                    }
                    else{
                        sPageHtml += "<li><a href=\"m/baiduyunwangpanSearchResult?keyword="+keyword+"&pageIndex="+(i+1)+"\">"+(i+1)+"</a></li>";
                    }

                }
            }
            pages.html(sPageHtml);


            var baiduUl = $("ul.baiduUl");
            var baidus = data;
            var sbaiduHtml = "";
            for (var i=0;i<baidus.length;i++){
                sbaiduHtml += "<li>\n" +
                    "                <p class=\"title\">\n" +
                    "                    <a href=\""+baidus[i]["link"]+"\" target='_blank'>"+baidus[i]["title"]+"</a>\n" +
                    "                </p>\n" +
                    "                <p class=\"description\">\n" + baidus[i]["des"] +
                    "                </p>\n" +
                    "            </li>";
            }
            baiduUl.html(sbaiduHtml);


            $("div.loadingTop").css({
                display:"none"
            });
        }
    });


    //数据提交
    $("div.searchArea p input").on("keydown",function (event) {
        $("div.searchArea a").attr("href","m/baiduyunwangpanSearchResult?keyword=" + $(this).val()+"&pageIndex=1");
        if (event.keyCode === 13 && $(this).val() !== ""){
            window.location = "m/baiduyunwangpanSearchResult?keyword=" + $(this).val()+"&pageIndex=1";
        }
    });

    $("div.searchArea p input").val(keyword);
});