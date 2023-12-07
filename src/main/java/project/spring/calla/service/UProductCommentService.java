package project.spring.calla.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import project.spring.calla.domain.UProductCommentVO;

public interface UProductCommentService {
	int create(UProductCommentVO vo) throws Exception;
	List<UProductCommentVO> read(int uProductId, HttpSession session);
	UProductCommentVO selectvo(int uProductId);
	int update(int uProductCommentId, String uProductCommentContent);
	int delete(int uProductCommentId, int uProductId) throws Exception;
	List<UProductCommentVO> read(int uProductId);

}
