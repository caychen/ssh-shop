package org.com.cay.shop.order.entity;

import java.io.Serializable;

import org.com.cay.shop.product.entity.Product;

//订单项
public class OrderItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer itemId;
	private Integer count;
	private Double subMoney;

	private Product product;
	private Order order;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getSubMoney() {
		return subMoney;
	}

	public void setSubMoney(Double subMoney) {
		this.subMoney = subMoney;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
