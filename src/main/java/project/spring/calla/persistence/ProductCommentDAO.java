package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.pageutil.PageCriteria;

public interface ProductCommentDAO {
	int insert(ProductCommentVO vo); // �뜝�룞�삕�뜝占� �뜝�룞�삕�뜝占�
	List<ProductCommentVO> select(int productId); // �뜝�룞�삕�뭹�뜝�룞�삕 �뜝�룞�삕�뜝占� �뜝�떙�궪�삕
	int update(int productCommentId, String productCommentContent); // �뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕
	int delete(int productCommentId); // �뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕
	List<ProductCommentVO> select(PageCriteria criteria, int productId);
	int getTotalCount(int productId);
	List<ProductCommentVO> select(String memberNickname);
	List<ProductCommentVO> select(int productId, int productRated);
}
