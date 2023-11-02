package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductOrderListVO;

public interface ProductOrderListDAO {
	int insert(ProductOrderListVO vo);
	int delete(int productId, String memberId);
	List<ProductOrderListVO> select();
	ProductOrderListVO select(int productId, String memberId);
	List<ProductOrderListVO> selectBy(String memberId);
	int update(int productId, String memberId, int productAmount);
	
}
