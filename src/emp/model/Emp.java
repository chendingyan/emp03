package emp.model;

public class Emp {
	private int id;
	private String name;
	private String sex;
	private double salary;
	private int depId;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public int getDepId() {
		return depId;
	}
	public void setDepId(int depId) {
		this.depId = depId;
	}
	@Override
	public String toString() {
		return "Emp [id=" + id + ", name=" + name + ", sex=" + sex + ", salary=" + salary + ", depId=" + depId + "]";
	}
	
}
