package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.util.PageCriteria;

public interface ProductService {
	int create(ProductVO vo); // �긽�뭹 �벑濡�
	List<ProductVO> read(PageCriteria criteria); // �럹�씠吏� 議곌굔�뿉 �뵲�씪 �긽�뭹 紐⑸줉 �씫湲� 
	ProductVO read(int productId); // �긽�뭹 id �씫湲�
	int update(ProductVO vo); // �긽�뭹 �젙蹂� �닔�젙
	int delete(int productId); // �긽�뭹 �궘�젣
	int getTotalCounts(); // �긽�뭹 珥� 媛쒖닔
	List<ProductVO> readByProductNameOrProductContent(PageCriteria criteria, String keyword); // keyword�뿉 �뵲�씪 �긽�뭹 議고쉶
	int getTotalCountsByProductNameOrProductContent(String keyword); // keyword�뿉 �뵲�씪 �긽�뭹 珥� 媛쒖닔
	int updateViews(int views, int productId); // �긽�뭹 議고쉶�닔 �뾽�뜲�씠�듃
	List<ProductVO> selectProductWithAmount(String memberId); // memberId�뿉 �긽�뭹 �닔�웾 議고쉶
	List<ProductVO> read(); // 紐⑤뱺 �긽�뭹 議고쉶
	List<ProductVO> readByInterest(String interest); 
	int createRecentlyView(int productId, String memberId); // 理쒓렐蹂� �긽�뭹 �벑濡�
	List<ProductCommentVO> getCommentsByProductId(int productId); // �긽�뭹�쓽 �뙎湲� 媛��졇�삤湲�
//	int update(int productId, String string);
//	int updateProductWithImages(ProductVO vo);
	int updateProductWithImages(int productId); // �긽�뭹 �씠誘몄� �뾽�뜲�씠�듃
	List<ProductVO> readByProductCategori(PageCriteria criteria, String keyword);
	int getTotalCountsByProductCategori(String keyword);
	
}
