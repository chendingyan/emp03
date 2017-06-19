package emp.DAO;

import java.util.List;

import emp.model.User;

public interface IUserDao {
	public void add(User user);
	public void delete(String username);
	public void update(User user);
	public User load(String username);
	public List<User> list();
	public User login(String username, String password);
}
