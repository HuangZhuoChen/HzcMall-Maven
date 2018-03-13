<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>
    
    <script type="text/javascript" src="${ctx}/static/lib/echarts/echarts.js"></script>
    <script type="text/javascript">
    	$(function() {
    		 // 基于准备好的dom，初始化echarts实例
        	var myChart = echarts.init(document.getElementById('main'));
    		$.ajax({
    			url : '${ctx}/category/getCategoryCountAnalysis.action',
    			type : 'POST',
    			dataType : 'json',
    			success : function(jsonObj) {
    				if(jsonObj.code == util.SUCCESS) {
    					var data = jsonObj.data;//data就是List<CategoryCountVo>
    					var xArrayData = new Array();//x轴放分类的name
    					var yArrayData = new Array();//y轴放分类的count
    					for(var i = 0; i < data.length; i++) {
    						xArrayData.push(data[i].name);
    						yArrayData.push(data[i].count);
    					}
    					// 指定图表的配置项和数据
    			        var option = {
    			            title: {
    			                text: '分类数量统计'
    			            },
    			            tooltip: {},
    			            legend: {
    			                data:['销量']
    			            },
    			            xAxis: {
    			                data: xArrayData
    			            },
    			            yAxis: {},
    			            series: [{
    			                name: '销量',
    			                type: 'bar',
    			                data: yArrayData
    			            }]
    			        };
    			        // 使用刚指定的配置项和数据显示图表。
    		        	myChart.setOption(option);
    				}
    			}
    		});
    	});
    </script>
</body>
</html>