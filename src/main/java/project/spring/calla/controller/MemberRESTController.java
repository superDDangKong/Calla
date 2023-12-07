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
	
	@PostMapping("/join") // join.jsp ajax url °æ·Î
	public ResponseEntity<Object> createMember(@RequestBody MemberVO vo) {  // 1. @RequestBody·Î ajax¿¡¼­ JSONÇüÅÂ·Î º¸³½ µ¥ÀÌÅÍ¸¦ memberVO vo (ÀÚ¹Ù°´Ã¼)·Î º¯È¯ÇØÁÖ°í
																			// @RequestBody´Â JSON.stringify(obj) Á¦ÀÌ½¼ °´Ã¼¸¦ ÀÚ¹Ù °´Ã¼·Î º¯È¯ÇØÁÜ
																			// @RequestParam Àº memberId : memberId, memberPw : memberPw, ... µîµî ¸ÅÇÎÇÒ¼ö ÀÖÀ½
		logger.info("createMember() : vo = " + vo.toString()); // jsp¿¡¼­ º¸³»ÁØ µ¥ÀÌÅÍ¸¦ Ãâ·Â
		int result = 0;
		try {
			result = memberService.create(vo); // 2.¼­ºñ½º¿¡ create¸Þ¼Òµå¿¡ Å¬¶óÀÌ¾ðÆ®¿¡¼­ Àü¼ÛÇÑ µ¥ÀÌÅÍ¸¦ ºñÁî´Ï½º ·ÎÁ÷À¸·Î Àü¼Û
		} catch (IllegalStateException e) {
			String resultString = e.getMessage();
			return new ResponseEntity<Object>(resultString, HttpStatus.BAD_REQUEST); // 
		}
		// ResponseEntity´Â HTTP ÀÀ´äÀÇ »óÅÂ ÄÚµå, Çì´õ, º»¹® µîÀ» ¼¼¹ÐÇÏ°Ô Á¦¾îÇÒ ¼ö ÀÖ´Ù.
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	} // end createMember
	
	@GetMapping("/checkemail")
	public String mailAuthentication(String memberEmail, HttpServletRequest request) throws Exception {
		logger.info("mailAuth() È£ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ã»ï¿½ï¿½ ï¿½Ì¸ï¿½ï¿½ï¿½ ï¿½Ö¼ï¿½ : " + memberEmail);
		String authKey = mailSendService.sendMail(memberEmail); //ï¿½ï¿½ï¿½ï¿½Ú°ï¿½ ï¿½Ô·ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Ö¼Ò·ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
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
		logger.info("mailAuthKeyConfirm() È£ï¿½ï¿½ AuthenticationKey: " + AuthenticationKey);
		HttpSession mailAuthSession = request.getSession();
	    String savedAuthenticationKey = (String) mailAuthSession.getAttribute("authKey");
	    logger.info(savedAuthenticationKey);
	    String result = "fail";
	    
	    if (AuthenticationKey.equals(savedAuthenticationKey)) {
	    	logger.info("ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½!");
	    	result = "success";
	    } else {
	    	logger.info("ï¿½Ô·ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½Å° : " + AuthenticationKey);
	    	logger.info("ï¿½ï¿½ï¿½ï¿½Ú¿ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½Å° : " + savedAuthenticationKey);
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
	
	

