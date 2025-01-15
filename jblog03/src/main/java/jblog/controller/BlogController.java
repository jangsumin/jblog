package jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jblog.service.BlogService;
import jblog.service.FileUploadService;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
//	@GetMapping({"/{id}", "/{id}/{path1}", "/{id}/{path1}/{path2}"})
//	public String blogMain(@PathVariable("id") String id, @PathVariable("path1") Optional<Long> path1, @PathVariable("path2") Optional<Long> path2, Model model) {
//		Long categoryId = 0L;
//		Long postId = 0L;
//		
//		if (path2.isPresent()) {
//			categoryId = path1.get();
//			postId = path2.get();
//			
//		} else if (path1.isPresent()) {
//			categoryId = path1.get();
//	
//			List<PostVo> postList = blogService.getPostsByCategoryId(id, categoryId);
//			model.addAttribute("postList", postList);
//		} else {			
//			List<PostVo> postList = blogService.getAllPostById(id);
//			model.addAttribute("postList", postList);			
//		}
//		List<CategoryVo> categoryList = blogService.getAllCategoriesById(id);
//		model.addAttribute("categoryList", categoryList);
//		
//		return "blog/blog-main";
//	}
	
	@GetMapping("/{id}")
	public String blogMain(@PathVariable("id") String id, Model model) {
		List<PostVo> postList = blogService.getAllPostById(id);
		model.addAttribute("postList", postList);			
	
		List<CategoryVo> categoryList = blogService.getAllCategoriesById(id);
		model.addAttribute("categoryList", categoryList);
		
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
	public String adminWrite(@PathVariable("id") String id, Model model) {
		List<CategoryVo> categoryList = blogService.getAllCategoriesById(id);
		
		model.addAttribute("categoryList", categoryList);
		return "blog/blog-admin-write";
	}
	
	@PostMapping("/{id}/admin/write")
	public String adminWrite(@PathVariable("id") String id, @RequestParam("title") String title, @RequestParam("category") Long categoryId, @RequestParam("content") String contents) {
		PostVo postVo = new PostVo();
		postVo.setTitle(title);
		postVo.setContents(contents);
		postVo.setCategoryId(categoryId);
		
		blogService.insertPost(postVo);
		
		return "redirect:/" + id;
	}
}
