package project.spring.calla.persistence;


import project.spring.calla.domain.UBoardVO;
import project.spring.calla.domain.UImageVO;

public interface UBoardDAO {
	
	int insert(UBoardVO vo);
	
	public void imageinsert(UImageVO vo);

}
