<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/view/common/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="${ctxsta}/img/img-01.png" type="image/x-icon" />
<link rel="stylesheet" href="${ctxsta}/layui/layui/css/layui.css">
<title>Insert title here</title>
</head>
<body>
<div style="margin-top: 20px;margin-left: 20px">

	<hr class="layui-bg-green">
	<form class="layui-form layui-form-pane">
		<!-- 一排3个 -->
		<div class="layui-form-item">
			<div class="layui-inline"style="width: 150px;">
				用户  ：<h1>${user.username}</h1>
			</div>
			
			<div class="layui-inline">
				<div class="layui-input-inline" style="width: 190px;">
				 账户余额:  <h1>${count}<h1>
				</div>
				<div class="layui-input-inline" style="left: 660px;">
				<button id="invert" class="layui-btn layui-btn-warm" type="button"><i class="layui-icon layui-icon-refresh-1"></i>充值</button>
				</div>
			</div>

		</div>
	</form>
	<hr class="layui-bg-green">
	<div class="layui-input-inline" style="width: 190px;"><h3>我的充值记录</h3>  </div>
	<form class="layui-form layui-form-pane">
		<!-- 一排3个 -->
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">交易金额</label>
				<div class="layui-input-inline">
					<input name="count" id="count" class="layui-input" placeholder="交易金额"  />
				</div>
			</div>
			
			<div class="layui-inline">
				<div class="layui-input-inline" style="width: 90px;">
					<button type="button" class="layui-btn" id="searchBtn"><i class="layui-icon layui-icon-search"></i>搜索</button>
				</div>
				<div class="layui-input-inline">
					<button class="layui-btn  layui-btn-primary" type="reset"><i class="layui-icon layui-icon-refresh-1"></i>重置</button>
				</div>
			</div>

		</div>
	</form>
	<table id="dataTable" lay-filter="dataTableFilter"></table>
</div>

<table id="demo" lay-filter="dataTableFilter1"></table>
<form class="layui-form" id="investform"  style="display:none">
 <div class="layui-form-item">
    <label class="layui-form-label" style="width:170px">请确认充值用户</label>
    <div class="layui-input-block" style="width:270px">
    <input type="text" name="title"  style="width:270px"
    lay-verify="required" class="layui-input" id="investuser" value="${user.username}">
    </div>
  </div>

 <div class="layui-form-item layui-form-text">
    <label class="layui-form-label" style="width:170px" >请确认充值卡号</label>
    <div class="layui-input-block"  style="width:270px">
      <input type="text" name="title"  style="width:270px"
    lay-verify="required" class="layui-input" id="investuserid" value="${user.userid}">
    </div>
  </div>
  
 <div class="layui-form-item layui-form-text">
    <label class="layui-form-label" style="width:170px" >请输入充值金额</label>
    <div class="layui-input-block"  style="width:270px">
      <input type="text" name="title"  style="width:270px"
    lay-verify="required" class="layui-input" id="investcount" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " >
    </div>
  </div>
  
</form>

</body>


<!-- 行工具栏 -->
<script type="text/html" id="rowBtns">
	<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除记录</button>
</script>

<script language="JavaScript" src="${ctxsta}/jquery-3.2.0.min.js"></script>
<script src="${ctxsta}/layui/layui/layui.js"></script>
<script>
    function Format(datetime,fmt) {
        if (parseInt(datetime)==datetime) {
            if (datetime.length==10) {
                datetime=parseInt(datetime)*1000;
            } else if(datetime.length==13) {
                datetime=parseInt(datetime);
            }
        }
        datetime=new Date(datetime);
        var o = {
            "M+" : datetime.getMonth()+1,                 //月份
            "d+" : datetime.getDate(),                    //日
            "h+" : datetime.getHours(),                   //小时
            "m+" : datetime.getMinutes(),                 //分
            "s+" : datetime.getSeconds()                 //秒
                    
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (datetime.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }
</script>
<script type="text/javascript">
	layui.use(['form','layer','laydate','table'],function(){
		
		var layer = layui.layer;
		var table = layui.table;
		//渲染table数据表格
		var t = table.render({
			id:"dataTableId",
			elem:"#dataTable",
			url:"${pageContext.request.contextPath}/invest/findlistnote",//数据地址
			page:true,//开启分页
			toolbar:"#headerBtns",//
			height:450,
			parseData:function(rs){//数据解析
				if(rs.code == 0){
					return {
						"code":rs.code,
						"msg":rs.message,
						"count":rs.data.count,
						"data":rs.data.data
					}
				}
			},
			
			cols:[[//数据表头
				{field:'actionid',title:'交易编号'},
				{field:'investtime',title:'交易时间',
			    	 templet:'<div>{{ Format(d.investtime,"yyyy-MM-dd hh:mm:ss")}}</div>'},
				{field:'investcount',title:'交易金额' ,style:'color:green'},
				{field:'count',title:'交易时余额 ',style:'color:blue' },
				{title:'操作',toolbar:'#rowBtns',fixed:'right',width:'150'}
			]]
		});
		//搜索按钮事件
		$("#searchBtn").click(function(){
			var count = $("#count").val();
			
			//进行表格数据重载
			t.reload({
				where:{
					'investcount':count,
				},
				page:1
			});
		});
		
		//==行监听事件=============================================
		table.on("tool(dataTableFilter)",function(d){
			var event = d.event;
			var data = d.data;
			if(event == "del"){
				//重置密码
				del(data,d);
			}
		});

		//-------------------------------
		function del(data,d){
			//使用二次确认
			layer.confirm("确定要删除吗?",function(index){
				//将需要重置的用户ID 传给后台
				$.post("${pageContext.request.contextPath}/invest/delinvestlistone",{actionid:data.actionid},function(rs){
					//校验业务码
					if(rs.code != 200){
						//显示异常信息
						layer.msg(rs.message);
						return false;
					}
					d.del();
					layer.msg("删除成功");
					//关闭弹出层
					layer.close(index);
					//重载数据列表
					//$("#searchBtn").click();
				});
			});
		}

	});
</script>
<script type="text/javascript">
function suaxin()
{
	window.location.href = '${pageContext.request.contextPath}/common/toinvest';
}
function dendai()
{
	var index = layer.load(1, {
		  shade: [0.1,'#fff'], //0.1透明度的白色背景
		  time:300000
		});
}
	$(document).ready(
			function(){
				var investuserid,
				investuser,
				investcount;
		  $("#invert").click(function(){
			  
			  layer.open({
				  type:1,
				  area:['438px','380px'],
				   title: '用户充值'
				   ,content: $("#investform"),
				   shade: 0,
				   btn: ['提交', '重置']
				   ,
				   btn2: function(index, layero){
				  return false;
				   },
				 cancel: function(layero,index){ 
					
				    layer.closeAll();
				   },
				   btn1: function(index, layero){
					   dendai();
					   investuserid=$("#investuserid").val();
					   investuser=$("#investuser").val();
					   investcount=$("#investcount").val();
					   $.ajax({
							type : 'post',
							dataType : 'json',
							 data: {
								 'investuserid':investuserid ,
								 'investuser':investuser,
								 'investcount':investcount,
						        },
							url :'${pageContext.request.contextPath}/invest/investcore',
							success : function(result) {
									
								if (result.code == 200) {
									layer.msg(result.message, {
										icon : 1,
										time : 1000
									});
									setInterval(suaxin,3000);
								} else {
									layer.alert(result.message, {
										icon : 2,
										time : 1000
									});
								}
							}
						})
				   }
			 });
		  });

		});

	</script>
</html>