package org.com.cay.shop.categorysecond.dao.impl;

import java.util.List;

import org.com.cay.shop.categorysecond.dao.ICategorySecondDao;
import org.com.cay.shop.categorysecond.entity.CategorySecond;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategorySecondDaoImpl implements ICategorySecondDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		List<Long> result = sessionFactory.getCurrentSession().createQuery("select count(1) from CategorySecond")
				.getResultList();
		if (result != null) {
			return result.get(0).intValue();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategorySecond> findByPage(int beginNo, int pageSize) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from CategorySecond order by csid desc")
				.setFirstResult(beginNo).setMaxResults(pageSize).getResultList();
	}

	@Override
	public void save(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(categorySecond);
	}

	@Override
	public void delete(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(categorySecond);
	}

	@Override
	public CategorySecond findByCsid(Integer csid) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(CategorySecond.class, csid);
	}

	@Override
	public void update(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(categorySecond);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategorySecond> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from CategorySecond").getResultList();
	}

}
