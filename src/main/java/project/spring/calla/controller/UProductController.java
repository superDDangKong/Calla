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
import project.spring.calla.service.UProductService;
import project.spring.calla.util.FileUploadUtil;
import project.spring.calla.util.MediaUtil;

@Controller

@RequestMapping(value = "/uProduct") // url : /calla/product
public class UProductController {
	private static final Logger logger = LoggerFactory.getLogger(UProductController.class);

	@Autowired
	private UProductService uproductService;

	@Resource(name = "uploadpath")
	private String uploadpath;

	@RequestMapping(value = "main", method = RequestMethod.GET)
	public void MainGET() throws Exception {

		logger.info("占쏙옙占쏙옙 占쏙옙占�");

	}

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public void TestGET() throws Exception {

		logger.info("占쏙옙占쏙옙 占쏙옙占�");

	}

	@GetMapping("/map")
	public void TestMAP(Model model, Integer page, Integer numsPerPage, String Address, String keyword,
			ModelMap models) 
			throws Exception {
		logger.info("占쏙옙 占쏙옙占�");

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

		if (Address != null) {

			if (Address.equals("searchAddress")) {
				logger.info("Address");
				list = uproductService.readByAddress(criteria, keyword);
				pageMaker.setTotalCount(uproductService.getTotalCountsByAddress(keyword));
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
		model.addAttribute("Address", Address);
		model.addAttribute("keyword", keyword);

		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);

	}

	@GetMapping("/list")
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

	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET()");
	} // end registerGET()

	@PostMapping("/register")
	public String registerPost(UProductVO vo, @RequestParam("productImage") MultipartFile file,
			RedirectAttributes reAttr) {
		logger.info("registerPOST() 호占쏙옙");
		logger.info(vo.toString());
		logger.info("占쏙옙占쏙옙 占싱몌옙 : " + file.getOriginalFilename());
		logger.info("占쏙옙占쏙옙 크占쏙옙 : " + file.getSize());

		try {
			// 占쏙옙占쏙옙 占쏙옙占쏙옙
			String savedFileName = FileUploadUtil.saveUploadedFile(uploadpath, file.getOriginalFilename(),
					file.getBytes());
			// 占싱뱄옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙
			vo.setuProductImagePath(savedFileName);
			int result = uproductService.create(vo);
			logger.info("result = " + result);
			logger.info(result + "占쏙옙 占쏙옙占쏙옙");

			if (result == 1) {
				reAttr.addFlashAttribute("insert_result", "success");
				return "redirect:/uProduct/list";
			} else {
				return "redirect:/uProduct/register";
			}
		} catch (Exception e) {
			return "redirect:/uProduct/register";
		}

	} // end registerPOST()

	@GetMapping("/detail")
	public void detail(Model model, Integer uProductId, Integer page, HttpServletRequest request) {
		logger.info("detail() 호占쏙옙 : productId = " + uProductId);
		UProductVO vo = uproductService.read(uProductId);
		logger.info("호占쏙옙 : prdocutVO = " + vo);
		
		List<UProductVO> list = uproductService.readrecommend(vo.getuProductCategori(), uProductId);
		
		
		model.addAttribute("list", list);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("memberId");
		
		if(memberId != null) {
			int recentlyViewInsert = uproductService.createRecentlyView(uProductId, memberId);
			logger.info(String.valueOf(recentlyViewInsert));
		}
	} // end detail()

	@GetMapping("/update")
	public void updateGET(Model model, Integer uProductId, Integer page) {
		logger.info("updateGET() 호占쏙옙 : productId = " + uProductId);
		UProductVO vo = uproductService.read(uProductId);
		logger.info("updateGET() 호占쏙옙 : vo = " + vo.toString());
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
	} // end updateGET()

	@PostMapping("/update")
	public String updatePOST(UProductVO vo, Integer page, @RequestParam("productImage") MultipartFile file) {
		logger.info("updatePOST() 호占쏙옙 : vo = " + vo.toString());
		logger.info("占쏙옙占쏙옙 占싱몌옙 : " + file.getOriginalFilename());
		logger.info("占쏙옙占쏙옙 크占쏙옙 : " + file.getSize());
		try {
			if (file != null && !file.isEmpty()) {
				String savedFileName = FileUploadUtil.saveUploadedFile(uploadpath, file.getOriginalFilename(),
						file.getBytes());
				vo.setuProductImagePath(savedFileName);
			}

			int result = uproductService.update(vo);

			if (result == 1) {
				return "redirect:/uProduct/list?page=" + page;
			} else {
				return "redirect:/uProduct/update?uProductId=" + vo.getuProductId();
			}
		} catch (Exception e) {
			return "redirect:/uProduct/update?uProductId=" + vo.getuProductId();
		}

	} // end updatePOST()

	@PostMapping("/delete")
	public String delete(Integer uProductId) {
		logger.info("delete() 호占쏙옙 : uProductId = " + uProductId);
		int result = uproductService.delete(uProductId);

		if (result == 1) {
			return "redirect:/uProduct/list";
		} else {
			return "redirect:/uProduct/list";
		}
	} // end delete()

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
	
	@GetMapping("/uproductbuy")
	public void MainGET(Model model, Integer page, Integer numsPerPage, HttpSession session) throws Exception {
		logger.info("list() 호占쏙옙");
		logger.info("page = " + page + "numsPerPage = " + numsPerPage);

		
		String buyerNickname = (String) session.getAttribute("memberNickname");
		
		// Paging 처占쏙옙
		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}

		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<UProductBuyVO> list = uproductService.readybuyuproduct(criteria, buyerNickname);
		model.addAttribute("list", list);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(uproductService.getTotalCountsbuyuproduct(buyerNickname));
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);

	}
	
	@GetMapping("/uproductsell")
	public void sellGET(Model model, Integer page, Integer numsPerPage, HttpSession session) throws Exception {
		logger.info("list() 호占쏙옙");
		logger.info("page = " + page + "numsPerPage = " + numsPerPage);

		
		String sellerNickname = (String) session.getAttribute("memberNickname");
		
		// Paging 처占쏙옙
		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}

		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<UProductSellVO> list = uproductService.readyselluproduct(criteria, sellerNickname);
		model.addAttribute("list", list);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(uproductService.getTotalCountsselluproduct(sellerNickname));
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);

	}
	
	@GetMapping("/uproductnickname")
	public void reviewboard(Model model, String memberNickname, Integer page, Integer numsPerPage, HttpSession session) throws Exception {
		logger.info("reviewboard() 호출");
		logger.info("page = " + page + "numsPerPage = " + numsPerPage);
		UProductVO vo = uproductService.readnickname(memberNickname);
		
		// Paging 처占쏙옙
		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}

		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}

		List<UProductVO> list = uproductService.readybyuproductnickname(criteria, memberNickname);
		
		
		model.addAttribute("list", list);
		model.addAttribute("vo", vo);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(uproductService.getTotalCountsbyuproductnickname(memberNickname));
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);

	}
	
	

}