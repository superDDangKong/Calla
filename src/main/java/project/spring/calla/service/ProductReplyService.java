package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.ProductReplyVO;

// CRUD(Create, Read, Update, Delete)
public interface ProductReplyService {
	int create(ProductReplyVO vo) throws Exception;
	List<ProductReplyVO> read(int productCommentId);
	int update(int productReplyId, String productReplyContent);
	int delete(int productReplyId) throws Exception;
}
