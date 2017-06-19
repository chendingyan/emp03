package emp.test;

import java.util.List;

import emp.DAO.EmpDao;
import emp.model.Emp;

public class TestEmp {
	private static EmpDao ed = new EmpDao();
	public static void main(String[] args) {
//		TestAdd(); 
//		TestLoad();
//		TestDelete();
//		TestUpdate();
//		TestList();
		TestList2();
	}
	
	public static void TestAdd(){
		Emp emp = new Emp();
		emp.setName("Fred");
		emp.setSex("male");
		emp.setSalary(9990.00);
		ed.add(emp, 1);
	}
	
	public static void TestLoad(){
		Emp emp = ed.load(1);
		System.out.println(emp);
	}
	
	public static void TestDelete(){
		ed.delete(2);
	}
	
	public static void TestUpdate(){
		Emp emp = new Emp();
		
	}
	
	public static void TestList(){
		List<Emp> emplist = ed.list();
		for(Emp e: emplist){
			System.out.println(e);
		}
	}
	
	public static void TestList2(){
		List<Emp> emplist = ed.list(2,"");
		for(Emp e: emplist){
			System.out.println(e);
		}
	}
}
