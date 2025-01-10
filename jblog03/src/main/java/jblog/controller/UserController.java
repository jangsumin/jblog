package jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user") // URL의 공통 패턴에 해당한다.
public class UserController {
	
	@GetMapping("/join")
	public String join() {
		return "user/join"; // .jsp 확장 표현을 적지 않아도 되는 이유는 ViewResolver가 있기 때문이다.
	}
	
//	@PostMapping("/join")
//	public String join() {
//		
//	}
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
}
