package project.spring.calla.persistence;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.ProductLikeVO;

@Repository
public class ProductLikeDAOImple implements ProductLikeDAO{
	
	private static final Logger logger =
			LoggerFactory.getLogger(ProductLikeDAOImple.class);
	
	private static final String NAMESPACE = 
			"project.spring.calla.ProductLikeMapper";
	@Override
	public int insert(ProductLikeVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductLikeVO> select(String memberNickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int productLikeId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
