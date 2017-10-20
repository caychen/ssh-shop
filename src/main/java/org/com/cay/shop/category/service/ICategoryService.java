package org.com.cay.shop.category.service;

import java.util.List;

import org.com.cay.shop.category.entity.Category;

public interface ICategoryService {

	List<Category> findAll();

	void save(Category category);

	Category findByCid(Integer cid);

	void delete(Category category);

	void update(Category category);

}
