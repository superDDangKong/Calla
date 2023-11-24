package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.QBoardVO;
import project.spring.calla.pageutil.PageCriteria;

// CRUD(Create, Read, Update, Delete)
public interface QBoardService {
	int create(QBoardVO vo);
	List<QBoardVO> read(PageCriteria criteria);
	QBoardVO read(int qBoardId);
	int update(QBoardVO vo);
	int delete(int qBoardId);
	int getTotalCounts();
	int updateViews(int views, int qBoardId);
	List<QBoardVO> readBymemberNickname(PageCriteria criteria, String keyword);
	int getTotalCountsByMeberNickname(String keyword);
	List<QBoardVO> readByTitle(PageCriteria criteria, String keyword);
	int getTotalCountsByTitle(String keyword);
}
