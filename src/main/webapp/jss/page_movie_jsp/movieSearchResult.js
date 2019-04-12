$(function () {

    var keyword = $("i#keyword").text();

    $.ajax({
        type: "GET",
        url: "selectByMovieLikeTitle?keyword=" + keyword,
        dataType: "json",
        success: function (data) {
            var movieUl = $("ul.movieUl");
            var movieList = data;
            var smovieHtml = "";

            for (var i=0;i<movieList.length;i++){

                var sStars = "主演&nbsp;&nbsp;";

                var starsObj = movieList[i]["movieStars"];

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

                smovieHtml += "<li>\n" +
                    "            <a href=\""+movieList[i]["href"]+"\" target=\"_blank\">\n" +
                    "                <img src=\""+movieList[i]["imgsrc"]+"\"/>\n<br/>" +
                    "                <span class=\"title\">"+movieList[i]["title"]+"</span>\n<br/>" +
                    "                <span class=\"zhuyan\">"+sStars+"</span>\n<br/>" +
                    "                <span class=\"jiNumber\">"+(movieList[i]["jinumber"] === undefined ? "" : movieList[i]["jinumber"])+"</span>\n" +
                    "            </a>\n" +
                    "        </li>";
            }

            movieUl.html(smovieHtml);


            if (movieList.length === 0){
                window.location = "movieResult?pageIndex=1";
            }


            $("div.loadingTop").css({
                display:"none"
            });
        }
    });


    //数据提交
    $("div.searchArea p input").on("keydown",function (event) {
        $("div.searchArea a").attr("href","movieSearchResult?keyword="+$(this).val());
        if (event.keyCode === 13 && $(this).val() !== ""){
            window.location = "movieSearchResult?keyword="+$(this).val();
        }
    });


    $("div.searchArea p input").val(keyword);
});