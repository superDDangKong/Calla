package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.persistence.ProductCommentDAO;
import project.spring.calla.persistence.ProductDAO;

@Service
public class ProductServiceImple implements ProductService {
	private static final Logger logger = 
			LoggerFactory.getLogger(ProductServiceImple.class);
	
	@Autowired
	private ProductDAO dao;
	
	@Autowired
    private ProductCommentDAO productCommentDAO;
	
	@Override
	public int create(ProductVO vo) {
		logger.info("create() 호출 : vo = " + vo.toString());
		return dao.insert(vo);
	}

	@Override
	public List<ProductVO> read(PageCriteria criteria) {
		logger.info("read() 호출");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		return dao.select(criteria);
	}

	@Override
	public ProductVO read(int productId) {
		logger.info("read() 호출 : boardId = " + productId);
		return dao.select(productId);
	}

	@Override
	public int update(ProductVO vo) {
		logger.info("update() 호출 : vo = " + vo.toString());
		return dao.update(vo);
	}

	@Override
	public int delete(int productId) {
		logger.info("delete() 호출 : productId = " + productId);
		return dao.delete(productId);
	}

	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts() 호출");
		return dao.getTotalCount();
	}

	@Override
	public List<ProductVO> readByProductNameOrProductContent(PageCriteria criteria, String keyword) {
		logger.info("readByTitleOrContent() 호출");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		logger.info("keyword = " + keyword);
		return dao.selectByProductNameOrProductContent(criteria, keyword);
	}

	@Override
	public int getTotalCountsByProductNameOrProductContent(String keyword) {
		logger.info("getTotalCountsByTitleContent() 호출");
		return dao.getTotalCountsByProductNameOrProductContent(keyword);
	}

	@Override
	public int updateViews(int views, int productId) {
		logger.info("updateViews() 호출");
		return dao.updateViews(views, productId);
	}

	@Override
	public List<ProductVO> selectProductWithAmount(String memberId) {
		logger.info("productwuthaomut()");
		return dao.selectProductWithAmount(memberId);
	}

	@Override
	public List<ProductVO> read() {
		logger.info("read() 호출");
		return dao.select();
	}

	@Override
	public List<ProductVO> readByInterest(String interest) {
		logger.info("readByInterest() 호출");
		return dao.selectByInterest(interest);
	}

	@Override
	public int createRecentlyView(int productId, String memberId) {
		logger.info("createRecentlyView() 호출");
		return dao.insertRecentlyView(productId, memberId);
	}

	@Override
	public List<ProductCommentVO> getCommentsByProductId(int productId) {
		logger.info("getCommentsByProductId() 호출 : productId = " + productId);
    	return productCommentDAO.selectByProductId(productId);
	}

	
	



	

}
