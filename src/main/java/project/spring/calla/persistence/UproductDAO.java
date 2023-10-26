package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.QBoardVO;
import project.spring.calla.domain.UproductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface UproductDAO {
	int insert(UproductVO vo); // 상품 등록
	List<UproductVO> select(); // 상품 전체 검색
	UproductVO select(int uProductId); // 상품 검색	
	int update(UproductVO vo); // 상품 수정
	int delete(int uProductId); // 상품 삭제
	List<UproductVO> select(PageCriteria criteria);
	int getTotalCount();
	List<UproductVO> select(String uProductName);
	List<UproductVO> selectByName(String keyword);
	int updateUproductCommentCount(int amount, int uProductId);
	List<UproductVO> selectAllByMemberNickname(String menberNickname);
	
}
