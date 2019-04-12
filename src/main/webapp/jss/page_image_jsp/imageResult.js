$(function () {

    var keyword = $("i#keyword").text();
    var pageIndex = $("i#pageIndex").text();
    var pageSize = $("i#pageSize").text();

    $.ajax({
        type: "GET",
        url: "getAllMatchingImages?keyword=" + keyword+"&pageIndex="+pageIndex+"&pageSize="+pageSize,
        dataType: "json",
        success: function (data) {
            var imageUl = $("ul.imageUl");
            var images = data;
            var simageHtml = "";
            for (var i=0;i<images.length;i++){
                simageHtml += "<li>\n" +
                    "            <p>\n" +
                    "                <img src=\""+images[i]["middleURL"]+"\"/>\n<br/>" +
                    "                <span class=\"title\">"+images[i]["fromPageTitleEnc"]+"</span>\n<br/>" +
                    "                <span class=\"type\">类型：&nbsp;&nbsp;"+images[i]["type"]+"</span>\n<br/>" +
                    "            </p>\n" +
                    "        </li>";
            }

            imageUl.html(simageHtml);
        }
    });


    //显示页码
    var pages = $("ul.list");
    var sPageHtml = "";
    for (var i=0;i<100;i++){
        if (i == pageIndex - 1){
            sPageHtml += "<li class='current'><a href=\"imageResult?keyword=" + keyword+"&pageIndex="+(i+1)+"&pageSize=60\">"+(i+1)+"</a></li>";
        }
        else{
            sPageHtml += "<li><a href=\"imageResult?keyword=" + keyword+"&pageIndex="+(i+1)+"&pageSize=60\">"+(i+1)+"</a></li>";
        }

    }
    pages.html(sPageHtml);

    //数据提交
    $("div.searchArea p input").on("keydown",function (event) {
        $("div.searchArea a").attr("href","imageResult?keyword=" + $(this).val()+"&pageIndex=1&pageSize=60");
        if (event.keyCode === 13 && $(this).val() !== ""){
            window.location = "imageResult?keyword=" + $(this).val()+"&pageIndex=1&pageSize=60";
        }
    });

    $("div.searchArea p input").val(keyword);

});