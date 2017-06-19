package emp.DAO;

public class DAOFactory {
	public static IUserDao getUserDao(){
		return new UserDaoJDBC();
	}
}
