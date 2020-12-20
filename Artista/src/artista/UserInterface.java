package artista;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class UserInterface {

	private JFrame frame;
	private JTextField userName;
	private JPasswordField password;
	private Graph graph;

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public UserInterface() throws IOException {
		graph = new Graph();
		initializeLogIn();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeLogIn() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 191, 255));
		frame.getContentPane().setLayout(null);
		frame.setBounds(400, 200, 429, 309);

		userName = new JTextField();
		userName.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		userName.setBounds(144, 91, 130, 26);
		frame.getContentPane().add(userName);
		userName.setColumns(10);

		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Malayalam MN", Font.BOLD, 15));
		lblNewLabel.setBounds(175, 63, 61, 16);
		frame.getContentPane().add(lblNewLabel);

		JLabel validation = new JLabel("");
		validation.setForeground(new Color(255, 0, 0));
		validation.setBounds(144, 160, 130, 16);
		frame.getContentPane().add(validation);

		JButton logIn = new JButton("LOGIN");
		logIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (userName == null || password == null
						|| graph.verifyProfileLogIn(userName.getText(), password.getText()) == false) {
					validation.setText("Invalid username or password.");
				} else {
					// go to profile not JoptionPane
					Vertex ver = graph.getProfileAccountLogIn(userName.getText(), password.getText());
					ProfileGUI profile = new ProfileGUI(graph, ver.getUserVertex().getUserIDNum());
					frame.dispose();
					profile.initializeProfileGUI();
					JOptionPane.showMessageDialog(null, "valid entry");
					;
				}

			}
		});
		logIn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		logIn.setForeground(new Color(255, 255, 255));
		logIn.setFont(new Font("Malayalam MN", Font.PLAIN, 13));
		logIn.setBackground(new Color(255, 255, 255));
		logIn.setBounds(156, 178, 108, 29);
		frame.getContentPane().add(logIn);

		JLabel lblNewLabel_1 = new JLabel("Create a new account?");
		lblNewLabel_1.setBounds(128, 216, 144, 16);
		frame.getContentPane().add(lblNewLabel_1);

		JButton register = new JButton("Register");
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				RegisterAccount account;
				try {
					account = new RegisterAccount(graph);
					account.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		register.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		register.setForeground(new Color(255, 255, 255));
		register.setBackground(new Color(255, 255, 255));
		register.setFont(new Font("Malayalam MN", Font.BOLD, 10));
		register.setBounds(276, 212, 70, 20);
		frame.getContentPane().add(register);

		JLabel lblNewLabel_2 = new JLabel("UserName");
		lblNewLabel_2.setBounds(49, 96, 83, 16);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(49, 134, 83, 16);
		frame.getContentPane().add(lblPassword);

		JLabel lblNewLabel_3 = new JLabel("ARTISTA");
		lblNewLabel_3.setForeground(new Color(178, 34, 34));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Trattatello", Font.BOLD, 30));
		lblNewLabel_3.setBounds(32, 19, 371, 32);
		frame.getContentPane().add(lblNewLabel_3);

		password = new JPasswordField();
		password.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		password.setBounds(144, 129, 130, 26);
		frame.getContentPane().add(password);

	}

}
