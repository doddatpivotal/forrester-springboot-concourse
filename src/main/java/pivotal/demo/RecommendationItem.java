package pivotal.demo;

import com.microsoft.azure.spring.data.documentdb.core.mapping.Document;

@Document(collection="items")
public class RecommendationItem {
	
	private String recId;
	private String cartId;
	private String recommendedProduct;
	private String recommendationDate;
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public String getRecommendedProduct() {
		return recommendedProduct;
	}
	public void setRecommendedProduct(String recommendedProduct) {
		this.recommendedProduct = recommendedProduct;
	}
	public String getRecommendationDate() {
		return recommendationDate;
	}
	public void setRecommendationDate(String recommendationDate) {
		this.recommendationDate = recommendationDate;
	}
	public String getRecId() {
		return recId;
	}
	public void setRecId(String recId) {
		this.recId = recId;
	}
}
