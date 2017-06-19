package emp.test;

import java.util.List;

import emp.DAO.DepDao;
import emp.model.Dep;

public class TestDep {
	private static DepDao dd = new DepDao();
	public static void main(String[] args) {
//		TestAdd();
//		TestLoad();
//		TestDelete();
//		TestUpdate();
//		TestList();
	}
	
	private static void TestAdd(){
		Dep d = new Dep();
		d.setName("IBM");
		dd.add(d);
	}
	
	private static void TestLoad(){
		Dep d = dd.load(3);
		System.out.println(d);
	}
	
	private static void TestDelete(){
		dd.delete(1);
	}
	
	private static void TestUpdate(){
		Dep d = new Dep();
		d.setId(4);
		d.setName("computer science");
		dd.update(d);
	}
	
	private static void TestList(){
		List<Dep> list = dd.list();
		for(Dep d:list){
			System.out.println(d);
		}
	}
}
