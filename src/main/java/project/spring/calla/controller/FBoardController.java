package project.spring.calla.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.PageMaker;
import project.spring.calla.service.FBoardService;

@Controller // @Component
// * 표현 계층(Presentation Layer)
// - view(페이지)와 service를 연결하는 역할
// - request에 대한 response를 전달하는 역할
@RequestMapping(value="/fBoard") // url : /ex02/board
public class FBoardController {
	private static final Logger logger = 
			LoggerFactory.getLogger(FBoardController.class);
	
	@Autowired
	private FBoardService fBoardService; 
	
	@GetMapping("/list")
	public void list(Model model, Integer page, Integer numsPerPage) {
		
		logger.info("list() 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		
		// Paging 처리
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
		}
		
		if(numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
		
		List<FBoardVO> list = fBoardService.read(criteria);
		model.addAttribute("list", list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(fBoardService.getTotalCounts());
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);
	} // end list()
	
	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET()");
	} // end registerGET()
	
	@PostMapping("/register")
	public String registerPOST(FBoardVO vo, RedirectAttributes reAttr) {
		// RedirectAttributes
		// - 리다이렉트 시 데이터를 전달하기 위한 인터페이스
		logger.info("registerPOST() 호출");
		logger.info(vo.toString());
		int result = fBoardService.create(vo);
		logger.info(result + "행 삽입");
		if(result == 1) {
			reAttr.addFlashAttribute("insert_result", "success");
			return "redirect:/fBoard/list";
			// redirect는 request 정보가 없어짐...
		} else {
			return "redirect:/fBoard/register";
		}
	} // end registerPOST()
	
	@GetMapping("/detail")
	public String detail(Model model, Integer fBoardId, Integer page, 
			HttpServletRequest request, HttpServletResponse response) {

		String cookieName = "fBoard_" + fBoardId;
		
		Cookie[] cookies = request.getCookies();
		boolean cookieFound = false;
		
		if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    cookieFound = true;
                    break;
                }
            }
        }
		 if (!cookieFound) {
	            // 쿠키가 존재하지 않는 경우, 조회수 증가 및 쿠키 설정
	            int views = 1; // 첫 번째 조회
	            Cookie viewCookie = new Cookie(cookieName, String.valueOf(views));
	            viewCookie.setMaxAge(60); // 쿠키 유효 시간 (1분)
	            response.addCookie(viewCookie);
	            
	            int result = fBoardService.updateViews(views, fBoardId);
	            if (result == 1) {
	            	logger.info("조회수 증가");
	            	
	            } else { 
	            	logger.info("조회수 증가 실패");
	            }
		 }
		 
		logger.info("detail() 호출 : fBoardId = " + fBoardId);
		FBoardVO vo = fBoardService.read(fBoardId);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		
		return "/fBoard/detail";
	} // end detail()
	
	@GetMapping("/update")
	public void updateGET(Model model, Integer fBoardId, Integer page) {
		logger.info("updateGET() 호출 : fBoardId = " + fBoardId);
		FBoardVO vo = fBoardService.read(fBoardId);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
	} // end updateGET()
	
	@PostMapping("/update")
	public String updatePOST(FBoardVO vo, Integer page) {
		logger.info("updatePOST() 호출 : vo = " + vo.toString());
		int result = fBoardService.update(vo);
		
		if(result == 1) {
			return "redirect:/fBoard/list?page=" + page;
		} else {
			return "redirect:/fBoard/update?fBoardId=" + vo.getfBoardId();
		}
	} // end updatePOST()
	
	@PostMapping("/delete")
	public String delete(Integer fBoardId) {
		logger.info("delete() 호출 : boardId = " + fBoardId);
		int result = fBoardService.delete(fBoardId);
		if(result == 1) {
			return "redirect:/fBoard/list";
		} else {
			return "redirect:/fBoard/list";
		}
	} // end deletePOST()
} // end BoardController
