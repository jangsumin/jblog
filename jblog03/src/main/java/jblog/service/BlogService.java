package jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jblog.repository.BlogRepository;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	
	public BlogVo getBlogById(String id) {
		return blogRepository.getBlogById(id);
	}

	public void updateBlog(BlogVo blogVo) {
		blogRepository.updateBlog(blogVo);
	}

	public void insertCategory(CategoryVo categoryVo) {
		blogRepository.insertCategory(categoryVo);
	}

	public List<CategoryVo> getAllCategoriesById(String id) {
		return blogRepository.getAllCategoriesById(id);
	}
}
