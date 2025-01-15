package jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

@Repository
public class BlogRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public int insert(String id) {
		return sqlSession.insert("blog.insert", id);
	}

	public BlogVo getBlogById(String id) {
		return sqlSession.selectOne("blog.getBlogById", id);
	}

	public void updateBlog(BlogVo blogVo) {
		sqlSession.update("blog.updateBlog", blogVo);
	}

	public int insertCategory(CategoryVo categoryVo) {
		return sqlSession.insert("blog.insertCategory", categoryVo);
	}

	public List<CategoryVo> getAllCategoriesById(String id) {
		return sqlSession.selectList("blog.getAllCategoriesById", id);
	}

	public int deleteCategory(Long id) {
		return sqlSession.delete("blog.deleteCategory", id);
	}

	public int insertPost(PostVo postVo) {
		return sqlSession.insert("blog.insertPost", postVo);
	}

	public List<PostVo> getAllPostById(String id) {
		return sqlSession.selectList("blog.getAllPostById", id);
	}

	public List<PostVo> getPostsByCategoryId(String id, Long categoryId) {
		return sqlSession.selectList("blog.getPostsByCategoryId", Map.of("id", id, "categoryId", categoryId));
	}
}
