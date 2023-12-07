package project.spring.calla.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.MyPageCriteria;
import project.spring.calla.pageutil.MyPageMaker;
import project.spring.calla.service.ActivityService;

@RestController
@RequestMapping(value="/activity")
public class ActivityRESTController {
	private static final Logger logger =
			LoggerFactory.getLogger(ActivityRESTController.class);
	
	@Autowired
	private ActivityService ativityService;
	
	
	@GetMapping("/orders/{page}")
	public ResponseEntity<Map<String, Object>> readOrderList(@PathVariable("page") int page, HttpServletRequest request) {
		logger.info("readOrders");
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		MyPageCriteria criteria = new MyPageCriteria();
		criteria.setPage(page);
		MyPageMaker pageMaker = new MyPageMaker();
		
		List<ProductOrderVO> list = null;
		
		list = ativityService.readOrders(memberId, criteria);
		pageMaker.setTotalCount(ativityService.getTotalCountsOrders(memberId));
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
		
		list = ativityService.readBoards(memberNickname, option, criteria);
		pageMaker.setTotalCount(ativityService.getTotalCountsBoard(memberNickname, option));
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
		
		list = ativityService.readComments(memberNickname, option, criteria);
		pageMaker.setTotalCount(ativityService.getTotalCountsComment(memberNickname, option));
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
			list = ativityService.readLikes(memberId, option, criteria);
			pageMaker.setTotalCount(ativityService.getTotalCountsLike(memberId, option));
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
				ativityService.deleteProductLike(productLikeIdList.get(i), amount, productIdList.get(i));
			}
		}
		
		if(uProductLikeIdList.size() != 0) {
			for(int i = 0; i < uProductLikeIdList.size(); i++) {
				ativityService.deleteUProductLike(uProductLikeIdList.get(i), amount, uProductIdList.get(i));
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
		
		Map<String, Integer> counts = ativityService.getTotalCountsByRecentlyView(memberId);
		pageMaker.setTotalCount(counts.get("productCount"));
		pageMaker.setCriteria(criteria);
		
		pageMaker.setPageData();
		Map<String, Object> lists = ativityService.readRecentlyView(criteria, memberId);
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
		Map<String, Integer> counts = ativityService.getTotalCountsByRecentlyView(memberId);
		pageMaker.setTotalCount(counts.get("uProductCount"));
		
		pageMaker.setCriteria(criteria);
		
		pageMaker.setPageData();
		Map<String, Object> lists = ativityService.readRecentlyView(criteria, memberId);
		lists.put("pageMaker", pageMaker);
		return new ResponseEntity<Map<String, Object>>(lists, HttpStatus.OK);
	}
	
	@DeleteMapping("/recentlyView/product/{productRecentlyViewId}")
	public ResponseEntity<Integer> deleteRecentlyViewProduct(@PathVariable("productRecentlyViewId") int productRecentlyViewId) {
		logger.info("productRecentlyViewId = " + productRecentlyViewId);

		int result = 0;
		try {
			result = ativityService.deleteRecentlyViewProduct(productRecentlyViewId);
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
			result = ativityService.deleteRecentlyViewUProduct(uProductRecentlyViewId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@PostMapping("/choosenickname")
	public ResponseEntity<Integer> createBuySell(@RequestBody UProductSellVO svo) {
		// @RequestBody
		// - 클라이언트에서 전송받은 json 데이터를
		// 자바 객체로 변환해주는 annotation
		//logger.info("createComment() 호출 : vo = " + vo.toString());
		logger.info("createComment() 호출 : vo = " + svo.toString());
		String uproductname = svo.getuProductName();
		String buyerNickname= svo.getBuyerNickname();
		String memberAddress = svo.getMemberAddress();
		String sellerNickname = svo.getSellerNickname();
		int uproductprice = svo.getuProductPrice();
		String uproductcategori = svo.getuProductCategori();
		String uproductcontent = svo.getuProductContent();
		String imagepath = svo.getuProductImagePath();
		int uproductid = svo.getuProductId();
		
		UProductBuyVO vo = new UProductBuyVO(uproductid, uproductname, uproductprice, null, uproductcategori, memberAddress, uproductcontent, buyerNickname, imagepath, uproductid, sellerNickname); 
		
		// ResponseEntity<T> : Rest 방식에서 데이터를 리턴할 때 쓰이는 객체
		// - 데이터 HttpStatus를 전송
		// - <T> : 보내고자 하는 데이터 타입
		int result = 0;
		try {
			result = ativityService.buysellcreate(vo, svo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
	
	

