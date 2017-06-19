package emp.util;

import java.awt.Component;

import javax.swing.JOptionPane;

public class EmpUtil {
	public static void showError(Component parent, String message){
		JOptionPane.showMessageDialog(parent, message,"Error", JOptionPane.ERROR_MESSAGE);
	}
}
