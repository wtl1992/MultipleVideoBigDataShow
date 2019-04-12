$(function () {

    $.ajax({
        type: "GET",
        url: "getAllIQIYICOM_BigData",
        dataType: "json",
        success: function(data){
            var rightDatas = data["rightData"];
            var rightDataUl = $("#tagChange div.iqiyicom_top ul.rightData");
            var srightDataHtml = "";
            for (var i=0;i<rightDatas.length;i++){
                srightDataHtml += "<li><a href='"+rightDatas[i]["href"]+"' target='_blank'>\n" +
                    "                                    <span class=\"text\">"+rightDatas[i]["text"] +
                    "                                    </span>\n" +
                    "                                </a>" +
                    "</li>";
            }
            rightDataUl.html(srightDataHtml);



            var banners = data["banner"];
            var topBarUl = $("#tagChange div.iqiyicom_top ul.topBar");
            var stopBarHtml = "";

            for (var i=0;i<banners.length;i++){
                stopBarHtml+= "<li>\n" +
                    "                                <a href='"+banners[i]["href"]+"' target='_blank'>\n" +
                    "                                    <img src='"+banners[i]["imgSrc"]+"'>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            topBarUl.html(stopBarHtml);



            var secondRights = data["secondRight"];
            var secondRightUl = $("#tagChange div.iqiyicom_secondRight ul");
            var ssecondRightHtml = "";

            for (var i=0;i<secondRights.length;i++){
                ssecondRightHtml += "<li>\n" +
                    "                                <a href='"+secondRights[i]["href"]+"' target='_blank'>\n" +
                    "                                    <img src=\""+secondRights[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+secondRights[i]["text"]+"</span><br/>\n"
                "                                </a>\n" +
                "                            </li>";
            }
            secondRightUl.html(ssecondRightHtml);



            var varietys = data["variety"];
            var varietyUl = $("#tagChange div.iqiyicom_variety ul");
            var svarietyHtml = "";

            for (var i=0;i<varietys.length;i++){
                svarietyHtml += "<li>\n" +
                    "                                <a href='"+varietys[i]["href"]+"' target='_blank'>\n" +
                    "                                    <img src=\""+varietys[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+varietys[i]["text"]+"</span><br/>\n"
                "                                </a>\n" +
                "                            </li>";
            }
            varietyUl.html(svarietyHtml);



            var homemades = data["homemade"];
            var homemadeUl = $("#tagChange div.iqiyicom_homemade ul");
            var shomemadeHtml = "";

            for (var i=0;i<homemades.length;i++){
                shomemadeHtml += "<li>\n" +
                    "                                <a href='"+homemades[i]["href"]+"' target='_blank'>\n" +
                    "                                    <img src=\""+homemades[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+homemades[i]["text"]+"</span><br/>\n"
                "                                </a>\n" +
                "                            </li>";
            }
            homemadeUl.html(shomemadeHtml);



            var talkShows = data["talkShow"];
            var talkShowUl = $("#tagChange div.iqiyicom_talkShow ul");
            var stalkShowHtml = "";

            for (var i=0;i<talkShows.length;i++){
                stalkShowHtml += "<li>\n" +
                    "                                <a href='"+talkShows[i]["href"]+"' target='_blank'>\n" +
                    "                                    <span class=\"time\">"+talkShows[i]["text"]+"</span>\n" +
                    "                                    <img src=\""+talkShows[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+talkShows[i]["sub_text2"]+"</span><br/>\n"
                "                                </a>\n" +
                "                            </li>";
            }
            talkShowUl.html(stalkShowHtml);



            var entertainments = data["entertainment"];
            var entertainmentUl = $("#tagChange div.iqiyicom_entertainment ul");
            var sentertainmentHtml = "";

            for (var i=0;i<entertainments.length;i++){
                sentertainmentHtml += "<li>\n" +
                    "                                <a href='"+entertainments[i]["href"]+"' target='_blank'>\n" +
                    "                                    <img src=\""+entertainments[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+entertainments[i]["text"]+"</span><br/>\n"
                "                                </a>\n" +
                "                            </li>";
            }
            entertainmentUl.html(sentertainmentHtml);



            var movies = data["movie"];
            var movieUl = $("#tagChange div.iqiyicom_movie ul");
            var smovieHtml = "";

            for (var i=0;i<movies.length;i++){
                smovieHtml += "<li>\n" +
                    "                                <a href='"+movies[i]["href"]+"' target='_blank'>\n" +
                    "                                    <img src=\""+movies[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+movies[i]["text"]+"</span><br/>\n"
                "                                </a>\n" +
                "                            </li>";
            }
            movieUl.html(smovieHtml);



            var tvs = data["tv"];
            var tvUl = $("#tagChange div.iqiyicom_tv ul");
            var stvHtml = "";

            for (var i=0;i<tvs.length;i++){
                stvHtml += "<li>\n" +
                    "                                <a href='"+tvs[i]["href"]+"' target='_blank'>\n" +
                    "                                    <span class=\"time\">"+(tvs[i]["sns_score"] != null ? tvs[i]["sns_score"] +"分" : "")+"</span>\n" +
                    "                                    <img src=\""+tvs[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+tvs[i]["text"]+"</span><br/>\n"
                "                                </a>\n" +
                "                            </li>";
            }
            tvUl.html(stvHtml);



            var comics = data["comic"];
            var comicUl = $("#tagChange div.iqiyicom_comic ul");
            var scomicHtml = "";

            for (var i=0;i<comics.length;i++){
                scomicHtml += "<li>\n" +
                    "                                <a href='"+comics[i]["href"]+"' target='_blank'>\n" +
                    "                                    <span class=\"time\">"+(comics[i]["score"] != null ? comics[i]["score"] +"分" : "")+"</span>\n" +
                    "                                    <img src=\""+comics[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+comics[i]["text"]+"</span><br/>\n"
                "                                </a>\n" +
                "                            </li>";
            }
            comicUl.html(scomicHtml);



            var childrens = data["children"];
            var childrenUl = $("#tagChange div.iqiyicom_children ul");
            var schildrenHtml = "";

            for (var i=0;i<childrens.length;i++){
                schildrenHtml += "<li>\n" +
                    "                                <a href='"+childrens[i]["href"]+"' target='_blank'>\n" +
                    "                                    <span class=\"time\">"+(childrens[i]["score"] != null ? childrens[i]["score"] +"分" : "")+"</span>\n" +
                    "                                    <img src=\""+childrens[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+childrens[i]["text"]+"</span><br/>\n"
                "                                </a>\n" +
                "                            </li>";
            }
            childrenUl.html(schildrenHtml);



            var games = data["game"];
            var gameUl = $("#tagChange div.iqiyicom_game ul");
            var sgameHtml = "";

            for (var i=0;i<games.length;i++){
                sgameHtml += "<li>\n" +
                    "                                <a href='"+games[i]["href"]+"' target='_blank'>\n" +
                    "                                    <span class=\"time\">"+(games[i]["score"] != null ? games[i]["score"] +"分" : "")+"</span>\n" +
                    "                                    <img src=\""+games[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+games[i]["text"]+"</span><br/>\n"
                "                                </a>\n" +
                "                            </li>";
            }
            gameUl.html(sgameHtml);



            var sports = data["sports"];
            var sportsUl = $("#tagChange div.iqiyicom_sports ul");
            var ssportsHtml = "";

            for (var i=0;i<sports.length;i++){
                ssportsHtml += "<li>\n" +
                    "                                <a href='"+sports[i]["href"]+"' target='_blank'>\n" +
                    "                                    <img src=\""+sports[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+sports[i]["text"]+"</span><br/>\n"
                "                                </a>\n" +
                "                            </li>";
            }
            sportsUl.html(ssportsHtml);



            var originals = data["original"];
            var originalUl = $("#tagChange div.iqiyicom_original ul");
            var soriginalHtml = "";

            for (var i=0;i<originals.length;i++){
                soriginalHtml += "<li>\n" +
                    "                                <a href='"+originals[i]["href"]+"' target='_blank'>\n" +
                    "                                    <img src=\""+originals[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+originals[i]["text"]+"</span><br/>\n"
                "                                </a>\n" +
                "                            </li>";
            }
            originalUl.html(soriginalHtml);



            var documentarys = data["documentary"];
            var documentaryUl = $("#tagChange div.iqiyicom_documentary ul");
            var sdocumentaryHtml = "";

            for (var i=0;i<documentarys.length;i++){
                sdocumentaryHtml += "<li>\n" +
                    "                                <a href='"+documentarys[i]["href"]+"' target='_blank'>\n" +
                    "                                    <img src=\""+documentarys[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+documentarys[i]["text"]+"</span><br/>\n"
                "                                </a>\n" +
                "                            </li>";
            }
            documentaryUl.html(sdocumentaryHtml);
        }
    });

});