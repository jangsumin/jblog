package jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.UserVo;

@Repository
public class UserRepository {
	
	private SqlSession sqlSession;
	
//	public int insert(UserVo userVo) {
//		return sqlSession.insert("user.insert", userVo);
//	}
}
