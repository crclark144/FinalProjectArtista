package artista;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Profile extends Accounts {

	private String profileImagePath;
	private String accountFilePath;
	private String profileImageName;
	private String bioDiscreption;
	private List<Image> portfolio = new ArrayList<Image>();

	// constructs profile from the database, reads all content from database for
	// that profile.
	public Profile(int uID, String un, String pw, String fn, String ln, String email) throws IOException {
		super(uID, un, pw, fn, ln, email);
		String line = "";

		accountFilePath = "/Users/coreyclark/Documents/CSIS 2420 PROJECTS/Artista/DATABASE-Artista/userID-" + uID
				+ "/Profile.csv";
		String imageFile = "/Users/coreyclark/Documents/CSIS 2420 PROJECTS/Artista/DATABASE-Artista/userID-" + uID
				+ "/";
		BufferedReader reader = new BufferedReader(new FileReader(accountFilePath));

		// sets all edges to each vertex's Linked List.
		line = reader.readLine();
		String[] arrProfile = line.split(",");

		if (arrProfile.length > 1) {
			this.profileImageName = arrProfile[0];
			this.profileImagePath = imageFile + profileImageName;
			this.bioDiscreption = arrProfile[1];

			do {
				line = reader.readLine();
				if (line != null) {
					Image im;
					String[] imageInfo = line.split(",");
					String imFileName = imageInfo[0];
					int likes = Integer.parseInt(imageInfo[1]);
					String imageDis = imageInfo[2];
					im = new Image(imFileName, imageDis, likes, imageFile + imFileName);

					portfolio.add(im);
					for (int x = 2; x < imageInfo.length; x++) {
						String comment = imageInfo[x];
						im.addComment(comment);
					}
				}
			} while (line != null);
		}
	}

	public Profile(int uID, String un, String pw, String fn, String ln, String email, String bioDiscreption)
			throws IOException {
		super(uID, un, pw, fn, ln, email);

		String dataBaseFile = "/Users/coreyclark/Documents/CSIS 2420 PROJECTS/Artista/DATABASE-Artista/";
		String dataBaseFileName = "DatabaseVertex.csv";
		String profile = "Profile.csv";
		this.bioDiscreption = bioDiscreption.toString();
		this.accountFilePath = "/Users/coreyclark/Documents/CSIS 2420 PROJECTS/Artista/DATABASE-Artista/userID-" + uID
				+ "/" + profile;
		String path = new String(
				"/Users/coreyclark/Documents/CSIS 2420 PROJECTS/Artista/DATABASE-Artista/userID-" + uID);
		File accountsFile = new File(path);
		accountsFile.mkdirs();

		try {
			FileWriter csvWriter = new FileWriter(accountsFile + "/" + profile);
			csvWriter.write(this.bioDiscreption.toString());
			csvWriter.close();
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		FileWriter csvWriter = new FileWriter(dataBaseFile + dataBaseFileName, true);
		BufferedWriter bw = new BufferedWriter(csvWriter);
		PrintWriter printWrite = new PrintWriter(bw);

		String userID = Integer.toString(uID);
		printWrite.println();
		printWrite.println(userID + "," + un + "," + pw + "," + fn + "," + ln + "," + email);
		printWrite.flush();
		printWrite.close();

	}

	public String getProfileBio() {
		return this.bioDiscreption;
	}

	public void setProfileBio(String s) {
		this.bioDiscreption = s;
	}

	public ArrayList<String> getCommentsFromImage(String imageName) {
		for (Image element : portfolio) {
			if (element.getImageName().equals(imageName)) {
				return element.getComments();
			} else
				return null;
		}
		return null;
	}

	public void uploadNewPortfolioImage() {

	}

	/*
	 * public String getProfileImagePath() { return ImagePath; }
	 * 
	 * public void setProfileImagePath(String profPath) { this.ImagePath = profPath;
	 * }
	 */

}
