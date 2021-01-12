<%--
  Created by IntelliJ IDEA.
  User: sdr29
  Date: 2021-01-11
  Time: 오후 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>

<head>
    <title>코딩 전문가를 만들기 위한 온라인 강의 시스템</title>
    <meta charset="UTF-8">
    <title>공지사항목록</title>

    <link href="/css/customer/layout.css" type="text/css" rel="stylesheet" />
    <style>

        #visual .content-container{
            height:inherit;
            display:flex;
            align-items: center;

            background: url("../../images/customer/visual.png") no-repeat center;
        }
    </style>
</head>

<body>
<!-- header 부분 -->
    <tiles:insertAttribute name="header"/>
<!-- --------------------------- <visual> --------------------------------------- -->
<!-- visual 부분 -->
    <tiles:insertAttribute name="visual"/>
<!-- --------------------------- <body> --------------------------------------- -->
<div id="body">
    <div class="content-container clearfix">

        <!-- --------------------------- aside --------------------------------------- -->
        <!-- aside 부분 -->
        <tiles:insertAttribute name="aside"/>



        <!-- --------------------------- main --------------------------------------- -->
        <tiles:insertAttribute name="body"/>

    </div>
</div>
<tiles:insertAttribute name="footer"/>


</body>

</html>