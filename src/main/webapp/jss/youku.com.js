$(function () {

    $.ajax({
        type: "GET",
        url: "getAllYOUKUCOM_BigData",
        dataType: "json",
        success: function(data){
            var rightDatas = data["rightData"];
            var rightDataUl = $("#tagChange div.youkucom_top ul.rightData");
            var srightDataHtml = "";
            for (var i=0;i<rightDatas.length;i++){
                srightDataHtml += "<li><a href='"+rightDatas[i]["href"]+"' target='_blank'>\n" +
                    "                                    <span class=\"text\">"+rightDatas[i]["text"]+"&nbsp;&nbsp;<span class=\"sub_text\">"+rightDatas[i]["sub_text"]+"</span>" +
                    "                                    </span>\n" +
                    "                                </a>" +
                    "</li>";
            }
            rightDataUl.html(srightDataHtml);


            var oneLines = data["oneLine"];
            var topBarUl = $("#tagChange div.youkucom_top ul.topBar");
            var stopBarHtml = "";

            for (var i=0;i<oneLines.length;i++){
                stopBarHtml+= "<li>\n" +
                    "                                <a href='"+oneLines[i]["href"]+"' target='_blank'>\n" +
                    "                                    <img src='"+oneLines[i]["imgSrc"]+"'>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            topBarUl.html(stopBarHtml);



            try {
                var hotings = data["hoting"];
                var hotingUl = $("#tagChange div.youkucom_hoting ul");
                var shotingHtml = "";

                for (var i=0;i<hotings.length;i++){
                    shotingHtml += "<li>\n" +
                        "                                <a href='"+hotings[i]["href"]+"' target='_blank'>\n" +
                        "                                    <span class=\"time\">"+hotings[i]["updateTo"]+"</span>\n" +
                        "                                    <img src=\""+hotings[i]["imgSrc"]+"\">\n" +
                        "                                    <span class=\"sub_text\">"+hotings[i]["sub_text"]+"</span><br/>\n"
                    "                                </a>\n" +
                    "                            </li>";
                }
                hotingUl.html(shotingHtml);
            }
            catch (e) {
                hotingUl.html("暂无数据");
            }



            var episodes = data["episode"];
            var episodeUl = $("#tagChange div.youkucom_episode ul");
            var sepisodeHtml = "";

            for (var i=0;i<episodes.length;i++){
                sepisodeHtml += "<li>\n" +
                    "                                <a href='"+episodes[i]["href"]+"' target='_blank'>\n" +
                    "                                    <span class=\"time\">"+episodes[i]["updateTo"]+"</span>\n" +
                    "                                    <img src=\""+episodes[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+episodes[i]["sub_text"]+"</span><br/>\n"
                "                                </a>\n" +
                "                            </li>";
            }
            episodeUl.html(sepisodeHtml);



            var superNets = data["superNet"];
            var superNetUl = $("#tagChange div.youkucom_superNet ul");
            var ssuperNetHtml = "";

            for (var i=0;i<superNets.length;i++){
                ssuperNetHtml += "<li>\n" +
                    "                                <a href='"+superNets[i]["href"]+"' target='_blank'>\n" +
                    "                                    <span class=\"time\">"+superNets[i]["updateTo"]+"</span>\n" +
                    "                                    <img src=\""+superNets[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+superNets[i]["sub_text"]+"</span><br/>\n"
                "                                </a>\n" +
                "                            </li>";
            }
            superNetUl.html(ssuperNetHtml);


            var varietys = data["variety"];
            var varietyUl = $("#tagChange div.youkucom_variety ul");
            var svarietyHtml = "";
            try {
                for (var i=0;i<varietys.length;i++){
                    svarietyHtml += "<li>\n" +
                        "                                <a href='"+varietys[i]["href"]+"' target='_blank'>\n" +
                        "                                    <span class=\"time\">"+varietys[i]["updateTo"]+"</span>\n" +
                        "                                    <img src=\""+varietys[i]["imgSrc"]+"\">\n" +
                        "                                    <span class=\"sub_text\">"+varietys[i]["sub_text"]+"</span><br/>\n"
                    "                                </a>\n" +
                    "                            </li>";
                }
                varietyUl.html(svarietyHtml);
            }
            catch (e) {
                varietyUl.html("暂无数据");
            }



            var movies = data["movie"];
            var movieUl = $("#tagChange div.youkucom_movie ul");
            var smovieHtml = "";

            for (var i=0;i<movies.length;i++){
                smovieHtml += "<li>\n" +
                    "                                <a href='"+movies[i]["href"]+"' target='_blank'>\n" +
                    "                                    <span class=\"time\">"+movies[i]["text"]+"</span>\n" +
                    "                                    <img src=\""+movies[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+movies[i]["sub_text"]+"</span><br/>\n"
                "                                </a>\n" +
                "                            </li>";
            }
            movieUl.html(smovieHtml);


            var members = data["member"];
            var memberUl = $("#tagChange div.youkucom_member ul");
            var smemberHtml = "";
            try {
                for (var i=0;i<members.length;i++){
                    smemberHtml += "<li>\n" +
                        "                                <a href='"+members[i]["href"]+"' target='_blank'>\n" +
                        "                                    <span class=\"time\">"+members[i]["updateTo"]+"</span>\n" +
                        "                                    <img src=\""+members[i]["imgSrc"]+"\">\n" +
                        "                                    <span class=\"sub_text\">"+members[i]["sub_text"]+"</span><br/>\n"
                    "                                </a>\n" +
                    "                            </li>";
                }
                memberUl.html(smemberHtml);
            }
            catch (e) {
                memberUl.html("暂无数据");
            }



            try {
                var comics = data["comic"];
                var comicUl = $("#tagChange div.youkucom_comic ul");
                var scomicHtml = "";

                for (var i=0;i<comics.length;i++){
                    scomicHtml += "<li>\n" +
                        "                                <a href='"+comics[i]["href"]+"' target='_blank'>\n" +
                        "                                    <span class=\"time\">"+comics[i]["updateTo"]+"</span>\n" +
                        "                                    <img src=\""+comics[i]["imgSrc"]+"\">\n" +
                        "                                    <span class=\"sub_text\">"+comics[i]["sub_text"]+"</span><br/>\n"
                    "                                </a>\n" +
                    "                            </li>";
                }
                comicUl.html(scomicHtml);
            }
            catch (e) {
                comicUl.html("暂无数据");
            }



            var bigFishs = data["bigFish"];
            var bigFishUl = $("#tagChange div.youkucom_bigFish ul");
            var sbigFishHtml = "";

            for (var i=0;i<bigFishs.length;i++){
                sbigFishHtml += "<li>\n" +
                    "                                <a href='"+bigFishs[i]["href"]+"' target='_blank'>\n" +
                    "                                    <span class=\"time\">"+bigFishs[i]["timeLength"]+"</span>\n" +
                    "                                    <img src=\""+bigFishs[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+bigFishs[i]["sub_text"]+"</span><br/>\n"
                "                                </a>\n" +
                "                            </li>";
            }
            bigFishUl.html(sbigFishHtml);



            var entertainments = data["entertainment"];
            var entertainmentUl = $("#tagChange div.youkucom_entertainment ul");
            var sentertainmentHtml = "";

            for (var i=0;i<entertainments.length;i++){
                sentertainmentHtml += "<li>\n" +
                    "                                <a href='"+entertainments[i]["href"]+"' target='_blank'>\n" +
                    "                                    <span class=\"time\">"+entertainments[i]["timeLength"]+"</span>\n" +
                    "                                    <img src=\""+entertainments[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+entertainments[i]["text"]+"</span><br/>\n"
                "                                </a>\n" +
                "                            </li>";
            }
            entertainmentUl.html(sentertainmentHtml);
        }
    });

});