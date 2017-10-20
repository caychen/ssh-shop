package org.com.cay.shop.order.service;

import java.util.List;

import org.com.cay.shop.order.entity.Order;
import org.com.cay.shop.order.entity.OrderItem;
import org.com.cay.shop.utils.PageBean;

public interface IOrderService {

	void save(Order order);

	PageBean<Order> findPageByUid(Integer uid, Integer pageNo);

	Order findByOid(Integer oid);

	void update(Order order);

	PageBean<Order> findByPage(Integer page);

	List<OrderItem> findOrderItem(Integer oid);

}
