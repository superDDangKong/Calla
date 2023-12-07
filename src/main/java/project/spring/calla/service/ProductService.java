package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface ProductService {
	int create(ProductVO vo); // 상품 등록
	List<ProductVO> read(PageCriteria criteria); // 페이지 조건에 따라 상품 목록 읽기 
	ProductVO read(int productId); // 상품 id 읽기
	int update(ProductVO vo); // 상품 정보 수정
	int delete(int productId); // 상품 삭제
	int getTotalCounts(); // 상품 총 개수
	List<ProductVO> readByProductNameOrProductContent(PageCriteria criteria, String keyword); // keyword에 따라 상품 조회
	int getTotalCountsByProductNameOrProductContent(String keyword); // keyword에 따라 상품 총 개수
	int updateViews(int views, int productId); // 상품 조회수 업데이트
	List<ProductVO> selectProductWithAmount(String memberId); // memberId에 상품 수량 조회
	List<ProductVO> read(); // 모든 상품 조회
	List<ProductVO> readByInterest(String interest); 
	int createRecentlyView(int productId, String memberId); // 최근본 상품 등록
	List<ProductCommentVO> getCommentsByProductId(int productId); // 상품의 댓글 가져오기
//	int update(int productId, String string);
//	int updateProductWithImages(ProductVO vo);
	int updateProductWithImages(int productId); // 상품 이미지 업데이트
	List<ProductVO> readByProductCategori(PageCriteria criteria, String keyword);
	int getTotalCountsByProductCategori(String keyword);
	
}
