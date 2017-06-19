package emp.test;

import java.util.List;

import org.dom4j.Document;

import emp.DAO.UserDao;
import emp.model.User;
import emp.util.XMLUtil;

public class TestUser {
	private static UserDao ud = new UserDao();
	public static void main(String[] args) {
//		TestSingleton();
		TestAdd();
//		TestAddEmpty();
//		TestLoad();
//		TestDelete();
//		TestUpdate();
		TestList();
	}
	
	private static void TestSingleton(){
		Document d1 = XMLUtil.getUserDocument();
		Document d2 = XMLUtil.getUserDocument();
		System.out.println(d1==d2);
	}
	private static void TestAdd(){
		User u = new User();
		u.setUsername("Crystal");
		u.setPassword("123");
		u.setNickname("zy");
		ud.add(u);
	}
	
	private static void TestAddEmpty(){
		User u = new User();
		u.setUsername("");
		u.setPassword("123");
		u.setNickname("mike");
		ud.add(u);
	}
	
	private static void TestLoad(){
		User u = ud.load("Mike");
		System.out.println(u);
	}
	
	private static void TestDelete(){
		ud.delete("Pike");
	}
	 
	private static void TestUpdate(){
		User u = new User();
		u.setUsername("Mike");
		u.setPassword("888");
		u.setNickname("mike");
		ud.update(u);
	}
	
	private static void TestList(){
		List<User> ulist = ud.list();
		for(User u: ulist){
			System.out.println(u);
		}
	}
}
