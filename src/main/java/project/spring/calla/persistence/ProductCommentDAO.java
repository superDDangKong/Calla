package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.pageutil.PageCriteria;

public interface ProductCommentDAO {
	int insert(ProductCommentVO vo); // ´ñ±Û µî·Ï
	List<ProductCommentVO> select(int productId); // »óÇ°ÀÇ ´ñ±Û °Ë»ö
	int update(int productCommentId, String productCommentContent); // ´ñ±Û ¼öÁ¤
	int delete(int productCommentId); // ´ñ±Û »èÁ¦
	List<ProductCommentVO> select(PageCriteria criteria, int productId);
	int getTotalCount(int productId);
}
