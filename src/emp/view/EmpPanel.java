package emp.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import emp.model.Dep;
import emp.model.Emp;
import emp.model.empException;
import emp.util.EmpUtil;

public class EmpPanel extends JPanel {
	private JLabel jl1,jl2,jl3;
	private JPanel jp1, jp2;
	private JTable jt;
	private JButton jb1,jb2,jb3,jb4;
	private JScrollPane jsp;
	private EmpModel em;
	private ManagerFrame jf;
	private addDialog ad;
	private updateDialog ud;
	private JTextField jtf;
	private JComboBox jcb;
	private DefaultComboBoxModel cbm;
	
	public EmpPanel(ManagerFrame jf) {
		this.jf = jf;
		this.setLayout(new BorderLayout());
		em = new EmpModel();
		jt = new JTable(em);
		jsp = new JScrollPane(jt);
		ad = new addDialog();
		ud = new updateDialog(); 
		jp1 = new JPanel();
		jp2 = new JPanel();
		jl1 = new JLabel("Employee Infomation Table");
		jl2 = new JLabel("Department:");
		jl3 = new JLabel("Filter Name:");
		jb1 = new JButton("Add Emp");
		jb2 = new JButton("Delete Emp");
		jb3 = new JButton("Update Emp");
		jb4 = new JButton("Filter");
		jtf = new JTextField(10);
		jb1.addActionListener(new EmpClick());
		jb2.addActionListener(new EmpClick());
		jb3.addActionListener(new EmpClick());
		jb4.addActionListener(new EmpClick());
		jcb = new JComboBox( );
		initialDep();
		jp1.add(jl1); jp1.add(jl2); jp1.add(jcb); jp1.add(jl3); jp1.add(jtf); jp1.add(jb4);
		jp2.add(jb1); jp2.add(jb2); jp2.add(jb3);
		this.add(jsp);
		this.add(jp1, BorderLayout.NORTH);
		this.add(jp2, BorderLayout.SOUTH);
	}
	
	private void initialDep(){
		List<Dep> allList = em.getDd().list();
		Vector<Dep> v = new Vector<Dep>();
		Dep dep = new Dep();
		dep.setId(0);
		dep.setName("All Deps");
		v.add(dep);
		v.addAll(allList);
		cbm = new DefaultComboBoxModel(v);
		jcb.setModel(cbm);
	}
	
	
	private class EmpClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==jb1){
				//add
				ad.reread();
				ad.setVisible(true);
			}else if(e.getSource()==jb2){
				//delete
				deleteEmp();
			}else if(e.getSource()==jb3){
				//update
				updateDep();
			}else if(e.getSource()==jb4){
				filter();
			}
		}
	}
	
	private void filter() {
		int depId =((Dep)jcb.getSelectedItem()).getId();
		String name = jtf.getText();
		em.initial(depId, name);
		jt.updateUI();
	}
	
	private void deleteEmp(){
		try {
			int confirm = JOptionPane.showConfirmDialog(jp1, "Are you sure to delete?");
			if(confirm==JOptionPane.YES_OPTION){
				int row = jt.getSelectedRow();
				if(row<0) {
					EmpUtil.showError(jp1, "Choose one employee to delete!");
					return;
				}
				int id= Integer.parseInt(em.getRowData().get(row).get(0));
				em.getEd().delete(id);
				refreshData(true);
			}
		} catch (empException e) {
			EmpUtil.showError(jp1, e.getMessage());
		}
	}
	
	public void refreshData(Boolean ref) {
		em.initial();
		jt.updateUI();
		initialDep();
		ud.reread();
		if(ref){
			jf.getDp().refreshData(false);
		}
	}
	
	private class addDialog extends JDialog{
		private JPanel jp1,jp2,jp3,jp4,jp5;
		private JLabel jl1,jl2,jl3,jl4;
		private JTextField jtf1,jtf2;
		private JButton jb1,jb2;
		private JRadioButton jrb1,jrb2;
		private JComboBox jcb;
		private DefaultComboBoxModel cbm;
		private ButtonGroup bg;
		
		public void reread(){
			List<Dep> allDep = em.getDd().list();
			Vector<Dep> vd = new Vector<Dep>(allDep);
			cbm = new DefaultComboBoxModel (vd);
			jcb.setModel(cbm);
		}
		
		public addDialog() {
			this.setSize(350,280);
			this.setModal(true);
			this.setTitle("Add Employee");
			this.setLocation(jf.getX()+10, jf.getY()+10);
			
			jp1 = new JPanel();
			jp2 = new JPanel();
			jp3 = new JPanel();
			jp4 = new JPanel();
			jp5 = new JPanel();
			jl1 = new JLabel("Name:");
			jl2 = new JLabel("Sex:");
			jl3 = new JLabel("Salary:");
			jl4 = new JLabel("Department:");
			jtf1 = new JTextField(20);
			jtf2 = new JTextField(20);
			jb1 = new JButton("Add");
			jb2 = new JButton("Reset");
			jb1.addActionListener(new addClick());
			jb2.addActionListener(new addClick());
			jcb = new JComboBox ();
			reread();
			jcb.setModel(cbm); 
			jrb1 = new JRadioButton("Male",true);
			jrb2 = new JRadioButton("Female");
			bg = new ButtonGroup();
			bg.add(jrb1); bg.add(jrb2);
			this.setLayout(new GridLayout(5, 1));
			jp1.add(jl1); jp1.add(jtf1);
			jp2.add(jl2);
			jp2.add(jrb1); jp2.add(jrb2);
			jp3.add(jl3); jp3.add(jtf2);
			jp4.add(jl4); jp4.add(jcb);
			jp5.add(jb1); jp5.add(jb2);
			this.add(jp1);this.add(jp2);this.add(jp3);this.add(jp4);this.add(jp5);
			
		}
		
		private class addClick implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==jb1){
					//add
					String name = jtf1.getText();
					String salary = jtf2.getText();
					if(name==null || "".equals(name.trim())){
						EmpUtil.showError(jp1, "The employee name can not be empty!");
						return;
					}
					String reg = "\\d+\\.?\\d+";
					if(!salary.matches(reg)){
						EmpUtil.showError(jp1, "The salary must be a number!");
						return;
					}
					int depId = ((Dep)jcb.getSelectedItem()).getId();
					String sex = "Male";
					if(jrb2.isSelected()){
						sex = "Female";
					}
					Emp emp = new Emp();
					emp.setName(name);
					emp.setSalary(Double.parseDouble(salary));
					emp.setSex(sex);
					em.getEd().add(emp, depId);
					refreshData(true);
					reset();
					ad.setVisible(false);
				}else if(e.getSource()==jb2){
					reset();
				}
			}
		}
		
		private void reset(){
			jtf1.setText("");
			jtf2.setText("");
			jrb1.setSelected(true);
			jcb.setSelectedIndex(0);
		}
		
	}
	
	private void updateDep() {
		int row = jt.getSelectedRow();
		if(row<0) {
			EmpUtil.showError(jp1, "Choose one employee to update!");
			return;
		} 
		int id = Integer.parseInt(em.getRowData().get(row).get(0));
		Emp emp = em.getEd().load(id);
		ud.show(emp);
	}
	
	
	
	private class updateDialog extends JDialog{
		private JPanel jp1,jp2,jp3,jp4,jp5;
		private JLabel jl1,jl2,jl3,jl4;
		private JTextField jtf1,jtf2;
		private JButton jb1,jb2;
		private JRadioButton jrb1,jrb2;
		private JComboBox jcb;
		private DefaultComboBoxModel cbm;
		private ButtonGroup bg;
		private Emp emp;
		
		public void reread(){
			List<Dep> allDep = em.getDd().list();
			Vector<Dep> vd = new Vector<Dep>(allDep);
			cbm = new DefaultComboBoxModel (vd);
			jcb.setModel(cbm);
		}
		
		public updateDialog() {
			this.setSize(350,280);
			this.setModal(true);
			this.setTitle("Update Employee");
			this.setLocation(jf.getX()+10, jf.getY()+10);
			
			jp1 = new JPanel();
			jp2 = new JPanel();
			jp3 = new JPanel();
			jp4 = new JPanel();
			jp5 = new JPanel();
			jl1 = new JLabel("Name:");
			jl2 = new JLabel("Sex:");
			jl3 = new JLabel("Salary:");
			jl4 = new JLabel("Department:");
			jtf1 = new JTextField(20);
			jtf2 = new JTextField(20);
			jb1 = new JButton("Update");
			jb2 = new JButton("Reset");
			jb1.addActionListener(new updateClick());
			jb2.addActionListener(new updateClick());
			jcb = new JComboBox ();
			reread();
			jcb.setModel(cbm); 
			jrb1 = new JRadioButton("Male",true);
			jrb2 = new JRadioButton("Female");
			bg = new ButtonGroup();
			bg.add(jrb1); bg.add(jrb2);
			this.setLayout(new GridLayout(5, 1));
			jp1.add(jl1); jp1.add(jtf1);
			jp2.add(jl2);
			jp2.add(jrb1); jp2.add(jrb2);
			jp3.add(jl3); jp3.add(jtf2);
			jp4.add(jl4); jp4.add(jcb);
			jp5.add(jb1); jp5.add(jb2);
			this.add(jp1);this.add(jp2);this.add(jp3);this.add(jp4);this.add(jp5);
			
		}
		
		public void show(Emp emp){
			this.emp = emp;
			reset();
			ud.setVisible(true);
		}
		
		
		private class updateClick implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==jb1){
					//add
					String name = jtf1.getText();
					String salary = jtf2.getText();
					if(name==null || "".equals(name.trim())){
						EmpUtil.showError(jp1, "The employee name can not be empty!");
						return;
					}
					String reg = "\\d+\\.?\\d+";
					if(!salary.matches(reg)){
						EmpUtil.showError(jp1, "The salary must be a number!");
						return;
					}
					int depId = ((Dep)jcb.getSelectedItem()).getId();
					String sex = "Male";
					if(jrb2.isSelected()){
						sex = "Female";
					}
					emp.setName(name);
					emp.setSalary(Double.parseDouble(salary));
					emp.setSex(sex);
					em.getEd().update(emp, depId);
					refreshData(true);
					reset();
					ud.setVisible(false);
				}else if(e.getSource()==jb2){
					reset();
				}
			}
		}
		
		private void reset(){
			jtf1.setText(emp.getName());
			jtf2.setText(String.valueOf(emp.getSalary()));
			if(emp.getSex().equals("Male")){
				jrb1.setSelected(true);
			}else{
				jrb2.setSelected(true);
			}
			jcb.setSelectedItem(em.getDd().load(emp.getDepId()));
		}
		
	}
}
