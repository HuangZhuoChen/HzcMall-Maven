<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>靓淘网_购物车</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/front/css/cart_style.css" />
	</head>

	<body>
		<div class="bg_color">
			<div class="top_center">
				<div class="left">
					<span class="wel">欢迎来到靓淘网！</span>
				</div>
				<div class="right">
					<ul>
						<li>
							<a class="login" href="login.html" target="_blank">请登录</a>
						</li>
						<li>
							<a href="register.html" target="_blank">快速注册</a>
						</li>
						<li>
							<a class="collect" href="">我的收藏</a>
						</li>
						<li>
							<a class="indent" href="">我的订单</a>
						</li>
						<li>
							<a class=phone href="">手机靓购</a>
						</li>
						<li>
							<a href="">我的积分</a>
						</li>
						<li>
							<a href="">我的评价</a>
						</li>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="logo_center">
			<div class="left">
				<img class="logo_img" src="${ctx}/static/front/img/LOGO.png" />
				<span class="car_text">购物车</span>
			</div>
			<div class="right">
				<input class="car_input" type="text" />
				<input class="car_search" type="button" value="搜索" />
			</div>
		</div>
		<div class="title">
			<div class="title_top">
				<ul>
					<li>
						<a href="">全部商品</a>
					</li>
					<li>
						<a href="">降价商品</a>
					</li>
					<li>
						<a href="">库存紧张</a>
					</li>
				</ul>
				<p style="margin:0;padding-right: 10px; float: right;line-height: 40px;">配送至：<span style="border: 1px solid rgb(51,51,51);">山东省 青岛市 市南区 <img src="${ctx}/static/front/img/narrow.png"/></span></p>
			</div>
			<div class="title_center">
				<ul style="color: #666666;margin-top: 10px;margin-bottom: 10px;">
					<li style="margin-left: 16px;margin-right: 8px;">
						<input type="checkbox" />
					</li>
					<li style="margin-left: 8px;margin-right: 38px;">全选</li>
					<li style="margin-left: 38px;margin-right: 168px;">商品</li>
					<li style="margin-left: 138px;margin-right: 58px;">单价</li>
					<li style="margin-left: 58px;margin-right: 58px;">数量</li>
					<li style="margin-left: 58px;margin-right: 58px;">小计</li>
					<li style="margin-left: 58px;margin-right: 36px;">操作</li>
				</ul>
			</div>
			<div class="title_bottom">
			<input type="checkbox" style="color: #666666;margin: 23px 11px 10px 22px;" />
			<img src="${ctx}/static/front/img/156.png" style="margin: 0px 142px 0px 11px; " />
			<img src="${ctx}/static/front/img/157.png" style="margin-left: 142px; " />
		</div>
		</div>
		<c:forEach items="${cartVo.cartItemVos}" var="cartItemVo">
			<div class="car_1">
				<div class="car_1_top">
					<img src="${ctx}/static/front/img/158.png" />
					<p class="car_1_top_p">
						<span class="span1">
							活动商品购满¥105.00 , 即可加价换购商品1件&gt;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
						<span class="span2">
							&nbsp;查看换购品
						</span>
						<span class="span3">
							&nbsp;&nbsp;去凑单&gt;
						</span>
					</p>
				</div>
				<div class="car_2_bottom">
					<div class="car_con_1" onclick="selectProductStatus(${cartItemVo.product.id})">
						<c:if test="${cartItemVo.isChecked==1}">
							<input checked id="checkbox${cartItemVo.product.id}" name="selectCheckbox" type="checkbox" />
						</c:if>
						<c:if test="${cartItemVo.isChecked==0}">
							<input id="checkbox${cartItemVo.product.id}" name="selectCheckbox" type="checkbox" />
						</c:if>
					</div>
					<div class="car_con_2">
						<img src="${ctx}/static/front/img/159.png" />
					</div>
					<div class="car_con_3">
						<p class="p_title">${cartItemVo.product.name}</p>
						<img src="${ctx}/static/front/img/160.png" />
						<p class="p_seven">&nbsp;支持7天无理由退货</p>
						<img src="${ctx}/static/front/img/161.png" />
						<p class="p_select">&nbsp;选包装</p>
					</div>
					<ul class="car_ul">
						<li class="price">
							<span style="color: #CCCCCC; margin-bottom: 15px;line-height: 20px;">
								<del>
									¥ 1699.00<br />
								</del>
							</span>
							<span style="color: #666666;" id="price${cartItemVo.product.id}" price="${cartItemVo.product.price}">
								¥ ${cartItemVo.product.price}
							</span>
						</li>
						<li class="num_select">
							<input class="car_ul_btn1" type="button" value="-" onclick="addOrSub(${cartItemVo.product.id}, '-')"/>
							<input class="car_ul_text" type="text" id="num${cartItemVo.product.id}" placeholder="1" value="${cartItemVo.amount}"/>
							<input class="car_ul_btn2" type="button" value="+" onclick="addOrSub(${cartItemVo.product.id}, '+')"/>
						</li>
						<li class="money">
							<span style="color: #F41443;" id="cartItemTotalPrice${cartItemVo.product.id}">
								${cartItemVo.product.price*cartItemVo.amount}
							</span>
						</li>
						<li class="delete">
							<img onclick="delCartItemById(${cartItemVo.product.id})" src="${ctx}/static/front/img/166.png" />
						</li>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
		</c:forEach>
	
		</div>
		<div class="blank">
			
		</div>
		<div class="total">
				<ul style="color: #666666;margin-top: 10px;margin-bottom: 10px;">
					<li style="margin-left: 16px;margin-right: 8px;">
						<input type="checkbox" />
					</li>
					<li style="margin-left: 8px;margin-right: 265px;">全选</li>
					<li style="margin-left: 265px;margin-right: 18px;">总金额（已免运费）：<span id="totalPrice" style="color: #F41443;">¥0</span></li>
					<li class="total_right"><a href="">立即结算</a></li>
				</ul>
			</div>
					<div class="sp">
			<div class="sp1">
				<p style="color: rgb(51,51,51);">品质保障</p>
				<p style="font-size: 12px; color: #808080;margin-top: 5px;">品质护航 购物无忧</p>
			</div>
			<div class="sp2">
				<p style="color: rgb(51,51,51);">品质保障</p>
				<p style="font-size: 12px; color: #808080;margin-top: 5px;">品质护航 购物无忧</p>
			</div>
			<div class="sp3">
				<p style="color: rgb(51,51,51);">品质保障</p>
				<p style="font-size: 12px; color: #808080;margin-top: 5px;">品质护航 购物无忧</p>
			</div>
			<div class="sp4">
				<p style="color: rgb(51,51,51);">品质保障</p>
				<p style="font-size: 12px; color: #808080;margin-top: 5px;">品质护航 购物无忧</p>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="more">
			<div class="mb1">
				<ul>
					<li>
						购物指南
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;免费注册
						</a>
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;开通支付宝
						</a>
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;支付宝充值
						</a>
					</li>
				</ul>
			</div>
			<div class="mb2">
				<ul>
					<li>
						品质保障
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;发票保障
						</a>
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;售后规则
						</a>
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;缺货赔付
						</a>
					</li>
				</ul>
			</div>
			<div class="mb3">
				<ul>
					<li>
						支付方式
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;快捷支付
						</a>
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;信用卡
						</a>
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;货到付款
						</a>
					</li>
				</ul>
			</div>
			<div class="mb4">
				<ul>
					<li>
						商家服务
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;商家入驻
						</a>
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;商家中心
						</a>
					</li>
					<li>
						<a href="">
							&nbsp;&nbsp;运营服务
						</a>
					</li>
				</ul>
			</div>
			<div class="mb5">
				<ul>
					<li>
						手机靓淘
					</li>
					<li>
						<img src="${ctx}/static/front/img/98.png"/>
					</li>
				</ul>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="link">
			<ul>
				<li>
					<a href="">
						关于靓淘
					</a>
				</li>
				<li>
					<a href="">
						帮助中心
					</a>
				</li>
				<li>
					<a href="">
						开放平台
					</a>
				</li>
				<li>
					<a href="">
						诚聘精英
					</a>
				</li>
				<li>
					<a href="">
						联系我们
					</a>
				</li>
				<li>
					<a href="">
						网站合作
					</a>
				</li>
				<li>
					<a href="">
						法律声明及隐私政策
					</a>
				</li>
				<li>
					<a href="">
						知识产权
					</a>
				</li>
				<li>
					<a href="">
						廉政举报
					</a>
				</li>
				<li>
					<a href="">
						规则意见征集
					</a>
				</li>
			</ul>
		</div>
		<div class="copyright">
			COPYRIGHT 2010-2017 北京创锐文化传媒有限公司 JUMEI.COM 保留一切权利. 客服热线：400-123-888888<br /> 
			京公网安备 110101020011226|京ICP证111033号|食品流通许可证 SP1101051110165515（1-1）|营业执照
		</div>
	</body>
	<script type="text/javascript">
		layui.use(['layer'], function(){
		  var layer = layui.layer;
		});
		
		$(function(){
			refreshTotalPrice();
		});
		
		//+ -更新商品数量
		function addOrSub(productId, operator){
			var delta;
			if(operator=='+') {
				delta = 1;
			} else {
				delta = -1;
			}
			var num = $('#num'+productId).val();
			$.ajax({
				url : '${ctx}/cart/updateCart.shtml',
				data : {'productId' : productId, 'amount' : delta},
				type : 'POST',
				dataType : 'json',
				success : function(jsonObj) {
					if(jsonObj.code == util.SUCCESS) {
						num = parseInt(num) + delta;
						$('#num'+productId).val(num);
						var price = $('#price'+productId).attr('price');
						var totalPrice = num * price;
						$('#cartItemTotalPrice'+productId).html(totalPrice);
					} else {
						mylayer.errorMsg(jsonObj.msg);
					}
				}
			});
		}
		
		//选择或取消选择某件商品，点击checkbox
		function selectProductStatus(productId) {
			var isChecked = $('#checkbox'+productId).prop('checked');
			$.ajax({
				url : '${ctx}/cart/updateCart.shtml',
				data : {'productId' : productId, 'isChecked' : isChecked},
				type : 'POST',
				dataType : 'json',
				success : function(jsonObj) {
					if(jsonObj.code == util.SUCCESS) {
						refreshTotalPrice();
					} else {
						mylayer.errorMsg(jsonObj.msg);
					}
				}
			});
		}
		
		//重新计算购物车选中商品的总价格
		function refreshTotalPrice() {
			var checkboxs = $('input[name=selectCheckbox]:checked');
			var totalPrice = 0.00;
			for(var i = 0; i < checkboxs.length; i++) {
				//checkbox268
				var checkboxId = checkboxs[i].getAttribute('id');
				var id = checkboxId.substr('checkbox'.length);
				var cartItemTotalPrice = $('#cartItemTotalPrice'+id).html();
				totalPrice += parseFloat(cartItemTotalPrice);
			}
			$('#totalPrice').html(totalPrice);
		}
		
		function delCartItemById(productId){
			layer.confirm('您确认要删除么?', function(){
				$.ajax({
					url : '${ctx}/cart/delCartItemById.shtml',
					data : {'productId' : productId},
					type : 'POST',
					dataType : 'json',
					success : function(jsonObj) {
						if(jsonObj.code == util.SUCCESS) {
							mylayer.success(jsonObj.msg);
							//在dom页面中将CartItem项移除
							$('#checkbox'+productId).parent().parent().parent().remove();
							refreshTotalPrice();
						} else {
							mylayer.errorMsg(jsonObj.msg);
						}
					} 
				});
			});
		}
	</script>

</html>