package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.MyPageCriteria;
import project.spring.calla.pageutil.PageCriteria;
public interface ActivityDAO {

	List<UProductVO> selectmyuproduct(PageCriteria criteria, String memberNickname);
	int getTotalCountsBymyuproduct(String memberNickname);
	UProductVO select(int uProductId); // 상품 검색
	int insertbuy(UProductBuyVO vo);
	int insertsell(UProductSellVO svo);
	
	List<UProductVO> selectBoards(String memberNickname, String option, MyPageCriteria criteria);
	int getTotalCountsBoard(String memberNickname, String option);
	
	List<UProductCommentVO> selectComments(String memberNickname, String option, MyPageCriteria criteria);
	int getTotalCountsComment(String memberNickname, String option);
	
	List<UProductVO> selectLikes(String memberId, String option, MyPageCriteria criteria);
	int getTotalCountsLike(String memberId, String option);
	
	List<ProductOrderVO> selectOrders(String memberId, MyPageCriteria criteria);
	int getTotalCountsOrders(String memberId);
	
	List<UProductVO> selectProductsByOption(PageCriteria criteria, String keyword, String interest, String option);
	int getTotalCountsProductsByOption(String keyword, String interest, String option);
}
