package project.spring.calla.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.PageMaker;
import project.spring.calla.persistence.MemberDAO;
import project.spring.calla.service.MemberService;
import project.spring.calla.service.UProductCommentService;
import project.spring.calla.util.MediaUtil;

@Controller // @Component
@RequestMapping(value = "/member") // url : /ex02/board
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private static Map<String, HttpSession> loginSessions = new HashMap<>();
	
	@Resource(name = "uploadpath")
	private String uploadpath;

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private UProductCommentService uProductCommentService;

	@Autowired
	private MemberDAO memberDAO;

	@GetMapping("/join")
	public void showJoinPage() {

	}

//	@PostMapping("/join")
//	public String joinPOST(MemberVO vo, RedirectAttributes reAttr) {
//		logger.info("joinPost È£Ãâ");
//		logger.info("createMember" + vo.toString());
//		int result = memberService.create(vo);
//		if(result == 1) {
//			reAttr.addFlashAttribute("joinResult", "success");
//			return "redirect:/member/login";
//		} else {
//			return "redirect:/member/join";
//		}
//	}
	
	@GetMapping("/login")
	public void loginGET(String targetURL) {
	}

	@GetMapping("/expired")
	public void expiredGET() {
	}

	@PostMapping("/login")
	public String loginPOST(String memberId, String memberPw, String targetURL, RedirectAttributes reAttr, HttpServletRequest request) {
		logger.info("loginPOST() ");

		logger.info("loginPOST() " + loginSessions.toString());
		HttpSession existedSession = loginSessions.get(memberId);
		logger.info("= " + existedSession);
		if (existedSession != null) {
			existedSession.invalidate();
		}

		String result = memberDAO.login(memberId, memberPw);
		if (result != null) {
			MemberVO vo = memberService.read(memberId);
			String memberNickname = vo.getMemberNickname();
			int memberLevel = vo.getMemberLevel();
			float memberManner = vo.getMemberManner();
			reAttr.addFlashAttribute("login_result", "success");
			HttpSession session = request.getSession();
			session.setAttribute("memberId", memberId);
			session.setAttribute("memberNickname", memberNickname);
			session.setAttribute("memberLevel", memberLevel);
			session.setAttribute("memberManner", memberManner);
			session.setMaxInactiveInterval(60 * 60);
			
			loginSessions.put(memberId, session);
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
		logger.info("logoutGET() " + loginSessions.toString());
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		loginSessions.remove(memberId);
		session.invalidate();
		return "redirect:/";
	} // end logoutGET()

	@GetMapping("/myPage")
	public void myPageGET(Model model, String memberId) {

		MemberVO vo = memberService.read(memberId);
		model.addAttribute("vo", vo);
	} // end myPageGET()

	@GetMapping("/update")
	public void updateGET(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		if (memberId != null) {
			MemberVO vo = memberService.read(memberId);
			model.addAttribute("vo", vo);
		}
	} // end updateGET()

	@PostMapping("/update")
	public String updatePOST(MemberVO vo) {
		int result = memberService.update(vo);
		String memberId = vo.getMemberId();
		if (result == 1) {
			return "redirect:/member/myPage?memberId=" + memberId;
		} else {
			return "redirect:/member/update";
		}
	} // end updatePOST()

	@GetMapping("/likes")
	public void likesGET() {} // end likesGET()

	@GetMapping("/orders")
	public void ordersGET() {} // end likesGET()

	@GetMapping("/searchMemberInfo")
	public void searchMemberInfoGET() {
	} // end searchIdGET()

	/*
	 * @GetMapping("/searchPw") public void searchPwGET() { } // end searchPwGET()
	 */
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

	@GetMapping("/order")
	public void orderGET() {
	}

	@GetMapping("/comments")
	public void commentsGET() {}

	@GetMapping("/manageMember")
	public void manageMemberGET(Model model, HttpServletRequest request) {
		List<MemberVO> list = memberService.read();
		model.addAttribute("list", list);
	} // end manageMember

	@GetMapping("/boards")
	public void boardsGET() {} // end boardsGET
	
	@GetMapping("/delete")
	public String deleteGET(String memberId, HttpServletRequest request) {
		memberService.delete(memberId);
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/info")
	public ModelAndView InfoGET(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String memberNickname = (String) session.getAttribute("memberNickname");
		String memberLevel = (String) session.getAttribute("memberLevel");
		ModelAndView mv = new ModelAndView();
		mv.addObject("memberNickname", memberNickname);
		mv.addObject("memberLevel", memberLevel);
		mv.setViewName("sidebar");
		return mv;
	}

	@GetMapping("/myuproduct")
	public void MainGET(Model model, Integer page, Integer numsPerPage, HttpSession session) throws Exception {
		logger.info("list() ");
		logger.info("page = " + page + "numsPerPage = " + numsPerPage);

		
		String memberNickname = (String) session.getAttribute("memberNickname");
		
		// Paging 
		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}

		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<UProductVO> list = memberService.readmyuproduct(criteria, memberNickname);
		model.addAttribute("list", list);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(memberService.getTotalCountsBymyuproduct(memberNickname));
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);

	}
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName) {
		logger.info("display() ");

		ResponseEntity<byte[]> entity = null;
		InputStream in = null;

		String filePath = uploadpath + fileName;

		try {
			in = new FileInputStream(filePath);

			
			String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
			logger.info(extension);


			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaUtil.getMediaType(extension));

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), 
					httpHeaders, 
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entity;
	}
	
	@Transactional(value = "transactionManager")
	@GetMapping("/choosenickname")
	public void ChooseGET(Model model, Integer uProductId, Integer page, HttpSession session) throws Exception {
		logger.info("choosenickname()  : uProductId = " + uProductId);
		UProductVO vo = memberService.read(uProductId);
		logger.info(" : prdocutVO = " + vo);
		List<UProductCommentVO> list = uProductCommentService.read(uProductId);
		
		model.addAttribute("list", list);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);

	}
	
	@PostMapping("/choosenickname")
	public void choosenicknamePOST(UProductBuyVO vo, RedirectAttributes reAttr, UProductSellVO svo) throws Exception {
		// RedirectAttributes
		
		logger.info("choosenicknamePOST() ");
		logger.info(vo.toString());
		logger.info(svo.toString());
		int result = memberService.buysellcreate(vo, svo);
		logger.info("result = " + result);
		
		
		if(result == 1) {
			logger.info("ï¿½ï¿½ï¿½ï¿½ï¿½Ì°ï¿½");
		} 
		
	} // end registerPOST()
	@GetMapping("/cancel")
	public void cancelGET() {};
	
	@GetMapping("/searchByOption")
	public void searchByOprionGet(Model model, String productOption, String category, String keyword, int page) {
		logger.info("searchByOption option = " + productOption + " keyword = " + keyword + " category = " + category);
		List<UProductVO> list = null;
	
		PageCriteria criteria = new PageCriteria();
		criteria.setPage(page);
		PageMaker pageMaker = new PageMaker();
		
		list = memberService.readProductsByOption(criteria, keyword, category, productOption);
		pageMaker.setTotalCount(memberService.getTotalCountsProductsByOption(keyword, category, productOption));
		
		model.addAttribute("list", list);
			
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		
 		model.addAttribute("pageMaker", pageMaker);
 		model.addAttribute("option", productOption);
 		model.addAttribute("keyword", keyword);
 		model.addAttribute("category", category);
	};
}
