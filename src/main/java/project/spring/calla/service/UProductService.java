package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface UProductService {
	int create(UProductVO vo) throws Exception; // 占쏙옙품 占쏙옙占쏙옙
	List<UProductVO> read(PageCriteria criteria); // 占쏙옙품占쏙옙占� 占싻억옙占쏙옙占�
	UProductVO read(int uProductId); // 占쏙옙품ID 占싻억옙占쏙옙占�
	int update(UProductVO vo); // 占쏙옙품 占쏙옙占쏙옙 占쏙옙占쏙옙
	int delete(int uProductId); // 占쏙옙품 占쏙옙占쏙옙
	int getTotalCounts(); // 占쏙옙품 占쏙옙체 占쏙옙占쏙옙
	List<UProductVO> readByCategoriorName(PageCriteria criteria, String keyword);
	int getTotalCountsByByCategoriorName(String keyword);
	List<UProductVO> readdate(PageCriteria criteria); // 占쏙옙품占쏙옙占� 占싻억옙占쏙옙占�
	int getTotalCountsBydate();
	List<UProductVO> readByAddress(PageCriteria criteria, String keyword);
	int getTotalCountsByAddress(String keyword);
	List<UProductVO> readrecommend(String uProductCategori, int uProductId); 
	List<UProductVO> read();
	List<UProductVO> readByInterest(String interest);
	int createRecentlyView(int uProductId, String memberId);
	
	List<UProductBuyVO> readybuyuproduct(PageCriteria criteria, String buyerNickname);
	int getTotalCountsbuyuproduct(String buyerNickname);
	
	List<UProductSellVO> readyselluproduct(PageCriteria criteria, String sellerNickname);
	int getTotalCountsselluproduct(String sellerNickname);
	
	UProductBuyVO read(String sellerNickname); // 占쏙옙품ID 占싻억옙占쏙옙占�
	
	List<UProductVO> readybyuproductnickname(PageCriteria criteria, String memberNickname);
	int getTotalCountsbyuproductnickname(String memberNickname);
	
	UProductVO readnickname(String memberNickname); // 占쏙옙품ID 占싻억옙占쏙옙占�

}
