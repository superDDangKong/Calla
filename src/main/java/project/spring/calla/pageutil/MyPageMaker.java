package project.spring.calla.pageutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.spring.calla.controller.FBoardController;

// 페이지 번호들의 링크를 만들기 위한 유틸리티 클래스
public class MyPageMaker {
	private static final Logger logger = 
			LoggerFactory.getLogger(MyPageMaker.class);
	
	private MyPageCriteria criteria;
	private int totalCount; // 전체 게시글 개수
	private boolean hasPrev; // 화면에 보이는 시작 페이지 번호보다 작은 숫자의 페이지가 있는 지
	private boolean hasNext; // 화면에 보이는 끝 페이지 번호보다 큰 숫자의 페이지가 있는 지
	private int totalLinkNo;
	
	public MyPageMaker() {
	}
	
	public MyPageCriteria getCriteria() {
		return criteria;
	}
	
	public void setCriteria(MyPageCriteria criteria) {
		this.criteria = criteria;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getTotalLinkNo() {
		return totalLinkNo;
	}
	
	public void setTotalLinkNo(int totalLinkNo) {
		this.totalLinkNo = totalLinkNo;
	}
	
	public boolean isHasPrev() {
		return hasPrev;
	}
	
	public boolean isHasNext() {
		return hasNext;
	}
	
	// startPageNo, endPageNo, hasPrev, hasNext 값을 계산 및 세팅
	public void setPageData() {
		
		logger.info("setPageData 호출");
		logger.info("totalcount" + totalCount);
		logger.info("page" + criteria.getPage());
		int totalLinkNo = (int) Math.ceil((double) totalCount / criteria.getNumsPerPage());
		setTotalLinkNo(totalLinkNo);
		
		if (criteria.getPage() == 1) {
			hasPrev = false;
		} else {
			hasPrev = true;
		}
		
		if (criteria.getPage() < totalLinkNo) {
			hasNext = true;
		} else {
			hasNext = false;
		}
		
	}
	
} // end PageMaker
