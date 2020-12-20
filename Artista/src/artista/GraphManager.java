package artista;

import java.io.IOException;
import java.util.ArrayList;

public interface GraphManager {
	
	public int createNewVertexAccountProfile(String un, String pw, String fn, String ln, String email, String bioDiscreption) throws IOException;

	public boolean verifyUserID(int userID);
	
	public boolean verifyRegisteredProfile(String un, String em);
	
	public boolean verifyProfileLogIn( String un, String pw);
	
	public Vertex getProfileAccount(int ID);
	
	public Vertex getProfileAccountLogIn(String ID, String pw);
	
	public void deleteVertexAccount();

	
	public ArrayList<Vertex> getAllAccountsPicsHomeButton();

	public void updateVertexDataBase();
	
	public void updateEdgeDataBase();
	
	public void updateUserFolder();

	public void searchFollowers();

	public void getAllFollowers();

	public void stopFollowing();

	public void searchFollowing();

	public void getAllFollowing();

	public void follow();

	// maybe not have cause gen-search will do the same.
	public void searchPhoto();

	// search first for account/profile then search for photo
	public void gerneralSearch();

	public void getNumberOfVertices();
	
	public void getNumberOfEdges();
	

}
