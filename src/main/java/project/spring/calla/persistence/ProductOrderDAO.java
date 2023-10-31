package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductOrderVO;

public interface ProductOrderDAO {
	int insert(ProductOrderVO vo);
	List<ProductOrderVO> select();
	int update(ProductOrderVO vo);
	int delete(int productOrderId);
	
}
