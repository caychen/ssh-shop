package org.com.cay.shop.product.adminproduct;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.com.cay.shop.categorysecond.entity.CategorySecond;
import org.com.cay.shop.categorysecond.service.ICategorySecondService;
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
public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Product model = new Product();

	@Autowired
	private IProductService productService;

	@Autowired
	private ICategorySecondService categorySecondService;

	private Integer page = 1;

	public void setPage(Integer page) {
		this.page = page;
	}

	// struts2文件上传需要的参数
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);

		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	public String addPage() {
		// 查询所有的二级分类
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPage";
	}

	public String save() throws Exception {
		model.setPdate(new Date());

		if (upload != null) {
			String realPath = ServletActionContext.getServletContext().getRealPath("/products/1");

			File diskFile = new File(realPath + "//" + uploadFileName);
			FileUtils.copyFile(upload, diskFile);

			model.setImage("products/1/" + uploadFileName);
		}

		productService.save(model);
		return SUCCESS;
	}
	
	public String delete(){
		model = productService.findByPid(model.getPid());
		
		//删除上传的图片
		String imagePath = model.getImage();		
		if(imagePath != null){
			String realPath = ServletActionContext.getServletContext().getRealPath(imagePath);
			
			File file = new File(realPath);
			file.delete();
		}
		
		productService.delete(model);
		
		return SUCCESS;
	}

	public String edit(){
		//商品对象
		model = productService.findByPid(model.getPid());
		
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "edit";
	}
	
	public String update() throws Exception{
		model.setPdate(new Date());
		
		if(upload != null){
			//先删除原来的图片
			String oldImage = model.getImage();
			if(oldImage != null){
				String oldRealPath = ServletActionContext.getServletContext().getRealPath(oldImage);
				new File(oldRealPath).delete();
			}
			
			//使用新图片替换
			String realPath = ServletActionContext.getServletContext().getRealPath("/products/1");

			File diskFile = new File(realPath + "//" + uploadFileName);
			FileUtils.copyFile(upload, diskFile);

			model.setImage("products/1/" + uploadFileName);
		}
		
		productService.update(model);
		return SUCCESS;
	}
	
}
