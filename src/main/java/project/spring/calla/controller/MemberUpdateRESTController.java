package project.spring.calla.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.service.MemberService;
import project.spring.calla.service.MemberUpdateService;

@RestController
@RequestMapping(value="/member/update")
public class MemberUpdateRESTController {
	private static final Logger logger =
			LoggerFactory.getLogger(MemberUpdateRESTController.class);
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberUpdateService memberUpdateService;
	
	@PutMapping("/pw/{memberId}") // 
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
			result = memberUpdateService.updatePw(memberId, newPw);
			
		} else {
			logger.info("pw ");
			
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updatePw
	
	@PutMapping("/nickname/{memberId}") // PUT :
	public ResponseEntity<Integer> updateMemberNickname(@PathVariable("memberId") String memberId, @RequestBody String newNickname, HttpSession session) {
		logger.info("updateMemberNickname() ");
		logger.info("newNickname = " + newNickname);
		
		int result = memberUpdateService.updateNickname(memberId, newNickname);
			if (result==1) {
				session.setAttribute("memberNickname", newNickname);
			}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updateNickname
	
	
	@PutMapping("/phone/{memberId}") // PUT :
	public ResponseEntity<Integer> updateMemberPhone(@PathVariable("memberId") String memberId, @RequestBody String newPhone) {
		logger.info("updateMemberPhone()");
		logger.info("newPhone = " + newPhone);
		
		int result = memberUpdateService.updatePhone(memberId, newPhone);
			
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updatePhone
	
	@PutMapping("/email/{memberId}") // PUT : 
	public ResponseEntity<Integer> updateMemberEmail(@PathVariable("memberId") String memberId, @RequestBody String newEmail) {
		logger.info("updateMemberEmail() ");
		logger.info("newEmail = " + newEmail);
		
		int result = memberUpdateService.updateEmail(memberId, newEmail);
			
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updateEmail
	
	@PutMapping("/interest/{memberId}") // PUT : 
	public ResponseEntity<Integer> updateMemberInterest(@PathVariable("memberId") String memberId, @RequestBody String newInterest) {
		logger.info("updateMemberInterest() ");
		logger.info("newInterest = " + newInterest);
		
		int result = memberUpdateService.updateInterest(memberId, newInterest);
			
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updateEmail
	
	@PutMapping("/address/{memberId}") // PUT : 
	public ResponseEntity<Integer> updateMemberAddress(@PathVariable("memberId") String memberId, @RequestBody String newAddress) {
		logger.info("updateMemberAddress() ");
		logger.info("newAddress = " + newAddress);
		
		int result = memberUpdateService.updateAddress(memberId, newAddress);
			
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updateAddress
	
	@PutMapping("/level/{memberId}") 
	public ResponseEntity<Integer> updateMemberLevel(@PathVariable("memberId") String memberId, @RequestBody String memberLevel, HttpSession session) {
		logger.info("updateMemberLevel() ");
		int amount = 0;
		if(Integer.parseInt(memberLevel) == 1) {
			amount = 1;
		} else if (Integer.parseInt(memberLevel) == 2) {
			amount = -1;
		}
		int updateResult = memberUpdateService.updateLevel(memberId, amount);
		MemberVO updatedVO = memberService.read(memberId);
		int newMemberLevel = updatedVO.getMemberLevel();
		logger.info(String.valueOf(newMemberLevel));

		return new ResponseEntity<Integer>(newMemberLevel, HttpStatus.OK);
	}// end updateAddress
}
	
	

