package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface ProductDAO {
	int insert(ProductVO vo); // ��ǰ ���
	List<ProductVO> select(); // ��ǰ ��ü �˻�
	ProductVO select(int productId); // ��ǰ �˻�	
	int update(ProductVO vo); // ��ǰ ����
	int delete(int productId); // ��ǰ ����
	List<ProductVO> select(PageCriteria criteria);
	int getTotalCount();
	List<ProductVO> selectByProductNameOrProductContent(PageCriteria criteria, String keyword);
	int getTotalCountsByProductNameOrProductContent(String keyword);
	int updateCommentCount(int amount, int productId);
	int updateViews(int views, int productId);
	int updateLikeCount(int amount, int productId);
	List<ProductVO> selectLikes(String memberId);
	List<ProductVO> selectProductWithAmount(String memberId);
	
}
