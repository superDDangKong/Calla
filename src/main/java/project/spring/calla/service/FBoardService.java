package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.pageutil.PageCriteria;

// CRUD(Create, Read, Update, Delete)
public interface FBoardService {
	int create(FBoardVO vo);
	List<FBoardVO> read(PageCriteria criteria);
	FBoardVO read(int fBoardId);
	int update(FBoardVO vo);
	int delete(int fBoardId);
	int getTotalCounts();
	int updateViews(int views, int fBoardId);
}
