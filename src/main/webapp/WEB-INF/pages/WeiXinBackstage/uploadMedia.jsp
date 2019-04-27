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

    <title>祥龙检索后台管理系统</title>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="referrer" content="no-referrer"/>
    <meta http-equiv="keywords" content="全网检索,大数据,高级检索"/>
    <meta http-equiv="description" content="全网检索数据库"/>
    <link rel="shortcut icon" href="../images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="../css/WeiXinBackstage/uploadMedia.css"/>
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
    <script type="text/javascript" src="../jss/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../jss/WeiXinBackstage/uploadMedia.js"></script>
</head>

<body>
    <div class="container">
        <h1>上传${media_type}接口</h1>
        <span id="type" style="display: none;">${type}</span>
        <div class="form">
            <input type="file" value="" id="file"/>
            <div id="selectMediaDiv">
                <span class="selectMedia">选择${media_type}文件</span><br/>
                <img src="../images/WeiXinBackstage/timg.jpg"/>
            </div>
            <input type="button" value="上传${media_type}素材到微信公众号空间">
        </div>
    </div>
</body>
</html>
