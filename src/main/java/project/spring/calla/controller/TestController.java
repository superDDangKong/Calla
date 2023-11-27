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


	@RequestMapping(value = "index", method = RequestMethod.GET)
	public void MainGET() throws Exception {

		logger.info("占쏙옙占쏙옙 占쏙옙占�");

	}
	
	

}