package project.spring.calla.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProductOrderVO {
	private String memberId;
	private Date productOrderCreatedDate;
	private String memberCard;
	private String memberCardNumber;
	private String memberEmail;
	private String recipientName;
	private String memberAddress;
	private String productOrderId;
	private List<ProductData> selectedProducts;
	private ProductData selectedProduct;
	
	public static class ProductData {
	    private String memberId;
		private int productId;
	    private String productName;
	    private int productPrice;
	    private int productAmount;
		
	    public ProductData() {
		}
	    
	    public String getMemberId() {
	    	return memberId;
	    }
	    
	    public void setMemberId(String memberId) {
	    	this.memberId = memberId;
	    }
	    
		public int getProductId() {
			return productId;
		}

		public void setProductId(int productId) {
			this.productId = productId;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public int getProductPrice() {
			return productPrice;
		}

		public void setProductPrice(int productPrice) {
			this.productPrice = productPrice;
		}

		public int getProductAmount() {
			return productAmount;
		}

		public void setProductAmount(int productAmount) {
			this.productAmount = productAmount;
		}
	    
		@Override
		public String toString() {

		    return "ProductData [productId=" + productId +
	                ", productName=" + productName +
	                ", productPrice=" + productPrice +
	                ", productAmount=" + productAmount + "]";
		}
	    
	    
	    
	}

	public ProductOrderVO() {
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Date getProductOrderCreatedDate() {
		return productOrderCreatedDate;
	}

	public void setProductOrderCreatedDate(Date productOrderCreatedDate) {
		this.productOrderCreatedDate = productOrderCreatedDate;
	}

	public String getMemberCard() {
		return memberCard;
	}

	public void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}

	public String getMemberCardNumber() {
		return memberCardNumber;
	}

	public void setMemberCardNumber(String memberCardNumber) {
		this.memberCardNumber = memberCardNumber;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getProductOrderId() {
		return productOrderId;
	}

	public void setProductOrderId(String productOrderId) {
		this.productOrderId = productOrderId;
	}

	public List<ProductData> getSelectedProducts() {
		return selectedProducts;
	}

	public void setSelectedProducts(List<ProductData> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}
	
	
	
	public ProductData getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(ProductData selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	@Override
	public String toString() {
		return "ProductOrderVO [memberId=" + memberId + ", productOrderCreatedDate=" + productOrderCreatedDate
				+ ", memberCard=" + memberCard + ", memberCardNumber=" + memberCardNumber + ", memberEmail="
				+ memberEmail + ", recipientName=" + recipientName + ", memberAddress=" + memberAddress
				+ ", productOrderId=" + productOrderId + ", selectedProducts=" + selectedProducts + "]";
	}
	
	



	
	
	
	
	
	
	
	
	

	
	
	
	
	
}
