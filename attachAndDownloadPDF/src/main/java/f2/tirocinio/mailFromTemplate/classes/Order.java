package f2.tirocinio.mailFromTemplate.classes;

public class Order {

	String product;
	Integer quantity;

	public Order(String product, Integer quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
