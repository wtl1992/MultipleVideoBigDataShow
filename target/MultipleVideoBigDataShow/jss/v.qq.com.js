$(function () {

    //添加图片的加载动画
    $("div.loading_pic").css({
        width : window.innerWidth + "px",
        height : window.innerHeight + "px",
        lineHeight : window.innerHeight /2 +"px"
    });

    $("body").css({
        height : window.innerHeight + "px",
        overflow : "hidden"
    });

    window.onresize = function (ev) {
        $("div.loading_pic").css({
            width : window.innerWidth + "px",
            height : window.innerHeight + "px",
            lineHeight : window.innerHeight /2 +"px"
        });
        $("body").css({
            height : window.innerHeight + "px",
            overflow : "hidden"
        });
    };

    $.ajax({
        type: "GET",
        url: "getAllVQQCOM_BigData",
        dataType: "json",
        success: function(data){
            var rightDatas = data["rightDatas"];
            var orightDataUl = $("#tagChange div.vqqcom_top ul.rightData");
            var srightDataHtml = "";
            for (var i=0;i<rightDatas.length;i++){
                srightDataHtml += "<li><a href='"+rightDatas[i]["href"]+"' target='_blank'>\n" +
                    "                                    <span class=\"text\">"+rightDatas[i]["text"]+"&nbsp;&nbsp;<span class=\"sub_text\">"+rightDatas[i]["sub_text"]+"</span>" +
                    "                                    </span>\n" +
                    "                                </a>" +
                    "</li>";
            }
            orightDataUl.html(srightDataHtml);

            var banners = data["rightDatas"];
            var topBarUl = $("#tagChange div.vqqcom_top ul.topBar");
            var stopBarHtml = "";

            for (var i=0;i<banners.length;i++){
                stopBarHtml+= "<li style='background-color: "+banners[i]["rgb"]+";'>\n" +
                    "                                <a href='"+banners[i]["href"]+"' target='_blank'>\n" +
                    "                                    <img src='"+banners[i]["imgSrc"]+"'>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            topBarUl.html(stopBarHtml);


            var todayHots = data["todayHot"];
            var todayHotUl = $("#tagChange div.vqqcom_todayHot ul");
            var stodayHotHtml = "";

            for (var i=0;i<todayHots.length;i++){
                stodayHotHtml += "<li>\n" +
                    "                                <a href='"+todayHots[i]["href"]+"' target='_blank'>\n" +
                    "                                    <span class=\"time\">"+todayHots[i]["timeLength"]+"</span>\n" +
                    "                                    <img src=\""+todayHots[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"mainText\">"+todayHots[i]["text"]+"</span><br/>\n"
                    "                                </a>\n" +
                    "                            </li>";
            }
            todayHotUl.html(stodayHotHtml);

            var originals = data["original"];
            var originalUl = $("#tagChange div.vqqcom_original ul");
            var soriginalHtml = "";

            for (var i=0;i<originals.length;i++){
                soriginalHtml += "<li>\n" +
                    "                                <a href=\""+originals[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"timeLength\">"+originals[i]["timeLength"]+"</span>\n" +
                    "                                    <img src=\""+originals[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"mainText\">"+originals[i]["detailText"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            originalUl.html(soriginalHtml);

            var mightys = data["mighty"];
            var mightyUl = $("#tagChange div.vqqcom_mighty ul");
            var smightyHtml = "";

            for (var i=0;i<mightys.length;i++){
                smightyHtml += "<li>\n" +
                    "                                <a href=\""+mightys[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"index\">"+mightys[i]["expectationIndex"]+"</span>\n" +
                    "                                    <img src=\""+mightys[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+mightys[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            mightyUl.html(smightyHtml);

            var synchronous = data["synchronous"];
            var synchronousUl = $("#tagChange div.vqqcom_synchronous ul");
            var ssynchronousHtml = "";

            for (var i=0;i<synchronous.length;i++){
                ssynchronousHtml += "<li>\n" +
                    "                                <a href=\""+synchronous[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"updateTo\">"+synchronous[i]["updateTo"]+"</span>\n" +
                    "                                    <img src=\""+synchronous[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+synchronous[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            synchronousUl.html(ssynchronousHtml);

            var trivias = data["trivia"];
            var triviaUl = $("#tagChange div.vqqcom_trivia ul");
            var striviaHtml = "";

            for (var i=0;i<trivias.length;i++){
                striviaHtml += "<li>\n" +
                    "                                <a href=\""+trivias[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"timeLength\">"+trivias[i]["timeLength"]+"</span>\n" +
                    "                                    <img src=\""+trivias[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+trivias[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            triviaUl.html(striviaHtml);

            var varietys = data["variety"];
            var varietyUl = $("#tagChange div.vqqcom_variety ul");
            var svarietyHtml = "";

            for (var i=0;i<varietys.length;i++){
                svarietyHtml += "<li>\n" +
                    "                                <a href=\""+varietys[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"qiNumber\">"+varietys[i]["qiNumber"]+"</span>\n" +
                    "                                    <img src=\""+varietys[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+varietys[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            varietyUl.html(svarietyHtml);

            var memberships = data["membership"];
            var membershipUl = $("#tagChange div.vqqcom_membership ul");
            var smembershipHtml = "";

            for (var i=0;i<memberships.length;i++){
                smembershipHtml += "<li>\n" +
                    "                                <a href=\""+memberships[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"qiNumber\">"+memberships[i]["qiNumber"]+"</span>\n" +
                    "                                    <img src=\""+memberships[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+memberships[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            membershipUl.html(smembershipHtml);

            var movies = data["movie"];
            var movieUl = $("#tagChange div.vqqcom_movie ul");
            var smovieHtml = "";

            for (var i=0;i<movies.length;i++){
                smovieHtml += "<li>\n" +
                    "                                <a href=\""+movies[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"timeLength\">"+movies[i]["timeLength"]+"</span>\n" +
                    "                                    <img src=\""+movies[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+movies[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            movieUl.html(smovieHtml);

            var moviePredicts = data["moviePredict"];
            var moviePredictUl = $("#tagChange div.vqqcom_moviePredict ul");
            var smoviePredictHtml = "";

            for (var i=0;i<moviePredicts.length;i++){
                smoviePredictHtml += "<li>\n" +
                    "                                <a href=\""+moviePredicts[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"timeLength\">"+moviePredicts[i]["timeLength"]+"</span>\n" +
                    "                                    <img src=\""+moviePredicts[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+moviePredicts[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            moviePredictUl.html(smoviePredictHtml);


            var comics = data["comic"];
            var comicUl = $("#tagChange div.vqqcom_comic ul");
            var scomicHtml = "";

            for (var i=0;i<comics.length;i++){
                scomicHtml += "<li>\n" +
                    "                                <a href=\""+comics[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"updateTo\">"+comics[i]["updateTo"]+"</span>\n" +
                    "                                    <img src=\""+comics[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+comics[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            comicUl.html(scomicHtml);


            var childrens = data["children"];
            var childrenUl = $("#tagChange div.vqqcom_children ul");
            var schildrenHtml = "";

            // console.dir(childrenUl)

            for (var i=0;i<childrens.length;i++){
                schildrenHtml += "<li>\n" +
                    "                                <a href=\""+childrens[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"timeLength\">"+childrens[i]["timeLength"]+"</span>\n" +
                    "                                    <img src=\""+childrens[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+childrens[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            childrenUl.html(schildrenHtml);

            var documentarys = data["documentary"];
            var documentaryUl = $("#tagChange div.vqqcom_documentary ul");
            var sdocumentaryHtml = "";

            for (var i=0;i<documentarys.length;i++){
                sdocumentaryHtml += "<li>\n" +
                    "                                <a href=\""+documentarys[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"timeLength\">"+documentarys[i]["timeLength"]+"</span>\n" +
                    "                                    <img src=\""+documentarys[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+documentarys[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            documentaryUl.html(sdocumentaryHtml);


            var britishAmericanDramas = data["britishAmericanDrama"];
            var britishAmericanDramaUl = $("#tagChange div.vqqcom_britishAmericanDrama ul");
            var sbritishAmericanDramaHtml = "";

            for (var i=0;i<britishAmericanDramas.length;i++){
                sbritishAmericanDramaHtml += "<li>\n" +
                    "                                <a href=\""+britishAmericanDramas[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"timeLength\">"+britishAmericanDramas[i]["timeLength"]+"</span>\n" +
                    "                                    <img src=\""+britishAmericanDramas[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+britishAmericanDramas[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            britishAmericanDramaUl.html(sbritishAmericanDramaHtml);

            var thaiDramas = data["thaiDrama"];
            var thaiDramaUl = $("#tagChange div.vqqcom_thaiDrama ul");
            var sthaiDramaHtml = "";

            for (var i=0;i<thaiDramas.length;i++){
                sthaiDramaHtml += "<li>\n" +
                    "                                <a href=\""+thaiDramas[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"updateTo\">"+thaiDramas[i]["updateTo"]+"</span>\n" +
                    "                                    <img src=\""+thaiDramas[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+thaiDramas[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            thaiDramaUl.html(sthaiDramaHtml);

            var sports = data["sports"];
            var sportsUl = $("#tagChange div.vqqcom_sports ul");
            var ssportsHtml = "";

            for (var i=0;i<sports.length;i++){
                ssportsHtml += "<li>\n" +
                    "                                <a href=\""+sports[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <img src=\""+sports[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+sports[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            sportsUl.html(ssportsHtml);

            var musics = data["music"];
            var musicUl = $("#tagChange div.vqqcom_music ul");
            var smusicHtml = "";

            for (var i=0;i<musics.length;i++){
                smusicHtml += "<li>\n" +
                    "                                <a href=\""+musics[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <img src=\""+musics[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+musics[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            musicUl.html(smusicHtml);


            var entertainments = data["entertainment"];
            var entertainmentUl = $("#tagChange div.vqqcom_entertainment ul");
            var sentertainmentHtml = "";

            for (var i=0;i<entertainments.length;i++){
                sentertainmentHtml += "<li>\n" +
                    "                                <a href=\""+entertainments[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"timeLength\">"+entertainments[i]["timeLength"]+"</span>\n" +
                    "                                    <img src=\""+entertainments[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+entertainments[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            entertainmentUl.html(sentertainmentHtml);



            var travels = data["travel"];
            var travelUl = $("#tagChange div.vqqcom_travel ul");
            var stravelHtml = "";

            for (var i=0;i<travels.length;i++){
                stravelHtml += "<li>\n" +
                    "                                <a href=\""+travels[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"timeLength\">"+travels[i]["timeLength"]+"</span>\n" +
                    "                                    <img src=\""+travels[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+travels[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            travelUl.html(stravelHtml);


            var fashions = data["fashion"];
            var fashionUl = $("#tagChange div.vqqcom_fashion ul");
            var sfashionHtml = "";

            for (var i=0;i<fashions.length;i++){
                sfashionHtml += "<li>\n" +
                    "                                <a href=\""+fashions[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"timeLength\">"+fashions[i]["timeLength"]+"</span>\n" +
                    "                                    <img src=\""+fashions[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+fashions[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            fashionUl.html(sfashionHtml);



            var games = data["game"];
            var gameUl = $("#tagChange div.vqqcom_game ul");
            var sgameHtml = "";

            for (var i=0;i<games.length;i++){
                sgameHtml += "<li>\n" +
                    "                                <a href=\""+games[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"timeLength\">"+games[i]["timeLength"]+"</span>\n" +
                    "                                    <img src=\""+games[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+games[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            gameUl.html(sgameHtml);



            var laughs = data["laugh"];
            var laughUl = $("#tagChange div.vqqcom_laugh ul");
            var slaughHtml = "";

            for (var i=0;i<laughs.length;i++){
                slaughHtml += "<li>\n" +
                    "                                <a href=\""+laughs[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"timeLength\">"+laughs[i]["timeLength"]+"</span>\n" +
                    "                                    <img src=\""+laughs[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+laughs[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            laughUl.html(slaughHtml);



            var motherAndInfants = data["motherAndInfant"];
            var motherAndInfantUl = $("#tagChange div.vqqcom_motherAndInfant ul");
            var smotherAndInfantHtml = "";

            for (var i=0;i<motherAndInfants.length;i++){
                smotherAndInfantHtml += "<li>\n" +
                    "                                <a href=\""+motherAndInfants[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"timeLength\">"+motherAndInfants[i]["timeLength"]+"</span>\n" +
                    "                                    <img src=\""+motherAndInfants[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+motherAndInfants[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            motherAndInfantUl.html(smotherAndInfantHtml);



            var lifes = data["life"];
            var lifeUl = $("#tagChange div.vqqcom_life ul");
            var slifeHtml = "";

            for (var i=0;i<lifes.length;i++){
                slifeHtml += "<li>\n" +
                    "                                <a href=\""+lifes[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"timeLength\">"+lifes[i]["timeLength"]+"</span>\n" +
                    "                                    <img src=\""+lifes[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+lifes[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            lifeUl.html(slifeHtml);



            var constellations = data["constellation"];
            var constellationUl = $("#tagChange div.vqqcom_constellation ul");
            var sconstellationHtml = "";

            for (var i=0;i<constellations.length;i++){
                sconstellationHtml += "<li>\n" +
                    "                                <a href=\""+constellations[i]["href"]+"\" target=\"_blank\">\n" +
                    "                                    <span class=\"timeLength\">"+constellations[i]["timeLength"]+"</span>\n" +
                    "                                    <img src=\""+constellations[i]["imgSrc"]+"\">\n" +
                    "                                    <span class=\"sub_text\">"+constellations[i]["sub_text"]+"</span>\n" +
                    "                                </a>\n" +
                    "                            </li>";
            }
            constellationUl.html(sconstellationHtml);



            var cars = data["car"];
            var carUl = $("#tagChange div.vqqcom_car ul");
            var scarHtml = "";

            if (cars != undefined){
                for (var i=0;i<cars.length;i++){
                    scarHtml += "<li>\n" +
                        "                                <a href=\""+cars[i]["href"]+"\" target=\"_blank\">\n" +
                        "                                    <span class=\"timeLength\">"+cars[i]["timeLength"]+"</span>\n" +
                        "                                    <img src=\""+cars[i]["imgSrc"]+"\">\n" +
                        "                                    <span class=\"sub_text\">"+cars[i]["sub_text"]+"</span>\n" +
                        "                                </a>\n" +
                        "                            </li>";
                }
                carUl.html(scarHtml);
            }
            else{
                carUl.html("暂无数据");
            }







            //加载动画js代码
            var script = document.createElement("script");
            script.setAttribute("src","jss/common.js");
            script.setAttribute("type","text/javascript");

            document.body.appendChild(script);
        }
    });

});