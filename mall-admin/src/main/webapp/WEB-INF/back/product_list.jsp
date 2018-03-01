<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title> - 商品列表</title>
</head>
<body>
	<div class="demoTable">
	  名称：
	  <div class="layui-inline">
	    <input class="layui-input" name="id" id="searchName" autocomplete="off">
	  </div>
	  子标题：
	  <div class="layui-inline">
	    <input class="layui-input" name="id" id="searchSubtitle" autocomplete="off">
	  </div>
	  <button class="layui-btn" data-type="search">搜索</button>
	  <button class="layui-btn layui-btn-danger" data-type="deleteAll">批量删除</button>
	  <button class="layui-btn" data-type="add">添加商品</button>
	</div>
	<table class="layui-hide" id="datagrid" lay-filter="datagrid"></table>
	
	<script type="text/html" id="toolbar">
  		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
  		<a class="layui-btn  layui-btn-xs" lay-event="edit">编辑</a>
  		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>
	
	<script type="text/html" id="imgTpl">
  		<img src="/pic/{{d.mainImage}}">
	</script>
	
	<script type="text/html" id="statusTpl">
  		{{# 	if(d.status==1) { 	}}
					上架
		{{# 	} else {			}}
					下架
		{{#		}					}}
	</script>
	<script type="text/html" id="statusTpl1">
  		{{# 	if(d.status==1) { 	}}
					<button class="layui-btn">上架</button>
		{{# 	} else {			}}
					<button class="layui-btn">下架</button>
		{{#		}					}}
	</script>
	
	<script>
		layui.use('table', function(){
		  var table = layui.table;
		  
		  table.render({
		    elem: '#datagrid', //要渲染哪个表格
		    url:'${ctx}/manager/product/pageList.action', //异步数据接口
		    cellMinWidth: 50, //列宽自动分配，全局定义常规单元格的最小宽度
		    cols: [[
			  {type:'checkbox'},
		      {field:'id', title: 'ID', sort: true, width:50},
		      {field:'name', title: '名称', sort: true},
		      {field:'subtitle', title: '标题', sort: true},
		      {field:'mainImage', title: '商品主图', templet: '#imgTpl'},
		      {field:'price', title: '价格', sort: true},
		      {field:'stock', title: '库存', sort: true},
		      {field:'status', title: '状态', templet: '#statusTpl'},
		      {field:'createTime', title: '创建时间', sort: true},
		      {field:'updateTime', title: '更新时间', sort: true},
		      {fixed: 'right', width : 170, toolbar: '#toolbar'} //这里的toolbar值是模板元素的选择器
		    ]],
		    page: true,
		    id : "listReload" //设定容器唯一ID，id值是对表格的数据操作方法上是必要的传递条件，它是表格容器的索引
		  });
		 
		  //var $ = layui.$;
		  active = {
		    //这里的表格重载是指对表格重新进行渲染，包括数据请求和基础参数的读取
		    search: function(){
		      //执行重载
		      table.reload('listReload', {//参数 ID 即为基础参数id对应的值,容器唯一ID 
	    	  	where: { //设定异步数据接口的额外参数，任意设
	    		    name : $('#searchName').val(),
	    		    subtitle : $('#searchSubtitle').val(),
	    		},
	    		page: {
	    		   curr: 1 //重新从第 1 页开始
	    		}
		      });
		    },
		    deleteAll : function() {
		    	var checkStatus = table.checkStatus('listReload');
		    	var data = checkStatus.data;
		    	console.log(checkStatus.data) //获取选中行的数据
		    	console.log(checkStatus.data.length) //获取选中行数量，可作为是否有选中行的条件
		    	console.log(checkStatus.isAll) //表格是否全选
		    	layer.confirm('确定要删除这' + data.length + '条数据吗？', function(index){
			    	var ids = util.getSelectedIds(data);
			    	$.ajax({
			    		url : '${ctx}/manager/product/deleteAll.action',
			    		data : {'ids' : ids},
			    		dataType : 'json',
			    		success : function(jsonData) {
			    			if(jsonData.code == util.SUCCESS) {
		       					mylayer.success(jsonData.msg);
		       					active.search();
		       				} else {
		       					mylayer.errorMsg(jsonData.msg);
		       				}
			    		}
		    		});
	    		});
		    },
		    add : function() {
		    	location.href = "${ctx}/manager/product/getAddPage.action";
		    }
		  };
		  
		 //监听工具条
		  table.on('tool(datagrid)', function(obj){ //注：tool是工具条事件名，datagrid是table原始容器的属性 lay-filter="对应的值"
		    var data = obj.data; //获得当前行数据
		    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		    var tr = obj.tr; //获得当前行 tr 的DOM对象
		   
		    if(layEvent === 'detail'){ //查看
		    	layer.msg("查看：" + data.id);
		    } else if(layEvent === 'del'){ //删除
		    	layer.confirm('真的删除行么', function(index){
		       		$.ajax({
		       			url : '${ctx}/manager/product/deleteById.action',
		       			data : {'id' : data.id},
		       			dataType : 'json',
		       			success : function(jsonData) {
		       				if(jsonData.code == util.SUCCESS) {
		       					mylayer.success(jsonData.msg);
		       					active.reload();
		       				} else {
		       					mylayer.errorMsg(jsonData.msg);
		       				}
		       				layer.close(index);
		       			}
		       		});
		      	});
		    } else if(layEvent === 'edit'){ //编辑
		    	layer.msg("查看：" + data.id);
		    	layer.open({
		    		type : 2, // iframe
		    		title : '商品编辑',
		    		area : ['1000px', '650px'],
		    		offset : '10px', //只定义top坐标，水平保持居中
		    		content : '${ctx}/manager/product/getEditPage.action?id=' + data.id
		    	});
		    }
		  });
		  
		  //触发搜索按钮点击事件
		  $('.demoTable .layui-btn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
		});
	</script>
</body>
</html>
