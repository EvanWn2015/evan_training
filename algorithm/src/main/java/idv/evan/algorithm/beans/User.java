package idv.evan.algorithm.beans;

public class User {

	private int id;
	private String name;
	private String password;

	private User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return this.id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this.hashCode() == obj.hashCode()) {
			return true;
		}
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public static User valueOf(int id, String name, String password) {
		User data = new User();
		data.id = id;
		data.name = name;
		data.password = password;
		return data;
	}

}
