package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductReplyVO;

public interface ProductReplyDAO {
	
	int insert(ProductReplyVO vo);
	List<ProductReplyVO> select(int productCommentId);
	int update(int productReplyId, String productReplyContent);
	int delete(int productReplyId);
}
