package project.spring.calla.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import project.spring.calla.domain.UProductReplyVO;

public interface UProductReplyService {
	int create(UProductReplyVO vo) throws Exception;
	List<UProductReplyVO> read(int uProductCommentId, HttpSession session);
	int update(int uProductReplyId, String uProductReplyContent);
	int delete(int uProductReplyId) throws Exception;

}
