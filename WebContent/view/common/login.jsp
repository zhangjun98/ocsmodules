<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/view/common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>学生一卡通信息管理系统</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!--图标-->
	<link rel="shortcut icon" href="${ctxsta}/img/img-01.png" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="${ctxsta}/css/font-awesome.min.css">
	<!--布局框架-->
	<link rel="stylesheet" type="text/css" href="${ctxsta}/css/util.css">
	<!--主要样式-->
	<link rel="stylesheet" type="text/css" href="${ctxsta}/css/main.css">
</head>
<body>
<div class="login">
	<div class="container-login100">
		<div class="wrap-login100">
			<div class="login100-pic js-tilt" data-tilt>
				<img src="${ctxsta}/img/img-01.png" alt="IMG">
			</div>

			<div class="login100-form validate-form" method="post">
				<span class="login100-form-title">
					学生一卡通管理系统
				</span>
				<div class="wrap-input100 validate-input">
					<input class="input100" type="text" name="username" placeholder="用户名">
					<span class="focus-input100"></span>
					<span class="symbol-input100">
						<i class="fa fa-envelope" aria-hidden="true"></i>
					</span>
				</div>

				<div class="wrap-input100 validate-input">
					<input class="input100" type="password" id="password" name="pass" placeholder="密码">
					<span class="focus-input100"></span>
					<span class="symbol-input100">
						<i class="fa fa-lock" aria-hidden="true"></i>
					</span>
				</div>
				 <div class="message" style="display: none;height:auto">
           		 <label class="valid" style="color: #f00;text-align:center;"></label>
         		 </div>
				<div class="container-login100-form-btn">
					<button class="login100-form-btn "  id="btn_login" onclick="javascript:;">
						登陆
					</button>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
<script src="${ctxsta}/common/jquery/jquery-2.1.4.min.js"></script> 
<script type="text/javascript"> var baselocation='${ctx}';</script>
<script type="text/javascript">
$(function() {
	$("#btn_login").click(function() {
		loginPassword = $("#password").val();
		loginName = $("input[name='username']").val();
		if(loginPassword==""||loginName=="")
			{
			$(".message").show();
			$(".message").children("label").text("用户名和密码不能为空");	
			return false;
			}
		$.ajax({
			type : "POST",
			url : baselocation+'/common/login',
			data : {
				"username" : loginName,
				"password" : loginPassword
			},
			dataType : "json",
			success : function(result) {
				if (result.code == 200) {
					$(".message").hide();
					window.location.href = baselocation + '/common/toindex';
				} else {
					$(".message").show();
					$(".message").children("label").text(result.message);
				}
			}
		})
	});
});


</script>
<script type="text/javascript">
window.location.hash="no-back";
window.location.hash="Again-No-back-button";
window.onhashchange=function(){window.location.hash="no-back";}
</script>
</html>