<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">

    <title>祥龙检索，千度寻</title>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="referrer" content="no-referrer"/>
    <meta http-equiv="keywords" content="全网检索,大数据,高级检索"/>
    <meta http-equiv="description" content="全网检索数据库"/>
    <link rel="shortcut icon" href="images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="css/v.qq.com.css"/>
    <link rel="stylesheet" type="text/css" href="css/aiqiyi.com.css"/>
    <link rel="stylesheet" type="text/css" href="css/youku.com.css"/>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
    <script type="text/javascript" src="jss/jquery-3.3.1.min.js"></script>
</head>

<body>
<div id="topBigPic">
    <div class="lineBackground">
        <div id="multify">
            <input type="text">
            <a>千度寻</a>

            <ul alt="点击切换">
                <li class="focus">腾讯视频</li>
                <li>爱奇艺</li>
                <li>优酷</li>
            </ul>
        </div>
        <div class="items">
            <ul>
                <li><a href="./">回首页</a></li>
                <li><a href="index">千度一下</a></li>
                <li><a href="tvResult?pageIndex=1">电视剧</a></li>
                <li><a href="movieResult?pageIndex=1">电影</a></li>
                <li><a href="appResult?categoryId=-10&pageSize=60&pageContext=1">app</a></li>
                <li><a href="imageResult?keyword=美女&pageIndex=1&pageSize=60">图片</a></li>
                <li><a href="musicResult?keyword=音乐&pageIndex=1&pageSize=20">音乐</a></li>
                <li><a href="getThunderPagingResult?classify=最新影片&pageIndex=1">迅雷下载</a></li>
                <li><a href="baiduyunwangpanSearchResult?keyword=数据库&pageIndex=1">百度网盘</a></li>
            </ul>
        </div>
    </div>


    <ul id="tagChange">
        <li>
            <div>
                <div class="vqqcom_top">
                    <ul class="topBar">
                    </ul>

                    <ul class="rightData">
                    </ul>
                </div>

                <div class="vqqcom_todayHot">
                    <p class="bigTitle">今日热门</p>
                    <ul>
                    </ul>
                </div>

                <div class="vqqcom_original">
                    <p class="bigTitle">原创精选</p>
                    <ul>
                    </ul>
                </div>

                <div class="vqqcom_mighty">
                    <p class="bigTitle">强势接档</p>
                    <ul>
                    </ul>
                </div>

                <div class="vqqcom_synchronous">
                    <p class="bigTitle">同步剧场</p>
                    <ul>
                    </ul>
                </div>

                <div class="vqqcom_trivia">
                    <p class="bigTitle">花絮·剧透·预告片</p>
                    <ul>
                    </ul>
                </div>

                <div class="vqqcom_variety">
                    <p class="bigTitle">综艺</p>
                    <ul>
                    </ul>
                </div>

                <div class="vqqcom_membership">
                    <p class="bigTitle">会员尊享</p>
                    <ul>
                    </ul>
                </div>

                <div class="vqqcom_movie">
                    <p class="bigTitle">电影</p>
                    <ul>
                    </ul>
                </div>

                <div class="vqqcom_moviePredict">
                    <p class="bigTitle">电影预告</p>
                    <ul>
                    </ul>
                </div>

                <div class="vqqcom_comic">
                    <p class="bigTitle">动漫</p>
                    <ul>
                    </ul>
                </div>

                <div class="vqqcom_children">
                    <p class="bigTitle">少儿</p>
                    <ul>
                    </ul>
                </div>

                <div class="vqqcom_documentary">
                    <p class="bigTitle">纪录片</p>
                    <ul>
                    </ul>
                </div>


                <div class="vqqcom_britishAmericanDrama">
                    <p class="bigTitle">英美剧</p>
                    <ul>
                    </ul>
                </div>

                <div class="vqqcom_thaiDrama">
                    <p class="bigTitle">泰剧</p>
                    <ul>
                    </ul>
                </div>


                <div class="vqqcom_sports">
                    <p class="bigTitle">体育资讯</p>
                    <ul>
                    </ul>
                </div>

                <div class="vqqcom_music">
                    <p class="bigTitle">音乐·演唱会</p>
                    <ul>
                    </ul>
                </div>

                <div class="vqqcom_entertainment">
                    <p class="bigTitle">娱乐热点</p>
                    <ul>
                    </ul>
                </div>

                <div class="vqqcom_travel">
                    <p class="bigTitle">旅游精选</p>
                    <ul>
                    </ul>
                </div>
            </div>


            <div class="vqqcom_fashion">
                <p class="bigTitle">时尚热度榜</p>
                <ul>
                </ul>
            </div>


            <div class="vqqcom_game">
                <p class="bigTitle">精品游戏</p>
                <ul>
                </ul>
            </div>

            <div class="vqqcom_laugh">
                <p class="bigTitle">最强笑点</p>
                <ul>
                </ul>
            </div>


            <div class="vqqcom_motherAndInfant">
                <p class="bigTitle">母婴常识</p>
                <ul>
                </ul>
            </div>


            <div class="vqqcom_life">
                <p class="bigTitle">生活资讯</p>
                <ul>
                </ul>
            </div>


            <div class="vqqcom_constellation">
                <p class="bigTitle">星座聚焦</p>
                <ul>
                </ul>
            </div>


            <div class="vqqcom_car">
                <p class="bigTitle">汽车资讯</p>
                <ul>
                </ul>
            </div>


            <div class="footer">
                <ul>
                    <li class="right"><a href="#" target="_blank">关于祥龙</a></li>
                    <li class="right"><a href="#" target="_blank"> About XiangLong </a></li>
                    <li class="right"><a href="#" target="_blank">服务协议</a></li>
                    <li class="right"><a href="#" target="_blank">隐私政策</a></li>
                    <li class="right"><a href="#" target="_blank">开放平台</a></li>
                    <li class="right"><a href="#" target="_blank">商务洽谈</a></li>
                    <li class="right"><a href="#" target="_blank">客服中心</a></li>
                    <li><a href="#" target="_blank">版权所有</a></li>
                </ul>

                <ul class="u2">
                    <li class="right"><a href="http://szwljb.gov.cn/" target="_blank">深圳举报中心</a></li>
                    <li class="right"><a href="http://www.szga.gov.cn/" target="_blank">深圳公安局</a></li>
                    <li class="right"><a href="#" target="_blank">抵制违法广告承诺书</a></li>
                    <li class="right"><a href="#" target="_blank">版权保护投诉指引</a></li>
                    <li><a href="https://www.gdca.gov.cn/" target="_blank">广东省通管局</a></li>
                </ul>
                <p>皖ICP备18021122号-1</p>
                <p>Copyright 2018 - 2019 XiangLong. All Rights Reserved</p>

                <ul class="u3">
                    <li><a href="#" target="_blank">
                        <img src="images/page_index_jsp/ind36.gif"/>
                        <span>
                            经营性网站<br/>备案信息
                        </span>
                    </a></li>
                    <li><a href="http://www.12377.cn/" target="_blank">
                        <img src="images/page_index_jsp/buliang.png"/>
                        <span>
                            中国互联网<br/>举报中心
                        </span>
                    </a></li>
                    <li><a href="http://www.wenming.cn/" target="_blank">
                        <img src="images/page_index_jsp/wmlogo.gif"/>
                        <span>
                            中国文明网<br/>传播文明
                        </span>
                    </a></li>
                    <li><a href="https://ss.knet.cn/verifyseal.dll?sn=2010051300100001081&ct=df&a=1&pa=337337" target="_blank">
                        <img src="images/page_index_jsp/cxrz5.png"/>
                    </a></li>
                    <li><a href="http://szcert.ebs.org.cn/6917b6fe-b844-4e12-97c5-85b8d1df30ed" target="_blank">
                        <img src="images/page_index_jsp/gswj2015.jpg"/>
                        <span>
                            工商网监<br/>电子标识
                        </span>
                    </a></li>
                </ul>
            </div>

        </li>

        <li>
            <div class="iqiyicom_top">
                <ul class="topBar">
                </ul>

                <ul class="rightData">
                </ul>
            </div>

            <div class="iqiyicom_secondRight">
                <p class="bigTitle">今日焦点</p>
                <ul>
                </ul>
            </div>


            <div class="iqiyicom_variety">
                <p class="bigTitle">综艺</p>
                <ul>
                </ul>
            </div>


            <div class="iqiyicom_homemade">
                <p class="bigTitle">自制综艺</p>
                <ul>
                </ul>
            </div>


            <div class="iqiyicom_talkShow">
                <p class="bigTitle">脱口秀</p>
                <ul>
                </ul>
            </div>


            <div class="iqiyicom_entertainment">
                <p class="bigTitle">娱乐</p>
                <ul>
                </ul>
            </div>


            <div class="iqiyicom_movie">
                <p class="bigTitle">电影</p>
                <ul>
                </ul>
            </div>


            <div class="iqiyicom_tv">
                <p class="bigTitle">电视剧</p>
                <ul>
                </ul>
            </div>


            <div class="iqiyicom_comic">
                <p class="bigTitle">动漫</p>
                <ul>
                </ul>
            </div>


            <div class="iqiyicom_children">
                <p class="bigTitle">儿童</p>
                <ul>
                </ul>
            </div>


            <div class="iqiyicom_game">
                <p class="bigTitle">游戏视频</p>
                <ul>
                </ul>
            </div>


            <div class="iqiyicom_sports">
                <p class="bigTitle">体育</p>
                <ul>
                </ul>
            </div>


            <div class="iqiyicom_original">
                <p class="bigTitle">原创</p>
                <ul>
                </ul>
            </div>


            <div class="iqiyicom_documentary">
                <p class="bigTitle">纪录片</p>
                <ul>
                </ul>
            </div>


            <div class="footer">
                <ul>
                    <li class="right"><a href="#" target="_blank">关于祥龙</a></li>
                    <li class="right"><a href="#" target="_blank"> About XiangLong </a></li>
                    <li class="right"><a href="#" target="_blank">服务协议</a></li>
                    <li class="right"><a href="#" target="_blank">隐私政策</a></li>
                    <li class="right"><a href="#" target="_blank">开放平台</a></li>
                    <li class="right"><a href="#" target="_blank">商务洽谈</a></li>
                    <li class="right"><a href="#" target="_blank">客服中心</a></li>
                    <li><a href="#" target="_blank">版权所有</a></li>
                </ul>

                <ul class="u2">
                    <li class="right"><a href="http://szwljb.gov.cn/" target="_blank">深圳举报中心</a></li>
                    <li class="right"><a href="http://www.szga.gov.cn/" target="_blank">深圳公安局</a></li>
                    <li class="right"><a href="#" target="_blank">抵制违法广告承诺书</a></li>
                    <li class="right"><a href="#" target="_blank">版权保护投诉指引</a></li>
                    <li><a href="https://www.gdca.gov.cn/" target="_blank">广东省通管局</a></li>
                </ul>
                <p>皖ICP备18021122号-1</p>
                <p>Copyright 2018 - 2019 XiangLong. All Rights Reserved</p>

                <ul class="u3">
                    <li><a href="#" target="_blank">
                        <img src="images/page_index_jsp/ind36.gif"/>
                        <span>
                            经营性网站<br/>备案信息
                        </span>
                    </a></li>
                    <li><a href="http://www.12377.cn/" target="_blank">
                        <img src="images/page_index_jsp/buliang.png"/>
                        <span>
                            中国互联网<br/>举报中心
                        </span>
                    </a></li>
                    <li><a href="http://www.wenming.cn/" target="_blank">
                        <img src="images/page_index_jsp/wmlogo.gif"/>
                        <span>
                            中国文明网<br/>传播文明
                        </span>
                    </a></li>
                    <li><a href="https://ss.knet.cn/verifyseal.dll?sn=2010051300100001081&ct=df&a=1&pa=337337" target="_blank">
                        <img src="images/page_index_jsp/cxrz5.png"/>
                    </a></li>
                    <li><a href="http://szcert.ebs.org.cn/6917b6fe-b844-4e12-97c5-85b8d1df30ed" target="_blank">
                        <img src="images/page_index_jsp/gswj2015.jpg"/>
                        <span>
                            工商网监<br/>电子标识
                        </span>
                    </a></li>
                </ul>
            </div>
        </li>

        <li>
            <div class="youkucom_top">
                <ul class="topBar">
                </ul>

                <ul class="rightData">
                </ul>
            </div>



            <div class="youkucom_hoting">
                <p class="bigTitle">正在热播</p>
                <ul>
                </ul>
            </div>



            <div class="youkucom_episode">
                <p class="bigTitle">剧集</p>
                <ul>
                </ul>
            </div>



            <div class="youkucom_superNet">
                <p class="bigTitle">超级网剧</p>
                <ul>
                </ul>
            </div>



            <div class="youkucom_variety">
                <p class="bigTitle">综艺</p>
                <ul>
                </ul>
            </div>



            <div class="youkucom_movie">
                <p class="bigTitle">电影</p>
                <ul>
                </ul>
            </div>



            <div class="youkucom_member">
                <p class="bigTitle">会员</p>
                <ul>
                </ul>
            </div>



            <div class="youkucom_comic">
                <p class="bigTitle">动漫</p>
                <ul>
                </ul>
            </div>



            <div class="youkucom_bigFish">
                <p class="bigTitle">大鱼号精选</p>
                <ul>
                </ul>
            </div>



            <div class="youkucom_entertainment">
                <p class="bigTitle">娱乐</p>
                <ul>
                </ul>
            </div>


            <div class="footer">
                <ul>
                    <li class="right"><a href="#" target="_blank">关于祥龙</a></li>
                    <li class="right"><a href="#" target="_blank"> About XiangLong </a></li>
                    <li class="right"><a href="#" target="_blank">服务协议</a></li>
                    <li class="right"><a href="#" target="_blank">隐私政策</a></li>
                    <li class="right"><a href="#" target="_blank">开放平台</a></li>
                    <li class="right"><a href="#" target="_blank">商务洽谈</a></li>
                    <li class="right"><a href="#" target="_blank">客服中心</a></li>
                    <li><a href="#" target="_blank">版权所有</a></li>
                </ul>

                <ul class="u2">
                    <li class="right"><a href="http://szwljb.gov.cn/" target="_blank">深圳举报中心</a></li>
                    <li class="right"><a href="http://www.szga.gov.cn/" target="_blank">深圳公安局</a></li>
                    <li class="right"><a href="#" target="_blank">抵制违法广告承诺书</a></li>
                    <li class="right"><a href="#" target="_blank">版权保护投诉指引</a></li>
                    <li><a href="https://www.gdca.gov.cn/" target="_blank">广东省通管局</a></li>
                </ul>
                <p>皖ICP备18021122号-1</p>
                <p>Copyright 2018 - 2019 XiangLong. All Rights Reserved</p>

                <ul class="u3">
                    <li><a href="#" target="_blank">
                        <img src="images/page_index_jsp/ind36.gif"/>
                        <span>
                            经营性网站<br/>备案信息
                        </span>
                    </a></li>
                    <li><a href="http://www.12377.cn/" target="_blank">
                        <img src="images/page_index_jsp/buliang.png"/>
                        <span>
                            中国互联网<br/>举报中心
                        </span>
                    </a></li>
                    <li><a href="http://www.wenming.cn/" target="_blank">
                        <img src="images/page_index_jsp/wmlogo.gif"/>
                        <span>
                            中国文明网<br/>传播文明
                        </span>
                    </a></li>
                    <li><a href="https://ss.knet.cn/verifyseal.dll?sn=2010051300100001081&ct=df&a=1&pa=337337" target="_blank">
                        <img src="images/page_index_jsp/cxrz5.png"/>
                    </a></li>
                    <li><a href="http://szcert.ebs.org.cn/6917b6fe-b844-4e12-97c5-85b8d1df30ed" target="_blank">
                        <img src="images/page_index_jsp/gswj2015.jpg"/>
                        <span>
                            工商网监<br/>电子标识
                        </span>
                    </a></li>
                </ul>
            </div>
        </li>
    </ul>
</div>


<div class="loading_pic">
    <img src="images/loading.gif">
</div>
<script type="text/javascript" src="jss/v.qq.com.js"></script>
<script type="text/javascript" src="jss/iqiyi.com.js"></script>
<script type="text/javascript" src="jss/youku.com.js"></script>
</body>
</html>
