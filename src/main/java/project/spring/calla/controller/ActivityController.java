package project.spring.calla.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.PageMaker;
import project.spring.calla.service.ActivityService;
import project.spring.calla.service.UProductCommentService;
import project.spring.calla.util.MediaUtil;

@Controller
@RequestMapping(value = "/activity")
public class ActivityController {

	private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);
	
//	private static Map<String, HttpSession> loginSessions = new HashMap<>();
	
	@Resource(name = "uploadpath")
	private String uploadpath;

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private UProductCommentService uProductCommentService;

	@GetMapping("/likes")
	public void likesGET() {} // end likesGET()

	@GetMapping("/comments")
	public void commentsGET() {}

	@GetMapping("/boards")
	public void boardsGET() {} // end boardsGET

	@GetMapping("/myuproduct")
	public void MainGET(Model model, Integer page, Integer numsPerPage, HttpSession session) throws Exception {
		logger.info("list() ");
		logger.info("page = " + page + "numsPerPage = " + numsPerPage);

		
		String memberNickname = (String) session.getAttribute("memberNickname");
		
		// Paging 
		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}

		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<UProductVO> list = activityService.readmyuproduct(criteria, memberNickname);
		model.addAttribute("list", list);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(activityService.getTotalCountsBymyuproduct(memberNickname));
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);

	}
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName) {
		logger.info("display() ");

		ResponseEntity<byte[]> entity = null;
		InputStream in = null;

		String filePath = uploadpath + fileName;

		try {
			in = new FileInputStream(filePath);

			
			String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
			logger.info(extension);


			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaUtil.getMediaType(extension));

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), 
					httpHeaders, 
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entity;
	}
	
	@Transactional(value = "transactionManager")
	@GetMapping("/choosenickname")
	public void ChooseGET(Model model, Integer uProductId, Integer page, HttpSession session) throws Exception {
		logger.info("choosenickname()  : uProductId = " + uProductId);
		UProductVO vo = activityService.read(uProductId);
		logger.info(" : prdocutVO = " + vo);
		List<UProductCommentVO> list = uProductCommentService.read(uProductId);
		
		model.addAttribute("list", list);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);

	}
	
	@PostMapping("/choosenickname")
	public void choosenicknamePOST(UProductBuyVO vo, RedirectAttributes reAttr, UProductSellVO svo) throws Exception {
		// RedirectAttributes
		
		logger.info("choosenicknamePOST() ");
		logger.info(vo.toString());
		logger.info(svo.toString());
		int result = activityService.buysellcreate(vo, svo);
		logger.info("result = " + result);
		
		
		if(result == 1) {
			logger.info("�����̰�");
		} 
		
	} // end registerPOST()
	
	@GetMapping("/searchByOption")
	public void searchByOprionGet(Model model, String productOption, String category, String keyword, int page) {
		logger.info("searchByOption option = " + productOption + " keyword = " + keyword + " category = " + category);
		List<UProductVO> list = null;
	
		PageCriteria criteria = new PageCriteria();
		criteria.setPage(page);
		PageMaker pageMaker = new PageMaker();
		
		list = activityService.readProductsByOption(criteria, keyword, category, productOption);
		pageMaker.setTotalCount(activityService.getTotalCountsProductsByOption(keyword, category, productOption));
		
		model.addAttribute("list", list);
			
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		
 		model.addAttribute("pageMaker", pageMaker);
 		model.addAttribute("option", productOption);
 		model.addAttribute("keyword", keyword);
 		model.addAttribute("category", category);
	};
}
