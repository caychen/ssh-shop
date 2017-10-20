package org.com.cay.shop.order.service.impl;

import java.util.List;

import org.com.cay.shop.common.ConstantUtil;
import org.com.cay.shop.order.dao.IOrderDao;
import org.com.cay.shop.order.entity.Order;
import org.com.cay.shop.order.entity.OrderItem;
import org.com.cay.shop.order.service.IOrderService;
import org.com.cay.shop.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderDao orderDao;

	@Transactional(readOnly = false)
	@Override
	public void save(Order order) {
		// TODO Auto-generated method stub
		orderDao.save(order);
	}

	@Override
	public PageBean<Order> findPageByUid(Integer uid, Integer pageNo) {
		// TODO Auto-generated method stub
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPageNo(pageNo);

		Integer pageSize = ConstantUtil.PAGESIZE;
		pageBean.setPageSize(pageSize);

		// 设置总记录数
		Integer totalCount = orderDao.findCountByUid(uid);
		pageBean.setTotalCount(totalCount);

		// 设置总页数
		Integer totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize + 1);
		pageBean.setTotalPage(totalPage);

		// 设置每页数据
		int beginNo = (pageNo - 1) * pageSize;
		List<Order> data = orderDao.findPageByUid(uid, beginNo, pageSize);
		pageBean.setData(data);
		return pageBean;
	}

	@Override
	public Order findByOid(Integer oid) {
		// TODO Auto-generated method stub
		return orderDao.findByOid(oid);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Order order) {
		// TODO Auto-generated method stub
		orderDao.update(order);
	}

	@Override
	public PageBean<Order> findByPage(Integer page) {
		// TODO Auto-generated method stub
		PageBean<Order> pageBean = new PageBean<Order>();

		pageBean.setPageNo(page);

		int pageSize = ConstantUtil.PAGESIZE;
		pageBean.setPageSize(pageSize);

		int totalCount = orderDao.getCount();
		pageBean.setTotalCount(totalCount);

		int totalPage = totalCount / pageSize;
		if (totalCount % pageSize != 0) {
			totalPage++;
		}
		pageBean.setTotalPage(totalPage);

		List<Order> orders = orderDao.findByPage((page - 1) * pageSize, pageSize);
		pageBean.setData(orders);

		return pageBean;
	}

	@Override
	public List<OrderItem> findOrderItem(Integer oid) {
		// TODO Auto-generated method stub
		return orderDao.findOrderItem(oid);
	}

}
