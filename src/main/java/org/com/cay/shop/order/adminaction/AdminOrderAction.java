package org.com.cay.shop.order.adminaction;

import java.util.List;

import org.com.cay.shop.order.entity.Order;
import org.com.cay.shop.order.entity.OrderItem;
import org.com.cay.shop.order.service.IOrderService;
import org.com.cay.shop.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Order model = new Order();

	private Integer page = 1;

	public void setPage(Integer page) {
		this.page = page;
	}

	@Autowired
	private IOrderService orderService;

	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public String findAll() {
		PageBean<Order> pageBean = orderService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);

		return "findAll";
	}

	public String findOrderItem() {
		List<OrderItem> list = orderService.findOrderItem(model.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}

	public String updateState() {
		model = orderService.findByOid(model.getOid());
		model.setState(3);//发货
		orderService.update(model);
		return SUCCESS;
	}

}
