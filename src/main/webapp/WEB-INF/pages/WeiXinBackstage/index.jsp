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
    <link rel="stylesheet" type="text/css" href="../css/WeiXinBackstage/index.css"/>
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
    <script type="text/javascript" src="../jss/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../jss/WeiXinBackstage/index.js"></script>
</head>

<body>
    <div id="login">
        <form action="weiXinBackstage/login" method="post">
            <h1></h1>
            用户名：<input type="text" placeholder="请输入用户名" name="username"/><br/>
            密 码: <input type="password" placeholder="请输入密码" name="password"/><br/>
            <input type="submit" value=""/>
        </form>
    </div>
</body>
</html>
