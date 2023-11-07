package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.domain.ProductOrderVO.ProductData;

public interface ProductOrderDAO {
	int insert(ProductOrderVO vo);
	int update(int productId, String memberId, String memberEmail, String recipientName, String memberAddress);
	int delete(int productId, String memberId);
	ProductOrderVO select(String memberId);
//	List<ProductOrderVO> select();
	int insert(ProductData productData);
}
