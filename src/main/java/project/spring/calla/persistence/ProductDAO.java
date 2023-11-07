package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface ProductDAO {
	int insert(ProductVO vo); // 占쏙옙품 占쏙옙占�
	List<ProductVO> select(); // 占쏙옙품 占쏙옙체 占싯삼옙
	ProductVO select(int productId); // 占쏙옙품 占싯삼옙	
	int update(ProductVO vo); // 占쏙옙품 占쏙옙占쏙옙
	int delete(int productId); // 占쏙옙품 占쏙옙占쏙옙
	List<ProductVO> select(PageCriteria criteria);
	int getTotalCount();
	List<ProductVO> selectByProductNameOrProductContent(PageCriteria criteria, String keyword);
	int getTotalCountsByProductNameOrProductContent(String keyword);
	int updateCommentCount(int amount, int productId);
	int updateViews(int views, int productId);
	int updateLikeCount(int amount, int productId);
	List<ProductVO> selectLikes(String memberId);
	List<ProductVO> selectProductWithAmount(String memberId);
	
	
}
