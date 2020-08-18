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
				<label class="layui-form-label">校车起点</label>
				<div class="layui-input-inline">
					<input name="bussstart" id="bussstart" class="layui-input" placeholder="请输入校车起点信息"  />
				</div>
			</div>
			
			<div class="layui-inline">
				<label class="layui-form-label">校车终点</label>
				<div class="layui-input-inline">
					<input name="bussend" id="bussend" class="layui-input" placeholder="请输入校车终点信息"  />
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


<table id="demo" lay-filter="dataTableFilter1"></table>
<form class="layui-form" id="leadform"  style="display:none">
 <div class="layui-form-item">
    <label class="layui-form-label" style="width:170px">确认路线编号</label>
    <div class="layui-input-block" style="width:270px">
    <input type="text" name="title"  style="width:270px"
    lay-verify="required" class="layui-input" id="buslineid" value="" readonly="true">
    </div>
  </div>

 <div class="layui-form-item layui-form-text">
    <label class="layui-form-label" style="width:150px" >路线起点</label>
    <div class="layui-input-block"  style="width:270px">
      <input type="text" name="title"  style="width:270px"
    lay-verify="required" class="layui-input" id="busstart" value="" readonly="true">
    </div>
  </div>
  
  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label" style="width:150px" >路线终点</label>
    <div class="layui-input-block"  style="width:270px">
      <input type="text" name="title"  style="width:270px"
    lay-verify="required" class="layui-input"  id="busend" value="" readonly="true">
    </div>
  </div>
  
 <div class="layui-form-item layui-form-text">
    <label class="layui-form-label" style="width:120px" >路线价格</label>
    <div class="layui-input-block"  style="width:270px">
      <input   style="width:270px"
    	 class="layui-input" id="busprice"  readonly="true"  >
    </div>
  </div>
  
  
</form>


</body>
<!-- 头工具栏 -->

<!-- 行工具栏 -->
<script type="text/html" id="rowBtns">
	<button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="add"><i class="layui-icon layui-icon-add"></i>申请该路线</button>
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
			url:"${pageContext.request.contextPath}/bus/usergetallschoolbus",//数据地址
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
				{field:'lineid',title:'路线编号'},
				{field:'starttime',title:'发车时间'},
				{field:'start',title:'出发地点'},
				{field:'end',title:'到达地点'},
				{field:'price',title:'学期金额' ,style:'color:green'},
				{field:'issub',title:'预定信息' ,style:'color:blue',templet:function(d){
					if(d.issub == 1){
						return "已预订";
					}else {
						return "";
					}
				}},
				{title:'操作',toolbar:'#rowBtns',fixed:'right',width:'150'}
			]]
		});
		//搜索按钮事件
		$("#searchBtn").click(function(){
			var bussstart = $("#bussstart").val();
			var bussend = $("#bussend").val();
			//进行表格数据重载
			t.reload({
				where:{
					'bussstart':bussstart,
					'bussend':bussend
				},
				page:1
			});
		});
		
		//==行监听事件=============================================
		table.on("tool(dataTableFilter)",function(d){
			var event = d.event;
			var data = d.data;
			if(event == "add"){
				//重置密码
				add(data);
			}
		});
		
		//--预定该路线    这里使用  借阅图书的模板    subline   userid   lineid
		function add(data,d){
			var busstart=data.start;
			var lineid=data.lineid;
			var busend=data.end;
			var busprice=data.price;
			$("#buslineid").val(lineid);
			$("#busstart").val(busstart);
			$("#busend").val(busend);
			$("#busprice").val(busprice+"元");
		  layer.open({
			  type:1,
			  area:['438px','540px'],
			   title: '用户校车申请'
			   ,content: $("#leadform"),
			   shade: 0,
			   btn: ['申请路线', '取消']
			   ,
			   btn2: function(index, layero){
			  return false;
			   },
			 cancel: function(layero,index){ 
				
			    layer.closeAll();
			   },
			   btn1: function(index, layero){
				   
				   lineid=data.lineid;
				   busstart=$("#busstart").val();
				   busend=$("#busend").val();
				   $.ajax({
						type : 'post',
						dataType : 'json',
						 data: {
							 'lineid':lineid ,
							 'price':busprice,
							 'busstart':busstart,
							 'busend':busend
					        },
						url :'${pageContext.request.contextPath}/bus/subline',
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

	});
</script>
</html>