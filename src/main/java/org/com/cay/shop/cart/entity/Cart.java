package org.com.cay.shop.cart.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<Integer, CartItem> cartItemMap = new LinkedHashMap<>();
	private double totalMoney;
	
	public Collection<CartItem> getCartItems(){
		return cartItemMap.values();
	}
	
	public double getTotalMoney() {
		return totalMoney;
	}

	//购物车功能
	//将购物项添加到购物车
	public void addToCart(CartItem cartItem){
		//判断购物车是否已经存在该购物项
		Integer pid = cartItem.getProduct().getPid();
		if(cartItemMap.containsKey(pid)){
			CartItem _cartItem = cartItemMap.get(pid);
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
		}else{
			cartItemMap.put(pid, cartItem);
		}
		
		totalMoney += cartItem.getSubMoney();
	}
	
	//将购物项从购物车移除
	public void removeFromCart(Integer pid){
		CartItem cartItem = cartItemMap.remove(pid);
		totalMoney -= cartItem.getSubMoney();
	}
	
	//清空购物车
	public void clearCart(){
		//将所有的购物项清空，并将总计设为0
		cartItemMap.clear();
		totalMoney = 0.0;
		
	}
}
