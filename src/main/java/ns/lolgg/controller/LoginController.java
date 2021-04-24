package ns.lolgg.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import ns.lolgg.service.UserService;

@SessionAttributes("user")
@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	
}
