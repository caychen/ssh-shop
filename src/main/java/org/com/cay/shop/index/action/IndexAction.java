package org.com.cay.shop.index.action;

import java.util.List;

import org.com.cay.shop.category.entity.Category;
import org.com.cay.shop.category.service.ICategoryService;
import org.com.cay.shop.product.entity.Product;
import org.com.cay.shop.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class IndexAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IProductService productService;
	
	public String index() {
		
		List<Category> cList = categoryService.findAll();
		
		//将一级分类存入到session中
		ActionContext.getContext().getSession().put("cList", cList);
		
		//查询热门商品,显示10个
		List<Product> hotProducts = productService.findHotProducts();
		ActionContext.getContext().getValueStack().set("hotProducts", hotProducts);
		
		List<Product> newProducts = productService.findNewProducts();
		ActionContext.getContext().getValueStack().set("newProducts", newProducts);
		
		return SUCCESS;
	}
}
