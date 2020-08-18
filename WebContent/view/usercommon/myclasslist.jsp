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
				 账户余额:<h1>${count}<h1>
				</div>
				<div class="layui-input-inline" style="left: 620px;">
				
				</div>
			</div>

		</div>
	</form>
	<hr class="layui-bg-green">
	
	<form class="layui-form layui-form-pane">
		<!-- 一排3个 -->
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">课程名称</label>
				<div class="layui-input-inline">
					<input name="classname" id="classname" class="layui-input" placeholder="课程名称"  />
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
	<form class="layui-form" id="leadform"  style="display:none">
 <div class="layui-form-item">
    <label class="layui-form-label" style="width:170px">确认课程名称</label>
    <div class="layui-input-block" style="width:270px">
    <input type="text" name="title"  style="width:270px"
    lay-verify="required" class="layui-input" id="classname1" value="" readonly="true">
    </div>
  </div>

 <div class="layui-form-item layui-form-text">
    <label class="layui-form-label" style="width:150px" >请确认用户</label>
    <div class="layui-input-block"  style="width:270px">
      <input type="text" name="title"  style="width:270px"
    lay-verify="required" class="layui-input" id="leadbookuser" value="${user.username}" readonly="true">
    </div>
  </div>
  
 <div class="layui-form-item layui-form-text">
    <label class="layui-form-label" style="width:120px" >当前打卡时间</label>
    <div class="layui-input-block"  style="width:270px">
      <input   style="width:270px"
    	 class="layui-input" id="classchecktime"  readonly="true"  >
    </div>
  </div>
  
</form>

	
	<table id="dataTable" lay-filter="dataTableFilter"></table>
</div>
</body>
<!-- 头工具栏 -->

<!-- 行工具栏 -->
<script type="text/html" id="rowBtns">
	<button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="add"><i class="layui-icon layui-icon-add"></i>课程打卡</button>
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
            "s+" : datetime.getSeconds()             //秒
            
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
	//渲染table数据表格
	var layer = layui.layer;
	var table = layui.table;
	var t = table.render({
		id:"dataTableId",
		elem:"#dataTable",
		url:"${pageContext.request.contextPath}/class/userclassinfo",//数据地址
		page:true,//开启分页
		toolbar:"#headerBtns",//
		height:750,
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
			{field:'classid',title:'课程编号'},
			{field:'classname',title:'课程名称'},
			{field:'classbegintime',title:'课程开始时间',
		    	 templet:'<div>{{ Format(d.classbegintime," hh:mm")}}</div>'},
		    {field:'classendtime',title:'课程结束时间',
			    	 templet:'<div>{{ Format(d.classendtime," hh:mm")}}</div>'},
			{field:'actionid',title:'课程选中状态 ',style:'color:blue' ,templet:function(d){
				if(d.actionid != null){
					return "已选择";
				}else {
					return "";
				}
			}},
			
			{title:'操作',toolbar:'#rowBtns',fixed:'right',width:'150'}
		]]
	});
	//搜索按钮事件
	$("#searchBtn").click(function(){
		var classname = $("#classname").val();
		//进行表格数据重载
		t.reload({
			where:{
				'classname':classname
			},
			page:1
		});
		
	});
	//----------------------------
	
	//==行监听事件=============================================
	table.on("tool(dataTableFilter)",function(d){
		var event = d.event;
		var data = d.data;
		if(event == "add"){
			//借阅书籍
			add(data,d);
		}
	});
	//==========用户打卡======
	function add(data,d){
		var classname=data.classname;
		 var now = new Date();
		 var hour = now.getHours();//得到小时
		  var minu = now.getMinutes();//得到分钟
		  var sec = now.getSeconds();//得到秒
		$("#classname1").val(classname);
		$("#classchecktime").val(hour+"时"+minu+"分"+sec+"秒");
	  layer.open({
		  type:1,
		  area:['438px','380px'],
		   title: '用户打卡'
		   ,content: $("#leadform"),
		   shade: 0,
		   btn: ['打卡', '取消']
		   ,
		   btn2: function(index, layero){
		  return false;
		   },
		 cancel: function(layero,index){ 
			
		    layer.closeAll();
		   },
		   btn1: function(index, layero){
			   
			   actionid=data.actionid;
			   classid=data.classid;
			   $.ajax({
					type : 'post',
					dataType : 'json',
					 data: {
						 'actionid':actionid,
						 'classid':classid
				        },
					url :'${pageContext.request.contextPath}/class/classcheck',
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
	}
	
	
	function suaxin()
	{  layer.closeAll();
		window.location.href = '${pageContext.request.contextPath}/common/tomyclass';
	}
	
	
	});
</script>
<script type="text/javascript">

</script>
</html>