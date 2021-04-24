package ns.lolgg.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ns.lolgg.domain.User;
import ns.lolgg.service.UserService;

@SessionAttributes("user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public String login(@RequestBody Map<String, String> map, Model model) {

		User user = userService.findUserById(map.get("userId"));
		if (user == null || !userService.checkUserPassword(user, map.get("password"))) {
			return "redirect:login.html";
		}

		model.addAttribute("user", user);
		return "forward:index.html";
	}

	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:index.html";
	}

	@PostMapping("/signin")
	public String signin(@RequestBody User user) {
		userService.registerUser(user);
		return "redirect:index.html";
	}

}
