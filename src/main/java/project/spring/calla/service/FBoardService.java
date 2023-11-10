package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.pageutil.PageCriteria;

// CRUD(Create, Read, Update, Delete)
public interface FBoardService {
	int create(FBoardVO vo);
	List<FBoardVO> read(PageCriteria criteria);
	int getTotalCounts();
	
	List<FBoardVO> readByMemberNickname(PageCriteria criteria, String keyword);
	int getTotalCountsLikeMemberNickname(String keyword);
	List<FBoardVO> readByTitleOrContent(PageCriteria criteria, String keyword);
	int getTotalCountsByTitleContent(String keyword);
	
	FBoardVO read(int fBoardId);
	int update(FBoardVO vo);
	int delete(int fBoardId);
	int updateViews(int views, int fBoardId);
	
}
