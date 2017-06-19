package emp.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import emp.model.User;
import emp.model.empException;
import emp.util.EmpUtil;

public class UserPanel extends JPanel{
	private JLabel jl;
	private JPanel jp1, jp2;
	private JTable jt;
	private JButton jb1,jb2,jb3;
	private JScrollPane jsp;
	private UserModel um;
	private addDialog ad;
	private JFrame jf = new JFrame();
	private updateDialog ud;
	public UserPanel(JFrame jf) {
		this.jf = jf;
		this.setLayout(new BorderLayout());
		jp1 = new JPanel();
		jp2 = new JPanel();
		jl = new JLabel("User Infomation Table");
		jb1 = new JButton("Add User");
		jb2 = new JButton("Delete User");
		jb3 = new JButton("Update User");
		jb1.addActionListener(new ButtonClick());
		jb2.addActionListener(new ButtonClick());
		jb3.addActionListener(new ButtonClick());
		jp1.add(jl);
		jp2.add(jb1); jp2.add(jb2); jp2.add(jb3);
		ad = new addDialog();
		ud = new updateDialog();
		um = new UserModel();
		jt = new JTable(um);
		jsp = new JScrollPane(jt);
		this.add(jsp);
		this.add(jp1, BorderLayout.NORTH);
		this.add(jp2, BorderLayout.SOUTH);
	
	}
	
	//按增加、删除、更新用户按钮的事件
	private class ButtonClick implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == jb1){
				ad.setVisible(true);
			}else if(e.getSource() == jb2){
				deleteUser();
			}else if(e.getSource() == jb3){
				updateUser();
			}
		}
		
	}
	//删除用户
	private void deleteUser() {
		int [] row = null;
		row = jt.getSelectedRows();
		if(row.length == 0){
			EmpUtil.showError(jp1, "You must select one user");
			return;
		}
		for(int i:row){
			String username = um.getRowData().get(i).get(0);
			um.getUd().delete(username);
		}
//		if(row == -1){
//			EmpUtil.showError(jp1, "You must select one user");
//			return;
//		}
//		String username = um.getRowData().get(row).get(0);
//		um.getUd().delete(username);
		refreshData();
	}
	//更新用户
	private void updateUser() {
		int	row = jt.getSelectedRow();
		if(row < 0 ){
			EmpUtil.showError(jp1, "You must select one user");
			return;
		}
		String username = um.getRowData().get(row).get(0);
		ud.show(um.getUd().load(username));
		ud.setVisible(true);
		refreshData();
	}
	
	//刷新重置数据
	private void refreshData(){
		um.initial();
		jt.updateUI();
	}
	
	
	//点击更新用户后弹出的窗口
	private class updateDialog extends JDialog{
		private JLabel jl1,jl2,jl3;
		private JButton jb1,jb2;
		private JPanel jp1,jp2,jp3,jp4;
		private JTextField jtf;
		private JPasswordField jpf;
		private User user;
		public void show(User user){
			this.user = user;
			this.setTitle("Updating User:"+ user.getUsername());
			this.jl1.setText("Username: "+ user.getUsername());
			this.jpf.setText(user.getPassword());
			this.jtf.setText(user.getNickname());
		}
		
		public updateDialog() {
			this.setSize(400, 200);
			this.setLocation(jf.getX()+10, jf.getY()+10);
			this.setModal(true);
			this.setLayout(new GridLayout(4, 1));
			jl1 = new JLabel();
			jl2 = new JLabel("Password");
			jl3 = new JLabel("Nickname");
			
			jb1 = new JButton("Update");
			jb2 = new JButton("Cancel");
			jb1.addActionListener(new updateDialogClick());
			jb2.addActionListener(new updateDialogClick());
			jtf = new JTextField(20);
			jpf = new JPasswordField(20);
			
			jp1= new JPanel();jp2= new JPanel();jp3= new JPanel();jp4= new JPanel();
			
			jp1.add(jl1); 
			jp2.add(jl2); jp2.add(jpf);
			jp3.add(jl3); jp3.add(jtf);
			jp4.add(jb1); jp4.add(jb2);
			
			this.add(jp1);this.add(jp2);this.add(jp3);this.add(jp4);
		}
		
		private class updateDialogClick implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(e.getSource() == jb1){
						String password = jpf.getText();
						String nickname = jtf.getText();
						user.setPassword(password);
						user.setNickname(nickname);
						um.getUd().update(user);
						ud.setVisible(false); 
						reset();
						refreshData();
					}else if(e.getSource() == jb2){
						reset();
					}
				} catch (empException e1) {
					EmpUtil.showError(jp1, "The username is already exists");
				}
			}
			
		}

		private void reset() {
			jtf.setText(user.getNickname());
			jpf.setText(user.getPassword());
		}
	}
	
	//点击增加用户后弹出的窗口
	private class addDialog extends JDialog{
		private JLabel jl1,jl2,jl3;
		private JButton jb1,jb2;
		private JPanel jp1,jp2,jp3,jp4;
		private JTextField jtf1, jtf2;
		private JPasswordField jpf;
		
		public addDialog() {
			this.setTitle("Add User");
			this.setSize(400, 200);
			this.setLocation(jf.getX()+10, jf.getY()+10);
			this.setModal(true);
			this.setLayout(new GridLayout(4, 1));
			jl1 = new JLabel("Username");
			jl2 = new JLabel("Password");
			jl3 = new JLabel("Nickname");
			
			jb1 = new JButton("Add");
			jb2 = new JButton("Cancel");
			jb1.addActionListener(new addDialogClick());
			jb2.addActionListener(new addDialogClick());
			jtf1 = new JTextField(20);
			jtf2 = new JTextField(20);
			jpf = new JPasswordField(20);
			
			jp1= new JPanel();jp2= new JPanel();jp3= new JPanel();jp4= new JPanel();
			
			jp1.add(jl1); jp1.add(jtf1);
			jp2.add(jl2); jp2.add(jpf);
			jp3.add(jl3); jp3.add(jtf2);
			jp4.add(jb1); jp4.add(jb2);
			
			this.add(jp1);this.add(jp2);this.add(jp3);this.add(jp4);
		}
		
		
		
		private class addDialogClick implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(e.getSource() == jb1){
						String username = jtf1.getText();
						if(username == null || "".equals(username.trim())){
							EmpUtil.showError(jp1, "The username can not be empty");
							return;
						}
						String password = jpf.getText();
						String nickname = jtf2.getText();
						User u = new User();
						u.setUsername(username);
						u.setPassword(password);
						u.setNickname(nickname);
						um.getUd().add(u);
						ad.setVisible(false); 
						reset();
						refreshData();
					}else if(e.getSource() == jb2){
						reset();
					}
				} catch (empException e1) {
					EmpUtil.showError(jp1, "The username is already exists");
				}
			}
			
		}

		private void reset() {
			jtf1.setText("");
			jtf2.setText("");
			jpf.setText("");
		}
	}

	
	
	
}
