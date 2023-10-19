package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.ProductCommentVO;

public interface ProductCommentService {
	int create(ProductCommentVO vo) throws Exception; // ´ñ±Û µî·Ï
	List<ProductCommentVO> read(int productId); // »óÇ° ´ñ±Û °Ë»ö
	int update(int productCommentId, String productCommentContent); // ´ñ±Û ¼öÁ¤
	int delete(int productCommentId, int productId) throws Exception; // ´ñ±Û »èÁ¦
}
