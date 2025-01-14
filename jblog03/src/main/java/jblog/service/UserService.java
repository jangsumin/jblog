package jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jblog.repository.BlogRepository;
import jblog.repository.UserRepository;
import jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;

	public void join(UserVo userVo) {
		// 회원가입과 동시에 블로그도 개설되어야 한다.
		userRepository.insert(userVo);
		blogRepository.insert(userVo.getId());
	}
	
	public UserVo getUser(String id, String password) {
		return userRepository.findByIdAndPassword(id, password);
	}
	
}
