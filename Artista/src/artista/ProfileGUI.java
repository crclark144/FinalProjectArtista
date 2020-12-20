package artista;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ProfileGUI{

	private Graph graph;
	private Vertex profileVertex;
	private JFrame frame;
	JLabel lblNewLabel;
	JLabel profileImage;
	JLabel username;
	JLabel bioDiscrep;

	/**
	 * Create the frame.
	 */
	public ProfileGUI(Graph graph, int user) {
		this.graph = graph;
		this.profileVertex = graph.getProfileAccount(user);
		initializeProfileGUI();
		frame.setVisible(true);
	}
	
	public void initializeProfileGUI() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 191, 255));
		frame.getContentPane().setLayout(null);
		
		this.lblNewLabel = new JLabel("ARTISTA");
		lblNewLabel.setForeground(new Color(178, 34, 34));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Malayalam MN", Font.BOLD, 60));
		lblNewLabel.setBounds(183, 55, 641, 84);
		frame.getContentPane().add(lblNewLabel);
		
		this.profileImage = new JLabel("");
		profileImage.setBounds(54, 175, 219, 223);
		frame.getContentPane().add(profileImage);
		
		this.username = new JLabel(profileVertex.getUserVertex().getUserName());
		username.setForeground(new Color(25, 25, 112));
		username.setBounds(326, 175, 125, 25);
		frame.getContentPane().add(username);
		
		bioDiscrep = new JLabel(profileVertex.getUserVertex().getProfileBio());
		bioDiscrep.setBounds(298, 212, 476, 241);
		frame.getContentPane().add(bioDiscrep);
		frame.setBounds(400, 200, 1000, 1000);
	}



}
