package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.domain.UProductMannerDownVO;
import project.spring.calla.domain.UProductMannerVO;
import project.spring.calla.domain.UProductReviewVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.util.PageCriteria;

public interface UProductReviewService {
	
	int create(UProductReviewVO vo) throws Exception; // �뜝�룞�삕�뭹 �뜝�룞�삕�뜝�룞�삕
	
	UProductReviewVO read(int uProductReviewId);
	
	List<UProductReviewVO> read(PageCriteria criteria); // 占쏙옙품占쏙옙占� 占싻억옙占쏙옙占�
	int getTotalCounts(); // 占쏙옙품 占쏙옙체 占쏙옙占쏙옙
	
	List<UProductReviewVO> readysellNickname(PageCriteria criteria, String sellerNickname);
	int getTotalCountssellNickname(String sellerNickname);
	
	UProductReviewVO read(String sellerNickname); // �뜝�룞�삕�뭹ID �뜝�떩�뼲�삕�뜝�룞�삕�뜝占�
	
	UProductVO readnickname(String sellerNickname); // �뜝�룞�삕�뭹ID �뜝�떩�뼲�삕�뜝�룞�삕�뜝占�
	
	MemberVO readMembermanner(String memberNickname);
	
	float updatememberManner(String memberNickname);
	
	float updatememberManners(String memberNickname);
	
	int insertmanner(UProductMannerVO vo);
	
	int count(int uProductId);
	
	int insertmannerdown(UProductMannerDownVO vo);
	
	int countmannerdown(int uProductId);

}
