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
	<!-- 搜索的form -->
	<form class="layui-form layui-form-pane">
		<!-- 一排3个 -->
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">交易地点</label>
				<div class="layui-input-inline">
					 <select name="city" lay-verify="required" id="materName">
      	 			 <option value=""></option>
      	 			  <option value="6">充值</option>
       				 <option value="1">食堂</option>
       				 <option value="2">餐厅</option>
       				 <option value="3">校车</option>
       				 <option value="4">超市</option>
       				 <option value="5">图书馆</option>
     				 </select>
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
	<hr class="layui-bg-green">
	<table id="dataTable" lay-filter="dataTableFilter"></table>
</div>
</body>
<!-- 头工具栏 -->

<!-- 行工具栏 -->
<script type="text/html" id="rowBtns">
	<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除该数据</button>
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
            "s+" : datetime.getSeconds(),                 //秒
            "q+" : Math.floor((datetime.getMonth()+3)/3), //季度
            "S"  : datetime.getMilliseconds()             //毫秒
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
			url:"${pageContext.request.contextPath}/shoping/findshopinglist",//数据地址
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
				{field:'actiontime',title:'交易时间',
			    	 templet:'<div>{{ Format(d.actiontime,"yyyy-MM-dd hh:mm:ss")}}</div>'},
				{field:'localid',title:'交易地点' ,templet:function(d){
					if(d.localid == 1){
						return "食堂";
					}else if(d.localid == 2){
						return "餐厅";
					}else if(d.localid == 3){
						return "校车";
					}else if(d.localid == 4){
						return "超市";
					}else if(d.localid == 5){
						return "图书馆";
					}else if(d.localid == 6){
						return "充值";
					}
				}},
				{field:'actionmoney',title:'交易金额' ,style:'color:green'},
				{field:'blance',title:'交易后余额' ,style:'color:blue'},
				{title:'操作',toolbar:'#rowBtns',fixed:'right',width:'150'}
			]]
		});
		//搜索按钮事件
		$("#searchBtn").click(function(){
			var materName = $("#materName").val();
			//进行表格数据重载
			t.reload({
				where:{
					'localid':materName,
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
				del(data);
			}
		});

		//--重置密码-----------------------------
		function del(data){
			//使用二次确认
			layer.confirm("确定要删除吗?",function(index){
				//将需要重置的用户ID 传给后台
				$.post("${pageContext.request.contextPath}/shoping/delshopinglistone",{actionid:data.actionid},function(rs){
					//校验业务码
					if(rs.code != 200){
						//显示异常信息
						layer.msg(rs.message);
						return false;
					}
					layer.msg("删除成功");
					//关闭弹出层
					layer.close(index);
					//重载数据列表
					$("#searchBtn").click();
				});
			});
		}



	});
</script>
</html>