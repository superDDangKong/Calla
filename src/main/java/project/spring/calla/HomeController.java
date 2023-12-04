package project.spring.calla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.service.MemberService;
import project.spring.calla.service.ProductService;
import project.spring.calla.service.UProductService;
import project.spring.calla.util.SessionManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	MemberService memberService;
	
	@Autowired
	private SessionManager sessionManager;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UProductService uProductService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		logger.info("home controller home »£√‚");
		Map<String, HttpSession> loginSessions = sessionManager.getLoginSessions();
		logger.info("loginPOST() " + loginSessions.toString());
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			String memberId = (String) session.getAttribute("memberId");
			
			Map<String, Object> lists = new HashMap();
			if(memberId != null) {
				MemberVO vo = memberService.read(memberId);
				String[] interests = vo.getMemberInterest().split(" ");
				int interestsLength = interests.length;
				List<ProductVO> productList = new ArrayList<ProductVO>();
				List<UProductVO> uProductList = new ArrayList<UProductVO>();;
				
				for(int i=0; i<interestsLength; i++) {
					String interest = interests[i];
					
					List<ProductVO> productListByInterest = productService.readByInterest(interest);
					productList.addAll(productListByInterest);
					
					List<UProductVO> uProductListByInterest = uProductService.readByInterest(interest);
					uProductList.addAll(uProductListByInterest);
				}
	
				lists.put("productList", productList);
				lists.put("uProductList", uProductList);
				
			} else {
				List<ProductVO> productList = productService.read();
				List<UProductVO> uProductList = uProductService.read();
				lists.put("productList", productList);
				lists.put("uProductList", uProductList);
			}
			model.addAttribute("lists", lists);
		}
		return "home";
	}
}
