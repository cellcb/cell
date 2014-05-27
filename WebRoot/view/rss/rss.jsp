<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;


%>
<!DOCTYPE html>
<!-- saved from url=(0042)http://v3.bootcss.com/examples/offcanvas/# -->
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="http://v3.bootcss.com/docs-assets/ico/favicon.png">

    <title>RSS</title>

    <!-- Bootstrap core CSS -->
    <link href="http://v3.bootcss.com/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="http://v3.bootcss.com/examples/offcanvas/offcanvas.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]>
    <script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand"
               href="./Off Canvas Template for Bootstrap_files/Off Canvas Template for Bootstrap.htm">Cell</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li>
                    <a href="./Off Canvas Template for Bootstrap_files/Off Canvas Template for Bootstrap.htm">Home</a>
                </li>
                <li class="active">
                    <a href="./Off Canvas Template for Bootstrap_files/Off Canvas Template for Bootstrap.htm">RSS</a>
                </li>
                <li><a href="http://v3.bootcss.com/examples/offcanvas/#about">About</a></li>
                <li><a href="http://v3.bootcss.com/examples/offcanvas/#contact">Contact</a></li>
            </ul>
        </div>
        <!-- /.nav-collapse -->
    </div>
    <!-- /.container -->
</div>
<!-- /.navbar -->

<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
            <p class="pull-right visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
            </p>
            <div class="jumbotron">
                <h1>Hello !</h1>

                <p>This is a RSS web site for CB.</p>
            </div>
            <div class="row" id="blogDiv">


                <!--/span-->
            </div>
            <!--/row-->
        </div>
        <!--/span-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
            <div class="list-group">

                <c:forEach var="rss" items="${rssList}">

                    <a href="#" onclick="loadBlog('${rss.url}');"
                       class="list-group-item ">${rss.author}</a>

                </c:forEach>

                <a href="./Off Canvas Template for Bootstrap_files/Off Canvas Template for Bootstrap.htm"
                   class="list-group-item">Link</a>
                <a href="./Off Canvas Template for Bootstrap_files/Off Canvas Template for Bootstrap.htm"
                   class="list-group-item">Link</a>

            </div>
        </div>
        <!--/span-->
    </div>
    <!--/row-->

    <hr>

    <footer>
        <p>© Chengbo 2014</p>
    </footer>

</div>
<!--/.container-->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=basePath%>/js/jquery.min.js"></script>
<script src="<%=basePath%>/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/js/offcanvas.js"></script>
<script>
    function loadBlog(url) {
        $.ajax({
            type: "POST",
            data: {
                url: url
            },
            url: "<%=basePath%>/rss/loadBlog.html",
            success: function (bloginfo) {

                for (var i = 0; i < bloginfo.result.length; i++) {

                    $("#blogDiv").append(' <div class="col-6 col-sm-6 col-lg-4">' +
                            '<h2>' +bloginfo.author+ '</h2>' +

                            ' <p>' + bloginfo.result[i].title + '</p>' + ' <p><a class="btn btn-default"' +
                            'href="'+bloginfo.result[i].url+'" '
                            +'role="button">View details »</a></p>' +
                            '</div>');

                }
            }
        });
    }
    $(document).ready(function () {

    });

</script>

</body>
</html>