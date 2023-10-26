package project.spring.calla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // @Component
// * 표현 계층(Presentation Layer)
// - view(페이지)와 service를 연결하는 역할
// - request에 대한 response를 전달하는 역할
 // url : /ex02/board
public class FBoardCommentController {
	@GetMapping("/detail")
	public void comment() {
		
	}
	
} // end BoardController
