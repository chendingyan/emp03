package emp.view;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import emp.DAO.DepDao;
import emp.model.Dep;

public class DepModel extends AbstractTableModel {
	
	private DepDao dd;
	private Vector<String> coloumnName;
	private Vector<Vector<String>> rowData;
	
	public Vector<Vector<String>> getRowData() {
		return rowData;
	}

	public void setRowData(Vector<Vector<String>> rowData) {
		this.rowData = rowData;
	}

	public DepModel() {
		dd = new DepDao();
		initial();
	}
	
	public DepDao getDd() {
		return dd;
	}

	public void setDd(DepDao dd) {
		this.dd = dd;
	}

	public void initial(){
		coloumnName = new Vector<String>();
		rowData = new Vector<Vector<String>>();
		coloumnName.add("Id");
		coloumnName.add("Name");
		coloumnName.add("Employee Num");
		Vector<String> ue = null;
		List<Dep> lists = dd.list();
		for(Dep dep: lists){
			ue = new Vector<String>();
			ue.add(String.valueOf(dep.getId()));
			ue.add(dep.getName());
			ue.add(String.valueOf(dd.getEdNum(dep.getId())));
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
