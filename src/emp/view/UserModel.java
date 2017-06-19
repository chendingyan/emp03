package emp.view;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import emp.DAO.DAOFactory;
import emp.DAO.IUserDao;
import emp.DAO.UserDao;
import emp.DAO.UserDaoJDBC;
import emp.model.User;

public class UserModel extends AbstractTableModel {
	
	private IUserDao ud;
	private Vector<String> coloumnName;
	private Vector<Vector<String>> rowData;
	
	public Vector<Vector<String>> getRowData() {
		return rowData;
	}

	public void setRowData(Vector<Vector<String>> rowData) {
		this.rowData = rowData;
	}

	public UserModel() {
		ud = DAOFactory.getUserDao();
		initial();
	}
	
	public IUserDao getUd() {
		return ud;
	}

	public void setUd(UserDao ud) {
		this.ud = ud;
	}

	public void initial(){
		coloumnName = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		coloumnName.add("Username");
		coloumnName.add("Password");
		coloumnName.add("Nickname");
		Vector<String> ue = null;
		List<User> lists = ud.list();
		for(User u: lists){
			ue = new Vector<String>();
			ue.add(u.getUsername());
			ue.add(u.getPassword());
			ue.add(u.getNickname());
			rowData.add(ue);
		}
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rowData.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return coloumnName.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return rowData.get(rowIndex).get(columnIndex);
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return coloumnName.get(column);
	}

}
