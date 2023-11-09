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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.calla.domain.ProductVO;
import project.spring.calla.domain.UProductBuyVO;
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
	public void reviewboard(Model model, String sellerNickname, Integer page, Integer numsPerPage, HttpSession session) throws Exception {
		logger.info("reviewboard() 호출");
		logger.info("page = " + page + "numsPerPage = " + numsPerPage);
		UProductReviewVO vo = uproductreviewservice.read(sellerNickname);
		
		
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
		model.addAttribute("vo", vo);
		
				

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(uproductreviewservice.getTotalCountssellNickname(sellerNickname));
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);

	}


	@GetMapping("/review")
	public void review(Model model, String sellerNickname, Integer page, HttpServletRequest request) {
		logger.info("review() 호출 : sellerNickname = " + sellerNickname);
		UProductBuyVO vo = uproductservice.read(sellerNickname);
		logger.info("호출 : prdocutVO = " + vo);
		
		
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		
	
	} // end review()

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

	
	

}