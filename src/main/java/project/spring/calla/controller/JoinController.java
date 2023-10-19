package project.spring.calla.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="" ,method= RequestMethod.GET)
public class JoinController {
	private static final Logger logger =
			LoggerFactory.getLogger(JoinController.class);
	
	@GetMapping("/join")
	public String showJoinPage() {
		logger.info("join()»£√‚");
		return "join";
		
	}
	
	
	
	
	
	
	
	
	
}
