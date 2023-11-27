package project.spring.calla.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.spring.calla.domain.AlarmVO;
import project.spring.calla.service.AlarmService;

@RestController
@RequestMapping(value="/alarm")
public class AlarmRESTController {

	private static final Logger logger 
				= LoggerFactory.getLogger(AlarmRESTController.class);

	@Autowired
	private AlarmService alarmService;
	
	@GetMapping("all/{memberNickname}")
	public ResponseEntity<List<AlarmVO>> readAlarm(@PathVariable String memberNickname) {
		logger.info("readAlarm 호출 memberNicknmae = " + memberNickname);
		List<AlarmVO> lists = alarmService.read(memberNickname);
		logger.info(lists.toString());
		return new ResponseEntity<List<AlarmVO>>(lists, HttpStatus.OK);
	} // end readAlarm
	
	@GetMapping("check/{memberNickname}")
	public ResponseEntity<Integer> checkAlarm(@PathVariable String memberNickname) {
		logger.info("readAlarm 호출 memberNicknmae = " + memberNickname);
		int result = 0;
		result = alarmService.check(memberNickname);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	} // end checkAlarm
	
	@GetMapping("findPage")
	public ResponseEntity<Integer> findPage(@RequestParam("alarmCode") String alarmCode,
		    @RequestParam("alarmPrefix") String alarmPrefix,
		    @RequestParam("boardId") int boardId,
		    @RequestParam("commentId") int commentId) {
		    
		    logger.info("findPage 호출: alarmCode={}, alarmPrefix={}, boardId={}, commentId={}", 
		        alarmCode, alarmPrefix, boardId, commentId);

		    AlarmVO vo = new AlarmVO();
		    vo.setAlarmCode(alarmCode);
		    vo.setAlarmPrefix(alarmPrefix);
		    vo.setBoardId(boardId);
		    vo.setCommentId(commentId);

		    int page = alarmService.findPage(vo);
		    return new ResponseEntity<>(page, HttpStatus.OK);
	} // end findPage
	
	@PutMapping("/{alarmId}") // PUT : 댓글 수정
	public ResponseEntity<Integer> updateCheck(@PathVariable int alarmId) {
		logger.info("upadateCheck 호출 alarmId = " + alarmId);
		int result = alarmService.updateCheck(alarmId);
		logger.info("result = " + result);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{alarmId}") // PUT : 댓글 수정
	public ResponseEntity<Integer> deleteAlarm(@PathVariable int alarmId) {
		logger.info("deleteAlarm 호출 alarmId = " + alarmId);
		int result = alarmService.delete(alarmId);
		logger.info("result = " + result);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}
