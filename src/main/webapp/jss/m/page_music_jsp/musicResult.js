$(function () {

    var keyword = $("i#keyword").text();
    var pageIndex = $("i#pageIndex").text();
    var pageSize = $("i#pageSize").text();

    $.ajax({
        type: "GET",
        url: "getAllMatchingMusics?keyword=" + keyword+"&pageIndex="+pageIndex+"&pageSize="+pageSize,
        dataType: "json",
        success: function (data) {
            var musicUl = $("ul.musicUl");
            var musics = data;
            var smusicHtml = "";
            for (var i=0;i<musics.length;i++){
                smusicHtml += "<li>\n" +
                    "            <p>\n" +
                    "                <img src='"+("exchangeMusicsUrl?url="+window.encodeURIComponent(musics[i]["pic"]))+"'/>\n<br/>" +
                    "                <span class=\"title\">"+musics[i]["name"]+"</span>\n<br/>" +
                    "                <span class=\"author\">"+musics[i]["singer"]+"</span>\n<br/>" +
                    "                <span class=\"audio\" _src='"+("exchangeMusicsUrl?url="+window.encodeURIComponent(musics[i]["url"]))+"' _title='"+musics[i]["name"]+"'>播放</span>\n<br/>" +
                    "            </p>\n" +
                    "        </li>";
            }

            window.setTimeout(function () {
                //处理视频播放
                $("span.audio").on("click",function (event) {
                    $("audio").attr("src",$(this).attr("_src"));
                    document.title = "祥龙检索，搜音乐--" + $(this).attr("_title");
                });
            },1000);

            musicUl.html(smusicHtml);
        }
    });


    //显示页码
    var pages = $("ul.list");
    var sPageHtml = "";
    for (var i=0;i<20;i++){
        if (i == pageIndex - 1){
            sPageHtml += "<li class='current'><a href=\"m/musicResult?keyword="+keyword+"&pageIndex="+(i+1)+"&pageSize=20\">"+(i+1)+"</a></li>";
        }
        else{
            sPageHtml += "<li><a href=\"m/musicResult?keyword="+keyword+"&pageIndex="+(i+1)+"&pageSize=20\">"+(i+1)+"</a></li>";
        }

    }
    pages.html(sPageHtml);

    //数据提交
    $("div.searchArea p input").on("keydown",function (event) {
        $("div.searchArea a").attr("href","m/musicResult?keyword=" + $(this).val()+"&pageIndex=1&pageSize=20");
        if (event.keyCode === 13 && $(this).val() !== ""){
            window.location = "m/musicResult?keyword=" + $(this).val()+"&pageIndex=1&pageSize=20";
        }
    });

    $("div.searchArea p input").val(keyword);
});