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

    <title>祥龙检索，千度寻--电影</title>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="referrer" content="no-referrer"/>
    <meta http-equiv="keywords" content="全网检索,大数据,高级检索"/>
    <meta http-equiv="description" content="全网检索数据库"/>
    <link rel="shortcut icon" href="images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="css/page_movie_jsp/movieResult.css"/>
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
    <script type="text/javascript" src="jss/jquery-3.3.1.min.js"></script>
</head>

<body>

<div style="position: fixed;left: 0;top: 0;width: 100%;height: 316px;background-color: rgba(0,0,0,.6);
z-index: 99999;">
    <div class="oneLine">
        <a title="祥龙检索，搜电视剧"><img src="images/index_jsp/homelogo.png"/></a>
    </div>
    <p style="margin: 10px auto;text-align: center;
position: relative;left: 0px;top: 64px;color: #FFFFFF;">可以搜索电影名称或者演员名字等</p>
    <div class="searchArea">
        <p><input type="text" title="可以搜索电影名称或者演员名字等"/></p>
        <a>千度电影</a>
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
<div class="list-view">
    <ul class="movieUl">
    </ul>

    <ul class="list">
    </ul>
</div>
<i id="pageIndex" style="display: none;">${pageIndex}</i>
<script type="text/javascript" src="jss/page_movie_jsp/movieResult.js"></script>
</body>
</html>
