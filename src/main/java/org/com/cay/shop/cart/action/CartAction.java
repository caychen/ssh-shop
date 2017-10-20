package org.com.cay.shop.cart.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.com.cay.shop.cart.entity.Cart;
import org.com.cay.shop.cart.entity.CartItem;
import org.com.cay.shop.product.entity.Product;
import org.com.cay.shop.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class CartAction extends ActionSupport {

	@Autowired
	private IProductService productService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer pid;
	private Integer count;

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String addToCart() {
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		
		Cart cart = getCart();
		cart.addToCart(cartItem);
		
		return "cart";
	}

	//从session中获得购物车
	private Cart getCart() {
		// TODO Auto-generated method stub
		HttpSession session = ServletActionContext.getRequest().getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	public String clearCart(){
		getCart().clearCart();
		return "clearCart";
	}
	
	public String removeCart(){
		getCart().removeFromCart(pid);
		return "removeCart";
	}
	
	public String list(){
		return "list";
	}
}
