package org.prizy.pricer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="pk_product_id")
	private int productId;
	
	@Column(name="bar_code", updatable=false, unique=true)
	private long barCode;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_desc")
	private String productDesc;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public long getBarCode() {
		return barCode;
	}
	public void setBarCode(long barCode) {
		this.barCode = barCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	
	
}
