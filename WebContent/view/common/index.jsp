<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/view/common/base.jsp"%>
 <%
response.setHeader("Cache-Control", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", 0); 
response.setHeader("Pragma", "no-cache"); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>智慧一卡通</title>
<link rel="shortcut icon" href="${ctxsta}/img/img-01.png" type="image/x-icon" />
<link rel="stylesheet" href="static/layui/layui/css/layui.css">
</head>

<body>
<div class="layui-layout layui-layout-admin">
<div class="layui-header layui-bg-green" >
 <div class="layui-logo layui-bg-green">智慧一卡通管理系统</div>

<ul class="layui-nav layui-bg-green layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
        ${user.username}
        </a>
      </li>
      <li class="layui-nav-item"><a href="${ctx}/common/loginout">退出登录</a></li>
    </ul>
    </div>
   <div class="layui-side layui-bg-green">
    <div class="layui-side-scroll ">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree "  >
  		<li class="layui-nav-item layui-bg-green"><a href="javaspript:;"  target="main-frame">首页</a>
        </li>
        <li class="layui-nav-item layui-bg-green"><a href="${ctx}/common/toshoping"  target="main-frame">交易管理</a>
         <shiro:hasRole name="super">
         <dl class="layui-nav-child">
			<dd><a href="${ctx}/common/toSshoping"  target="main-frame">所有学生消费记录</a></dd>
   		 </dl>
         </shiro:hasRole>
        </li>
        <li class="layui-nav-item layui-bg-green"><a href="${ctx}/common/toinvest"  target="main-frame">我的一卡通</a>
        </li>
        <li class="layui-nav-item layui-bg-green"><a href="javaspript:;"   target="main-frame" >图书管理</a>
        <dl class="layui-nav-child">
         <c:if test="${user.usertype==1}">
      		<dd><a  href="${ctx}/common/tomyleadbook"  target="main-frame">我的借阅</a></dd>
      		<dd><a href="${ctx}/common/tobookstore"  target="main-frame">图书商城</a></dd>
      		</c:if>
      		 <c:if test="${user.usertype==2}">
			<dd><a href="${ctx}/common/toSbook"  target="main-frame">管理图书归还信息</a></dd>
			<dd><a href="${ctx}/common/toSbookall"  target="main-frame">所有归还记录</a></dd>
			 </c:if>
   		 </dl>
        </li>
       
        <li class="layui-nav-item layui-bg-green"><a href="javaspript:;"  target="main-frame">课程信息</a>
        <dl class="layui-nav-child">
         <c:if test="${user.usertype==1}">
      		<dd><a href="${ctx}/common/tomyclasscheck"  target="main-frame">考勤记录</a></dd>
      		<dd><a href="${ctx}/common/tomyclass"  target="main-frame">我的课程</a></dd>
      		 </c:if>
      		 <dd><a  href="${ctx}/common/toclasslist"  target="main-frame">所有课程</a></dd>
      		
      		  <c:if test="${user.usertype==2}">
      		   <dd><a  href="${ctx}/common/toSclasscheck"  target="main-frame">学生考勤记录</a></dd>
      		   </c:if>
   		 </dl>
        </li>
       
        <li class="layui-nav-item layui-bg-green"><a href="javaspript:;"  target="main-frame">校车信息</a>
        <dl class="layui-nav-child">
      		<dd><a  href="${ctx}/common/toschoolbusline"  target="main-frame">所有校车线路</a></dd>
      		<dd><a  href="${ctx}/common/tomyschoolbus"  target="main-frame">我的预定</a></dd>
      		  <c:if test="${user.usertype==2}">
      		   <dd><a  href="${ctx}/common/toSschoolbus"  target="main-frame">所有学生预定记录</a></dd>
      		   </c:if>
   		 </dl>
        </li>
       
        
      </ul>
    </div>
  </div>
  
    
  <div class="layui-body">
    <!-- 内容主体区域 -->
   <iframe src="${ctxsta}/img/bgr1.jpg"  id="main-frame"  width="100%" height="100%"  marginwidth="0"
             marginheight="0" name="main-frame"  >
</iframe>
  </div>
  
  <div class="layui-footer" style="left:0px;;">
    <!-- 底部固定区域 -->
  
    <div style="text-align:center;">  © 2020 - 武汉科技大学城市学院 赵自强 </div>
  </div>
    </div>
</body>
<script language="JavaScript" src="static/jquery-3.2.0.min.js"></script>
<script src="static/layui/layui/layui.js"></script>
<script>
layui.use('element', function(){
	  var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
	});

</script>
</html>