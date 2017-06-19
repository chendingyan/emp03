package emp.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import emp.model.User;

public class ManagerFrame extends JFrame {
	
	private JMenuBar jmb;
	private JMenu jm1,jm2,jm3,jm4;
	private JMenuItem jmi1, jmi2, jmi3,jmi4;
	private JPanel jp;
	private UserPanel up;
	private DepPanel dp;
	private EmpPanel ep;
	private User admin;
	public UserPanel getUp() {
		return up;
	}

	public void setUp(UserPanel up) {
		this.up = up;
	}

	public DepPanel getDp() {
		return dp;
	}

	public void setDp(DepPanel dp) {
		this.dp = dp;
	}

	public EmpPanel getEp() {
		return ep;
	}

	public void setEp(EmpPanel ep) {
		this.ep = ep;
	}

	public ManagerFrame(User admin) {
		
		this.setSize(700, 600);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.admin = admin;
		this.setTitle("Welcome to Employee System, "+this.admin+"!");
		jmb = new JMenuBar();
		jm1 = new JMenu("Department Management");
		jm2 = new JMenu("Employee Management");
		jm3 = new JMenu("User Management");
		jm4 = new JMenu("Exit");
		jmi1 = new JMenuItem("Department Info Management");
		jmi2 = new JMenuItem("Employee Info Management");
		jmi3 = new JMenuItem("User Info Management");
		jmi4 = new JMenuItem("Exit");
		jm1.add(jmi1);
		jm2.add(jmi2);
		jm3.add(jmi3);
		jm4.add(jmi4);
		jmb.add(jm1); jmb.add(jm2); jmb.add(jm3); jmb.add(jm4);
		this.add(jmb, BorderLayout.NORTH);
		jmi1.addActionListener(new MenuClick());
		jmi2.addActionListener(new MenuClick());
		jmi3.addActionListener(new MenuClick());
		jmi4.addActionListener(new MenuClick());
		up = new UserPanel(this);
		ep = new EmpPanel(this);
		dp = new DepPanel(this);
		jp = new JPanel(new BorderLayout());
		
		this.add(jp);
		this.setVisible(true);
		
	}
	
	private void close(){
		this.setVisible(false);
		new LoginFrame();
	}
	
	private class MenuClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			jp.removeAll();
			if(e.getSource() == jmi1){
				jp.add(dp);
			}else if(e.getSource() == jmi2){
				jp.add(ep);
			}else if(e.getSource() == jmi3){
				jp.add(up);
			}else if(e.getSource() == jmi4){
				close();
			}
			jp.updateUI();
		}
		
	}
}
