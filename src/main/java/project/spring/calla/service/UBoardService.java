package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.UBoardVO;
import project.spring.calla.domain.UImageVO;

// CRUD(Create, Read, Update, Delete)
public interface UBoardService {
	int create(UBoardVO vo);
	
	public void imageinsert(UImageVO vo);
	
}
