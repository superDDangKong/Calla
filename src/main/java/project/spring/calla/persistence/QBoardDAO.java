package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.domain.QBoardVO;
import project.spring.calla.pageutil.PageCriteria;

public interface QBoardDAO {
	int insert(QBoardVO vo);
	List<QBoardVO> select();
	QBoardVO select(int qBoardId);
	int update(QBoardVO vo);
	int delete(int qBoardId);
	List<QBoardVO> select(PageCriteria criteria);
	int getTotalCounts(); // 
	
	int updateCommentCnt(int amount, int qBoardId);
	int updateViews(int views, int qBoardId);
	
	List<QBoardVO> selectAllByMemberNickname(String menberNickname);
	List<QBoardVO> select(String memberNickname); // 작성자 이름으로 검색
	
	
	List<QBoardVO> selectByMemberNickname(PageCriteria criteria, String keyword);
	int getTotalCountsByMemberNickname(String keyword);
	List<QBoardVO> selectByTitle(PageCriteria criteria, String keyword); // 게시글 제목 또는 내용으로 검색
	int getTotalCountsByTitle(String keyword);
}
