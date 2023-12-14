package project.spring.calla.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.persistence.ActivityDAO;
import project.spring.calla.persistence.FBoardDAO;
import project.spring.calla.persistence.ProductDAO;
import project.spring.calla.persistence.ProductLikeDAO;
import project.spring.calla.persistence.QBoardDAO;
import project.spring.calla.persistence.UProductDAO;
import project.spring.calla.persistence.UProductLikeDAO;
import project.spring.calla.util.MyPageCriteria;
import project.spring.calla.util.PageCriteria;


@Service
public class ActivityServiceImple implements ActivityService {
		private static final Logger logger = 
				LoggerFactory.getLogger(ActivityServiceImple.class);
	    
		@Autowired
		private ActivityDAO ActivityDAO;
		
		@Autowired
		private FBoardDAO fBoardDAO;
	  
		@Autowired
		private QBoardDAO qBoardDAO;
		
		@Autowired
		private UProductDAO uProductDAO;
		
		@Autowired
		private ProductDAO productDAO;
		
		@Autowired
		private ProductLikeDAO productLikeDAO;
		
		@Autowired
		private UProductLikeDAO uProductLikeDAO;
		
		@Override
		public Map<String, Integer> getTotalCountsByMemberNickname(String memberNickname) {
			logger.info("getTotalCountsByMemberNickname : " + memberNickname);
			Map<String, Integer> args = new HashMap();
			args.put("fBoardCount", fBoardDAO.getTotalCountsByMemberNickname(memberNickname));
			args.put("qBoardCount", qBoardDAO.getTotalCountsByMemberNickname(memberNickname));
			args.put("uProductCount", uProductDAO.getTotalCountsByMemberNickname(memberNickname));
			return args;
		}
		
		@Override
		public Map<String, Object> readLikes(String memberId) {
			logger.info("readLikes() 호출 memberId : " + memberId);
			Map<String, Object> args = new HashMap();
			args.put("productLikeList", productDAO.selectLikes(memberId));
			args.put("uProductLikeList", uProductDAO.selectLikes(memberId));
			return args;
		}
		
		@Override
		public Map<String, Object> readRecentlyView(MyPageCriteria criteria, String memberId) {
			logger.info("readRecentlyView() 호출 memberId : " + memberId);
			logger.info("getTotalCountsByRecentlyView 호출 criteria : " + criteria.toString());
			Map<String, Object> args = new HashMap();
			List<ProductVO> productList = productDAO.selectRecentlyView(criteria, memberId);
			for(ProductVO vo : productList) {
				String[] imageList = vo.getProductImagePath().split(",");
				vo.setProductImagePath(imageList[0]);
			}
			args.put("productList", productList);
			args.put("uProductList", uProductDAO.selectRecentlyView(criteria, memberId));
			return args;
		}

		@Override
		public Map<String, Integer> getTotalCountsByRecentlyView(String memberId) {
			logger.info("getTotalCountsByRecentlyView 호출 memberId : " + memberId);

			Map<String, Integer> args = new HashMap();
			args.put("productCount", productDAO.getTotalCountsByRecentlyView(memberId));
			args.put("uProductCount", uProductDAO.getTotalCountsByRecentlyView(memberId));
			return args;
		}

		@Override
		public int deleteRecentlyViewProduct(int productRecentlyViewId) {
			logger.info("deleteRecentlyViewProduct() 호출");
			return productDAO.deleteRecentlyView(productRecentlyViewId);
		}

		@Override
		public int deleteRecentlyViewUProduct(int uProductRecentlyViewId) {
			logger.info("deleteRecentlyViewUProduct() 호출");
			return uProductDAO.deleteRecentlyView(uProductRecentlyViewId);
		}

		@Override
		public List<UProductVO> readmyuproduct(PageCriteria criteria,  String memberNickname) {
			logger.info("readmyuproduct() 호출");
			logger.info("start = " + criteria.getStart());
			logger.info("end = " + criteria.getEnd());
			logger.info("memberNickname = " + memberNickname);
			
			return ActivityDAO.selectmyuproduct(criteria, memberNickname);
		}

		@Override
		public int getTotalCountsBymyuproduct(String memberNickname) {
			logger.info("getTotalCountsBymyuproduct() 호출");
			return ActivityDAO.getTotalCountsBymyuproduct(memberNickname);	
		}

		@Override
		public UProductVO read(int uProductId) {
			logger.info("read() 호출 : boardId = " + uProductId);
			return ActivityDAO.select(uProductId);
		}

		@Transactional(value = "transactionManager")
		@Override
		public int buysellcreate(UProductBuyVO vo, UProductSellVO svo) {
			int result = ActivityDAO.insertbuy(vo);
			int results = ActivityDAO.insertsell(svo);
			
			if(result ==1 && results ==1) {
				return 1;
			} else {
				return 0;
			}
		}
		
		@Transactional(value = "transactionManager")
		@Override
		public int deleteProductLike(int productLikeId, int amount, int productId) {
			try {
		        logger.info("deleteUProductLike() 호출");
		        productLikeDAO.deleteById(productLikeId);
		        productDAO.updateLikeCount(amount, productId);
		        return 1;
		    } catch (Exception e) {
		        // 롤백을 확인하기 위해 예외를 다시 던질 수도 있음
		        throw new RuntimeException("트랜잭션 롤백 테스트", e);
		    }
		}
		
		@Transactional(value = "transactionManager")
		@Override
		public int deleteUProductLike(int uProductLikeId, int amount, int uProductId) {
			logger.info("deleteUProductLike() 호출");
			uProductLikeDAO.deleteById(uProductLikeId);
			uProductDAO.updateLikeCount(amount, uProductId);
			return 1;
		}

		@Override
		public List<UProductVO> readProductsByOption(PageCriteria criteria, String keyword, String interest, String option) {
			logger.info("readProductsByOption() 호출");
			List<UProductVO> list = ActivityDAO.selectProductsByOption(criteria, keyword, interest, option);
			for (UProductVO vo : list) {
				String[] imagePath = vo.getuProductImagePath().split(",");
				if(imagePath.length > 1) {
					vo.setuProductImagePath(imagePath[0]);
				}
			}
			return list;
		}

		@Override
		public int getTotalCountsProductsByOption(String keyword, String interest, String option) {
			logger.info("getTotalCountsProductsByOption() 호출");
			return ActivityDAO.getTotalCountsProductsByOption(keyword, interest, option);	
		}

		@Override
		public List<UProductVO> readBoards(String memberNickname, String option, MyPageCriteria criteria) {
			logger.info("readBoards() 호출");
			return ActivityDAO.selectBoards(memberNickname, option, criteria);
		}

		@Override
		public int getTotalCountsBoard(String memberNickname, String option) {
			logger.info("getTotalCountsBoard 호출");
			return ActivityDAO.getTotalCountsBoard(memberNickname, option);	
		}
		
		@Override
		public List<UProductCommentVO> readComments(String memberNickname, String option, MyPageCriteria criteria) {
			logger.info("readComments() 호출");
			return ActivityDAO.selectComments(memberNickname, option, criteria);
		}

		@Override
		public int getTotalCountsComment(String memberNickname, String option) {
			logger.info("getTotalCountsComment 호출");
			return ActivityDAO.getTotalCountsComment(memberNickname, option);	
		}
		
		@Override
		public List<UProductVO> readLikes(String memberId, String option, MyPageCriteria criteria) {
			logger.info("readLikes() 호출");
			return ActivityDAO.selectLikes(memberId, option, criteria);
		}

		@Override
		public int getTotalCountsLike(String memberId, String option) {
			logger.info("getTotalCountsLike 호출");
			return ActivityDAO.getTotalCountsLike(memberId, option);	
		}

		@Override
		public List<ProductOrderVO> readOrders(String memberId, MyPageCriteria criteria) {
			logger.info("readOrders() 호출");
			return ActivityDAO.selectOrders(memberId, criteria);
		}

		@Override
		public int getTotalCountsOrders(String memberId) {
			logger.info("getTotalCountsOrderList 호출");
			return ActivityDAO.getTotalCountsOrders(memberId);	
		}

	

}
