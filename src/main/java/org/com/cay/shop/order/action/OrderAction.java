package org.com.cay.shop.order.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.com.cay.shop.cart.entity.Cart;
import org.com.cay.shop.cart.entity.CartItem;
import org.com.cay.shop.order.entity.Order;
import org.com.cay.shop.order.entity.OrderItem;
import org.com.cay.shop.order.service.IOrderService;
import org.com.cay.shop.user.entity.User;
import org.com.cay.shop.utils.PageBean;
import org.com.cay.shop.utils.PaymentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	@Autowired
	private IOrderService orderService;

	private Integer pageNo = 1;

	// 支付通道编码
	private String pd_FrpId;

	// 接收付款成功后的参数:
	private String r3_Amt;
	private String r6_Order;

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Order model = new Order();

	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public String save() {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (user == null) {
			return "loginPage";
		}
		model.setUser(user);

		// 数据补全
		model.setOrderTime(new Date());
		model.setState(1);// 1：未付款，2：已付款，但未发货，3：已发货，但未确认收获，4：已确认收获

		// 获取购物车
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			ActionContext.getContext().getValueStack().set("message", "亲，您还未购物！");
			return "msg";
		}

		// 获取购物车中的订单项
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubMoney(cartItem.getSubMoney());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(model);

			model.getOrderItems().add(orderItem);
		}
		model.setTotalMoney(cart.getTotalMoney());

		orderService.save(model);

		// 清空购物车
		cart.clearCart();
		return "save";
	}

	public String findByUid() {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");

		PageBean<Order> pageBean = orderService.findPageByUid(user.getUid(), pageNo);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUid";
	}

	public String findByOid() {
		model = orderService.findByOid(model.getOid());
		return "findByOidSuccess";
	}

	public String payOrder() throws Exception {
		// 修改订单
		Order currentOrder = orderService.findByOid(model.getOid());
		currentOrder.setAddr(model.getAddr());
		currentOrder.setName(model.getName());
		currentOrder.setPhone(model.getPhone());
		// 保存订单
		orderService.update(currentOrder);

		// 为订单付款
		String p0_Cmd = "Buy";// 必填，业务类型
		String p1_MerId = "10001126856";// 必填，商户编号
		String p2_Order = model.getOid().toString();// 非必填，商户订单号
		String p3_Amt = "0.01";// 非必填，支付金额
		String p4_Cur = "CNY";// 必填，交易币种
		String p5_Pid = "";// 非必填，商品名称
		String p6_Pcat = "";// 非必填，商品种类
		String p7_Pdesc = "";// 非必填，商品描述
		String p8_Url = "http://localhost:8080/shop/order_callback.action";// 非必填，商户接收支付成功数据的地址
		String p9_SAF = "";// 非必填，送货地址
		String pa_MP = "";// 非必填，商户扩展信息
		String pd_FrpId = this.pd_FrpId;// 非必填，支付通道编码
		String pr_NeedResponse = "1";// 非必填，应答机制

		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";// 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);// 非必填，签名数据

		// 向易宝发送请求
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);

		// 重定向到易宝
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}

	// 付款成功后跳转回来的路径:
	public String callback() {
		Order currentOrder = orderService.findByOid(Integer.parseInt(r6_Order));

		// 修改订单状态为2,即已经付款
		currentOrder.setState(2);
		orderService.update(currentOrder);
		//this.addActionMessage("支付成功!订单编号为: " + r6_Order + ",付款金额为: " + r3_Amt);
		
		ActionContext.getContext().getValueStack().set("message", "支付成功!订单编号为: " + r6_Order + ",付款金额为: " + r3_Amt);
		return "msg";
	}
	
	//确认收货
	public String updateState(){
		model = orderService.findByOid(model.getOid());
		model.setState(4);//确认收货
		orderService.update(model);
		return SUCCESS;
	}
}
