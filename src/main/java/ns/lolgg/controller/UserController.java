package ns.lolgg.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ns.lolgg.domain.User;
import ns.lolgg.dto.UserDTO.UserLogin;
import ns.lolgg.dto.UserDTO.UserRegi;
import ns.lolgg.service.UserService;

@SessionAttributes("user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/logins")
	public String login(UserLogin userLogin, HttpSession session) {
		User user = userService.findUserById(userLogin.getId());
		if (user == null || !userService.checkUserPassword(user, userLogin.getPassword())) {
			return "redirect:login";
		}

		session.setAttribute("user", user);
		return "index";
	}
	
	@GetMapping("/login")
	public String loginpage() {
		return "login";
	}
	

	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:/";
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
