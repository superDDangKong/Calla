package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.QBoardVO;
import project.spring.calla.pageutil.PageCriteria;

// CRUD(Create, Read, Update, Delete)
public interface QBoardService {
	int create(QBoardVO vo);
	List<QBoardVO> read(PageCriteria criteria);
	QBoardVO read(int boardId);
	int update(QBoardVO vo);
	int delete(int boardId);
	int getTotalCounts();
	int updateViews(int views, int qBoardId);
}
