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
				<div class="layui-input-inline" style="left: 620px;">
				<button id="gotomylead" class="layui-btn layui-btn-warm" type="button" ><i class="layui-icon ">&#xe602;</i>查看我的借阅</button>
				</div>
			</div>

		</div>
	</form>
	<hr class="layui-bg-green">
	
	<form class="layui-form layui-form-pane">
		<!-- 一排3个 -->
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">书籍名称</label>
				<div class="layui-input-inline">
					<input name="bookname" id="bookname" class="layui-input" placeholder="书籍名称"  />
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">书籍类型</label>
				<div class="layui-input-inline">
					 <select name="bookclass" lay-verify="required" id="bookclass">
      	 			 <option value=""></option>
      	 			  <option value="1">计算机</option>
       				 <option value="2">人文</option>
       				 <option value="3">金融</option>
       				 <option value="4">社科</option>
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
	<table id="dataTable" lay-filter="dataTableFilter"></table>
</div>

<table id="demo" lay-filter="dataTableFilter1"></table>
<form class="layui-form" id="leadform"  style="display:none">
 <div class="layui-form-item">
    <label class="layui-form-label" style="width:170px">确认借阅书籍名称</label>
    <div class="layui-input-block" style="width:270px">
    <input type="text" name="title"  style="width:270px"
    lay-verify="required" class="layui-input" id="leadbookname" value="" readonly="true">
    </div>
  </div>

 <div class="layui-form-item layui-form-text">
    <label class="layui-form-label" style="width:150px" >请确认借阅用户</label>
    <div class="layui-input-block"  style="width:270px">
      <input type="text" name="title"  style="width:270px"
    lay-verify="required" class="layui-input" id="leadbookuser" value="${user.username}" readonly="true">
    </div>
  </div>
  
 <div class="layui-form-item layui-form-text">
    <label class="layui-form-label" style="width:120px" >书籍押金</label>
    <div class="layui-input-block"  style="width:270px">
      <input   style="width:270px"
    	 class="layui-input" id="leadbookprice"  readonly="true"  >
    </div>
  </div>
  
</form>


</body>


<!-- 行工具栏 -->
<script type="text/html" id="rowBtns">
	<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="add"><i class="layui-icon layui-icon-add"></i>申请借阅</button>
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
		var form = layui.form;
		//渲染table数据表格
		var t = table.render({
			id:"dataTableId",
			elem:"#dataTable",
			url:"${pageContext.request.contextPath}/book/getallbooklist",//数据地址
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
				{field:'bookid',title:'书籍编号'},
				{field:'bookname',title:'书籍名称'},
				{field:'subdate',title:'上架时间',
			    	 templet:'<div>{{ Format(d.subdate,"yyyy-MM-dd")}}</div>'},
				{field:'price',title:'借阅押金' ,style:'color:green'},
			
				{field:'classify',title:'书籍分类 ',style:'color:blue' ,templet:function(d){
					if(d.classify == 1){
						return "计算机";
					}else if(d.classify == 2){
						return "人文";
					}else if(d.classify == 3){
						return "金融";
					}else if(d.classify == 4){
						return "社科";
					}
				}},
				{field:'isbrrow',title:'借阅状态' ,style:'color:red',templet:function(d){
					if(d.isbrrow == 1){
						return "已借阅";
					}else if(d.isbrrow == 2){
						return "归还中";
					}else {
						return "";
					}
				}},
				{title:'操作',toolbar:'#rowBtns',fixed:'right',width:'150'}
			]]
		});
		//搜索按钮事件
		$("#searchBtn").click(function(){
			var bookclass = $("#bookclass").val();
			var bookname = $("#bookname").val();
			//进行表格数据重载
			t.reload({
				where:{
					'bookname':bookname,
					'bookclass':bookclass
				},
				page:1
			});
		});
		
		//跳转按钮事件
		$("#gotomylead").click(function(){
			window.location.href = '${pageContext.request.contextPath}/common/tomyleadbook';
		});
		
		
		
		//==行监听事件=============================================
		table.on("tool(dataTableFilter)",function(d){
			var event = d.event;
			var data = d.data;
			if(event == "add"){
				//借阅书籍
				add(data,d);
			}
		});

		//-------------------------------
		function add(data,d){
			var bookname=data.bookname;
			var bookprice=data.price;
			$("#leadbookname").val(bookname);
			$("#leadbookprice").val(bookprice+"元");
		  layer.open({
			  type:1,
			  area:['438px','380px'],
			   title: '用户书籍借阅'
			   ,content: $("#leadform"),
			   shade: 0,
			   btn: ['申请借阅', '取消']
			   ,
			   btn2: function(index, layero){
			  return false;
			   },
			 cancel: function(layero,index){ 
				
			    layer.closeAll();
			   },
			   btn1: function(index, layero){
				   
				   bookid=data.bookid;
				   leadbookname=$("#leadbookname").val();
				   leadbookuser=$("#leadbookuser").val();
				   $.ajax({
						type : 'post',
						dataType : 'json',
						 data: {
							 'bookid':bookid ,
							 'leadbookprice':bookprice ,
							 'leadbookname':leadbookname,
							 'leadbookuser':leadbookuser
					        },
						url :'${pageContext.request.contextPath}/book/userleadbook',
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
		
		//===================
	});
</script>
<script type="text/javascript">
function suaxin()
{
	window.location.href = '${pageContext.request.contextPath}/common/tobookstore';
}

	
	</script>
</html>