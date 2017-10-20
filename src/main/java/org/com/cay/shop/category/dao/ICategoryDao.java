package org.com.cay.shop.category.dao;

import java.util.List;

import org.com.cay.shop.category.entity.Category;

public interface ICategoryDao {

	List<Category> findAll();

	void save(Category category);

	Category findByCid(Integer cid);

	void delete(Category category);

	void update(Category category);

}
