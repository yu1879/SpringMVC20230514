package spring.mvc.session17.entity;

public class Master {
	private String name;
	private Integer price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Master [name=" + name + ", price=" + price + "]";
	}

}
