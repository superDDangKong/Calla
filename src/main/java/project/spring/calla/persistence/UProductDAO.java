package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.domain.QBoardVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.MyPageCriteria;

public interface UProductDAO {
	int insert(UProductVO vo); // 占쏙옙품 占쏙옙占�
	List<UProductVO> select(); // 占쏙옙품 占쏙옙체 占싯삼옙
	UProductVO select(int uProductId); // 占쏙옙품 占싯삼옙	
	int update(UProductVO vo); // 占쏙옙품 占쏙옙占쏙옙
	int delete(int uProductId); // 占쏙옙품 占쏙옙占쏙옙
	List<UProductVO> select(PageCriteria criteria);
	int getTotalCount();
	
	List<UProductVO> select(String uProductName);
	List<UProductVO> selectByName(String keyword);
	
	int updateUproductCommentCount(int amount, int uProductId);
	
	List<UProductVO> selectAllByMemberNickname(MyPageCriteria criteria, String menberNickname);
	int getTotalCountsByMemberNickname(String memberNickname);
	
	List<UProductVO> selectByCategoriorName(PageCriteria criteria, String keyword); // 占쏙옙품 占싱몌옙占실댐옙 카占쌓곤옙占쏙옙 占싯삼옙
	int getTotalCountsByCategoriorName(String keyword);
	
	List<UProductVO> selectByUproductCreatedDate(PageCriteria criteria); // 占신삼옙품 占싯삼옙
	int getTotalCountsByUproductCreatedDate();
	
	List<UProductVO> selectByAddress(PageCriteria criteria, String keyword); // 占쏙옙품 占쌍쇽옙 占싯삼옙
	int getTotalCountsByAddress(String keyword);
	
	List<UProductVO> recommendCategori(String uProductCategori, int uProductId);
	
	List<UProductVO> selectLikes(String memberId);
	List<UProductVO> selectByInterest(String interest);
	
	int insertRecentlyView(int uProductId, String memberId);
	List<UProductVO> selectRecentlyView(MyPageCriteria criteria, String memberId);
	int getTotalCountsByRecentlyView(String memberId);
	int deleteRecentlyView(int uProductRecentlyViewId);
	List<UProductBuyVO> selectbuyuproduct(PageCriteria criteria, String buyerNickname);
	int getTotalCountsbuyuproduct(String buyerNickname);
	
	List<UProductSellVO> selectselluproduct(PageCriteria criteria, String sellerNickname);
	int getTotalCountsselluproduct(String sellerNickname);
	
	UProductBuyVO selectseller(String sellerNickname); // 占쏙옙품 占싯삼옙
	
	List<UProductVO> selectbynickname(PageCriteria criteria, String memberNickname);
	int getTotalCountsbynickname(String memberNickname);
	
	UProductVO selectnickname(String memberNickname); // 占쏙옙품 占싯삼옙
	
	int updateLikeCount(int amount, int uProductId);
	
}






