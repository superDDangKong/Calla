package project.spring.calla.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.calla.domain.QBoardVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.PageMaker;
import project.spring.calla.service.QBoardService;

@Controller //@Component
// * 표현 계층(Presentation Layer)
// - view(페이지)와 service를 연결하는 역할
// - request에 대한 response를 전달하는 역할
@RequestMapping(value="/qBoard") // url : /ex02/board
public class QBoardController {
	private static final Logger logger = 
			LoggerFactory.getLogger(QBoardController.class);

	@Autowired
	private QBoardService qBoardService;
	
	@GetMapping("/list")
	public void list(Model model, Integer page, Integer numsPerPage) {
		logger.info("list() 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
		}
		if(numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
		
		List<QBoardVO> list = qBoardService.read(criteria);
		model.addAttribute("list", list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(qBoardService.getTotalCounts());
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);
	
	} // end list()
	
	@GetMapping("/register")
	public void registerGET() {
		logger.info("registerGET()");
	} // end registerGET()
	
	@PostMapping("/register")
	public String registerPOST(QBoardVO vo, RedirectAttributes reAttr) {
		// RedirectAttributes
		// - 리다이렉트 시 데이터를 전달하기 위한 인테페이스
		logger.info("registerPOST() 호출");
		logger.info(vo.toString());
		int result = qBoardService.create(vo);
		logger.info(result + "행 삽입"); // 여기서 result 1이 나와야함
		if(result == 1) { 
			reAttr.addFlashAttribute("insert_result", "success");
			return "redirect:/qBoard/list"; // 띄어쓰기 ㅅㅂ
		} else {
			return "redirect:/qBoard/register";
		}
		
	} // end registerPOST
	
	@GetMapping("/detail")
	public void detail(Model model, Integer qBoardId, Integer page) {
		logger.info("detail() 호출 : boardId = " + qBoardId);
		QBoardVO vo = qBoardService.read(qBoardId);
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
	} // end detail
	
	@GetMapping("/update")
	public void updateGET(Model model, Integer qBoardId, Integer page) {
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
	
} // end BoardController
