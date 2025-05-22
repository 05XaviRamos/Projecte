package model;

public class Item {
	private Integer id;
	private String name;
	public Item(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	@Override
	public String toString() {
		return  name;
	}
}
