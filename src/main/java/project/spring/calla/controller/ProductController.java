package project.spring.calla.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.PageMaker;
import project.spring.calla.service.ProductCommentService;
import project.spring.calla.service.ProductService;
import project.spring.calla.util.FileUploadUtil;
import project.spring.calla.util.MediaUtil;

@Controller

@RequestMapping(value="/product") // url : /calla/product
public class ProductController {
	private static final Logger logger =
			LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductCommentService productCommentService;
	
	@Resource(name = "uploadpath")
	private String uploadpath;
	
	@GetMapping("/list")
	public void list(Model model, Integer page, Integer numsPerPage, String option, String keyword) {
		logger.info("list() 호출");
		logger.info("page = " + page + " , numsPerPage = " + numsPerPage);
		List<ProductVO> list = null;
		
		//Paginf 처리
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
		}
		if(numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
		PageMaker pageMaker = new PageMaker();
		if(option != null) {
			if(option.equals("searchMemberNickname")) {
				logger.info("if");
				list = productService.readByProductNameOrProductContent(criteria, keyword);
				pageMaker.setTotalCount(productService.getTotalCountsByProductNameOrProductContent(keyword));	
			} else {
				logger.info("if else");
				list = productService.read(criteria);
				pageMaker.setTotalCount(productService.getTotalCounts());
			}
		}else {
			logger.info("else");
			list = productService.read(criteria);
			pageMaker.setTotalCount(productService.getTotalCounts());
		}
		logger.info("totalCount = " + pageMaker.getTotalCount());
		model.addAttribute("list",list);
		model.addAttribute("option", option);
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
	public String registerPost(ProductVO vo,  @RequestParam("productImage") MultipartFile file, RedirectAttributes reAttr) {
		logger.info("registerPOST() 호출");
		logger.info(vo.toString());		
		logger.info("파일 이름 : " + file.getOriginalFilename());
		logger.info("파일 크기 : " + file.getSize());
		
		try {
			// 파일 저장
			String savedFileName = FileUploadUtil.saveUploadedFile(uploadpath, file.getOriginalFilename(), file.getBytes());
			// 이미지 경로 저장
			vo.setProductImagePath(savedFileName);
			int result = productService.create(vo);
			logger.info(result + "행 삽입");
			
			if(result == 1) {
				reAttr.addFlashAttribute("insert_result", "success");
				return "redirect:/product/list";
			} else {
				return "redirect:/product/register";
			}
		} catch (Exception e) {
			return "redirect:/product/register";
		}
		
		
		
	}  // end registerPOST()
	
	@GetMapping("/detail")
	public String detail(Model model, Integer productId, Integer page, HttpServletRequest request, HttpServletResponse response) {
		String cookieName = "product_" + productId;
		Cookie[] cookies = request.getCookies();
		boolean cookieFound = false;
		
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(cookieName)) {
					cookieFound = true;
					break;
				}
			}
		}
		
		if(!cookieFound) { // 쿠키가 존재하지 않는 경우, 조회수 증감 및 쿠키 설정
			int views = 1; // 첫번째 조회
			Cookie viewCookie = new Cookie(cookieName, String.valueOf(views));
			viewCookie.setMaxAge(180); // 쿠키 유효 시간 3분
			response.addCookie(viewCookie);
			
			int result = productService.updateViews(views, productId);
			if(result == 1) {
				logger.info("조회수 증가");
			} else {
				logger.info("실패");
			}
			
		}
		logger.info("deatil() 호출 : productId = " + productId);
		ProductVO vo = productService.read(productId);
		ProductCommentVO commentVO = new ProductCommentVO();
		PageMaker pageMaker = new PageMaker();
		PageCriteria criteria = new PageCriteria();
		
		pageMaker.setTotalCount(productCommentService.getTotalCounts(productId));
		
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		
		String memberNickname = commentVO.getMemberNickname();
		
		model.addAttribute("memberNickname", memberNickname);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		model.addAttribute("pageMaker", pageMaker);
		
		return "/product/detail";
	} // end detail()

	
	@GetMapping("/update")
	public void updateGET(Model model, Integer productId, Integer page) {
		logger.info("updateGET() 호출 : productId = " + productId);
		ProductVO vo = productService.read(productId);
		logger.info("updateGET() 호출 : vo = " + vo.toString());
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);		
	} // end updateGET()
	
	@PostMapping("/update")
	public String updatePOST(ProductVO vo, Integer page, @RequestParam("productImage") MultipartFile file) {
		logger.info("updatePOST() 호출 : vo = " + vo.toString());			
		logger.info("파일 이름 : " + file.getOriginalFilename());
		logger.info("파일 크기 : " + file.getSize());
		try {	
			if(file != null && !file.isEmpty()) {
				String savedFileName = FileUploadUtil.saveUploadedFile(uploadpath, file.getOriginalFilename(), file.getBytes());
				vo.setProductImagePath(savedFileName);
			}
			
			int result = productService.update(vo);
			
			if(result == 1) {
				return "redirect:/product/list?page=" + page;
			} else {
				return "redirect:product/update?productId=" + vo.getProductId();
			}	
		} catch (Exception e) {
			return "redirect:product/update?productId=" + vo.getProductId();
		}
		
		
	} // end updatePOST()
	
	@PostMapping("/delete")
	public String delete(Integer productId) {
		logger.info("delete() 호출 : productId = " + productId);
		int result = productService.delete(productId);
		
		if(result == 1) {
			return "redirect:/product/list";
		} else {
			return "redirect:/product/list";
		}
	} // end delete()
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName){
		logger.info("display() 호출");
		
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		
		String filePath = uploadpath + fileName;
		
		try {
			in = new FileInputStream(filePath);
			
			// 파일 확장자
			String extension =
					filePath.substring(filePath.lastIndexOf(".") + 1);
			logger.info(extension);
			
			// 응답 헤더(response header)에 Content-Type 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaUtil.getMediaType(extension));
			// 데이터 전송
			entity = new ResponseEntity<byte[]>(
					IOUtils.toByteArray(in), // 파일에서 읽은 데이터
					httpHeaders, // 응답 헤더
					HttpStatus.OK
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	

	
	
	
} 
