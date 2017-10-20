<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>

<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="${pageContext.request.contextPath }/index">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.png">
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
					<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障">
</div>	</div>
	<%@ include file="menu.jsp" %>
	
</div>	<div class="container cart">
		<s:if test="#session.cart.cartItems.size() != 0">
			<div class="span24">
				<div class="step step1">
					
				</div>
					<table>
						<tbody><tr>
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
							<th>操作</th>
						</tr>
							<s:iterator value="#session.cart.cartItems" var="cartItem">
							<tr>
								<td width="60">
									<img src="${pageContext.request.contextPath}/<s:property value="#cartItem.product.image"/>">
								</td>
								<td>
									<a target="_blank"><s:property value="#cartItem.product.pname"/></a>
								</td>
								<td>
									￥<s:property value="#cartItem.product.shop_price"/>
								</td>
								<td class="quantity" width="60">
									<s:property value="#cartItem.count"/>
								</td>
								<td width="140">
									<span class="subtotal">￥<s:property value="#cartItem.subMoney"/></span>
								</td>
								<td>
									<a href="${pageContext.request.contextPath }/cart_removeCart.action?pid=<s:property value="#cartItem.product.pid" />" class="delete">删除</a>
								</td>
							</tr>
							</s:iterator>
					</tbody></table>
					<dl id="giftItems" class="hidden" style="display: none;">
					</dl>
					<div class="total">
						<em id="promotion"></em>
								<em>
									登录后确认是否享有优惠
								</em>
						赠送积分: <em id="effectivePoint">596</em>
						商品金额: <strong id="effectivePrice">￥<s:property value="#session.cart.totalMoney"/>元</strong>
					</div>
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/cart_clearCart.action" id="clear" class="clear">清空购物车</a>
						<a href="${pageContext.request.contextPath }/order_save.action" id="submit" class="submit">提交订单</a>
					</div>
			</div>
		</s:if>
		<s:else>
			<div class="span24" style="text-align: center;margin-top: 20px;">
				<div class="step step1">
					<h2><span style="color:red;">亲，您的购物车空空如也！</span></h2>
				</div>
			</div>
		</s:else>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
				<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a>招贤纳士</a>
						|
					</li>
					<li>
						<a>法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a>服务声明</a>
						|
					</li>
					<li>
						<a>广告声明</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>