package bbd;

public class User {
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int id;
	private String username;
	// Otros atributos necesarios

	public User(int id, String username) {
		this.id = id;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	// Getter y setter adicionales
}