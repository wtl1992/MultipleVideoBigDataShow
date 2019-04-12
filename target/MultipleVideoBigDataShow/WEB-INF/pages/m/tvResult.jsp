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

    <title>祥龙检索，千度寻--电视剧</title>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimal-ui"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="referrer" content="no-referrer"/>
    <meta http-equiv="keywords" content="全网检索,大数据,高级检索"/>
    <meta http-equiv="description" content="全网检索数据库"/>
    <link rel="shortcut icon" href="../images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="../css/m/page_tvPage_jsp/tvResult.css"/>
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
    <script type="text/javascript" src="../jss/jquery-3.3.1.min.js"></script>
</head>

<body>

<div style="position: fixed;left: 0;top: 0;width: 100%;max-height:300px;background-color: rgba(0,0,0,.6);
z-index: 99999;">
    <div class="oneLine">
        <a title="祥龙检索，搜电视剧"><img src="../images/index_jsp/homelogo.png"/></a>
    </div>
    <p style="margin: 0px auto;text-align: center;
position: relative;left: 0px;top: -6px;color: #FFFFFF;">可以搜索电视剧名称或者演员名字等</p>
    <div class="searchArea">
        <p><input type="text" title="可以搜索电视剧名称或者演员名字等"/></p>
        <a>千度电视剧</a>
    </div>

    <div class="items">
        <ul>
            <li><a href="m/tvResult?pageIndex=1">电视剧</a></li>
            <li><a href="m/movieResult?pageIndex=1">电影</a></li>
            <li><a href="m/appResult?categoryId=-10&pageSize=60&pageContext=1">app</a></li>
            <li><a href="m/imageResult?keyword=美女&pageIndex=1&pageSize=60">图片</a></li>
            <li><a href="m/musicResult?keyword=音乐&pageIndex=1&pageSize=20">音乐</a></li>
            <li><a href="m/getThunderPagingResult?classify=最新影片&pageIndex=1">迅雷下载</a></li>
            <li><a href="m/baiduyunwangpanSearchResult?keyword=数据库&pageIndex=1">百度网盘</a></li>
        </ul>
    </div>
</div>
<div class="list-view">
    <ul class="tvUl">
    </ul>

    <ul class="list">
    </ul>
</div>
<i id="pageIndex" style="display: none;">${pageIndex}</i>
<script type="text/javascript" src="../jss/m/page_tvPage_jsp/tvResult.js"></script>
</body>
</html>
