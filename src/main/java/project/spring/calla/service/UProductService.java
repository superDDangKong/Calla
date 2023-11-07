package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface UProductService {
	int create(UProductVO vo) throws Exception; // ��ǰ ����
	List<UProductVO> read(PageCriteria criteria); // ��ǰ��� �о����
	UProductVO read(int uProductId); // ��ǰID �о����
	int update(UProductVO vo); // ��ǰ ���� ����
	int delete(int uProductId); // ��ǰ ����
	int getTotalCounts(); // ��ǰ ��ü ����
	List<UProductVO> readByCategoriorName(PageCriteria criteria, String keyword);
	int getTotalCountsByByCategoriorName(String keyword);
	List<UProductVO> readdate(PageCriteria criteria); // ��ǰ��� �о����
	int getTotalCountsBydate();
	List<UProductVO> readByAddress(PageCriteria criteria, String keyword);
	int getTotalCountsByAddress(String keyword);
	List<UProductVO> readrecommend(String uProductCategori, int uProductId); 
	List<UProductVO> read();
	List<UProductVO> readByInterest(String interest);
	int createRecentlyView(int uProductId, String memberId);
	
	List<UProductBuyVO> readybuyuproduct(PageCriteria criteria, String buyerNickname);
	int getTotalCountsbuyuproduct(String buyerNickname);
	
	List<UProductSellVO> readyselluproduct(PageCriteria criteria, String memberNickname);
	int getTotalCountsselluproduct(String memberNickname);

}
