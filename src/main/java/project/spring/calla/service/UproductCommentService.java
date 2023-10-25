package project.spring.calla.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import project.spring.calla.domain.UproductCommentVO;

public interface UproductCommentService {
	int create(UproductCommentVO vo) throws Exception;
	List<UproductCommentVO> read(int uProductId, HttpSession session);
	int update(int uProductCommentId, String uProductCommentContent);
	int delete(int uProductCommentId, int uProductId) throws Exception;

}
