package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.ProductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface ProductService {
	int create(ProductVO vo); // 상품 생성
	List<ProductVO> read(PageCriteria criteria); // 상품목록 읽어오기
	ProductVO read(int productId); // 상품ID정보 읽어오기
	int update(ProductVO vo); // 상품 정보 수정
	int delete(int productId); // 상품 삭제
	int getTotalCounts(); // 상품 전체 갯수
	List<ProductVO> readByProductNameOrProductContent(PageCriteria criteria, String keyword);
	int getTotalCountsByProductNameOrProductContent(String keyword);
	int updateViews(int views, int productId);
	List<ProductVO> selectProductWithAmount(String memberId);
	

}
