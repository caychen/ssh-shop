package org.com.cay.shop.product.dao;

import java.util.List;

import org.com.cay.shop.product.entity.Product;

public interface IProductDao {

	List<Product> findHotProducts();

	List<Product> findNewProducts();

	Product findByPid(Integer pid);

	int findCountByCid(Integer cid);

	List<Product> findPageByCid(Integer cid, int beginNo, int pageSize);

	int findCountByCsid(Integer csid);

	List<Product> findPageByCsid(Integer csid, int beginNo, int pageSize);

	int getCount();

	List<Product> findByPage(int beginNo, int pageSize);

	void save(Product product);

	void delete(Product product);

	void update(Product product);

}
