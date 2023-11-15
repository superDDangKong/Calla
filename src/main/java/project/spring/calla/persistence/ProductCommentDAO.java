package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.pageutil.PageCriteria;

public interface ProductCommentDAO {
	int insert(ProductCommentVO vo); 
	List<ProductCommentVO> select(int productId); 
	int update(int productCommentId, String productCommentContent); 
	int delete(int productCommentId); // �뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕
	List<ProductCommentVO> select(PageCriteria criteria, int productId);
	int getTotalCount(int productId);
	List<ProductCommentVO> select(String memberNickname);
	List<ProductCommentVO> select(int productId, int productRated);
	List<ProductCommentVO> selectByProductId(int productId);
	int getRatedCounts(int productRated, int productId);
	ProductCommentVO selectBy(int productCommentId);
}
