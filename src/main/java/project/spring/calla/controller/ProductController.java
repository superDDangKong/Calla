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
import project.spring.calla.domain.ProductLikeVO;
import project.spring.calla.domain.ProductOrderListVO;
import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.PageMaker;
import project.spring.calla.service.ProductCommentService;
import project.spring.calla.service.ProductLikeService;
import project.spring.calla.service.ProductOrderListService;
import project.spring.calla.service.ProductOrderService;
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
	
	@Autowired
	private ProductLikeService productLikeService;
	
	@Autowired
	private ProductOrderListService productOrderListService;
	
	@Autowired
	private ProductOrderService productOrderService;
	
	@Resource(name = "uploadpath")
	private String uploadpath;
	
	@GetMapping("/list")
	public void list(Model model, Integer page, Integer numsPerPage, String option, String keyword) {
		logger.info("list() 호占쏙옙");
		logger.info("page = " + page + " , numsPerPage = " + numsPerPage);
		
		// 상품 list 초기화
		List<ProductVO> list = null;
		
		// Paging 처리
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
		}
		if(numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
		
		// pagerMaker 초기화
		PageMaker pageMaker = new PageMaker();
		
		// 상품 조회
		if(option != null) {
			if(option.equals("searchTitleOrContent")) {
				logger.info("if문 실행");
				list = productService.readByProductNameOrProductContent(criteria, keyword);
				pageMaker.setTotalCount(productService.getTotalCountsByProductNameOrProductContent(keyword));
				
				
			} else {
				logger.info("if else문 실행");
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
		logger.info("registerPOST() 호占쏙옙");
		logger.info(vo.toString());		
		logger.info("占쏙옙占쏙옙 占싱몌옙 : " + file.getOriginalFilename());
		logger.info("占쏙옙占쏙옙 크占쏙옙 : " + file.getSize());
		
		try {
			// 占쏙옙占쏙옙 占쏙옙占쏙옙
			String savedFileName = FileUploadUtil.saveUploadedFile(uploadpath, file.getOriginalFilename(), file.getBytes());
			// 占싱뱄옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙
			vo.setProductImagePath(savedFileName);
			int result = productService.create(vo);
			logger.info(result + "占쏙옙 占쏙옙占쏙옙");
			
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
	public String detail(Model model, Integer productId, String memberId, Integer page, HttpServletRequest request, HttpServletResponse response) {
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
		
		if(!cookieFound) { // 占쏙옙키占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占십댐옙 占쏙옙占�, 占쏙옙회占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙키 占쏙옙占쏙옙
			int views = 1; // 첫占쏙옙째 占쏙옙회
			Cookie viewCookie = new Cookie(cookieName, String.valueOf(views));
			viewCookie.setMaxAge(180); // 占쏙옙키 占쏙옙효 占시곤옙 3占쏙옙
			response.addCookie(viewCookie);
			
			int result = productService.updateViews(views, productId);
			if(result == 1) {
				logger.info("占쏙옙회占쏙옙 占쏙옙占쏙옙");
			} else {
				logger.info("占쏙옙占쏙옙");
			}
			
		}
		logger.info("deatil() 호占쏙옙 : productId = " + productId);
		ProductVO vo = productService.read(productId);
		ProductCommentVO commentVO = new ProductCommentVO();
		PageMaker pageMaker = new PageMaker();
		PageCriteria criteria = new PageCriteria();
		
		pageMaker.setTotalCount(productCommentService.getTotalCounts(productId));
		
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		
		String memberNickname = commentVO.getMemberNickname();
		
		Integer productLikeId = 0;
		ProductLikeVO productLikeVO = productLikeService.read(productId, memberId);
			if (productLikeVO != null) {
				productLikeId = productLikeVO.getProductLikeId();
			} 
			
		Integer productOrderListId = 0;
		ProductOrderListVO productOrderListVO = productOrderListService.read(productId, memberId);
			if (productOrderListVO != null) {
				productOrderListId = productOrderListVO.getProductOrderListId();
			} 		
			
		if(!memberId.isEmpty()) {
			int recentlyViewInsert = productService.createRecentlyView(productId, memberId);
			logger.info(String.valueOf(recentlyViewInsert));
		}
		
		logger.info("productLikeId = "+ productLikeId + ", productOrderListid = " + productOrderListId);
		model.addAttribute("productOrderListId", productOrderListId);
		model.addAttribute("productLikeId", productLikeId);
		model.addAttribute("memberId", memberId);
		model.addAttribute("memberNickname", memberNickname);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		model.addAttribute("pageMaker", pageMaker);
		
		
		return "/product/detail";
	} // end detail()

	
	@GetMapping("/update")
	public void updateGET(Model model, Integer productId, Integer page) {
		logger.info("updateGET() 호占쏙옙 : productId = " + productId);
		ProductVO vo = productService.read(productId);
		logger.info("updateGET() 호占쏙옙 : vo = " + vo.toString());
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);		
	} // end updateGET()
	
	@PostMapping("/update")
	public String updatePOST(ProductVO vo, Integer page, @RequestParam("productImage") MultipartFile file) {
		logger.info("updatePOST() 호占쏙옙 : vo = " + vo.toString());			
		logger.info("占쏙옙占쏙옙 占싱몌옙 : " + file.getOriginalFilename());
		logger.info("占쏙옙占쏙옙 크占쏙옙 : " + file.getSize());
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
		logger.info("delete() 호占쏙옙 : productId = " + productId);
		int result = productService.delete(productId);
		
		if(result == 1) {
			return "redirect:/product/list";
		} else {
			return "redirect:/product/list";
		}
	} // end delete()
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName){
		logger.info("display() 호占쏙옙");
		
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		
		String filePath = uploadpath + fileName;
		
		try {
			in = new FileInputStream(filePath);
			
			// 占쏙옙占쏙옙 확占쏙옙占쏙옙
			String extension =
					filePath.substring(filePath.lastIndexOf(".") + 1);
			logger.info(extension);
			
			// 占쏙옙占쏙옙 占쏙옙占�(response header)占쏙옙 Content-Type 占쏙옙占쏙옙
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaUtil.getMediaType(extension));
			// 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
			entity = new ResponseEntity<byte[]>(
					IOUtils.toByteArray(in), // 占쏙옙占싹울옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙
					httpHeaders, // 占쏙옙占쏙옙 占쏙옙占�
					HttpStatus.OK
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	@GetMapping("/orderList")
	public String orderList(Model model, String memberId) {
		logger.info("orderList() 호占쏙옙 : memberId = " + memberId);
	    
	    List<ProductVO> productList = productService.selectProductWithAmount(memberId);

	    double totalSum = 0;
	    for (ProductVO vo : productList) {
	        totalSum += vo.getProductPrice() * vo.getProductAmount();
	    }
	    
	    model.addAttribute("totalSum", totalSum);
	    model.addAttribute("memberId", memberId);
	    model.addAttribute("productList", productList);
	    
		
	    return "/product/orderList";
	}
	
	@GetMapping("/order")
	public String order(Model model, String memberId) {
	    logger.info("order() 호출 : memberId = " + memberId);
	    
	    List<ProductOrderVO> productOrderList = productOrderService.read(memberId);
	    

	    model.addAttribute("memberId", memberId);
	    model.addAttribute("productOrderList", productOrderList);

	    return "/product/order";
	}
} 
