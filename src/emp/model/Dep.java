package emp.model;

public class Dep {
	
	private int id;
	private String name;
	
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
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		Dep dep = (Dep)obj;
		return this.id == dep.id;
	}
	
	@Override
	public int hashCode() {
		return  (987*33/55)+id;
	}	
}
