package emp.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import emp.DAO.DAOFactory;
import emp.DAO.IUserDao;
import emp.DAO.UserDao;
import emp.DAO.UserDaoJDBC;
import emp.model.User;
import emp.model.empException;
import emp.util.EmpUtil;

public class LoginFrame extends JFrame {
	private JPanel jp1,jp2,jp3;
	private JLabel jl1,jl2;
	private JTextField jtf;
	private JPasswordField jpf;
	private JButton jb;
	private IUserDao ud;
	
	public LoginFrame() {
		this.setTitle("Login");
		this.setSize(350, 200);
		this.setLocation(100, 100);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(3, 1));
		ud = DAOFactory.getUserDao();
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jl1 = new JLabel("Username:");
		jl2 = new JLabel("Password:");
		jtf = new JTextField(20);
		jpf = new JPasswordField(20);
		jb = new JButton("Login");
		jp1.add(jl1); jp1.add(jtf);
		jp2.add(jl2); jp2.add(jpf);
		jp3.add(jb);
		jb.addActionListener(new BtnClick());
		this.add(jp1); this.add(jp2); this.add(jp3);
		this.setVisible(true);
	}
	
	private void close(){
		this.setVisible(false);
	}
	
	private class BtnClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(e.getSource()==jb){
					String username = jtf.getText();
					String password = new String(jpf.getPassword());
					User u = ud.login(username, password);
					new ManagerFrame(u);
					close();
				}
			} catch (empException e1) {
				EmpUtil.showError(jp1, e1.getMessage());
			}
		}
		
	}
}
