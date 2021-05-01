package ns.lolgg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ns.lolgg.domain.User;
import ns.lolgg.exception.UserNotExistedException;
import ns.lolgg.service.UserService;
import ns.lolgg.util.LolUtil;

@Controller
public class SearchController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/search")
	public String search(Model model, @RequestParam(value = "id", defaultValue = "Hide on bush") String id) {
		
		User user = userService.findUserByLolId(id).orElseThrow(()-> new UserNotExistedException(id));
		model.addAttribute("user", user);
		model.addAttribute("mathces", user.getMatches());
		
		return "smDetail";
	}
	
	@PostMapping("/serach/refresh")
	public String search(@RequestParam(value = "id", defaultValue = "Hide on bush") String id) {
		
		return "redirect:/search?id="+id;
	}
	
}
