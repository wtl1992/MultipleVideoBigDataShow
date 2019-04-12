$(function () {

    var keyword = $("i#keyword").text();

    $.ajax({
        type: "GET",
        url: "appSearchResultJson?keyword=" + keyword,
        dataType: "json",
        success: function (data) {
            var appUl = $("ul.appUl");
            var apps = data;
            var sappHtml = "";
            for (var i=0;i<apps.length;i++){
                sappHtml += "<li>\n" +
                    "            <p>\n" +
                    "                <img src=\""+apps[i]["iconUrl"]+"\"/>\n<br/>" +
                    "                <span class=\"title\">"+apps[i]["appName"]+"</span>\n<br/>" +
                    "                <span class=\"version\">版本：&nbsp;&nbsp;"+apps[i]["versionName"]+"</span>\n<br/>" +
                    "                <span class=\"size\">"+(apps[i]["fileSize"]*1.0/1024/1024).toFixed(2) +"MB"+"</span>\n" +
                    "            </p>\n" +
                    "            <p>\n" +
                    "                <a href=\""+apps[i]["apkUrl"]+"\">\n" +
                    "                    <img src=\"images/page_result_jsp/timg.png\"/>\n" +
                    "                </a>\n" +
                    "            </p>\n" +
                    "        </li>";
            }

            appUl.html(sappHtml);


            $("div.loadingTop").css({
                display:"none"
            });
        }
    });


    //数据提交
    $("div.searchArea p input").on("keydown",function (event) {
        $("div.searchArea a").attr("href","appSearchResult?keyword="+$(this).val());
        if (event.keyCode === 13 && $(this).val() !== ""){
            window.location = "appSearchResult?keyword="+$(this).val();
        }
    });


    $("div.searchArea p input").val(keyword);
});