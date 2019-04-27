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

    <title>祥龙检索，千度寻--app</title>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimal-ui"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="referrer" content="no-referrer"/>
    <meta http-equiv="keywords" content="全网检索,大数据,高级检索"/>
    <meta http-equiv="description" content="全网检索数据库"/>
    <link rel="shortcut icon" href="../images/logo/index_logo.png" type="image/icon"/>
    <link rel="stylesheet" type="text/css" href="../css/m/page_app_jsp/appResult.css"/>
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
    <script type="text/javascript" src="../jss/jquery-3.3.1.min.js"></script>
    <style type="text/css">
        div h1{
            text-align: center;
        }
    </style>
</head>

<body>
<div>
    <h1>异常页面</h1>
</div>
</body>
</html>
