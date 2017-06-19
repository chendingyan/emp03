package emp.DAO;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import emp.model.User;
import emp.model.empException;
import emp.util.XMLUtil;

public class UserDao implements IUserDao{
	private Document ud;
	public UserDao() {
		ud = XMLUtil.getUserDocument();
	}
	
	private void write(){
		XMLUtil.write2XML(ud, "users");
	}
	
	private User element2User(Element e) {
		User u = new User();
		u.setUsername(e.elementText("username"));
		u.setPassword(e.elementText("password"));
		u.setNickname(e.elementText("nickname"));
		return u;
	}
	
	private Element loadElementByUsername(String username){
		String path = "/users/user[username = '"+username+"']";
		Element e = (Element) ud.selectSingleNode(path);
		return e;
	}
	
	public void add(User user){
		if(user.getUsername()=="" || user.getUsername().equals("")) throw new empException("Please input correct username!");
		User u = load(user.getUsername());
		if(u!=null) throw new empException("The user is already exists");
		Element r = ud.getRootElement().addElement("user");
		r.addElement("username").addText(user.getUsername());
		r.addElement("password").addText(user.getPassword());
		r.addElement("nickname").addText(user.getNickname());
		write();
	}
	
	public void delete(String username){
		Element root = ud.getRootElement();
		root.remove(loadElementByUsername(username));
		write();
	}
	
	public void update(User user){
		Element e = loadElementByUsername(user.getUsername());
		if(e==null) throw new empException("The user you want to update is not exists");
		e.element("password").setText(user.getPassword());
		e.element("nickname").setText(user.getNickname());
		write();
	}
	
	public User load(String username){
		Element e = loadElementByUsername(username);
		if(e==null) return null;
		return element2User(e);
	}
	
	public List<User> list(){
		String path = "/users/user";
		List<Element> list = ud.selectNodes(path);
		List<User> ulist = new ArrayList<User>();
		for(Element e:list){
			User u = element2User(e);
			ulist.add(u);
		}
		return ulist;
	}
	
	public User login(String username, String password){
		User u = load(username);
		if(u==null) throw new empException("The username doesn't exist!");
		if(!u.getPassword().equals(password)) throw new empException("The password is incorrect!");
		return u;
		
	}
}
