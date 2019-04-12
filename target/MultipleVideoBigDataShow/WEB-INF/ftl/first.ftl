<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<#list users as item>
${item}---${item_index}
</#list>

<#if name == "wtl">
wtl成功
<#else>
ljx成功
</#if>

<br/>

<#if course gt 80>
<#elseif course == 75>
及格
<#else>
不及格
</#if>

<#macro border param>
${param}
</#macro>

<@border "hello,world!!!"/>

<#include "test.txt">

<#macro test >
    <#nested/>
</#macro>

<@test>
wwwwwwwwwwwwwww
</@test>

<#import "second.ftl" as second/>

<@second.other></@second.other>

${sss!"abc"}
</body>
</html>