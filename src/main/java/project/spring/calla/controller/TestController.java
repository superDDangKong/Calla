package project.spring.calla.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.spring.calla.domain.UProductVO;
import project.spring.calla.service.UProductLikeService;
import project.spring.calla.service.UProductService;
import project.spring.calla.util.MediaUtil;
import project.spring.calla.util.PageCriteria;
import project.spring.calla.util.PageMaker;

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
		logger.info("list() �샇�뜝�룞�삕");
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
		logger.info("list() �샇�뜝�룞�삕");
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
		logger.info("display() �샇�뜝�룞�삕");

		ResponseEntity<byte[]> entity = null;
		InputStream in = null;

		String filePath = uploadpath + fileName;

		try {
			in = new FileInputStream(filePath);

			// �뜝�룞�삕�뜝�룞�삕 �솗�뜝�룞�삕�뜝�룞�삕
			String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
			logger.info(extension);

			// �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝占�(response header)�뜝�룞�삕 Content-Type �뜝�룞�삕�뜝�룞�삕
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaUtil.getMediaType(extension));
			// �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), // �뜝�룞�삕�뜝�떦�슱�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
					httpHeaders, // �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝占�
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entity;
	}
	
	

}