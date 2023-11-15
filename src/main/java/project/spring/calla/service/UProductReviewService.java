package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.domain.UProductReviewVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.PageCriteria;

public interface UProductReviewService {
	
	int create(UProductReviewVO vo) throws Exception; // 占쏙옙품 占쏙옙占쏙옙
	
	List<UProductReviewVO> read(PageCriteria criteria); // 占쏙옙품占쏙옙占� 占싻억옙占쏙옙占�
	int getTotalCounts(); // 占쏙옙품 占쏙옙체 占쏙옙占쏙옙
	
	List<UProductReviewVO> readysellNickname(PageCriteria criteria, String sellerNickname);
	int getTotalCountssellNickname(String sellerNickname);
	
	UProductReviewVO read(String sellerNickname); // 占쏙옙품ID 占싻억옙占쏙옙占�
	
	UProductVO readnickname(String sellerNickname); // 占쏙옙품ID 占싻억옙占쏙옙占�
	
	MemberVO readMembermanner(String memberNickname);
	
	float updatememberManner(String memberNickname);
	

}
