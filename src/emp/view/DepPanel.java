package emp.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import emp.model.Dep;
import emp.model.empException;
import emp.util.EmpUtil;

public class DepPanel extends JPanel {
	private JLabel jl;
	private JPanel jp1, jp2;
	private JTable jt;
	private JButton jb1,jb2,jb3;
	private JScrollPane jsp;
	private DepModel dm;
	private ManagerFrame jf;
	private addDialog ad;
	private updateDialog ud;
	
	public JTable getJt() {
		return jt;
	}

	public void setJt(JTable jt) {
		this.jt = jt;
	}

	public DepModel getDm() {
		return dm;
	}

	public void setDm(DepModel dm) {
		this.dm = dm;
	}

	public DepPanel(ManagerFrame jf) {
		this.jf = jf;
		this.setLayout(new BorderLayout());
		jp1 = new JPanel();
		jp2 = new JPanel();
		jl = new JLabel("Department Infomation Table");
		jb1 = new JButton("Add Dep");
		jb2 = new JButton("Delete Dep");
		jb3 = new JButton("Update Dep");
		jb1.addActionListener(new DepClick());
		jb2.addActionListener(new DepClick());
		jb3.addActionListener(new DepClick());
		dm = new DepModel();
		jt = new JTable(dm);
		jsp = new JScrollPane(jt);
		ad = new addDialog();
		ud = new updateDialog();
		this.add(jsp);
		jp1.add(jl);
		jp2.add(jb1); jp2.add(jb2); jp2.add(jb3);
		this.add(jp1, BorderLayout.NORTH);
		this.add(jp2, BorderLayout.SOUTH);
	}
	
	public void refreshData(boolean ref){
		 dm.initial();
		 jt.updateUI();
		 if(ref){
			 jf.getEp().refreshData(false);
		 }
 	}
	
	private class DepClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == jb1){
				//add
				ad.setVisible(true);
			}else if(e.getSource() == jb2){
				//delete
				deleteDep();
			}else if(e.getSource() == jb3){
				//update
				updateDep();
			}
		}
		
	}
	
	
	private void updateDep() {
		int row = jt.getSelectedRow();
		if(row<0) {
			EmpUtil.showError(jp1, "Choose one department to update!");
			return;
		} 
		int depId = Integer.parseInt(dm.getRowData().get(row).get(0));
		Dep dep = dm.getDd().load(depId);
		ud.show(dep);
	}
	
	private class updateDialog extends JDialog{
		private JPanel jp;
		private JLabel jl;
		private JTextField jtf;
		private JButton jb1,jb2;
		private Dep dep;
		
		private void reset(){
			jtf.setText("");
		}
		
		private void show(Dep dep){
			this.dep = dep;
			jtf.setText(dep.getName());
			jtf.setSelectionStart(0);
			jtf.setSelectionEnd(dep.getName().length());
			ud.setVisible(true);
		}
		
		public updateDialog() {
			this.setSize(300,150);
			this.setTitle("Update Department");
			this.setLocation(jf.getX()+10, jf.getY()+10);
			this.setModal(true);
			
			jp = new JPanel();
			jl = new JLabel("Department name:");
			jtf = new JTextField(20);
			jb1 = new JButton("Add");
			jb2 = new JButton("Reset");
			jb1.addActionListener(new ButtonClick());
			jb2.addActionListener(new ButtonClick());
			jp.add(jl);
			jp.add(jtf);
			jp.add(jb1);
			jp.add(jb2);
			this.add(jp);
		}
		
		private class ButtonClick implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == jb1){
					String name = jtf.getText();
					if(name==null ||"".equals(name.trim())){
						EmpUtil.showError(jp, "The department name can not be empty");
						return;
					}
					dep.setName(name);
					dm.getDd().update(dep) ;
					reset();
					refreshData(true);
					ud.setVisible(false);
				}else if(e.getSource()==jb2){
					jtf.setText(dep.getName());
				}
			}
			
		}
	}
	
	
	
	private void deleteDep() {
		try {
			int row = jt.getSelectedRow();
			if(row<0) {
				EmpUtil.showError(jp1, "Choose one department to delete!");
				return;
			}
			int depId = Integer.parseInt(dm.getRowData().get(row).get(0));
			dm.getDd().delete(depId);
			refreshData(true);
		} catch (empException e) {
			EmpUtil.showError(jp1, e.getMessage());
		}
	}
	
	

	
	
	private class addDialog extends JDialog{
		private JPanel jp;
		private JLabel jl;
		private JTextField jtf;
		private JButton jb;
		
		private void reset(){
			jtf.setText("");
		}
		
		public addDialog() {
			this.setSize(300,150);
			this.setTitle("Add Department");
			this.setLocation(jf.getX()+10, jf.getY()+10);
			this.setModal(true);
			
			jp = new JPanel();
			jl = new JLabel("Department name:");
			jtf = new JTextField(20);
			jb = new JButton("Add");
			jb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == jb){
						String name = jtf.getText();
						if(name==null ||"".equals(name.trim())){
							EmpUtil.showError(jp, "The department name can not be empty");
							return;
						}
						Dep d = new Dep();
						d.setName(name);
						dm.getDd().add(d);
						reset();
						refreshData(true);
						ad.setVisible(false);
					}
				}
			});
			jp.add(jl);
			jp.add(jtf);
			jp.add(jb);
			this.add(jp);
			
		}
	}
}
