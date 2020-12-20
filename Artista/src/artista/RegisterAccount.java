package artista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JPasswordField;

public class RegisterAccount {
	
	private Graph graph;
	private JFrame frame;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField email;
	private JTextField userName;
	private JPasswordField confirmPassword;
	private JPasswordField password;
	private JTextField bio;

	public RegisterAccount(Graph graph) throws IOException{
		this.graph = graph;
		initializeRegisterAccount();
		frame.setVisible(true);
	}
	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public void initializeRegisterAccount(){
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 191, 255));
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 500, 400);

		JLabel lblNewLabel = new JLabel("First");
		lblNewLabel.setBounds(31, 54, 73, 16);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(31, 92, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("UserName");
		lblNewLabel_2.setBounds(31, 130, 73, 16);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setBounds(31, 170, 61, 16);
		frame.getContentPane().add(lblNewLabel_3);

		firstName = new JTextField();
		firstName.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		firstName.setBounds(104, 49, 130, 26);
		frame.getContentPane().add(firstName);
		firstName.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Last");
		lblNewLabel_4.setBounds(267, 54, 38, 16);
		frame.getContentPane().add(lblNewLabel_4);

		lastName = new JTextField();
		lastName.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lastName.setBounds(311, 49, 130, 26);
		frame.getContentPane().add(lastName);
		lastName.setColumns(10);

		email = new JTextField();
		email.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		email.setBounds(104, 87, 348, 26);
		frame.getContentPane().add(email);
		email.setColumns(10);

		userName = new JTextField();
		userName.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		userName.setColumns(10);
		userName.setBounds(104, 125, 348, 26);
		frame.getContentPane().add(userName);

		password = new JPasswordField();
		password.setColumns(10);
		password.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		password.setBounds(103, 165, 349, 26);
		frame.getContentPane().add(password);

		confirmPassword = new JPasswordField();
		confirmPassword.setColumns(10);
		confirmPassword.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		confirmPassword.setBounds(104, 201, 348, 26);
		frame.getContentPane().add(confirmPassword);

		JLabel passwordValidation = new JLabel("");
		passwordValidation.setForeground(new Color(255, 255, 255));
		passwordValidation.setBounds(104, 239, 337, 16);
		frame.getContentPane().add(passwordValidation);

		JLabel lblPassword = new JLabel("Confirm Password");
		lblPassword.setBounds(31, 206, 73, 26);
		frame.getContentPane().add(lblPassword);
		
		bio = new JTextField();
		bio.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bio.setBounds(104, 267, 348, 66);
		frame.getContentPane().add(bio);
		bio.setColumns(10);

		JButton submitAccount = new JButton("SUBMIT");
		submitAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (!password.getText().equals(confirmPassword.getText()) || firstName == null || lastName == null || userName == null || email == null || password == null
							|| confirmPassword == null) {
						passwordValidation.setText("Passwords do not match or you left a blank field.");
						
					} else {
						int profileID = graph.createNewVertexAccountProfile(userName.getText(), password.getText(),
								firstName.getText(), lastName.getText(), email.getText(), bio.getText());
						if (profileID != 0) {
							frame.dispose();
							ProfileGUI profile = new ProfileGUI(graph, profileID);
							profile.initializeProfileGUI();
							JOptionPane.showMessageDialog(null, "valid registry.");
						} else {
							System.out.println("sorry wrong");
						}
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		submitAccount.setForeground(new Color(255, 255, 255));
		submitAccount.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		submitAccount.setBounds(188, 345, 117, 27);
		frame.getContentPane().add(submitAccount);

		JLabel lblNewLabel_5 = new JLabel("ARTISTA");
		lblNewLabel_5.setForeground(new Color(220, 20, 60));
		lblNewLabel_5.setFont(new Font("Malayalam MN", Font.PLAIN, 20));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(104, 6, 296, 26);
		frame.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Register New Account");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(110, 26, 290, 16);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Profile-Bio");
		lblNewLabel_7.setBounds(31, 267, 73, 16);
		frame.getContentPane().add(lblNewLabel_7);

	}
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}

}
