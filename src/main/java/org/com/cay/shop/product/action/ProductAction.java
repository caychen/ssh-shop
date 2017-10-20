package org.com.cay.shop.product.action;

import org.com.cay.shop.product.entity.Product;
import org.com.cay.shop.product.service.IProductService;
import org.com.cay.shop.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class ProductAction extends ActionSupport implements ModelDriven<Product> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Product model = new Product();

	private int pageNo = 1;

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	private Integer cid;
	private Integer csid;

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCid() {
		return cid;
	}

	@Autowired
	private IProductService productService;

	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	// 根据pid查询商品信息
	public String findByPid() {
		model = productService.findByPid(model.getPid());
		return "findByPid";
	}

	public String findByCid() {
		PageBean<Product> pageBean = productService.findPageByCid(cid, pageNo);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}

	public String findByCsid() {
		PageBean<Product> pageBean = productService.findPageByCsid(csid, pageNo);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}

}
