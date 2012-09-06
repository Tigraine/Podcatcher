package at.tigraine.podcatcher2.models;

public class Podcast {
	private String name;
	private int id;
	
	public Podcast(int id, String name) {
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
}
