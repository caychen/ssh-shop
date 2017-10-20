package org.com.cay.shop.categorysecond.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.com.cay.shop.category.entity.Category;
import org.com.cay.shop.product.entity.Product;

public class CategorySecond implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer csid;
	private String csname;

	private Category category;

	private Set<Product> products = new HashSet<>();

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public String getCsname() {
		return csname;
	}

	public void setCsname(String csname) {
		this.csname = csname;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
