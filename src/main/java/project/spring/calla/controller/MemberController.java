package project.spring.calla.controller;

	
	
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

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

import project.spring.calla.domain.FBoardCommentVO;
import project.spring.calla.domain.MemberVO;
import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.domain.QBoardCommentVO;
import project.spring.calla.domain.UproductCommentVO;
import project.spring.calla.persistence.MemberDAO;
import project.spring.calla.service.MemberService;

@Controller // @Component
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
	
	@PostMapping("/join")
	public String joinPOST(MemberVO vo, RedirectAttributes reAttr) {
		logger.info("joinPOST ȣ�� : " + vo.toString());
		int result = memberService.create(vo);
		if(result == 1) {
			reAttr.addFlashAttribute("insert", "success");
			return "redirect:/member/login";
		} else {
			return "redirect:/member/join";
		}
	}
	
	@GetMapping("/login")
	public void loginGET(String targetURL) {
	}
	
	@GetMapping("/expired")
	public void expiredGET() {}
	
	@PostMapping("/login")
	public String loginPOST(String memberId, String memberPw, String targetURL, RedirectAttributes reAttr, HttpServletRequest request) {
		// RedirectAttributes
		// 
		logger.info("loginPOST() 호출");
		String result = memberDAO.login(memberId, memberPw);
		if(result != null) {
			MemberVO vo = memberService.read(memberId);
			String memberNickname = vo.getMemberNickname();
			int memberLevel = vo.getMemberLevel(); 
			reAttr.addFlashAttribute("login_result", "success");
			
			HttpSession session = request.getSession();
			session.setAttribute("memberId", memberId);
			session.setAttribute("memberNickname", memberNickname);
			session.setAttribute("memberLevel", memberLevel);
			session.setMaxInactiveInterval(300);
			
			if(targetURL != null) {
				return "redirect:" + targetURL; 
			} else {
				return "redirect:/";
			}
			
			
		} else {
			logger.info("로그인 실패");
			if(targetURL != null) {
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
	
	@GetMapping("/searchId")
	public void searchIdGET() {} // end searchIdGET()
	
	@GetMapping("/searchPw")
	public void searchPwGET() {} // end searchPwGET()
	
	@PostMapping("/searchId")
	public String searchIdPOST(String memberName, String memberEmail, RedirectAttributes reAttr) {
		logger.info("searchId() 호출 memberName = " + memberName);
		logger.info("searchId() 호출 memberEmail = " + memberEmail);
		String result = memberService.searchId(memberName, memberEmail);
		if(result != null) {
			reAttr.addFlashAttribute("searchResult", "idSearch");
			reAttr.addFlashAttribute("searchId", result);
			logger.info("searchId 성공 memberId = " + result);
			return "redirect:/member/login";
		} else {
			reAttr.addFlashAttribute("searchResult", "fail");
			return "redirect:/member/searchId";
		}
		
		
	} // end searchIdPOST()
	
	@PostMapping("/searchPw")
	public String searchPwPOST(Model model, String memberId, String memberPhone, RedirectAttributes reAttr) {
		logger.info("searchPw() 호출 memberId = " + memberId);
		logger.info("searchPw() 호출 memberPhone = " + memberPhone);
		String result = memberService.searchPw(memberId, memberPhone);
		if(result != null) {
			reAttr.addFlashAttribute("searchResult", "pwSearch");
			reAttr.addFlashAttribute("searchPw", result);
			logger.info("searchPw 성공 memberPw = " + result);
			return "redirect:/member/login";
		} else {
			reAttr.addFlashAttribute("searchResult", "fail");
			return "redirect:/member/searchPw";
		}
		
	} // end searchPwPOST()
	
	@GetMapping("/order")
	public void orderGET() {}
	
	@GetMapping("/comments")
	public void commentsGET(Model model, HttpServletRequest request) {
		logger.info("commentsGET() 호출");
		
		HttpSession session = request.getSession();
		String memberNickname = (String) session.getAttribute("memberNickname");
		
		Map<String, Object> args = memberService.readComments(memberNickname);
		model.addAttribute("lists", args);
		
		
		
	}
	
	@GetMapping("/boards")
	public void boardsGET(Model model, HttpServletRequest request) {
		logger.info("commentsGET() 호출");
		
		HttpSession session = request.getSession();
		String memberNickname = (String) session.getAttribute("memberNickname");
		
		Map<String, Object> args = memberService.readBoards(memberNickname);
		model.addAttribute("lists", args);
		
	}
}
