package org.com.cay.shop.product.dao.impl;

import java.util.List;

import org.com.cay.shop.product.dao.IProductDao;
import org.com.cay.shop.product.entity.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements IProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findHotProducts() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Product where is_hot = ? order by pdate desc")
				.setParameter(0, 1).setFirstResult(0).setMaxResults(10).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findNewProducts() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Product order by pdate desc").setFirstResult(0)
				.setMaxResults(10).getResultList();
	}

	@Override
	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().find(Product.class, pid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findCountByCid(Integer cid) {
		// TODO Auto-generated method stub
		List<Long> result = sessionFactory.getCurrentSession()
				.createQuery("select count(1) from Product p where p.categorySecond.category.cid = ?")
				.setParameter(0, cid).getResultList();
		if (result != null) {
			return result.get(0).intValue();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findPageByCid(Integer cid, int beginNo, int pageSize) {
		// TODO Auto-generated method stub

		// 在hql中使用join关键字，就会把两表通过外键自动关联起来
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		return sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, cid).setFirstResult(beginNo)
				.setMaxResults(pageSize).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findCountByCsid(Integer csid) {
		// TODO Auto-generated method stub
		List<Long> result = sessionFactory.getCurrentSession()
				.createQuery("select count(*) from Product p where p.categorySecond.csid = ?").setParameter(0, csid)
				.getResultList();
		if (result != null) {
			return result.get(0).intValue();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findPageByCsid(Integer csid, int beginNo, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		return sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, csid).setFirstResult(beginNo)
				.setMaxResults(pageSize).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		List<Long> result = sessionFactory.getCurrentSession().createQuery("select count(1) from Product").getResultList();
		if(result != null && result.size() > 0){
			return result.get(0).intValue();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findByPage(int beginNo, int pageSize) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Product order by pdate desc")
				.setFirstResult(beginNo).setMaxResults(pageSize).getResultList();
	}

	@Override
	public void save(Product product) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(product);
	}

	@Override
	public void delete(Product product) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(product);
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(product);
	}
}
