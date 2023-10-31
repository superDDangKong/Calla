package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface UProductService {
	int create(UProductVO vo) throws Exception; // 상품 생성
	List<UProductVO> read(PageCriteria criteria); // 상품목록 읽어오기
	UProductVO read(int uProductId); // 상품ID 읽어오기
	int update(UProductVO vo); // 상품 정보 수정
	int delete(int uProductId); // 상품 삭제
	int getTotalCounts(); // 상품 전체 갯수
	List<UProductVO> readByCategoriorName(PageCriteria criteria, String keyword);
	int getTotalCountsByByCategoriorName(String keyword);
	List<UProductVO> readdate(PageCriteria criteria); // 상품목록 읽어오기
	int getTotalCountsBydate();
	List<UProductVO> readByAddress(PageCriteria criteria, String keyword);
	int getTotalCountsByAddress(String keyword);

}
