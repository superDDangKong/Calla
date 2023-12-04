package project.spring.calla.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import project.spring.calla.domain.ProductLikeVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductLikeVO;
import project.spring.calla.domain.UProductReviewVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.PageMaker;
import project.spring.calla.service.ProductService;
import project.spring.calla.service.UProductLikeService;
import project.spring.calla.service.UProductService;
import project.spring.calla.util.FileUploadUtil;
import project.spring.calla.util.MediaUtil;

@Controller

@RequestMapping(value = "/test") // url : /calla/product
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private UProductService uproductService;
	
	@Autowired
	private UProductLikeService uproductlikeService;

	@Resource(name = "uploadpath")
	private String uploadpath;


	@GetMapping("/index")
	public void list(Model model, Integer page, Integer numsPerPage, String name, String keyword) {
		logger.info("list() 호占쏙옙");
		logger.info("page = " + page + " , numsPerPage = " + numsPerPage);
		List<UProductVO> list = null;

		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		PageMaker pageMaker = new PageMaker();

		if (name != null) {

			if (name.equals("searchName")) {
				logger.info("if elseif");
				list = uproductService.readByCategoriorName(criteria, keyword);
				pageMaker.setTotalCount(uproductService.getTotalCountsByByCategoriorName(keyword));
			} else if (name.equals("searchDate")) {

				list = uproductService.readdate(criteria);
				pageMaker.setTotalCount(uproductService.getTotalCountsBydate());

			} else if (name.equals("searchlike")) {

				list = uproductService.readlike(criteria);
				pageMaker.setTotalCount(uproductService.getTotalCountsBylike());

			} else {

				logger.info("if else");
				list = uproductService.read(criteria);
				pageMaker.setTotalCount(uproductService.getTotalCounts());

			}
		} else {
			logger.info("else");
			list = uproductService.read(criteria);
			pageMaker.setTotalCount(uproductService.getTotalCounts());
		}

		logger.info("totalCount = " + pageMaker.getTotalCount());
		model.addAttribute("list", list);
		model.addAttribute("name", name);
		model.addAttribute("keyword", keyword);

		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);

	} // end list()
	
	@GetMapping("/list")
	public void lists(Model model, Integer page, Integer numsPerPage, String name, String keyword) {
		logger.info("list() 호占쏙옙");
		logger.info("page = " + page + " , numsPerPage = " + numsPerPage);
		List<UProductVO> list = null;

		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		PageMaker pageMaker = new PageMaker();

		if (name != null) {

			if (name.equals("searchName")) {
				logger.info("if elseif");
				list = uproductService.readByCategoriorName(criteria, keyword);
				pageMaker.setTotalCount(uproductService.getTotalCountsByByCategoriorName(keyword));
			} else if (name.equals("searchDate")) {

				list = uproductService.readdate(criteria);
				pageMaker.setTotalCount(uproductService.getTotalCountsBydate());

			} else if (name.equals("searchlike")) {

				list = uproductService.readlike(criteria);
				pageMaker.setTotalCount(uproductService.getTotalCountsBylike());

			} else {

				logger.info("if else");
				list = uproductService.read(criteria);
				pageMaker.setTotalCount(uproductService.getTotalCounts());

			}
		} else {
			logger.info("else");
			list = uproductService.read(criteria);
			pageMaker.setTotalCount(uproductService.getTotalCounts());
		}

		logger.info("totalCount = " + pageMaker.getTotalCount());
		model.addAttribute("list", list);
		model.addAttribute("name", name);
		model.addAttribute("keyword", keyword);

		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);

	} // end list()
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName) {
		logger.info("display() 호占쏙옙");

		ResponseEntity<byte[]> entity = null;
		InputStream in = null;

		String filePath = uploadpath + fileName;

		try {
			in = new FileInputStream(filePath);

			// 占쏙옙占쏙옙 확占쏙옙占쏙옙
			String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
			logger.info(extension);

			// 占쏙옙占쏙옙 占쏙옙占�(response header)占쏙옙 Content-Type 占쏙옙占쏙옙
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaUtil.getMediaType(extension));
			// 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), // 占쏙옙占싹울옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙
					httpHeaders, // 占쏙옙占쏙옙 占쏙옙占�
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entity;
	}
	
	

}