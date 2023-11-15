package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.domain.UProductReviewVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface UProductReviewDAO {
	
	int insert(UProductReviewVO vo);

	List<UProductReviewVO> select(PageCriteria criteria);
	int getTotalCount();
	
	List<UProductReviewVO> selectbySellnickname(PageCriteria criteria, String sellerNickname);
	int getTotalCountbySellnickname(String sellerNickname);
	
	UProductReviewVO select(String sellerNickname); // ��ǰ �˻�
	
	UProductVO selectnickname(String sellerNickname); // ��ǰ �˻�
	
	MemberVO selectmemberManner(String memberNickname);
	
	float updatememberManner(String memberNickname);
	
}
