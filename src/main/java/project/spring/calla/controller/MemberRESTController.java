package project.spring.calla.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import project.spring.calla.service.MemberService;

@RestController
@RequestMapping(value="/member")
public class MemberRESTController {
	private static final Logger logger =
			LoggerFactory.getLogger(MemberRESTController.class);
	
	@Autowired
	private MemberService memberService;
	

	
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
	
//	@PutMapping("memberNickname/{memberNickname}") // PUT : 
//	public ResponseEntity<Integer> updateMemberNickname(@PathVariable("memberNickname") String memberNickname) {
//		int result = memberService.update(memberNickname);
//		return new ResponseEntity<Integer>(result, HttpStatus.OK);
//	}
	
	@PutMapping("/updatePw/{memberId}") // 
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
	
	@PutMapping("/updateNickname/{memberId}") // PUT :
	public ResponseEntity<Integer> updateMemberNickname(@PathVariable("memberId") String memberId, @RequestBody String newNickname, HttpSession session) {
		logger.info("updateMemberNickname() ");
		logger.info("newNickname = " + newNickname);
		
		int result = memberService.updateNickname(memberId, newNickname);
			if (result==1) {
				session.setAttribute("memberNickname", newNickname);
			}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updateNickname
	
	
	@PutMapping("/updatePhone/{memberId}") // PUT :
	public ResponseEntity<Integer> updateMemberPhone(@PathVariable("memberId") String memberId, @RequestBody String newPhone) {
		logger.info("updateMemberPhone()");
		logger.info("newPhone = " + newPhone);
		
		int result = memberService.updatePhone(memberId, newPhone);
			
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updatePhone
	
	@PutMapping("/updateEmail/{memberId}") // PUT : 
	public ResponseEntity<Integer> updateMemberEmail(@PathVariable("memberId") String memberId, @RequestBody String newEmail) {
		logger.info("updateMemberEmail() ");
		logger.info("newEmail = " + newEmail);
		
		int result = memberService.updateEmail(memberId, newEmail);
			
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updateEmail
	
	@PutMapping("/updateInterest/{memberId}") // PUT : 
	public ResponseEntity<Integer> updateMemberInterest(@PathVariable("memberId") String memberId, @RequestBody String newInterest) {
		logger.info("updateMemberInterest() ");
		logger.info("newInterest = " + newInterest);
		
		int result = memberService.updateInterest(memberId, newInterest);
			
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updateEmail
	
	@PutMapping("/updateAddress/{memberId}") // PUT : 
	public ResponseEntity<Integer> updateMemberAddress(@PathVariable("memberId") String memberId, @RequestBody String newAddress) {
		logger.info("updateMemberAddress() ");
		logger.info("newAddress = " + newAddress);
		
		int result = memberService.updateAddress(memberId, newAddress);
			
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}// end updateAddress
	
	@PutMapping("/updateLevel/{memberId}") 
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
	@GetMapping("/orders/{page}")
	public ResponseEntity<Map<String, Object>> readOrderList(@PathVariable("page") int page, HttpServletRequest request) {
		logger.info("readOrders ");
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		MyPageCriteria criteria = new MyPageCriteria();
		criteria.setPage(page);
		MyPageMaker pageMaker = new MyPageMaker();
		
		List<ProductOrderVO> list = null;
		
		list = memberService.readOrders(memberId, criteria);
		pageMaker.setTotalCount(memberService.getTotalCountsOrders(memberId));
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		
		
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("list", list);
		args.put("pageMaker", pageMaker);
		return new ResponseEntity<Map<String, Object>>(args, HttpStatus.OK);
	}
	
	@GetMapping("/boards/{memberNickname}/{page}/{option}")
	public ResponseEntity<Map<String, Object>> readBoardsByOption(@PathVariable("memberNickname") String memberNickname, @PathVariable("page") int page, @PathVariable("option") String option) {
		logger.info("readBoardsByOption()");
		
		MyPageCriteria criteria = new MyPageCriteria();
		criteria.setPage(page);
		MyPageMaker pageMaker = new MyPageMaker();
		List<UProductVO> list = null;
		
		list = memberService.readBoards(memberNickname, option, criteria);
		pageMaker.setTotalCount(memberService.getTotalCountsBoard(memberNickname, option));
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("list", list);
		args.put("pageMaker", pageMaker);
		return new ResponseEntity<Map<String, Object>>(args, HttpStatus.OK);
	}
	
	@GetMapping("/comments/{memberNickname}/{option}/{page}")
	public ResponseEntity<Map<String, Object>> readCommentsByOption(@PathVariable("memberNickname") String memberNickname, @PathVariable("option") String option, @PathVariable("page") int page) {
		logger.info("readCommentsByOption()");
		MyPageCriteria criteria = new MyPageCriteria();
		criteria.setPage(page);
		MyPageMaker pageMaker = new MyPageMaker();
		List<UProductCommentVO> list = null;
		
		list = memberService.readComments(memberNickname, option, criteria);
		pageMaker.setTotalCount(memberService.getTotalCountsComment(memberNickname, option));
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("list", list);
		args.put("pageMaker", pageMaker);
		return new ResponseEntity<Map<String, Object>>(args, HttpStatus.OK);
	}
	
	@GetMapping("/likes/{page}/{option}")
	public ResponseEntity<Map<String, Object>> readLikesByOption(@PathVariable("page") int page, @PathVariable("option") String option, HttpServletRequest request) {
		logger.info("readLikesByOption()");
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		MyPageCriteria criteria = new MyPageCriteria();
		criteria.setPage(page);
		MyPageMaker pageMaker = new MyPageMaker();
		
		List<UProductVO> list = null;
		if(memberId != null) {
			list = memberService.readLikes(memberId, option, criteria);
			pageMaker.setTotalCount(memberService.getTotalCountsLike(memberId, option));
		} 
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("list", list);
		args.put("pageMaker", pageMaker);
		return new ResponseEntity<Map<String, Object>>(args, HttpStatus.OK);
	} // end likesGET()
	
	@DeleteMapping("/deleteLikes")
	ResponseEntity<Integer> deleteLikes(@RequestBody Map<String, List<Integer>> args) {
		logger.info("deleteLikes ");
		int result = 1;
		List<Integer> productIdList = args.get("productIdList");
		List<Integer> productLikeIdList = args.get("productLikeIdList");
		
		List<Integer> uProductIdList = args.get("uProductIdList");
		List<Integer> uProductLikeIdList = args.get("uProductLikeIdList");
		int amount = -1;
		if(productLikeIdList.size() != 0) {
			for(int i = 0; i < productLikeIdList.size(); i++) {
				memberService.deleteProductLike(productLikeIdList.get(i), amount, productIdList.get(i));
			}
		}
		
		if(uProductLikeIdList.size() != 0) {
			for(int i = 0; i < uProductLikeIdList.size(); i++) {
				memberService.deleteUProductLike(uProductLikeIdList.get(i), amount, uProductIdList.get(i));
			}
		}
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	} // end deleteLikes() 

	@GetMapping("/recentlyView/product/{memberId}/{page}") 
	public ResponseEntity<Map<String, Object>> recentlyViewProductGET(@PathVariable("memberId") String memberId, @PathVariable("page") int page) {
		logger.info("recentlyViewProductGET()  : memberId = " + memberId);
		
		MyPageCriteria criteria = new MyPageCriteria();
		MyPageMaker pageMaker = new MyPageMaker();
		
		criteria.setPage(page);
		criteria.setNumsPerPage(4);
		
		Map<String, Integer> counts = memberService.getTotalCountsByRecentlyView(memberId);
		pageMaker.setTotalCount(counts.get("productCount"));
		pageMaker.setCriteria(criteria);
		
		pageMaker.setPageData();
		Map<String, Object> lists = memberService.readRecentlyView(criteria, memberId);
		lists.put("pageMaker", pageMaker);
		return new ResponseEntity<Map<String, Object>>(lists, HttpStatus.OK);
	}
	
	@GetMapping("/recentlyView/uProduct/{memberId}/{page}") 
	public ResponseEntity<Map<String, Object>> recentlyViewUProductGET(@PathVariable("memberId") String memberId, @PathVariable("page") int page) {
		logger.info("recentlyUProductViewGET()  : memberId = " + memberId);
		
		MyPageCriteria criteria = new MyPageCriteria();
		MyPageMaker pageMaker = new MyPageMaker();
		
		criteria.setPage(page);
		criteria.setNumsPerPage(4);
		Map<String, Integer> counts = memberService.getTotalCountsByRecentlyView(memberId);
		pageMaker.setTotalCount(counts.get("uProductCount"));
		
		pageMaker.setCriteria(criteria);
		
		pageMaker.setPageData();
		Map<String, Object> lists = memberService.readRecentlyView(criteria, memberId);
		lists.put("pageMaker", pageMaker);
		return new ResponseEntity<Map<String, Object>>(lists, HttpStatus.OK);
	}
	
	@DeleteMapping("/recentlyView/product/{productRecentlyViewId}")
	public ResponseEntity<Integer> deleteRecentlyViewProduct(@PathVariable("productRecentlyViewId") int productRecentlyViewId) {
		logger.info("productRecentlyViewId = " + productRecentlyViewId);

		int result = 0;
		try {
			result = memberService.deleteRecentlyViewProduct(productRecentlyViewId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/recentlyView/uProduct/{uProductRecentlyViewId}")
	public ResponseEntity<Integer> deleteRecentlyViewUProduct(@PathVariable("uProductRecentlyViewId") int uProductRecentlyViewId) {
		logger.info("uProductRecentlyViewId = " + uProductRecentlyViewId);

		int result = 0;
		try {
			result = memberService.deleteRecentlyViewUProduct(uProductRecentlyViewId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
	
	

