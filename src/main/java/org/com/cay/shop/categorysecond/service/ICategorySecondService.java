package org.com.cay.shop.categorysecond.service;

import java.util.List;

import org.com.cay.shop.categorysecond.entity.CategorySecond;
import org.com.cay.shop.utils.PageBean;

public interface ICategorySecondService {

	PageBean<CategorySecond> findByPage(Integer page);

	void save(CategorySecond categorySecond);

	void delete(CategorySecond categorySecond);

	CategorySecond findByCsid(Integer csid);

	void update(CategorySecond categorySecond);

	List<CategorySecond> findAll();

}
