package project.spring.calla.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	

	@Autowired
	private MemberService memberService;
	
//	@Autowired
//	private SessionManager sessionManager;
	
	
	
	@GetMapping("/join")
	public void showJoinPage() {
	}

	@GetMapping("/login")
	public void loginGET(String targetURL) {
//		logger.info("loginGET() " + sessionManager.getLoginSessions().toString());
	}

	@GetMapping("/expired")
	public void expiredGET() {
	}

	@GetMapping("/searchMemberInfo")
	public void searchMemberInfoGET() {
	} // end searchIdGET()
	
	@PostMapping("/login")
	public String loginPOST(String memberId, String memberPw, String targetURL, RedirectAttributes reAttr, HttpServletRequest request) {
		
//		logger.info("loginPOST() " + loginSessions.toString());
		
		String result = memberService.login(memberId, memberPw);
		if (result != null) {
			MemberVO vo = memberService.read(memberId);
			String memberNickname = vo.getMemberNickname();
			int memberLevel = vo.getMemberLevel();
			float memberManner = vo.getMemberManner();
			
			reAttr.addFlashAttribute("login_result", "success");
//			String sessionId = request.getSession().getId();
//			HttpSession session = loginSessions.get(sessionId);
			HttpSession session = request.getSession();
			logger.info("session = " + session);
//			HttpSession existedSession = loginSessions.get(memberId);
//			if(existedSession != null) {
//				logger.info(existedSession.toString());
//				existedSession.invalidate();
//			}
			session.setAttribute("memberId", memberId);
			session.setAttribute("memberNickname", memberNickname);
			session.setAttribute("memberLevel", memberLevel);
			session.setAttribute("memberManner", memberManner);
			session.setMaxInactiveInterval(60*60);
			
//			loginSessions.put(memberId, session);
			if (targetURL != null) {
				return "redirect:" + targetURL;
			} else {
				return "redirect:/";
			}
		} else {
			if (targetURL != null) {
				try {
					targetURL = URLEncoder.encode(targetURL, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				return "redirect:/member/login?targetURL=" + targetURL;
			} else {
				return "redirect:/member/login";
			}
		}

	} // end loginPOST()
	@GetMapping("/logout")
	public String logoutGET(HttpServletRequest request) {
//		Map<String, HttpSession> loginSessions = sessionManager.getLoginSessions();
//		logger.info("logoutGET() " + loginSessions.toString());
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
//		loginSessions.remove(memberId);
		session.invalidate();
//		logger.info(""+loginSessions);
		return "redirect:/";
	} // end logoutGET()


	@GetMapping("/update")
	public void updateGET(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		if (memberId != null) {
			MemberVO vo = memberService.read(memberId);
			model.addAttribute("vo", vo);
		}
	} // end updateGET()
	
	@PostMapping("/searchId")
	public String searchIdPOST(String memberName, String memberEmail, RedirectAttributes reAttr) {

		String result = memberService.searchId(memberName, memberEmail);
		if (result != null) {
			reAttr.addFlashAttribute("searchResult", "idSearch");
			reAttr.addFlashAttribute("searchId", result);

			return "redirect:/member/login";
		} else {
			reAttr.addFlashAttribute("searchResult", "fail");
			return "redirect:/member/searchId";
		}

	} // end searchIdPOST()

	@PostMapping("/searchPw")
	public String searchPwPOST(Model model, String memberId, String memberPhone, RedirectAttributes reAttr) {
		String result = memberService.searchPw(memberId, memberPhone);
		logger.info("memberId: " + memberId + ", memberPhone: " + memberPhone);
		if (result != null) {
			reAttr.addFlashAttribute("searchResult", "pwSearch");
			reAttr.addFlashAttribute("searchPw", result);
			return "redirect:/member/login";
		} else {
			reAttr.addFlashAttribute("searchResult", "fail");
			return "redirect:/member/searchPw";
		}

	} // end searchPwPOST()

	@GetMapping("/delete")
	public String deleteGET(String memberId, HttpServletRequest request) {
		memberService.delete(memberId);
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/manageMember")
	public void manageMemberGET(Model model, HttpServletRequest request) {
		List<MemberVO> list = memberService.read();
		model.addAttribute("list", list);
	} // end manageMember
	
}
