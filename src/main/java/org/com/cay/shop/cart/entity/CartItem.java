package org.com.cay.shop.cart.entity;

import java.io.Serializable;

import org.com.cay.shop.product.entity.Product;

public class CartItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Product product;
	private int count;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getSubMoney() {
		return this.count * product.getShop_price();
	}
}
