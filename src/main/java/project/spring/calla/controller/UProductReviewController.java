package project.spring.calla.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.domain.UProductReviewVO;
import project.spring.calla.service.UProductReviewService;
import project.spring.calla.service.UProductService;
import project.spring.calla.util.PageCriteria;
import project.spring.calla.util.PageMaker;

@Controller

@RequestMapping(value = "/uProduct") // url : /calla/product
public class UProductReviewController {
	private static final Logger logger = LoggerFactory.getLogger(UProductReviewController.class);

	@Autowired
	private UProductReviewService uproductreviewservice;
	
	@Autowired
	private UProductService uproductservice;

	@Resource(name = "uploadpath")
	private String uploadpath;
	
	@GetMapping("/reviewboard")
	public void reviewboard(Model model, String sellerNickname, Integer page, Integer numsPerPage, Integer uProductId, HttpSession session) throws Exception {
		logger.info("reviewboard() 호출");
		logger.info("page = " + page + "numsPerPage = " + numsPerPage);
			
		// Paging 처占쏙옙
		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}

		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<UProductReviewVO> list = uproductreviewservice.readysellNickname(criteria, sellerNickname);
		model.addAttribute("list", list);
		model.addAttribute("sellerNickname", sellerNickname);
		
				

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(uproductreviewservice.getTotalCountssellNickname(sellerNickname));
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);

	}


	@GetMapping("/review")
	public void review(Model model, String sellerNickname, int uProductId, Integer page, HttpServletRequest request) {
		logger.info("review() 호출 : sellerNickname = " + sellerNickname);
		
		
		MemberVO vo = uproductreviewservice.readMembermanner(sellerNickname);
		
		model.addAttribute("vo", vo);
		model.addAttribute("sellerNickname", sellerNickname);
		model.addAttribute("uProductId", uProductId);
		model.addAttribute("page", page);
		
	
	} // end review()

	
	@Transactional(value = "transactionManager")
	@PostMapping("/review")
	public String reviewPost(UProductReviewVO vo, RedirectAttributes reAttr) throws Exception {
		// RedirectAttributes
				// - 리다이렉트 시 데이터를 전달하기 위한 인터페이스
		

		
				logger.info("reviewPOST() 호출");
				logger.info(vo.toString());
				int result = uproductreviewservice.create(vo);
				logger.info(result + "행 삽입");
				if(result == 1) {
					reAttr.addFlashAttribute("insert_result", "success");
					return "redirect:/uProduct/uproductbuy";
				} else {
					return "redirect:/uProduct/review";
				}

	} // end registerPOST()
	
	@GetMapping("/reviewdetail")
	public void reviewdetail(Model model, Integer uProductReviewId, Integer page) {
		logger.info("detail() 호출 : uProductReviewId = " + uProductReviewId);
		UProductReviewVO vo = uproductreviewservice.read(uProductReviewId);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
	}

	
	

}