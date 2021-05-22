package ns.lolgg.controller;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ns.lolgg.domain.User;
import ns.lolgg.dto.UserDTO.UserRegi;
import ns.lolgg.exception.UserExistedException;
import ns.lolgg.service.UserService;
import ns.lolgg.util.LolUtil;

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
	public String signin(UserRegi user) throws ParseException, IOException {
		userService.registerUser(user);
		return "redirect:login";
	}
	
	@GetMapping("/signin")
	public String signinpage() {
		return "signin";
	}
	
	@ResponseBody
	@GetMapping("/signin/idcheck/{id}")
	public String idCheck(@PathVariable("id") String id){
		if (userService.findUserById(id)!=null) {
			throw new UserExistedException("중복 아이디");
		}
		return "true";
	}
	
	@ResponseBody
	@GetMapping("/signin/lolid/{id}")
	public String lolIdCheck(@PathVariable("id") String id) throws ParseException, IOException, Exception{
		
		if (LolUtil.getSummoners(id)==null) {
			throw new Exception();
		}
		User user = userService.findUserByLolId(id).orElse(null);
		if (user==null) {
			return "true";
		} else if (user.getUserId().equals("!!!!!")) {
			return "true";
		}
		throw new UserExistedException("중복 아이디");
	}
	
	@ResponseBody
	@GetMapping("/auth")
	public Authentication auth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
}
