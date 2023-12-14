package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.util.PageCriteria;

public interface UProductService {
	int create(UProductVO vo) throws Exception; 
	List<UProductVO> read(PageCriteria criteria); 
	UProductVO read(int uProductId); 
	int update(UProductVO vo); 
	int delete(int uProductId); 
	int getTotalCounts(); 
	List<UProductVO> readByCategoriorName(PageCriteria criteria, String keyword);
	int getTotalCountsByByCategoriorName(String keyword);
	List<UProductVO> readdate(PageCriteria criteria); 
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
	
	UProductBuyVO read(String sellerNickname); 
	
	List<UProductVO> readybyuproductnickname(PageCriteria criteria, String memberNickname);
	int getTotalCountsbyuproductnickname(String memberNickname);
	
	UProductVO readnickname(String memberNickname); 
	
	String readmanner(String memberNickname);
	
	int statementupdate(int uProductId);
	
	int statementupdates(int uProductId);
	
	int deleteUProductsell(int uProductSellId);
	
	int deleteUProductbuy(int uProductBuyId);
	
	List<UProductVO> readlike(PageCriteria criteria); 
	int getTotalCountsBylike();
	
	

}
