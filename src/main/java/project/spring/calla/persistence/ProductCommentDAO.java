package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.pageutil.PageCriteria;

public interface ProductCommentDAO {
	int insert(ProductCommentVO vo); // ��� ���
	List<ProductCommentVO> select(int productId); // ��ǰ�� ��� �˻�
	int update(int productCommentId, String productCommentContent); // ��� ����
	int delete(int productCommentId); // ��� ����
	List<ProductCommentVO> select(PageCriteria criteria);
	int getTotalCount();
	List<ProductCommentVO> select(String memberNickname);
}
