package project.spring.calla.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.persistence.MemberDAO;
import project.spring.calla.service.MemberService;
import project.spring.calla.service.MemberUpdateService;
import project.spring.calla.service.UProductCommentService;

@Controller // @Component
@RequestMapping(value = "/member/update") // url : /ex02/board
public class MemberUpdateController {

	private static final Logger logger = LoggerFactory.getLogger(MemberUpdateController.class);
	
	private static Map<String, HttpSession> loginSessions = new HashMap<>();
	
	@Resource(name = "uploadpath")
	private String uploadpath;

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberUpdateService memberUpdateService;
	
	@GetMapping
	public void updateGET(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		if (memberId != null) {
			MemberVO vo = memberService.read(memberId);
			model.addAttribute("vo", vo);
		}
	} // end updateGET()

	@GetMapping("/delete")
	public String deleteGET(String memberId, HttpServletRequest request) {
		memberUpdateService.delete(memberId);
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}

	
}
