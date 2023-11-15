package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface ProductService {
	int create(ProductVO vo); // 상품 등록
	List<ProductVO> read(PageCriteria criteria); // 페이징 처리 
	ProductVO read(int productId); // 상품ID 가져오기	
	int update(ProductVO vo); // 상품 수정
	int delete(int productId); // 상품 삭제
	int getTotalCounts(); // 상품 전체 개수
	List<ProductVO> readByProductNameOrProductContent(PageCriteria criteria, String keyword); // keyword 검색
	int getTotalCountsByProductNameOrProductContent(String keyword); // 이름, 내용 검색
	int updateViews(int views, int productId); // 조회수 업데이트
	List<ProductVO> selectProductWithAmount(String memberId); // 장바구니 상품 수량
	List<ProductVO> read(); 
	List<ProductVO> readByInterest(String interest); 
	int createRecentlyView(int productId, String memberId); // 최근 본 상품
	List<ProductCommentVO> getCommentsByProductId(int productId); // 댓글가져오기
	
}
