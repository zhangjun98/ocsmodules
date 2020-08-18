<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/view/common/base.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>404 - Not found</title>
	<link rel="shortcut icon" href="${ctx}/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="${ctxsta}/common/error/css/main.css" /> <!-- main stylesheet -->
	<link rel="stylesheet" href="${ctxsta}/common/error/css/tipsy.css" /> <!-- Tipsy implementation -->
	<script src="${ctxsta}/common/error/scripts/jquery-1.7.2.min.js"></script> <!-- uiToTop implementation -->
	<script src="${ctxsta}/common/error/scripts/custom-scripts.js"></script>
	<script src="${ctxsta}/common/error/scripts/jquery.tipsy.js"></script> <!-- Tipsy -->
	<script>
	$(document).ready(function(){
		universalPreloader();
	});
	$(window).load(function(){
		//remove Universal Preloader
		universalPreloaderRemove();
		rotate();
	    dogRun();
		dogTalk();
		//Tipsy implementation
		$('.with-tooltip').tipsy({gravity: $.fn.tipsy.autoNS});
	});
	</script>
  </head>
<body>
<!-- Universal preloader -->
<div id="universal-preloader">
    <div class="preloader">
        <img src="${ctxsta}/common/error/images/universal-preloader.gif" alt="universal-preloader" class="universal-preloader-preloader" />
    </div>
</div>
<!-- Universal preloader -->

<div id="wrapper">
<!-- 404 graphic -->
	<div class="graphic404"></div>
<!-- 404 graphic -->
<!-- Not found text -->
	<div class="not-found-text">
    	<h1 class="not-found-text">：（ 很抱歉，您所访问的页面不存在！请重新登录</h1>
    </div>
<!-- Not found text -->

<!-- search form -->

<!-- search form -->



<div class="dog-wrapper">
<!-- dog running -->
	<div class="dog"></div>
<!-- dog running -->

    
    
    <!-- The dog bubble rotates these -->
<!-- dog bubble talking -->
</div>

<!-- planet at the bottom -->
	<div class="planet"></div>
<!-- planet at the bottom -->
</div>

</body>
</html>