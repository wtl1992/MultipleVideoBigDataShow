$(function () {

    var pageIndex = $("i#pageIndex").text();

    $.ajax({
        type: "GET",
        url: "tvPlayResult?pageIndex=" + pageIndex,
        dataType: "json",
        success: function (data) {
            var tvUl = $("ul.tvUl");
            var tvList = data["tvList"];
            var stvHtml = "";

            for (var i=0;i<tvList.length;i++){

                var sStars = "主演&nbsp;&nbsp;";

                var starsObj = tvList[i]["star"]["starts"];

                // console.dir($.type(starsObj))
                var length = 3;
                if ($.type(starsObj) !== "string"){
                    if (starsObj.length > 3){
                        length = 3;
                    }
                    else{
                        length = starsObj.length;
                    }
                    for (var j=0;j<length;j++){
                        sStars += (starsObj[j]["name"] +"&nbsp;&nbsp;");
                    }
                }
                else{
                    sStars = starsObj;
                }

                stvHtml += "<li>\n" +
                    "            <a href=\""+tvList[i]["href"]+"\" target=\"_blank\">\n" +
                    "                <img src=\""+tvList[i]["imgSrc"]+"\"/>\n<br/>" +
                    "                <span class=\"title\">"+tvList[i]["title"]+"</span>\n<br/>" +
                    "                <span class=\"zhuyan\">"+sStars+"</span>\n<br/>" +
                    "                <span class=\"jiNumber\">"+(tvList[i]["jiNumber"] === undefined ? "" : tvList[i]["jiNumber"])+"</span>\n" +
                    "            </a>\n" +
                    "        </li>";
            }

            tvUl.html(stvHtml);


            $("div.loadingTop").css({
                display:"none"
            });
        }
    });


    //显示页码
    var pages = $("ul.list");
    var sPageHtml = "";
    for (var i=0;i<100;i++){
        if (i == pageIndex - 1){
            sPageHtml += "<li class='current'><a href=\"tvResult?pageIndex="+(i+1)+"\">"+(i+1)+"</a></li>";
        }
        else{
            sPageHtml += "<li><a href=\"tvResult?pageIndex="+(i+1)+"\">"+(i+1)+"</a></li>";
        }

    }
    pages.html(sPageHtml);



    //数据提交
    $("div.searchArea p input").on("keydown",function (event) {
        $("div.searchArea a").attr("href","tvSearchResult?keyword="+$(this).val());
        if (event.keyCode === 13 && $(this).val() !== ""){
            window.location = "tvSearchResult?keyword="+$(this).val();
        }
    });

});