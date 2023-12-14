package project.spring.calla.util;

// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½È£ï¿½ï¿½
// ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ô½Ã±ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Ï´ï¿½ Å¬ï¿½ï¿½ï¿½ï¿½
// -> paging Ã³ï¿½ï¿½ï¿½ï¿½ ï¿½Ê¿ï¿½ï¿½ï¿½ startï¿½ï¿½ end ï¿½ï¿½È£ï¿½ï¿½ ï¿½ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
public class PageCriteria {
	private int page; // ÇöÀç ÆäÀÌÁö ¹øÈ£
	private int numsPerPage; // ÇÑ ÆäÀÌÁö¿¡ °Ô½Ã±Û °³¼ö
	
	public PageCriteria() {
		this.page = 1;
		this.numsPerPage = 3;
	}
	
	public PageCriteria(int page, int numsPerPage) {
		this.page = page;
		this.numsPerPage = numsPerPage;
	}

	// getter/setter
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNumsPerPage() {
		return numsPerPage;
	}

	public void setNumsPerPage(int numsPerPage) {
		this.numsPerPage = numsPerPage;
	}
	
	// ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½Ï·Ã¹ï¿½È£(rn)
	public int getStart() {
		return (this.page - 1) * this.numsPerPage + 1;
	}
	
	// ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½Ï·Ã¹ï¿½È£(rn)
	public int getEnd() {
		return this.page * this.numsPerPage;
	}
}

