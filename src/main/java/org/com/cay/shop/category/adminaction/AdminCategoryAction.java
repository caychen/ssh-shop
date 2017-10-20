package org.com.cay.shop.category.adminaction;

import java.util.List;

import org.com.cay.shop.category.entity.Category;
import org.com.cay.shop.category.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Category model = new Category();
	
	@Autowired
	private ICategoryService categoryService;
	
	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	/**
	 * 查询所有的一级分类
	 * @return
	 */
	public String findAll(){
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}

	public String save(){
		categoryService.save(model);
		return "success";
	}
	
	public String delete(){
		model = categoryService.findByCid(model.getCid());
		categoryService.delete(model);
		return SUCCESS;
	}
	
	public String edit(){
		model = categoryService.findByCid(model.getCid());
		return "edit";
	}
	
	public String update(){
		categoryService.update(model);
		return SUCCESS;
	}
}
