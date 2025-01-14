package jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;

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
}
