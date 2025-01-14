package jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jblog.service.BlogService;
import jblog.service.FileUploadService;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@GetMapping("/{id}")
	public String blogMain() {
		return "blog/blog-main";
	}
	
	@GetMapping("/{id}/admin/basic")
	public String adminBasic() {
		return "blog/blog-admin-basic";
	}
	
	@PostMapping("/{id}/admin/basic")
	public String adminBasic(@PathVariable("id") String id, @RequestParam("title") String title, @RequestParam("profile") String profile, @RequestParam("logo-file") MultipartFile multipartFile) {
		String newProfile = fileUploadService.restore(multipartFile);
	
		BlogVo blogVo = new BlogVo(id, title, profile);
		
		if (newProfile != null) {
			blogVo.setProfile(newProfile);
		}
		
		blogService.updateBlog(blogVo);
		return "redirect:/" + id + "/admin/basic";
	}
	
	@GetMapping("/{id}/admin/category")
	public String adminCategory(@PathVariable("id") String id, Model model) {
		List<CategoryVo> categoryList = blogService.getAllCategoriesById(id);
		
		model.addAttribute("categoryList", categoryList);
		return "blog/blog-admin-category";
	}
	
	@PostMapping("/{id}/admin/category")
	public String adminCategory(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("desc") String description) {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setName(name);
		categoryVo.setDescription(description);
		categoryVo.setBlogId(id);
		
		blogService.insertCategory(categoryVo);
		
		return "redirect:/" + id + "/admin/category";
	}
	
	@GetMapping("/{id}/admin/write")
	public String adminWrite() {
		return "blog/blog-admin-write";
	}
}
