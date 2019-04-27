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
    <link rel="stylesheet" type="text/css" href="../css/WeiXinBackstage/uploadImage.css"/>
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
    <script type="text/javascript" src="../jss/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../jss/WeiXinBackstage/uploadImage.js"></script>
</head>

<body>
    <div class="container">
        <h1>上传图片接口</h1>
        <div class="form">
            <input type="file" value="" id="file"/>
            <div id="selectImageDiv">
                <span class="selectImage">选择文件</span><br/>
                <img src="../images/WeiXinBackstage/image.jpg"/>
            </div>
            <input type="button" value="上传图片素材到微信公众号空间">
        </div>
    </div>
</body>
</html>
