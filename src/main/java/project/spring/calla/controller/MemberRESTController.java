//package project.spring.calla.controller;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import project.spring.calla.domain.FBoardReplyVO;
//import project.spring.calla.service.MemberService;
//
//@RestController
//@RequestMapping("/member")
//public class MemberRESTController {
//	
//	private static final Logger logger 
//		= LoggerFactory.getLogger(MemberRESTController.class);
//	
//	@Autowired
//	private MemberService memberService;
//	
//	@Autowired
//	private ProductLikeService productLikeService;
//	
//	@GetMapping("/allProductLikes/{MemberId}")
//	public ResponseEntity<List<FBoardReplyVO>> readLikeProducts(@PathVariable("MemberId") String memberId) {
//		// @PathVariable("fBoardId") : /all/{fBboardId} 값을 설정된 변수에 저장
//		logger.info("readLikeProducts() 호출 : memberId = " + memberId);
//
//		List<ProductLikeVO> list = productLikeService.read(memberId);
//		return new ResponseEntity<List<productLikeVO>>(list, HttpStatus.OK);
//	}
//	
//}
