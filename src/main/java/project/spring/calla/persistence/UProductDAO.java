package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.domain.QBoardVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.RecentlyViewPageCriteria;

public interface UProductDAO {
	int insert(UProductVO vo); // ��ǰ ���
	List<UProductVO> select(); // ��ǰ ��ü �˻�
	UProductVO select(int uProductId); // ��ǰ �˻�	
	int update(UProductVO vo); // ��ǰ ����
	int delete(int uProductId); // ��ǰ ����
	List<UProductVO> select(PageCriteria criteria);
	int getTotalCount();
	
	List<UProductVO> select(String uProductName);
	List<UProductVO> selectByName(String keyword);
	
	int updateUproductCommentCount(int amount, int uProductId);
	List<UProductVO> selectAllByMemberNickname(String memberNickname);
	
	List<UProductVO> selectByCategoriorName(PageCriteria criteria, String keyword); // ��ǰ �̸��Ǵ� ī�װ��� �˻�
	int getTotalCountsByCategoriorName(String keyword);
	
	List<UProductVO> selectByUproductCreatedDate(PageCriteria criteria); // �Ż�ǰ �˻�
	int getTotalCountsByUproductCreatedDate();
	
	List<UProductVO> selectByAddress(PageCriteria criteria, String keyword); // ��ǰ �ּ� �˻�
	int getTotalCountsByAddress(String keyword);
	
	List<UProductVO> recommendCategori(String uProductCategori, int uProductId);
	
	List<UProductVO> selectLikes(String memberId);
	List<UProductVO> selectByInterest(String interest);
	
	int insertRecentlyView(int uProductId, String memberId);
	List<UProductVO> selectRecentlyView(RecentlyViewPageCriteria criteria, String memberId);
	int getTotalCountsByRecentlyView(String memberId);
	int deleteRecentlyView(int uProductRecentlyViewId);
	List<UProductBuyVO> selectbuyuproduct(PageCriteria criteria, String buyerNickname);
	int getTotalCountsbuyuproduct(String buyerNickname);
	
	List<UProductSellVO> selectselluproduct(PageCriteria criteria, String memberNickname);
	int getTotalCountsselluproduct(String memberNickname);
	
	
	
}






