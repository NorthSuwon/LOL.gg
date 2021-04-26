package ns.lolgg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ns.lolgg.dto.UserDTO.UserRegi;
import ns.lolgg.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String loginpage() {
		return "login";
	}

	@PostMapping("/signin")
	public String signin(UserRegi user) {
		userService.registerUser(user.toEntity());
		return "redirect:login";
	}
	
	@GetMapping("/signin")
	public String signinpage() {
		return "signin";
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
}
