package org.prizy.pricer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="product_price")
public class Price {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="pk_price_id")
	private int priceId;
	
	@Column(name="store_name")
	private String storeName;
	
	@Column(name="fk_product_id")
	private int productId;
	
	// private Product product // Not used because product is not required on basis of price
	
	@Column(name="price")
	private long price;
	
	@Column(name="notes")
	private String notes;
	
	public int getPriceId() {
		return priceId;
	}
	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
