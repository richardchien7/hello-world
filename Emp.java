package homework;
import java.util.LinkedList;
public class Emp {
	private String name;
	private int salary;
	
	public Emp() {
		
	}
	
	public Emp(String st, int wage) {
		name = new String(st);
		salary = wage;
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String st) {
		this.name = st;
	}
	
	public int getSalary() {
		return salary;
	}
	public void setSalary(int wage) {
		this.salary = wage;
	}
}
