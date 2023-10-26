package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.pageutil.PageCriteria;

public interface ProductCommentDAO {
	int insert(ProductCommentVO vo); // 占쏙옙占� 占쏙옙占�
	List<ProductCommentVO> select(int productId); // 占쏙옙품占쏙옙 占쏙옙占� 占싯삼옙
	int update(int productCommentId, String productCommentContent); // 占쏙옙占� 占쏙옙占쏙옙
	int delete(int productCommentId); // 占쏙옙占� 占쏙옙占쏙옙
	List<ProductCommentVO> select(PageCriteria criteria, int productId);
	int getTotalCount(int productId);
	List<ProductCommentVO> select(String memberNickname);
}
