package org.com.cay.shop.order.dao;

import java.util.List;

import org.com.cay.shop.order.entity.Order;
import org.com.cay.shop.order.entity.OrderItem;

public interface IOrderDao {

	void save(Order order);

	Integer findCountByUid(Integer uid);

	List<Order> findPageByUid(Integer uid, int beginNo, Integer pageSize);

	Order findByOid(Integer oid);

	void update(Order order);

	int getCount();

	List<Order> findByPage(int beginNo, int pageSize);

	List<OrderItem> findOrderItem(Integer oid);

}
