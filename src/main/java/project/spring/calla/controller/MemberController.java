package project.spring.calla.controller;

	
	
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.domain.MemberVO;
import project.spring.calla.persistence.MemberDAO;
import project.spring.calla.service.MemberService;

@Controller // @Component
//* 표현 계층(Presentation Layer)
//- view(페이지)와 service를 연결하는 역할
//- request에 대한 response를 전달하는 역할
@RequestMapping(value="/member") // url : /ex02/board
public class MemberController {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService; 
	
	@Autowired
	private MemberDAO memberDAO; 
	
  	
	@GetMapping("/join")
	public void showJoinPage() {
		
	}
	@GetMapping("/main")
	public String mainGET() {
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public void loginGET() {}
	
	@PostMapping("/login")
	public String loginPOST(String memberId, String memberPw, RedirectAttributes reAttr, HttpServletRequest request) {
		// RedirectAttributes
		// - 리다이렉트 시 데이터를 전달하기 위한 인터페이스
		logger.info("loginPOST() 호출");
		String result = memberDAO.login(memberId, memberPw);
		
		if(result != null) {
			MemberVO vo = memberService.read(memberId);
			String memberNickname = vo.getMemberNickname();
			reAttr.addFlashAttribute("login_result", "success");
			
			HttpSession session = request.getSession();
			session.setAttribute("memberId", memberId);
			session.setAttribute("memberNickname", memberNickname);
			session.setMaxInactiveInterval(600);
			
			return "redirect:/";
			
			// redirect는 request 정보가 없어짐...
		} else {
			return "/member/login";
		}
	} // end registerPOST()
	
	@GetMapping("/logout")
	public String logoutGET(HttpServletRequest request) {
		logger.info("logout() 호출");
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	} // end logoutGET()
	
	@GetMapping("/myPage")
	public void myPageGET(Model model, String memberId) {
		logger.info("myPageGET() 호출 memberId = " + memberId);
		MemberVO vo = memberService.read(memberId);
		model.addAttribute("vo", vo);
	} // end myPageGET()
	
	@GetMapping("/update")
	public void updateGET(Model model, HttpServletRequest request) {
		logger.info("updateGET() 호출");
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		if(memberId != null) {
		MemberVO vo = memberService.read(memberId);
		model.addAttribute("vo", vo);
		}
	} // end updateGET()
	
	@PostMapping("/update")
	public String updatePOST(MemberVO vo) {
		logger.info("updatePOST() 호출 : vo = " + vo.toString());
		int result = memberService.update(vo);
		String memberId = vo.getMemberId();
		if(result == 1) {
			return "redirect:/member/myPage?memberId=" + memberId;
		} else {
			return "redirect:/member/update";
		}
	} // end updatePOST()
	@GetMapping("/fBoard")
	public String fBoardGET() {
		return "redirect:/fBoard/list";
	} // end fBoardGET()
	
	@GetMapping("/likes")
	public void likesGET(Model model, HttpServletRequest request) {
		logger.info("likesGET() 호출");
		
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		if(memberId != null) {
		MemberVO vo = memberService.read(memberId);
		model.addAttribute("vo", vo);
		}
	} // end likesGET()
}
