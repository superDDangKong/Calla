package project.spring.calla.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.service.MailServiceImple;
import project.spring.calla.service.MemberService;

@RestController
@RequestMapping(value="/member")
public class MemberRESTController {
	private static final Logger logger =
			LoggerFactory.getLogger(MemberRESTController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MailServiceImple mailSendService;
	
	@PostMapping("/join") // join.jsp ajax url 占쏙옙占�
	public ResponseEntity<Object> createMember(@RequestBody MemberVO vo) {  // 1. @RequestBody占쏙옙 ajax占쏙옙占쏙옙 JSON占쏙옙占승뤄옙 占쏙옙占쏙옙 占쏙옙占쏙옙占싶몌옙 memberVO vo (占쌘바곤옙체)占쏙옙 占쏙옙환占쏙옙占쌍곤옙
																			// @RequestBody占쏙옙 JSON.stringify(obj) 占쏙옙占싱쏙옙 占쏙옙체占쏙옙 占쌘뱄옙 占쏙옙체占쏙옙 占쏙옙환占쏙옙占쏙옙
																			// @RequestParam 占쏙옙 memberId : memberId, memberPw : memberPw, ... 占쏙옙占� 占쏙옙占쏙옙占쌀쇽옙 占쏙옙占쏙옙
		logger.info("createMember() : vo = " + vo.toString()); // jsp占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싶몌옙 占쏙옙占�
		int result = 0;
		try {
			result = memberService.create(vo); // 2.占쏙옙占쏟스울옙 create占쌨소드에 클占쏙옙占싱억옙트占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싶몌옙 占쏙옙占쏙옙絿占� 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
		} catch (IllegalStateException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST); // 
		}
		// ResponseEntity占쏙옙 HTTP 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쌘듸옙, 占쏙옙占�, 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占싹곤옙 占쏙옙占쏙옙占쏙옙 占쏙옙 占쌍댐옙.
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	} // end createMember
	
	@GetMapping("/checkemail")
	public String mailAuthentication(String memberEmail, HttpServletRequest request) throws Exception {
		logger.info("mailAuth()호출 인증번호를 보낼 이메일 주소 : " + memberEmail);
		String authKey = mailSendService.sendMail(memberEmail); 
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
		logger.info("mailAuthKeyConfirm() 호출");
		logger.info("AuthenticationKey: " + AuthenticationKey);
		HttpSession mailAuthSession = request.getSession();
	    String savedAuthenticationKey = (String) mailAuthSession.getAttribute("authKey");
	    logger.info(savedAuthenticationKey);
	    String result = "fail";
	    
	    if (AuthenticationKey.equals(savedAuthenticationKey)) {
	    	logger.info("이메일 인증성공!");
	    	result = "success";
	    } else {
	    	logger.info("사용자가 입력한 인증번호 : " + AuthenticationKey);
	    	logger.info("세션에 저장된 인증번호 : " + savedAuthenticationKey);
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
			if (category.equals("member_nickname") && result==1) {
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
			result = memberService.updatePw(memberId, newPw);
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
		MemberVO vo = memberService.read(memberId);
		int newMemberLevel = vo.getMemberLevel();
		logger.info(String.valueOf(newMemberLevel));

		return new ResponseEntity<Integer>(newMemberLevel, HttpStatus.OK);
	}// end updateAddress

}
	
	

