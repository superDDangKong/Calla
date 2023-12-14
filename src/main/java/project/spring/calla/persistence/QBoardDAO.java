package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.QBoardVO;
import project.spring.calla.util.MyPageCriteria;
import project.spring.calla.util.PageCriteria;

public interface QBoardDAO {
	int insert(QBoardVO vo);
	List<QBoardVO> select();
	QBoardVO select(int qBoardId);
	int update(QBoardVO vo);
	int delete(int qBoardId);
	List<QBoardVO> select(PageCriteria criteria);
	int getTotalCounts(); // 
	
	int updateCommentCnt(int amount, int qBoardId);
	List<QBoardVO> selectAllByMemberNickname(MyPageCriteria criteria, String menberNickname);
	int getTotalCountsByMemberNickname(String memberNickname);
	int updateViews(int views, int qBoardId);
	
	//List<QBoardVO> select(String memberNickname); // �ۼ��� �̸����� �˻�
	
	
	List<QBoardVO> selectByMemberNickname(PageCriteria criteria, String keyword);
	int getTotalCountsLikeMemberNickname(String keyword);
	List<QBoardVO> selectByTitle(PageCriteria criteria, String keyword); // �Խñ� ���� �Ǵ� �������� �˻�
	int getTotalCountsByTitle(String keyword);
	
	
	
	
}
