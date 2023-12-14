package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.FBoardCommentVO;
import project.spring.calla.util.PageCriteria;

// CRUD(Create, Read, Update, Delete)
public interface FBoardCommentService {
	int create(FBoardCommentVO vo) throws Exception;
	List<FBoardCommentVO> read(int fBoardId);
	int update(int fBoardCommentId, String fBoardCommentContent);
	int delete(int fBoardCommentId, int fBoardId) throws Exception;
	List<FBoardCommentVO> read(PageCriteria criteria, int fBoardId);
	int getTotalCounts(int fBoardId);
	
}
