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

    <title>祥龙检索，一搜便知</title>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="referrer" content="no-referrer"/>
    <meta http-equiv="keywords" content="全网检索,大数据,高级检索"/>
    <meta http-equiv="description" content="全网检索数据库"/>
    <link rel="shortcut icon" href="images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="css/index_jsp/index.css"/>
    <script type="text/javascript" src="jss/jquery-3.3.1.min.js"></script>
</head>

<body>
    <div class="background-pic">
    </div>

    <div class="oneLine">
        <a title="祥龙检索，千度一下"><img src="images/index_jsp/homelogo.png"/></a>
    </div>

    <div class="searchArea">
        <p><input type="text"/></p>
        <a target="_blank">千度一下</a>
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
    <!--导航列表-->
    <div class="bannerUrl">
        <ul>
            <li title="千度一下，你便知道">
                <a href="index" target="_blank">千度一下</a>
            </li>

            <li title="淘宝">
                <a href="http://www.taobao.com/" target="_blank">
                    <img src="images/index_jsp/taobao.jpg"/>
                </a>
            </li>

            <li title="新浪网">
                <a href="http://www.sina.com.cn/" target="_blank">
                    <img src="images/index_jsp/xinlang.jpg"/>
                </a>
            </li>

            <li title="搜狐网">
                <a href="http://www.sohu.com/" target="_blank">
                    <img src="images/index_jsp/souhu.jpg"/>
                </a>
            </li>

            <li title="网易">
                <a href="http://www.163.com/" target="_blank">
                    <img src="images/index_jsp/wangyi.jpg"/>
                </a>
            </li>

            <li title="微博">
                <a href="http://www.weibo.com/" target="_blank">
                    <img src="images/index_jsp/weibo.jpg"/>
                </a>
            </li>

            <li title="优酷网">
                <a href="http://www.youku.com/" target="_blank">
                    <img src="images/index_jsp/youku.jpg"/>
                </a>
            </li>

            <li title="腾讯网">
                <a href="http://www.qq.com/" target="_blank">
                    <img src="images/index_jsp/tengxun.jpg"/>
                </a>
            </li>

            <li title="爱奇艺">
                <a href="http://www.iqiyi.com/" target="_blank">
                    <img src="images/index_jsp/aiqiyi.jpg"/>
                </a>
            </li>

            <li title="智联招聘">
                <a href="http://www.zhaopin.com/" target="_blank">
                    <img src="images/index_jsp/zhilianzhaopin.jpg"/>
                </a>
            </li>

            <li title="大众点评">
                <a href="http://www.dianping.com/" target="_blank">
                    <img src="images/index_jsp/dazhongdianping.jpg"/>
                </a>
            </li>

            <li title="央视网">
                <a href="http://www.cntv.cn/" target="_blank">
                    <img src="images/index_jsp/cctv.jpg"/>
                </a>
            </li>

            <li title="人民网">
                <a href="http://www.people.com.cn/" target="_blank">
                    <img src="images/index_jsp/renmin.jpg"/>
                </a>
            </li>

            <li title="凤凰网">
                <a href="http://www.ifeng.com/" target="_blank">
                    <img src="images/index_jsp/fenghuangwang.jpg"/>
                </a>
            </li>

            <li title="中关村在线">
                <a href="http://www.zol.com.cn/" target="_blank">
                    <img src="images/index_jsp/zhongguancun.jpg"/>
                </a>
            </li>

            <li title="汽车之家">
                <a href="http://www.autohome.com.cn/" target="_blank">
                    <img src="images/index_jsp/qichezhijia.jpg"/>
                </a>
            </li>

            <li title="土豆网">
                <a href="http://www.tudou.com/" target="_blank">
                    <img src="images/index_jsp/tudou.jpg"/>
                </a>
            </li>

            <li id="startGame" title="开始游戏">
                <img class="game" src="images/index_jsp/button_game.jpg"/>
            </li>

            <li title="起点中文网">
                <a href="http://www.qidian.com/" target="_blank">
                    <img src="images/index_jsp/qidianzhongwen.jpg"/>
                </a>
            </li>

            <li title="百度糯米">
                <a href="http://www.nuomi.com/?cid=xsydh02" target="_blank">
                    <img src="images/index_jsp/baidunuomi.jpg"/>
                </a>
            </li>

            <li title="爱淘宝">
                <a href="http://ai.taobao.com?pid=mm_110502715_10024318_42370103" target="_blank">
                    <img src="images/index_jsp/aitaobao.jpg"/>
                </a>
            </li>

            <li title="天猫超市">
                <a href="https://chaoshi.tmall.com" target="_blank">
                    <img src="images/index_jsp/tianmaochaoshi.jpg"/>
                </a>
            </li>

            <li title="唯品会">
                <a href="http://www.vip.com/" target="_blank">
                    <img src="images/index_jsp/weipinhui.jpg"/>
                </a>
            </li>

            <li title="当当网">
                <a href="http://www.dangdang.com/" target="_blank">
                    <img src="images/index_jsp/dangdangwang.jpg"/>
                </a>
            </li>

            <li title="一号店">
                <a href="http://www.yihaodian.com/" target="_blank">
                    <img src="images/index_jsp/1haodian.jpg"/>
                </a>
            </li>

            <li title="苏宁易购">
                <a href="http://www.suning.com/" target="_blank">
                    <img src="images/index_jsp/suningyigou.jpg"/>
                </a>
            </li>

            <li title="国美在线">
                <a href="http://www.gome.com.cn/" target="_blank">
                    <img src="images/index_jsp/guomeizaixian.jpg"/>
                </a>
            </li>

            <li title="聚美优品">
                <a href="http://www.jumei.com/" target="_blank">
                    <img src="images/index_jsp/jumeiyoupin.jpg"/>
                </a>
            </li>

            <li title="有货网">
                <a href="http://www.yohobuy.com/" target="_blank">
                    <img src="images/index_jsp/youhuo.jpg"/>
                </a>
            </li>

            <li title="美丽说">
                <a href="http://www.meilishuo.com/" target="_blank">
                    <img src="images/index_jsp/meilishuo.jpg"/>
                </a>
            </li>

            <li title="12306">
                <a href="http://www.12306.cn/" target="_blank">
                    <img src="images/index_jsp/12306.jpg"/>
                </a>
            </li>

            <li title="赶集">
                <a href="http://www.ganji.com/" target="_blank">
                    <img src="images/index_jsp/ganji.jpg"/>
                </a>
            </li>

            <li title="快递100">
                <a href="http://www.kuaidi100.com/" target="_blank">
                    <img src="images/index_jsp/kuaidi100.jpg"/>
                </a>
            </li>

            <li title="去哪儿网">
                <a href="http://www.qunar.com/" target="_blank">
                    <img src="images/index_jsp/qunaer.jpg"/>
                </a>
            </li>

            <li title="天涯">
                <a href="http://www.tianya.cn/" target="_blank">
                    <img src="images/index_jsp/tianyashequ.jpg"/>
                </a>
            </li>
        </ul>
    </div>

<script type="text/javascript" src="jss/index_jsp/index.js"></script>
</body>
</html>
