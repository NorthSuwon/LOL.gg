package ns.lolgg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ns.lolgg.dto.UserDTO.UserRegi;
import ns.lolgg.exception.UserExistedException;
import ns.lolgg.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String loginpage() {
		return "login";
	}
	
	@GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginError", true);
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
	
	@GetMapping("/signin/idcheck/{id}")
	public String idCheck(@PathVariable("id") String id){
		if (userService.findUserById(id)!=null) {
			throw new UserExistedException("중복 아이디");
		}
		return "IdCheck";
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
}
