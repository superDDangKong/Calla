package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.FBoardReplyVO;

// CRUD(Create, Read, Update, Delete)
public interface FBoardReplyService {
	int create(FBoardReplyVO vo) throws Exception;
	List<FBoardReplyVO> read(int fBoardCommentId);
	int update(int fBoardReplyId, String fBoardReplyContent);
	int delete(int fBoardReplyId, int fBoardCommentId) throws Exception;
}
