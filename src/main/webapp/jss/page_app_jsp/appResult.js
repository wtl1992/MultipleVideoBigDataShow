$(function () {

    var categoryId = $("i#categoryId").text();
    var pageSize = $("i#pageSize").text();
    var pageContext = $("i#pageContext").text();

    $.ajax({
        type: "GET",
        url: "getClassifyApps?categoryId=" + categoryId+"&pageSize="+pageSize+"&pageContext="+pageContext,
        dataType: "json",
        success: function (data) {
            var appUl = $("ul.appUl");
            var apps = data["apps"];
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
        }
    });


    //显示页码
    var pages = $("ul.list");
    var sPageHtml = "";
    for (var i=0;i<100;i++){
        if (i == pageContext - 1){
            sPageHtml += "<li class='current'><a href=\"appResult?categoryId=" + categoryId+"&pageSize="+pageSize+"&pageContext="+(i+1)+"\">"+(i+1)+"</a></li>";
        }
        else{
            sPageHtml += "<li><a href=\"appResult?categoryId=" + categoryId+"&pageSize="+pageSize+"&pageContext="+(i+1)+"\">"+(i+1)+"</a></li>";
        }

    }
    pages.html(sPageHtml);


    //设置链接列表
    var classifyUl = $("div.classifyUrl ul");
    var keywords = ["腾讯软件","购物","阅读","新闻","视频","旅游"
    ,"工具","社交","音乐","美化","摄影","理财","系统","生活","出行",
        "安全","教育","健康","娱乐","儿童","办公","通讯"];
    var categoryIds = [-10,122,102,110,103,108,115,106,101,119,104,114,117,107,
    112,118,111,109,105,100,113,116];
    // console.dir(categoryIds.length)
    // console.dir(keywords.length)
    var sclassifyHtml = "";
    for (var i=0;i<keywords.length;i++){
        if (categoryId == categoryIds[i]){
            sclassifyHtml += "<li class='current'>\n" +
                "                <a href=\"appResult?categoryId=" + categoryIds[i]+"&pageSize="+pageSize+"&pageContext=1\">"+keywords[i]+"</a>\n" +
                "            </li>";
        }
        else{
            sclassifyHtml += "<li>\n" +
                "                <a href=\"appResult?categoryId=" + categoryIds[i]+"&pageSize="+pageSize+"&pageContext=1\">"+keywords[i]+"</a>\n" +
                "            </li>";
        }

    }
    classifyUl.html(sclassifyHtml);


    //数据提交
    $("div.searchArea p input").on("keydown",function (event) {
        $("div.searchArea a").attr("href","appSearchResult?keyword="+$(this).val());
        if (event.keyCode === 13 && $(this).val() !== ""){
            window.location = "appSearchResult?keyword="+$(this).val();
        }
    });

});