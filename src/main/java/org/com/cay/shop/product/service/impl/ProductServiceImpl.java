package org.com.cay.shop.product.service.impl;

import java.util.List;

import org.com.cay.shop.common.ConstantUtil;
import org.com.cay.shop.product.dao.IProductDao;
import org.com.cay.shop.product.entity.Product;
import org.com.cay.shop.product.service.IProductService;
import org.com.cay.shop.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ProductServiceImpl implements IProductService {

	private int pageSize = ConstantUtil.PAGESIZE;

	@Autowired
	private IProductDao productDao;

	@Override
	public List<Product> findHotProducts() {
		// TODO Auto-generated method stub
		return productDao.findHotProducts();
	}

	@Override
	public List<Product> findNewProducts() {
		// TODO Auto-generated method stub
		return productDao.findNewProducts();
	}

	@Override
	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return productDao.findByPid(pid);
	}

	@Override
	public PageBean<Product> findPageByCid(Integer cid, int pageNo) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean = new PageBean<>();

		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);

		int totalCount = productDao.findCountByCid(cid);
		pageBean.setTotalCount(totalCount);

		int totalPage = totalCount / pageSize;
		totalPage = totalCount % pageSize == 0 ? totalPage : totalPage + 1;
		pageBean.setTotalPage(totalPage);

		int beginNo = (pageNo - 1) * pageSize;
		pageBean.setData(productDao.findPageByCid(cid, beginNo, pageSize));
		return pageBean;
	}

	@Override
	public PageBean<Product> findPageByCsid(Integer csid, int pageNo) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean = new PageBean<>();

		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);

		int totalCount = productDao.findCountByCsid(csid);
		pageBean.setTotalCount(totalCount);

		int totalPage = totalCount / pageSize;
		totalPage = totalCount % pageSize == 0 ? totalPage : totalPage + 1;
		pageBean.setTotalPage(totalPage);

		int beginNo = (pageNo - 1) * pageSize;
		pageBean.setData(productDao.findPageByCsid(csid, beginNo, pageSize));
		return pageBean;
	}

	@Override
	public PageBean<Product> findByPage(Integer page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean = new PageBean<Product>();

		pageBean.setPageNo(page);

		pageBean.setPageSize(pageSize);

		int totalCount = productDao.getCount();
		pageBean.setTotalCount(totalCount);

		int totalPage = totalCount / pageSize;
		if (totalCount % pageSize != 0) {
			totalPage++;
		}

		pageBean.setTotalPage(totalPage);

		List<Product> products = productDao.findByPage((page - 1) * pageSize, pageSize);
		pageBean.setData(products);
		return pageBean;
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Product product) {
		// TODO Auto-generated method stub
		productDao.save(product);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Product product) {
		// TODO Auto-generated method stub
		productDao.delete(product);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Product product) {
		// TODO Auto-generated method stub
		productDao.update(product);
	}
}
