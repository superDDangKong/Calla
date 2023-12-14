package project.spring.calla.service;

import java.util.List;
import java.util.Map;

import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.util.MyPageCriteria;
import project.spring.calla.util.PageCriteria;

public interface ActivityService {

	// controller
	List<UProductVO> readmyuproduct(PageCriteria criteria, String memberNickname); 
	int getTotalCountsBymyuproduct(String memberNickname);
	UProductVO read(int uProductId); 
	int buysellcreate(UProductBuyVO vo, UProductSellVO svo);
	
	List<UProductVO> readProductsByOption(PageCriteria criteria, String keyword, String interest, String option);
	int getTotalCountsProductsByOption(String keyword, String interest, String option);
	
	// restcontroller
	Map<String, Integer> getTotalCountsByMemberNickname(String memberNickname);
	Map<String, Object> readLikes(String memberId);
	
	Map<String, Object> readRecentlyView(MyPageCriteria criteria, String memberId);
	Map<String, Integer> getTotalCountsByRecentlyView(String memberId);
	
	int deleteRecentlyViewProduct(int productRecentlyViewId);
	int deleteRecentlyViewUProduct(int uProductRecentlyViewId);

	
	int deleteProductLike(int productLikeId, int amount, int productId);
	int deleteUProductLike(int uProductLikeId, int amount, int uProductId);
	
	List<UProductVO> readBoards(String memberNickname, String option, MyPageCriteria criteria);
	int getTotalCountsBoard(String memberNickname, String option);
	
	List<UProductCommentVO> readComments(String memberNickname, String option, MyPageCriteria criteria);
	int getTotalCountsComment(String memberNickname, String option);
	
	List<UProductVO> readLikes(String memberId, String option, MyPageCriteria criteria);
	int getTotalCountsLike(String memberId, String option);
	
	List<ProductOrderVO> readOrders(String memberId, MyPageCriteria criteria);
	int getTotalCountsOrders(String memberId);
	

}
