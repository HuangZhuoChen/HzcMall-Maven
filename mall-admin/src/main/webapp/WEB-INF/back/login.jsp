<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title> - 登录</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="${ctx}/static/back/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/back/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/static/back/css/animate.css" rel="stylesheet">
    <link href="${ctx}/static/back/css/style.css" rel="stylesheet">
    <link href="${ctx}/static/back/css/login.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>

</head>

<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-12">
                <form id="login-form" method="post">
                    <h4 class="no-margins">登录：</h4>
                    <p class="m-t-md">登录到H+后台主题UI框架</p>
                    <input type="text" id="username" name="username" class="form-control uname" placeholder="用户名" />
                    <input type="password" id="password" name="password" class="form-control pword m-b" placeholder="密码" />
                    <a href="">忘记密码了？</a>
                </form>
                <button class="btn btn-success btn-block" onclick="submitForm()">登录</button>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
                &copy; hAdmin
            </div>
        </div>
    </div>
    <script type="text/javascript">
    	layui.use(['layer', 'form'], function(){
		  var layer = layui.layer;
		  var form = layui.form;
		  
		  layer.msg('Hello World');
		});
    
    	function submitForm() {
    		/*
    		1、验证用户名
    		1.1、验证用户名是否为空
    		1.2、是否合法：4-8数字或字母
    		2、密码不能为空
    		3、ajax提交用户名和密码，并且接受后台返回的json数据
    		*/
    		var username = $("#username").val();
    		var password = $("#password").val();
    		//1.1、验证用户名是否为空
    		if(util.isNull(username)) {
    			mylayer.errorMsg("用户名不能为空");
    			return;
    		}
    		
    		//1.2、是否合法：4-8数字或字母
    		if(!isUsernameValid(username)) {
    			mylayer.errorMsg("用户名不合法，4-8数字或字母");
    			return;
    		}
    		
    		//2、密码不能为空
    		if(util.isNull(password)) {
    			mylayer.errorMsg("密码不能为空");
    			return;
    		}
    		
    		//3、ajax提交用户名和密码，并且接受后台返回的json数据
    		$.ajax({
    			url : "${ctx}/manager/user/login.action",
    			type : "POST",
    			dataType : "json",
    			data : $("#login-form").serialize(),
    			success : function(data) {
    				if(data.code == util.SUCCESS) {
    					mylayer.successUrl(data.msg, "${ctx}/manager/index.action");
    				} else {
    					mylayer.errorMsg(data.msg);
    				}
    			}
    		});
    	}
    	
    	/*是否合法：4-8数字或字母*/
    	function isUsernameValid(value) {
    		var pattern = /^[0-9a-zA-Z]{4,8}$/;
    		return pattern.test(value);
    	} 
    </script>
</body>

</html>
