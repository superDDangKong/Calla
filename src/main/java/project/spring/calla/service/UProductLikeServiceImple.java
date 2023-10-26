package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.UProductLikeVO;
import project.spring.calla.persistence.UProductLikeDAO;

@Service
public class UProductLikeServiceImple implements UProductLikeService {

	private static final Logger logger =
			LoggerFactory.getLogger(UProductLikeServiceImple.class);
	
	@Autowired
	private UProductLikeDAO dao;
	
	@Override
	public int create(UProductLikeVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UProductLikeVO> read(String memberNickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int uProductLikeId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
