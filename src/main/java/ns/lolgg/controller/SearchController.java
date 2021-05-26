package ns.lolgg.controller;

import java.io.IOException;
import java.net.URLEncoder;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import ns.lolgg.domain.User;
import ns.lolgg.service.UserService;

@Controller
public class SearchController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/search")
	public String search(Model model, @RequestParam("id") String id) throws ParseException, IOException {
		
		User user = userService.findUserByLolId(id).orElse(null);
		if (user==null) {
			user = userService.searchUser(id);
		}
		
		if (user != null) {
			model.addAttribute("user", user);
			model.addAttribute("matches", user.getMatches());
		}
		return "smDetail";
	}
	
	
	
	@PostMapping("/serach/refresh")
	public RedirectView search(String id) throws ParseException, IOException {
		User user = userService.refreshUser(userService.findUserByLolId(id).orElseThrow());
		return new RedirectView("/search?id=" + URLEncoder.encode(user.getUserLolId(), "UTF-8"));
	}
	
}
