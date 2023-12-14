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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.service.FBoardCommentService;
import project.spring.calla.service.FBoardService;
import project.spring.calla.util.MediaUtil;
import project.spring.calla.util.PageCriteria;
import project.spring.calla.util.PageMaker;

@Controller // @Component
@RequestMapping(value="/fBoard")

public class FBoardController {
	private static final Logger logger = 
			LoggerFactory.getLogger(FBoardController.class);
	
	@Resource(name = "uploadpath")
	private String uploadpath;
	
	@Autowired
	private FBoardService fBoardService; 
	
	@Autowired
	private FBoardCommentService fBoardCommentService;
	
	@GetMapping("/list")
	public void list(Model model, Integer page, Integer numsPerPage, String option, String keyword) {
		
		logger.info("list() 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		List<FBoardVO> list = null;
		
		PageCriteria criteria = new PageCriteria();
		PageMaker pageMaker = new PageMaker();
		if(page != null) {
			criteria.setPage(page);
		}
		
		if(numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
		
		if (option != null) {
			if (option.equals("searchMemberNickname")) {
				list = fBoardService.readByMemberNickname(criteria, keyword);
				pageMaker.setTotalCount(fBoardService.getTotalCountsLikeMemberNickname(keyword));
			} else if (option.equals("searchTitleOrContent")) {
				list = fBoardService.readByTitleOrContent(criteria, keyword);
				pageMaker.setTotalCount(fBoardService.getTotalCountsByTitleContent(keyword));
			} else {
				list = fBoardService.read(criteria);
				pageMaker.setTotalCount(fBoardService.getTotalCounts());
			}
		} else {
			logger.info("else");
			list = fBoardService.read(criteria);
			pageMaker.setTotalCount(fBoardService.getTotalCounts());
		}
		logger.info("totalCount = " + pageMaker.getTotalCount());
		model.addAttribute("list", list);
		model.addAttribute("option", option);
		model.addAttribute("keyword", keyword);
		
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);
	} // end list()
	
	@GetMapping("/register")
	public void registerGET() {} // end registerGET()
	
	@PostMapping("/register")
	public String registerPOST(FBoardVO vo, RedirectAttributes reAttr) {
		
		logger.info("registerPost() 호출 : vo = " + vo.toString());
		
		int result = fBoardService.create(vo);
		logger.info(result + "행 삽입");
		if(result == 1) {
			reAttr.addFlashAttribute("insert_result", "success");
			return "redirect:/fBoard/list";
		} else {
			return "fBoard/register";
		}
	} // end registerPOST()
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName) {
		logger.info("display() 호출");
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		String filePath = uploadpath + fileName;
		try {
			in = new FileInputStream(filePath);
			// 파일 확장자
			String extension =
					filePath.substring(filePath.lastIndexOf(".") + 1);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}
	
	@GetMapping("/detail")
	public String detail(Model model, Integer fBoardId, Integer page, 
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("detail() 호출 : fBoardId = " + fBoardId);
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
	            viewCookie.setMaxAge(60*60*24); // 쿠키 유효 시간 (1일)
	            response.addCookie(viewCookie);
	            
	            int result = fBoardService.updateViews(views, fBoardId);
	            if (result == 1) {
	            	logger.info("조회수 증가");
	            	
	            } else { 
	            	logger.info("조회수 증가 실패");
	            }
		 }
		 
		FBoardVO vo = fBoardService.read(fBoardId);
		logger.info(vo.getfBoardImagePath());
		PageMaker pageMaker = new PageMaker();
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
			model.addAttribute("page", page);
		}
		pageMaker.setTotalCount(fBoardCommentService.getTotalCounts(fBoardId));
		
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		model.addAttribute("vo", vo);
		model.addAttribute("pageMaker", pageMaker);
		
		return "/fBoard/detail";
	} // end detail()
	
	@GetMapping("/update")
	public void updateGET(Model model, Integer fBoardId) {
		logger.info("updateGET() 호출 : fBoardId = " + fBoardId);
		FBoardVO vo = fBoardService.read(fBoardId);
		model.addAttribute("vo", vo);
	} // end updateGET()
	
	@PostMapping("/update")
	public String updatePOST(FBoardVO vo, RedirectAttributes reAttr) {
		logger.info("updatePOST() 호출 : vo = " + vo.toString());
		
		
		int result = fBoardService.update(vo);
		
		if(result == 1) {
			reAttr.addFlashAttribute("update_result", "success");
			return "redirect:/fBoard/list";
		} else {
			return "redirect:/fBoard/update?fBoardId=" + vo.getfBoardId();
		}
	} // end updatePOST()
	
	@PostMapping("/delete")
	public String delete(Integer fBoardId, RedirectAttributes reAttr) {
		logger.info("delete() 호출 : boardId = " + fBoardId);
		int result = fBoardService.delete(fBoardId);
		if(result == 1) {
			reAttr.addFlashAttribute("delete_result", "success");
			return "redirect:/fBoard/list";
		} else {
			return "redirect:/fBoard/list";
		}
	} // end deletePOST()
	
} // end fBoardController
