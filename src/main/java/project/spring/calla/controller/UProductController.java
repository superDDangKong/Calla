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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.calla.domain.ProductVO;
import project.spring.calla.domain.UproductVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.PageMaker;
import project.spring.calla.service.ProductService;
import project.spring.calla.service.UproductService;
import project.spring.calla.util.FileUploadUtil;
import project.spring.calla.util.MediaUtil;

@Controller

@RequestMapping(value="/uProduct") // url : /calla/product
public class UProductController {
	private static final Logger logger =
			LoggerFactory.getLogger(UProductController.class);
	
	@Autowired
	private UproductService uproductService;
	
	@Resource(name = "uploadpath")
	private String uploadpath;
	
	@RequestMapping(value="main", method = RequestMethod.GET)
	public void MainGET() throws Exception{
		
		logger.info("메인 출력");
		
	}
	
	@RequestMapping(value="test", method = RequestMethod.GET)
	public void TestGET() throws Exception{
		
		logger.info("메인 출력");
		
	}
	
	@GetMapping("/list")
	public void list(Model model, Integer page, Integer numsPerPage) {
		logger.info("list() 호출");
		logger.info("page = " + page + " , numsPerPage = " + numsPerPage);
		
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
		}
		if(numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
		
		List<UproductVO> list = uproductService.read(criteria);
		model.addAttribute("list",list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(uproductService.getTotalCounts());
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);
	} // end list()
	
	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET()");
	} // end registerGET()
	
	@PostMapping("/register")
	public String registerPost(UproductVO vo,  @RequestParam("productImage") MultipartFile file, RedirectAttributes reAttr) {
		logger.info("registerPOST() 호출");
		logger.info(vo.toString());		
		logger.info("파일 이름 : " + file.getOriginalFilename());
		logger.info("파일 크기 : " + file.getSize());
		
		try {
			// 파일 저장
			String savedFileName = FileUploadUtil.saveUploadedFile(uploadpath, file.getOriginalFilename(), file.getBytes());
			// 이미지 경로 저장
			vo.setuProductImagePath(savedFileName);
			int result = uproductService.create(vo);
			logger.info("result = " + result);
			logger.info(result + "행 삽입");
			
			
			if(result == 1) {
				reAttr.addFlashAttribute("insert_result", "success");
				return "redirect:/uProduct/list";
			} else {
				return "redirect:/uProduct/register";
			}
		} catch (Exception e) {
			return "redirect:/uProduct/register";
		}
		
		
		
	}  // end registerPOST()
	
	@GetMapping("/detail")
	public void detail(Model model, Integer uProductId, Integer page) {
		logger.info("detail() 호출 : productId = " + uProductId);
		UproductVO vo = uproductService.read(uProductId);
		logger.info("호출 : prdocutVO = " + vo);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
	} // end detail()
	
	@GetMapping("/update")
	public void updateGET(Model model, Integer uProductId, Integer page) {
		logger.info("updateGET() 호출 : productId = " + uProductId);
		UproductVO vo = uproductService.read(uProductId);
		logger.info("updateGET() 호출 : vo = " + vo.toString());
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);		
	} // end updateGET()
	
	@PostMapping("/update")
	public String updatePOST(UproductVO vo, Integer page, @RequestParam("productImage") MultipartFile file) {
		logger.info("updatePOST() 호출 : vo = " + vo.toString());			
		logger.info("파일 이름 : " + file.getOriginalFilename());
		logger.info("파일 크기 : " + file.getSize());
		try {	
			if(file != null && !file.isEmpty()) {
				String savedFileName = FileUploadUtil.saveUploadedFile(uploadpath, file.getOriginalFilename(), file.getBytes());
				vo.setuProductImagePath(savedFileName);
			}
			
			int result = uproductService.update(vo);
			
			if(result == 1) {
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
		logger.info("delete() 호출 : uProductId = " + uProductId);
		int result = uproductService.delete(uProductId);
		
		if(result == 1) {
			return "redirect:/uProduct/list";
		} else {
			return "redirect:/uProduct/list";
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