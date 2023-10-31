package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.domain.QBoardVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface UProductDAO {
	int insert(UProductVO vo); // 상품 등록
	List<UProductVO> select(); // 상품 전체 검색
	UProductVO select(int uProductId); // 상품 검색	
	int update(UProductVO vo); // 상품 수정
	int delete(int uProductId); // 상품 삭제
	List<UProductVO> select(PageCriteria criteria);
	int getTotalCount();
	
	List<UProductVO> select(String uProductName);
	List<UProductVO> selectByName(String keyword);
	
	int updateUproductCommentCount(int amount, int uProductId);
	List<UProductVO> selectAllByMemberNickname(String menberNickname);
	
	List<UProductVO> selectByCategoriorName(PageCriteria criteria, String keyword);
	int getTotalCountsByCategoriorName(String keyword);
	
	List<UProductVO> selectByUproductCreatedDate(PageCriteria criteria);
	int getTotalCountsByUproductCreatedDate();
	
	List<UProductVO> selectByAddress(PageCriteria criteria, String keyword);
	int getTotalCountsByAddress(String keyword);
	
}
