package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface ProductService {
	int create(ProductVO vo); // �긽�뭹 �벑濡�
	List<ProductVO> read(PageCriteria criteria); // �럹�씠吏� 泥섎━ 
	ProductVO read(int productId); // �긽�뭹ID 媛��졇�삤湲�	
	int update(ProductVO vo); // �긽�뭹 �닔�젙
	int delete(int productId); // �긽�뭹 �궘�젣
	int getTotalCounts(); // �긽�뭹 �쟾泥� 媛쒖닔
	List<ProductVO> readByProductNameOrProductContent(PageCriteria criteria, String keyword); // keyword 寃��깋
	int getTotalCountsByProductNameOrProductContent(String keyword); // �씠由�, �궡�슜 寃��깋
	int updateViews(int views, int productId); // 議고쉶�닔 �뾽�뜲�씠�듃
	List<ProductVO> selectProductWithAmount(String memberId); // �옣諛붽뎄�땲 �긽�뭹 �닔�웾
	List<ProductVO> read(); 
	List<ProductVO> readByInterest(String interest); 
	int createRecentlyView(int productId, String memberId); // 理쒓렐 蹂� �긽�뭹
	List<ProductCommentVO> getCommentsByProductId(int productId); // �뙎湲�媛��졇�삤湲�
	
}
