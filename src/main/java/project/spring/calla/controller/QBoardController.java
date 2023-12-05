package project.spring.calla.controller;

import java.io.FileInputStream;
import java.io.InputStream;
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

import project.spring.calla.domain.QBoardVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.PageMaker;
import project.spring.calla.service.QBoardCommentService;
import project.spring.calla.service.QBoardService;
import project.spring.calla.util.FileUploadUtil;
import project.spring.calla.util.MediaUtil;

@Controller //@Component
// * 표현 계층(Presentation Layer)
// - view(페이지)와 service를 연결하는 역할
// - request에 대한 response를 전달하는 역할
@RequestMapping(value="/qBoard") // url : /ex02/board
public class QBoardController {
	private static final Logger logger = 
			LoggerFactory.getLogger(QBoardController.class);
	
	@Resource(name = "uploadpath")
	private String uploadpath;
	
	@Autowired
	private QBoardService qBoardService;
	
	@Autowired
	private QBoardCommentService qBoardCommentService;
	
	@GetMapping("/note")
	public void noteget() {
		
	}
	
	@GetMapping("/list")
	public void list(Model model, Integer page, Integer numsPerPage, String option, String keyword, RedirectAttributes reAttr) {
		logger.info("list() 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		List<QBoardVO> list = null;
		
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
				logger.info("ifif");
				list = qBoardService.readBymemberNickname(criteria, keyword);
				pageMaker.setTotalCount(qBoardService.getTotalCountsByMeberNickname(keyword));				
			} else if (option.equals("searchTitle")) {
				logger.info("if elseif");
				list = qBoardService.readByTitle(criteria, keyword);
				pageMaker.setTotalCount(qBoardService.getTotalCountsByTitle(keyword));
			} else {
				logger.info("if else");
				list = qBoardService.read(criteria);
				pageMaker.setTotalCount(qBoardService.getTotalCounts());
			}	
		} else {
			logger.info("else");
			list = qBoardService.read(criteria);
			pageMaker.setTotalCount(qBoardService.getTotalCounts());
		}
		logger.info("totalCount = " + pageMaker.getTotalCount());
		model.addAttribute("list", list);
		model.addAttribute("option", option);
		model.addAttribute("keyword", keyword);
		
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);
		reAttr.addFlashAttribute("status_result", "secret");
	} // end list()
	
	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET()");
	} // end registerGET()
	
	@PostMapping("/register")
	public String registerPOST(QBoardVO vo, @RequestParam("customFile") MultipartFile file, RedirectAttributes reAttr) {
		// RedirectAttributes
		// - 리다이렉트 시 데이터를 전달하기 위한 인테페이스
		logger.info("registerPOST() 호출");
		logger.info(vo.toString());
		logger.info("파일 이름 : " + file.getOriginalFilename());
		logger.info("파일 크기 : " + file.getSize());
	
		try {
	    
	      // 파일 저장
	      String savedFileName = FileUploadUtil.saveUploadedFile(uploadpath, file.getOriginalFilename(), file.getBytes());
	      // 이미지 경로 저장
	      vo.setqBoardImagePath(savedFileName);
	      int result = qBoardService.create(vo);
	      logger.info("result = " + result);
	      logger.info(result + "행 삽입"); // 여기서 result 1이 나와야함

	      if (result == 1) {
	      reAttr.addFlashAttribute("insert_result", "success");
	      return "redirect:/qBoard/list"; 
	      } else {
	      return "redirect:/qBoard/register";
	      }

	      } catch (Exception e) {
	        logger.info("register POST 호출");
	        logger.info("catch가 실행");
	        e.printStackTrace();
	      }
	    

	    // 파일이 비어있거나 업로드 및 처리 중 예외 발생 시, 여기로 리다이렉트
	    return "redirect:/qBoard/register";
		
	} // end registerPOST
	

	
	
	@GetMapping("/detail")
	public String detail(Model model, Integer qBoardId, Integer page,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes reAttr) {
		String cookieName = "qBoard_" + qBoardId;
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
			// 쿠키가 존재하지 않는 경우, 조회수 증가 및 쿠키 설정
			int views = 1; // 첫 번째 조회
			Cookie viewCookie = new Cookie(cookieName, String.valueOf(views));
			viewCookie.setMaxAge(60); // 쿠키 유효 시간 1초
			response.addCookie(viewCookie);
			int result = qBoardService.updateViews(views, qBoardId);
			if(result == 1) {
				logger.info("조회수 증가");
			} else {
				logger.info("조회수 증가 실패");
			}	
		}
		logger.info("detail() 호출 : boardId = " + qBoardId);
		QBoardVO vo = qBoardService.read(qBoardId);
		HttpSession session = request.getSession();
		String memberNickname = (String) session.getAttribute("memberNickname");
		Integer memberLevel = (Integer) session.getAttribute("memberLevel");
		logger.info("게시글 공개 여부 : " + vo.getqBoardStatus()); // 게시글의 공개 or 비공개
		
		PageMaker pageMaker = new PageMaker();
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
			model.addAttribute("page", page);
		}
		
		pageMaker.setTotalCount(qBoardCommentService.getTotalCounts(qBoardId));
		
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		model.addAttribute("vo", vo);
		model.addAttribute("pageMaker", pageMaker);
		
		
		
	    if(vo.getqBoardStatus().equals("공개")) { // 선택한 게시글의 공개여부를 가져오고 공개일 때
	    	logger.info("공개게시글임");
	    	logger.info("현재 로그인한 멤버 닉네임 : " + memberNickname);
			logger.info("현재 로그인한 멤버 레벨 : " + memberLevel);
			return "/qBoard/detail";
			
		} else {
			logger.info("비공개게시글임");
			logger.info("작성자 닉네임 : " + vo.getMemberNickname());
			logger.info("현재 로그인한 멤버 닉네임 : " + memberNickname);
			logger.info("현재 로그인한 멤버 레벨 : " + memberLevel);
			if(memberNickname != null) {
				if(vo.getMemberNickname().equals(memberNickname) || memberLevel.equals(2) || memberLevel.equals(3)) {
					logger.info("비공개인데 작성자 닉네임이랑 현재로 로그인한 멤버 닉네임이 같고, 관리자 등급일 때 볼수 있음");
					return "/qBoard/detail";
					
				} else {
					logger.info("비공개인데 로그인을 안한 경우");
					reAttr.addFlashAttribute("status_result", "secret");
					return "redirect:/qBoard/list";
					
				}
				
			}
			logger.info("비공개인데 로그인을 안한 경우");
			reAttr.addFlashAttribute("status_result", "secret");
			return "redirect:/qBoard/list";
			
		}
		
		
	} // end detail
	
	@GetMapping("/update")
	public void updateGET(Model model, Integer qBoardId, String qBoardStatus, Integer page) {
		logger.info("updateGET() 호출 : boardId = " + qBoardId);
		QBoardVO vo = qBoardService.read(qBoardId);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
	} // end updateGET()
	
	@PostMapping("/update")
	public String updatePOST(QBoardVO vo, Integer page) {
		logger.info("updatePOST() 호출 : vo = " + vo.toString());
		int result = qBoardService.update(vo);
		
		if(result == 1) {
			return "redirect:/qBoard/list?page=" + page;
		} else {
			return "redirect:/qBoard/update?boardId=" + vo.getqBoardId();
		}
	} // end updatePOST()
	
	@PostMapping("/delete")
	public String delete(Integer qBoardId) {
		logger.info("delete() 호출 : boardId = " + qBoardId);
		int result = qBoardService.delete(qBoardId);
		if(result == 1) {
			return "redirect:/qBoard/list";
		} else {
			return "redirect:/qBoard/list";
		}
	}
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName) {
		logger.info("display() 호출");
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		
		String filePath = uploadpath + fileName;
		
		try {
			in = new FileInputStream(filePath);
			// 파일 확장자
			String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
			logger.info(extension);
			// 응답 헤더(respponse header)에 Content-Type 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaUtil.getMediaType(extension));
			// 데이터 전송
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), // 파일에서 읽은 데이터
					httpHeaders, // 응답 헤더
					HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return entity;		
	}
	
} // end BoardController
