package org.com.cay.shop.product.service;

import java.util.List;

import org.com.cay.shop.product.entity.Product;
import org.com.cay.shop.utils.PageBean;

public interface IProductService {

	List<Product> findHotProducts();

	List<Product> findNewProducts();

	Product findByPid(Integer pid);

	PageBean<Product> findPageByCid(Integer cid, int pageNo);

	PageBean<Product> findPageByCsid(Integer csid, int pageNo);

	PageBean<Product> findByPage(Integer page);

	void save(Product product);

	void delete(Product product);

	void update(Product product);

}
