package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface ProductDAO {
	int insert(ProductVO vo); // 상품 등록
	List<ProductVO> select(); // 상품 전체 검색
	ProductVO select(int productId); // 상품 검색	
	int update(ProductVO vo); // 상품 수정
	int delete(int productId); // 상품 삭제
	List<ProductVO> select(PageCriteria criteria);
	int getTotalCount();
	List<ProductVO> selectByProductNameOrProductContent(PageCriteria criteria, String keyword);
	int getTotalCountsByProductNameOrProductContent(String keyword);
	int updateCommentCount(int amount, int productId);
	int updateViews(int views, int productId);
	
}
