package jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jblog.service.UserService;
import jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/join")
	public String join() {
		return "user/join"; // .jsp 확장 표현을 적지 않아도 되는 이유는 ViewResolver가 있기 때문이다.
	}

	@PostMapping("/join")
	public String join(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("password") String password) {
		UserVo userVo = new UserVo();
		userVo.setId(id);
		userVo.setName(name);
		userVo.setPassword(password);

		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}

	@GetMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
}
