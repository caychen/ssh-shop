package org.com.cay.shop.categorysecond.service.impl;

import java.util.List;

import org.com.cay.shop.categorysecond.dao.ICategorySecondDao;
import org.com.cay.shop.categorysecond.entity.CategorySecond;
import org.com.cay.shop.categorysecond.service.ICategorySecondService;
import org.com.cay.shop.common.ConstantUtil;
import org.com.cay.shop.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CategorySecondServiceImpl implements ICategorySecondService {

	@Autowired
	private ICategorySecondDao categorySecondDao;

	@Override
	public PageBean<CategorySecond> findByPage(Integer page) {
		// TODO Auto-generated method stub
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();

		pageBean.setPageNo(page);

		int pageSize = ConstantUtil.PAGESIZE;
		pageBean.setPageSize(pageSize);

		int totalCount = categorySecondDao.getCount();
		pageBean.setTotalCount(totalCount);

		int totalPage = totalCount / pageSize;
		if (totalCount % pageSize != 0) {
			totalPage++;
		}
		pageBean.setTotalPage(totalPage);

		List<CategorySecond> data = categorySecondDao.findByPage((page - 1) * pageSize, pageSize);
		pageBean.setData(data);
		return pageBean;
	}

	@Override
	@Transactional(readOnly = false)
	public void save(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.save(categorySecond);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.delete(categorySecond);
	}

	@Override
	public CategorySecond findByCsid(Integer csid) {
		// TODO Auto-generated method stub
		return categorySecondDao.findByCsid(csid);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.update(categorySecond);
	}

	@Override
	public List<CategorySecond> findAll() {
		// TODO Auto-generated method stub
		return categorySecondDao.findAll();
	}
}
