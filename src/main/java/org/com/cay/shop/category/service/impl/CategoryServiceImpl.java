package org.com.cay.shop.category.service.impl;

import java.util.List;

import org.com.cay.shop.category.dao.ICategoryDao;
import org.com.cay.shop.category.entity.Category;
import org.com.cay.shop.category.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryDao categoryDao;

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryDao.save(category);
	}

	@Override
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return categoryDao.findByCid(cid);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Category category) {
		// TODO Auto-generated method stub
		categoryDao.delete(category);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Category category) {
		// TODO Auto-generated method stub
		categoryDao.update(category);
	}
}
