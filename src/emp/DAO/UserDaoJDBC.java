package emp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import emp.model.User;
import emp.model.empException;
import emp.util.JDBCUtil;

public class UserDaoJDBC implements IUserDao {

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConnection();
			String sql ="select count(*) from t_user where username=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			rs = ps.executeQuery();
			int count = 0;
			while(rs.next()){
				count = rs.getInt(1);
			}
			if(count>0) throw new empException("There is already exist this user");
			sql ="insert into t_user values (?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickname());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(ps);
			JDBCUtil.close(con);
		}
		
	}

	@Override
	public void delete(String username) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = JDBCUtil.getConnection();
			String sql = "delete from t_user where username=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(ps);
			JDBCUtil.close(con);
		}
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = JDBCUtil.getConnection();
			String sql = "update t_user set password =?, nickname=? where username=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getNickname());
			ps.setString(3, user.getUsername());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JDBCUtil.close(ps);
			JDBCUtil.close(con);
		}
	}

	@Override
	public User load(String username) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		User u = null;
		try {
			con = JDBCUtil.getConnection();
			String sql = "select * from t_user where username=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while(rs.next()){
				u = new User();
				u.setNickname(rs.getString("nickname"));
				u.setPassword(rs.getString("password"));
				u.setUsername(rs.getString("username"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(ps);
			JDBCUtil.close(con);
		}
		return u;
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConnection();
			String sql = "select * from t_user";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			User u =null;
			while(rs.next()){
				u = new User();
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setNickname(rs.getString("nickname"));
				users.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(ps);
			JDBCUtil.close(con);
		}
		return users;
	}

	@Override
	public User login(String username, String password) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		User u = null;
		try {
			con = JDBCUtil.getConnection();
			String sql = "select * from t_user where username=? and password=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next()){
				u = new User();
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setNickname(rs.getString("nickname"));
			}
			if(u==null) throw new empException("Username or Password incorrect!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(ps);
			JDBCUtil.close(con);
		}
		return u;
	}

}
