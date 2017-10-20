package org.com.cay.shop.categorysecond.dao;

import java.util.List;

import org.com.cay.shop.categorysecond.entity.CategorySecond;

public interface ICategorySecondDao {

	int getCount();

	List<CategorySecond> findByPage(int beginNo, int pageSize);

	void save(CategorySecond categorySecond);

	void delete(CategorySecond categorySecond);

	CategorySecond findByCsid(Integer csid);

	void update(CategorySecond categorySecond);

	List<CategorySecond> findAll();


}
