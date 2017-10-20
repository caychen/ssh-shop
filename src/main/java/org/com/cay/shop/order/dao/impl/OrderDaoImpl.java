package org.com.cay.shop.order.dao.impl;

import java.util.List;

import org.com.cay.shop.order.dao.IOrderDao;
import org.com.cay.shop.order.entity.Order;
import org.com.cay.shop.order.entity.OrderItem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements IOrderDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Order order) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(order);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer findCountByUid(Integer uid) {
		// TODO Auto-generated method stub
		List<Long> result = sessionFactory.getCurrentSession()
				.createQuery("select count(1) from Order o where o.user.uid = ?").setParameter(0, uid).getResultList();

		if (result != null) {
			return result.get(0).intValue();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findPageByUid(Integer uid, int beginNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession()
				.createQuery("from Order o where o.user.uid = ? order by orderTime desc").setParameter(0, uid)
				.setFirstResult(beginNo).setMaxResults(pageSize).getResultList();
	}

	@Override
	public Order findByOid(Integer oid) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().get(Order.class, oid);
	}

	@Override
	public void update(Order order) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(order);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		List<Long> result = sessionFactory.getCurrentSession().createQuery("select count(1) from Order")
				.getResultList();

		if (result != null) {
			return result.get(0).intValue();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findByPage(int beginNo, int pageSize) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Order order by orderTime desc")
				.setFirstResult(beginNo).setMaxResults(pageSize).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderItem> findOrderItem(Integer oid) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from OrderItem oi where oi.order.oid = ?")
				.setParameter(0, oid).getResultList();
	}

}
