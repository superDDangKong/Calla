package project.spring.calla.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
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
import project.spring.calla.service.ProductCommentService;
import project.spring.calla.service.ProductLikeService;
import project.spring.calla.service.ProductOrderListService;
import project.spring.calla.service.ProductOrderService;
import project.spring.calla.service.ProductService;
import project.spring.calla.util.FileUploadUtil;
import project.spring.calla.util.MediaUtil;
import project.spring.calla.util.PageCriteria;
import project.spring.calla.util.PageMaker;

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
		logger.info("list() �샇異�");
		logger.info("page = " + page + " , numsPerPage = " + numsPerPage);
		
		List<ProductVO> list = null;
		
		// Paging 泥섎━
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
		}
		if(numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
		
		// pagerMaker 泥섎━
		PageMaker pageMaker = new PageMaker();
		
		if(option != null) {
			if(option.equals("searchTitleOrContent")) {
				logger.info("sarchTitleOrContent() �샇異�");
				list = productService.readByProductNameOrProductContent(criteria, keyword);
				pageMaker.setTotalCount(productService.getTotalCountsByProductNameOrProductContent(keyword));
				
			} else if (option.equals("searchProductCategori")) {
				logger.info("searchProductCategori() �샇異�");
				list = productService.readByProductCategori(criteria, keyword);
				pageMaker.setTotalCount(productService.getTotalCountsByProductCategori(keyword));
				
			}else {
				logger.info("if else臾�");
				list = productService.read(criteria);
				pageMaker.setTotalCount(productService.getTotalCounts());
				
			}
		}else {
			logger.info("else");
			list = productService.read(criteria);
			pageMaker.setTotalCount(productService.getTotalCounts());
			
		}
		logger.info("totalCount = " + pageMaker.getTotalCount());
		
		logger.info("list = " + list);
		for(ProductVO productVo : list) {
			String productImagePath = productVo.getProductImagePath();
			String[] imagePaths = productImagePath.split(","); 

			String firstImagePath = imagePaths[0];
			
			productVo.setProductImagePath(firstImagePath);
		}
		
		
		model.addAttribute("list",list);
		model.addAttribute("option", option);
		model.addAttribute("keyword", keyword);
		
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);
	} // end list()
	
	@GetMapping("/register")
	public String registerGET(HttpSession session) {
		Integer memberLevel = (Integer) session.getAttribute("memberLevel");
		logger.info("memberLevel" + memberLevel);
		if(memberLevel == null || memberLevel < 2) {
			return "redirect:/product/list";
		} 
		logger.info("registerGET()");
		return "product/register";
	} // end registerGET()
	
	@PostMapping("/register")
	public String registerPost(ProductVO vo, @RequestParam("productImages") MultipartFile[] files, RedirectAttributes reAttr) {
	    logger.info("registerPOST() �샇異�");
	    logger.info(vo.toString());
	    String fileString ="";
	    try {
	        List<String> savedFileNames = new ArrayList<>();

	        for (MultipartFile file : files) {
	            logger.info("�뙆�씪 �씠由� : " + file.getOriginalFilename());
	            logger.info("�뙆�씪 �겕湲� : " + file.getSize());

	            String savedFileName = FileUploadUtil.saveUploadedFile(uploadpath, file.getOriginalFilename(), file.getBytes());
	            fileString += savedFileName + ",";
	            logger.info("�뙆�씪" + savedFileNames);
	        }

	        vo.setProductImagePath(fileString);
	        int result = productService.create(vo);
	        logger.info(result + "�벑濡� �셿猷�");

	        if (result == 1) {
	            reAttr.addFlashAttribute("insert_result", "success");
	            return "redirect:/product/list";
	        } else {
	            return "redirect:/product/register";
	        }
	    } catch (Exception e) {
	        return "redirect:/product/register";
	    }
	} // end registerPOST()

	
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
		
		if(!cookieFound) { 
			int views = 1; 
			Cookie viewCookie = new Cookie(cookieName, String.valueOf(views));
			viewCookie.setMaxAge(180); 
			response.addCookie(viewCookie);
			
			int result = productService.updateViews(views, productId);
			if(result == 1) {
				logger.info("result == 1");
			} else {
				logger.info("result != 1");
			}
			
		}
		logger.info("deatil() �샇異� : productId = " + productId);
		ProductVO vo = productService.read(productId);
		
		String[] imageArray = vo.getProductImagePath().split(",");
		ProductCommentVO commentVO = new ProductCommentVO();
		PageMaker pageMaker = new PageMaker();
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
			model.addAttribute("page", page);
		}
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
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("imageArray", imageArray);
		
		
		return "/product/detail";
	} // end detail()

	
	@GetMapping("/update")
	public String updateGET(Model model, Integer productId, Integer page, RedirectAttributes reAttr, HttpSession session) {
		logger.info("updateGET() �샇異� : productId = " + productId);
		ProductVO vo = productService.read(productId);
		logger.info("updateGET() �샇異� : vo = " + vo.toString());
		
		Integer memberLevel = (Integer) session.getAttribute("memberLevel");
		logger.info("memberLevel" + memberLevel);
		if(memberLevel == null || memberLevel < 2) {
			return "redirect:/product/list";
		} 
		
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		
		return "/product/update";		
	} // end updateGET()
	
	@PostMapping("/update")
	public String updatePost(ProductVO vo, @RequestParam("productImages") MultipartFile[] files, Integer page, RedirectAttributes reAttr, HttpSession session) {
	    logger.info("updatePost() �샇異� : vo = " + vo.toString());
	    
	    try {
	        StringBuilder fileString = new StringBuilder();

	        if (files != null && files.length > 0) {
	            for (MultipartFile file : files) {
	                logger.info("�뙆�씪 �씠由� : " + file.getOriginalFilename());
	                logger.info("�뙆�씪 �겕湲� : " + file.getSize());

	                String savedFileName = FileUploadUtil.saveUploadedFile(uploadpath, file.getOriginalFilename(), file.getBytes());

	                if (fileString.length() > 0) {
	                    fileString.append(",");
	                }
	                fileString.append(savedFileName);
	            }
	        }

	        vo.setProductImagePath(fileString.toString());

	        productService.update(vo);

	        return "redirect:/product/list?page=" + page;
	    } catch (Exception e) {
	        return "redirect:/product/update?productId=" + vo.getProductId();
	    }
	} // end updatePost();


	
	@PostMapping("/delete")
	public String delete(Integer productId) {
		logger.info("delete() �샇異� : productId = " + productId);
		int result = productService.delete(productId);
		
		if(result == 1) {
			return "redirect:/product/list";
		} else {
			return "redirect:/product/list";
		}
	} // end delete()
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName){
		logger.info("display() �샇異�");
		
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		
		String filePath = uploadpath + fileName;
		
		try {
			in = new FileInputStream(filePath);
			
			String extension =
					filePath.substring(filePath.lastIndexOf(".") + 1);
			logger.info(extension);
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaUtil.getMediaType(extension));
			entity = new ResponseEntity<byte[]>(
					IOUtils.toByteArray(in), 
					httpHeaders, 
					HttpStatus.OK
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	@GetMapping("/orderList")
	public String orderList(Model model, String memberId) {
		logger.info("orderList() �샇異� : memberId = " + memberId);
	    
	    List<ProductVO> productList = productService.selectProductWithAmount(memberId);

	    double totalSum = 0;
	    for (ProductVO vo : productList) {
	        totalSum += vo.getProductPrice() * vo.getProductAmount();
	        
	        String[] imagePath = vo.getProductImagePath().split(",");
	        vo.setProductImagePath(imagePath[0]);
	    }
	    
	    model.addAttribute("totalSum", totalSum);
	    model.addAttribute("memberId", memberId);
	    model.addAttribute("productList", productList);
	    
	    return "/product/orderList";
	}
	
	@GetMapping("/order")
	public String order(Model model, HttpServletRequest request) {
	    logger.info("order() �샇異� : memberId = " );
	    HttpSession session = request.getSession();
	    int memberLevel = (int) session.getAttribute("memberLevel");
	    String memberId = (String) session.getAttribute("memberId");
	    List<ProductOrderVO> productOrderList = null;
	    if(memberLevel >=2) { // memberLevel 2 �씠�긽�씪�븣
	    	productOrderList = productOrderService.read();
	    	
	    } else { 
	    	productOrderList = productOrderService.read(memberId);
	    	
	    }
	    
	    model.addAttribute("productOrderList", productOrderList);

	    return "/product/order";
	}
} 
