package project.spring.calla.persistence;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import project.spring.calla.domain.MemberVO;
public interface MemberDAO {
	//throws DataIntegrityViolationException
	int insert(MemberVO vo) throws DataIntegrityViolationException; // �����ͺ��̽��� ���� ����, ���Ἲ ��Ģ  
	
	// controller
	String login(String memberId, String memberPw);
	MemberVO select(String memberId);
	String searchId(String memberName, String memberEmail);
	String searchPw(String memberId, String memberPhone);
	int delete(String memberId);
	List<MemberVO> select();
	
	// restcontroller
	int insert(MemberVO vo);
	int checkId(String memberId);
	int checkNickname(String memberNickname);
	int update(String memberId, String newData, String category);
	int updatePw(String memberId, String memberPw);
	int updateLevel(String memberId, int amount);
	
	int deleteUProduct(int uProductId);
	
}
