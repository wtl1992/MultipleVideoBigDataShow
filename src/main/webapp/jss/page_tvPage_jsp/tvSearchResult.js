$(function () {

    var keyword = $("i#keyword").text();

    $.ajax({
        type: "GET",
        url: "selectByTvLikeTitle?keyword=" + keyword,
        dataType: "json",
        success: function (data) {
            var tvUl = $("ul.tvUl");
            var tvList = data;
            var stvHtml = "";

            for (var i=0;i<tvList.length;i++){

                console.dir(tvList[i])

                var sStars = "主演&nbsp;&nbsp;";

                var starsObj = tvList[i]["tvStars"];

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
                    "                <img src=\""+tvList[i]["imgsrc"]+"\"/>\n<br/>" +
                    "                <span class=\"title\">"+tvList[i]["title"]+"</span>\n<br/>" +
                    "                <span class=\"zhuyan\">"+sStars+"</span>\n<br/>" +
                    "                <span class=\"jiNumber\">"+(tvList[i]["jinumber"] === undefined ? "" : tvList[i]["jinumber"])+"</span>\n" +
                    "            </a>\n" +
                    "        </li>";
            }

            tvUl.html(stvHtml);


            if (tvList.length === 0){
                window.location = "tvResult?pageIndex=1";
            }


            $("div.loadingTop").css({
                display:"none"
            });
        }
    });


    //数据提交
    $("div.searchArea p input").on("keydown",function (event) {
        $("div.searchArea a").attr("href","tvSearchResult?keyword="+$(this).val());
        if (event.keyCode === 13 && $(this).val() !== ""){
            window.location = "tvSearchResult?keyword="+$(this).val();
        }
    });


    $("div.searchArea p input").val(keyword);
});