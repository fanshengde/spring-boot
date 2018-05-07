package com.fsd.pojo.order;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.util.Assert;

import com.fsd.pojo.AbstractEntity;
import com.fsd.pojo.Product;

@Entity
public class LineItem extends AbstractEntity {
	@ManyToOne
	private Product product;

	@Column(nullable = false)
	private BigDecimal price;

	private int amount;

	public LineItem() {

	}

	public LineItem(Product product) {
		this(product, 1);
	}

	public LineItem(Product product, int amount) {
		Assert.notNull(product, "The given Product must not be null!");
		Assert.isTrue(amount > 0, "The amount of Products to be bought must be greater than 0!");

		this.product = product;
		this.amount = amount;
		this.price = product.getPrice();
	}

	public BigDecimal getUnitPrice() {
		return price;
	}

	public BigDecimal getTotal() {
		return price.multiply(BigDecimal.valueOf(amount));
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
