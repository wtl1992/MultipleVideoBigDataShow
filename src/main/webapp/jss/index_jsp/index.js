$(function () {
    $("div.background-pic").css({
        width : window.innerWidth + "px",
        height : window.innerHeight + "px"
    });

    $(window).on("resize",function () {
        $("div.background-pic").css({
            width : window.innerWidth + "px",
            height : window.innerHeight + "px"
        });
    });


    //处理首页按钮的事件
    $("div.bannerUrl ul > li img.game").on("mousedown",function () {
        $(this).attr("src","images/index_jsp/button_game_after.jpg");
    });
    $("div.bannerUrl ul > li img.game").on("mouseup",function () {
        $(this).attr("src","images/index_jsp/button_game.jpg");
    });

    //用中心的开始游戏按钮随机生成开启链接的href
    var gameLis = $("div.bannerUrl li");
    var startGameBtn = $("li#startGame");
    var startGameBtnIndex = -1;
    for (var i=0;i<gameLis.length;i++){
        if (gameLis.eq(i).attr("id") === "startGame"){
            startGameBtnIndex = i;
            break;
        }
    }

    function startGameEventFunc (event) {
        startGameBtn.unbind();
        var randomNumber = randomNum(0,gameLis.length-1);
        var i = 0;
        function f() {
            window.setTimeout(function () {
                for (var j=0;j<=randomNumber;j++){
                    gameLis.eq(j).removeClass("focus");
                }
                gameLis.eq(i).addClass("focus");
                if (i < randomNumber){
                    f();
                    i++;
                }
                else{
                    gameLis.eq(randomNumber).removeClass("focus");
                    gameLis.eq(randomNumber).find("a").click();
                    window.location = gameLis.eq(randomNumber).find("a").attr("href");
                }
            },200);
        }
        f();
    }
    startGameBtn.on("click",startGameEventFunc);


    //生成[n,m]的随机整数
    function randomNum(minNum,maxNum){
        switch(arguments.length){
            case 1:
                return parseInt(Math.random()*minNum+1,10);
                break;
            case 2:
                return parseInt(Math.random()*(maxNum-minNum+1)+minNum,10);
                break;
            default:
                return 0;
                break;
        }
    }


    //数据提交
    $("div.searchArea p input").on("keydown",function (event) {
       $("div.searchArea a").attr("href","result?keyword="+$(this).val()+"&pageIndex=1");
       if (event.keyCode === 13 && $(this).val() !== ""){
           window.location = "result?keyword="+$(this).val()+"&pageIndex=1";
       }
    });
});