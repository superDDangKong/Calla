package project.spring.calla.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.MyPageCriteria;
import project.spring.calla.pageutil.MyPageMaker;
import project.spring.calla.service.MailService;
import project.spring.calla.service.MemberService;

@RestController
@RequestMapping(value="/member")
public class MemberRESTController {
	private static final Logger logger =
			LoggerFactory.getLogger(MemberRESTController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MailService mailSendService;
	
	@PostMapping("/join")
	public ResponseEntity<Object> createMember(@RequestBody MemberVO vo) {
		logger.info("createMember() : vo = " + vo.toString());
		int result = 0;
		try {
			result = memberService.create(vo);
		} catch (IllegalStateException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	} // end createMember
	
	@GetMapping("/checkemail")
	public String mailAuthentication(String memberEmail, HttpServletRequest request) throws Exception {
		logger.info("mailAuth() 호출 인증요청한 이메일 주소 : " + memberEmail);
		String authKey = mailSendService.sendMail(memberEmail); //사용자가 입력한 메일주소로 메일을 보냄
		// memberService.registMailAuthentication(memberEmail, authKey);
		HttpSession mailAuthSession = request.getSession();
		mailAuthSession.setAttribute("memberEmail", memberEmail);
		mailAuthSession.setAttribute("authKey", authKey);
		mailAuthSession.setMaxInactiveInterval(60 * 3);
		logger.info(authKey);
		return authKey; 
	}
	
	@PostMapping("/authenticationConfirm")
	public String mailAuthKeyConfirm(String AuthenticationKey, HttpServletRequest request) {
		logger.info("mailAuthKeyConfirm() 호출 AuthenticationKey: " + AuthenticationKey);
		HttpSession mailAuthSession = request.getSession();
	    String savedAuthenticationKey = (String) mailAuthSession.getAttribute("authKey");
	    logger.info(savedAuthenticationKey);
	    String result = "fail";
	    
	    if (AuthenticationKey.equals(savedAuthenticationKey)) {
	    	logger.info("인증성공!");
	    	result = "success";
	    } else {
	    	logger.info("입력한 인증키 : " + AuthenticationKey);
	    	logger.info("사용자에게 보낸 인증키 : " + savedAuthenticationKey);
		    return result;
	    }
	    return result;
	}
	
	@PostMapping("/checkId") // @RequestParam("member_Id")
	public int checkId(@RequestParam("memberId") String id) {
		logger.info("checkId()");
		logger.info(id); // 
			try {
				id = URLDecoder.decode(id, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info(id); // 

		return memberService.checkId(id);
	} // end checkId
	
	@PostMapping("/checkNick")
	public int checkNick(@RequestParam("memberNickname") String nick) {
		logger.info("checkNick() ");
		logger.info(nick);
			try {
				nick = URLDecoder.decode(nick,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info(nick);
		int result = memberService.checkNick(nick);
		logger.info(String.valueOf(result));
		return result;
	} // end checkNick
	
	@PutMapping("/update/{memberId}") 
	public ResponseEntity<Integer> updateMember(@PathVariable("memberId") String memberId, @RequestBody Map<String, String> obj, HttpSession session) {
		logger.info("updateMember() ");
		logger.info("newData = " + obj);
		String newData = obj.get("newData");
		String category = obj.get("category");
		
		int result = memberService.update(memberId, newData, category);
			if (category.equals("memberNickname") && result==1) {
				session.setAttribute("memberNickname", newData);
			}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updateNickname
	
	@PutMapping("/updatepw/{memberId}") // 
	public ResponseEntity<Integer> updateMemberPw(@PathVariable("memberId") String memberId, @RequestBody Map<String, Object> args, HttpSession session) {
		logger.info("updateMemberPw() ");
		logger.info("memberId = " + memberId);
		logger.info(args.get("currentPw").toString());
		logger.info(args.get("newPw").toString());
		logger.info(args.get("newPwCheck").toString());
		MemberVO vo = memberService.read(memberId);
		String memberPw = vo.getMemberPw();
		String newPw = args.get("newPw").toString();
		int result = 0;
		if (memberPw.equals(args.get("currentPw"))) {
			logger.info("pw ");
			result = memberService.updatePw(memberId, newPw);
			
		} else {
			logger.info("pw ");
			
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updatePw
	
	@PutMapping("/updatelevel/{memberId}") 
	public ResponseEntity<Integer> updateMemberLevel(@PathVariable("memberId") String memberId, @RequestBody String memberLevel, HttpSession session) {
		logger.info("updateMemberLevel() ");
		int amount = 0;
		if(Integer.parseInt(memberLevel) == 1) {
			amount = 1;
		} else if (Integer.parseInt(memberLevel) == 2) {
			amount = -1;
		}
		int updateResult = memberService.updateLevel(memberId, amount);
		MemberVO updatedVO = memberService.read(memberId);
		int newMemberLevel = updatedVO.getMemberLevel();
		logger.info(String.valueOf(newMemberLevel));

		return new ResponseEntity<Integer>(newMemberLevel, HttpStatus.OK);
	}// end updateAddress

}
	
	

