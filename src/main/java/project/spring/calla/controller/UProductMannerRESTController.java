package project.spring.calla.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductMannerDownVO;
import project.spring.calla.domain.UProductMannerVO;
import project.spring.calla.domain.UProductReviewVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.PageMaker;
import project.spring.calla.service.ProductService;
import project.spring.calla.service.UProductReviewService;
import project.spring.calla.service.UProductService;
import project.spring.calla.util.FileUploadUtil;
import project.spring.calla.util.MediaUtil;

@Controller

@RestController
@RequestMapping(value = "/uProduct/mannerdown") // url : /calla/product
public class UProductMannerRESTController {
	private static final Logger logger = LoggerFactory.getLogger(UProductMannerRESTController.class);

	@Autowired
	private UProductReviewService reviewservice;
	
	@Transactional(value = "transactionManager")
	@PostMapping // POST : 댓글 입력
	public ResponseEntity<Integer> mannerDown(@RequestBody UProductMannerDownVO vo) {
		// @RequestBody
		// - 클라이언트에서 전송받은 json epdlxjfmf
		// 자바 객체로 변환해주는 annotation
		logger.info("createReply() 호출 : vo = " + vo.toString());

	
		int result = 0;
		String sellerNickname = vo.getSellerNickname();
		logger.info("mannerDown() 호출 : uProductId = " + vo.getuProductId());

		try {
			
			int count = reviewservice.countmannerdown(vo.getuProductId());
			logger.info("reviewservice() 호출 : count = " + count);
			if(count == 0) {
			
			result = reviewservice.insertmannerdown(vo);
		
			reviewservice.updatememberManners(sellerNickname);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
			
		} else {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	
	

}