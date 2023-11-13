package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.pageutil.MyPageCriteria;
import project.spring.calla.pageutil.PageCriteria;

public interface FBoardDAO {
	
	int insert(FBoardVO vo);
	List<FBoardVO> select();
	FBoardVO select(int fBoardId);
	int update(FBoardVO vo);
	int delete(int fBoardId);
	List<FBoardVO> select(PageCriteria criteria);
	int getTotalCounts();
	
	List<FBoardVO> selectByMemberNickname(PageCriteria criteria, String keyword);
	int getTotalCountsLikeMemberNickname(String keyword);
	List<FBoardVO> selectByTitleOrContent(PageCriteria criteria, String keyword);
	int getTotalCountsByTitleContent(String keyword);
	
	int updateCommentCount(int amount, int fBoardId);
	int updateViews(int views, int fBoardId);
	
	List<FBoardVO> selectAllByMemberNickname(MyPageCriteria criteria, String menberNickname);
	int getTotalCountsByMemberNickname(String memberNickname);
	
}
