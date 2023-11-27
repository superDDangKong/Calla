package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface ProductService {
	int create(ProductVO vo); // 占쎄맒占쎈�� 占쎈쾻嚥∽옙
	List<ProductVO> read(PageCriteria criteria); // 占쎈읂占쎌뵠筌욑옙 筌ｌ꼶�봺 
	ProductVO read(int productId); // 占쎄맒占쎈�툶D 揶쏉옙占쎌죬占쎌궎疫뀐옙	
	int update(ProductVO vo); // 占쎄맒占쎈�� 占쎈땾占쎌젟
	int delete(int productId); // 占쎄맒占쎈�� 占쎄텣占쎌젫
	int getTotalCounts(); // 占쎄맒占쎈�� 占쎌읈筌ｏ옙 揶쏆뮇�땾
	List<ProductVO> readByProductNameOrProductContent(PageCriteria criteria, String keyword); // keyword 野껓옙占쎄퉳
	int getTotalCountsByProductNameOrProductContent(String keyword); // 占쎌뵠�뵳占�, 占쎄땀占쎌뒠 野껓옙占쎄퉳
	int updateViews(int views, int productId); // 鈺곌퀬�돳占쎈땾 占쎈씜占쎈쑓占쎌뵠占쎈뱜
	List<ProductVO> selectProductWithAmount(String memberId); // 占쎌삢獄쏅떽�럡占쎈빍 占쎄맒占쎈�� 占쎈땾占쎌쎗
	List<ProductVO> read(); 
	List<ProductVO> readByInterest(String interest); 
	int createRecentlyView(int productId, String memberId); // 筌ㅼ뮄�젏 癰귨옙 占쎄맒占쎈��
	List<ProductCommentVO> getCommentsByProductId(int productId); // 占쎈솊疫뀐옙揶쏉옙占쎌죬占쎌궎疫뀐옙
//	int update(int productId, String string);
//	int updateProductWithImages(ProductVO vo);
	int updateProductWithImages(int productId);
	
}
