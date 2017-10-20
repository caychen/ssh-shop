package org.com.cay.shop.categorysecond.adminaction;

import java.util.List;

import org.com.cay.shop.category.entity.Category;
import org.com.cay.shop.category.service.ICategoryService;
import org.com.cay.shop.categorysecond.entity.CategorySecond;
import org.com.cay.shop.categorysecond.service.ICategorySecondService;
import org.com.cay.shop.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CategorySecond model = new CategorySecond();
	
	@Autowired
	private ICategorySecondService categorySecondService;
	
	@Autowired
	private ICategoryService categoryService;
	
	private Integer page = 1;
	
	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	public String findAll(){
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	public String addPage(){
		//查询所有的一级分类
		List<Category> cList = categoryService.findAll();
		
		ActionContext.getContext().getValueStack().set("cList", cList);
		
		return "addPage";
	}
	
	//保存二级分类
	public String save(){
		categorySecondService.save(model);
		return SUCCESS;
	}
	
	public String delete(){
		//如果级联删除，先查询后删除，并配置cascade属性
		model = categorySecondService.findByCsid(model.getCsid());
		categorySecondService.delete(model);
		return SUCCESS;
	}
	
	public String edit(){
		model = categorySecondService.findByCsid(model.getCsid());
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "edit";
	}
	
	public String update(){
		categorySecondService.update(model);
		return SUCCESS;
	}
}
