package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductOrderVO;

public interface ProductOrderDAO {
	int insert(ProductOrderVO vo);
	int update(int productId, String memberId, String memberEmail, String recipientName, String memberAddress);
	int delete(int productId, String memberId);
	ProductOrderVO selectBy(String memberId);
	List<ProductOrderVO> select();
	List<ProductOrderVO> select(String memberId);
	int updateDeliveryStatus(int productId, String memberId, String deliveryStatus);
}
