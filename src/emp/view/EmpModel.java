package emp.view;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import emp.DAO.DepDao;
import emp.DAO.EmpDao;
import emp.model.Emp;

public class EmpModel extends AbstractTableModel {
	
	private EmpDao ed;
	private DepDao dd;
	private Vector<String> coloumnName;
	private Vector<Vector<String>> rowData;
	
	public Vector<Vector<String>> getRowData() {
		return rowData;
	}

	public void setRowData(Vector<Vector<String>> rowData) {
		this.rowData = rowData;
	}

	public DepDao getDd() {
		return dd;
	}

	public void setDd(DepDao dd) {
		this.dd = dd;
	}

	public EmpModel() {
		dd = new DepDao(); 
		ed = new EmpDao();
		initial();
	}
	
	public EmpDao getEd() {
		return ed;
	}

	public void setEd(EmpDao ed) {
		this.ed = ed;
	}

	public void initial(){
		coloumnName = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		coloumnName.add("Id");
		coloumnName.add("Name");
		coloumnName.add("Sex");
		coloumnName.add("Salary");
		coloumnName.add("Department");
		Vector<String> ue = null;
		List<Emp> lists = ed.list();
		for(Emp emp: lists){
			ue = new Vector<String>();
			ue.add(String.valueOf(emp.getId()));
			ue.add(emp.getName());
			ue.add(emp.getSex());
			ue.add(String.valueOf(emp.getSalary()));
			ue.add(dd.load(emp.getDepId()).getName());
			rowData.add(ue);
		}
	}
	
	public void initial(int depId, String name){
		coloumnName = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		coloumnName.add("Id");
		coloumnName.add("Name");
		coloumnName.add("Sex");
		coloumnName.add("Salary");
		coloumnName.add("Department");
		Vector<String> ue = null;
		List<Emp> lists = ed.list(depId,name);
		for(Emp emp: lists){
			ue = new Vector<String>();
			ue.add(String.valueOf(emp.getId()));
			ue.add(emp.getName());
			ue.add(emp.getSex());
			ue.add(String.valueOf(emp.getSalary()));
			ue.add(dd.load(emp.getDepId()).getName());
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
