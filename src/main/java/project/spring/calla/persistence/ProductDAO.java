package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductVO;
import project.spring.calla.util.MyPageCriteria;
import project.spring.calla.util.PageCriteria;

public interface ProductDAO {
	int insert(ProductVO vo); 
	List<ProductVO> select();
	ProductVO select(int productId); 	
	int update(ProductVO vo); 
	int delete(int productId); 
	List<ProductVO> select(PageCriteria criteria);
	int getTotalCount();
	List<ProductVO> selectByProductNameOrProductContent(PageCriteria criteria, String keyword);
	int getTotalCountsByProductNameOrProductContent(String keyword);
	int updateCommentCount(int amount, int productId);
	int updateViews(int views, int productId);
	int updateLikeCount(int amount, int productId);
	List<ProductVO> selectLikes(String memberId);
	List<ProductVO> selectProductWithAmount(String memberId);
	List<ProductVO> selectByInterest(String interest);
	int insertRecentlyView(int productId, String memberId);
	List<ProductVO> selectRecentlyView(MyPageCriteria criteria, String memberId);
	int getTotalCountsByRecentlyView(String memberId);	
	int deleteRecentlyView(int productRecentlyViewId);
	int updateRatedCount(int amount, int productId);
//	int update(int productId, String string);
	int update(int productId);
	List<ProductVO> selectByProductCategori(PageCriteria criteria, String keyword);
	int getTotalCountByProductCategori(String keyword);
	
	
}
