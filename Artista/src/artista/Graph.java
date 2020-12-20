package artista;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

class Edge {
	private static int totalNumEdges;
	private Vertex userA;
	private Vertex userB;

	public Edge(Vertex a, Vertex b) {
		this.userA = a;
		this.userB = b;
	}

	public void neighbors() {
		totalNumEdges++;
		userA.setLLVertexFollowing(userB);
		userB.setLLVertexFollowers(userA);
	}

	public static int getTotalNumEdges() {
		return totalNumEdges;
	}
}

class Vertex {

	private static int numVertices = 0;
	private Profile vertexProfile;
	private LinkedList<Vertex> llVertexFollowing;
	private LinkedList<Vertex> llVertexFollowers;
	private int followersEdges = 0;
	private int followingEdges = 0;

	/**
	 * creates a new vertex for a new user at login/registor time.
	 * 
	 * @param un
	 * @param fn
	 * @param ln
	 * @param email
	 * @throws IOException
	 */
	public Vertex(int uID, String un, String pw, String fn, String ln, String email) throws IOException {
		this.vertexProfile = new Profile(uID, un, pw, fn, ln, email);
		llVertexFollowing = new LinkedList<Vertex>();
		llVertexFollowers = new LinkedList<Vertex>();
		numVertices++;
	}
	
	public Vertex(int uID, String un, String pw, String fn, String ln, String email, String bioDis) throws IOException {
		this.vertexProfile = new Profile(uID, un, pw, fn, ln, email, bioDis);
		llVertexFollowing = new LinkedList<Vertex>();
		llVertexFollowers = new LinkedList<Vertex>();
		numVertices++;
	}

	/**
	 * Creates a vertex for each username found in the database.
	 * 
	 * @param un
	 */
	public Vertex(Profile un) {
		this.vertexProfile = un;
		llVertexFollowing = new LinkedList<Vertex>();
		llVertexFollowers = new LinkedList<Vertex>();
	}

	public Profile getUserVertex() {
		return this.vertexProfile;
	}

	public LinkedList<Vertex> getLLVertexFollowing() {
		return this.llVertexFollowing;
	}

	public void setLLVertexFollowing(Vertex e) {
		llVertexFollowing.add(e);
		followingEdges++;
	}

	public LinkedList<Vertex> getLLVertexFollowers() {
		return this.llVertexFollowers;
	}

	public void setLLVertexFollowers(Vertex e) {
		llVertexFollowers.add(e);
		followersEdges++;
	}

	public int getFollowersEdges() {
		return followersEdges;
	}

	public int getFollowingEdges() {
		return followingEdges;
	}
	
	public int getNumVertices() {
		return this.numVertices;
	}

}

public class Graph implements GraphManager {

	int numLines = 0;
	String line = "";
	List<Vertex> adjLists = new ArrayList<Vertex>();
	int edges = Edge.getTotalNumEdges();

	public Graph() throws IOException {
		String dataBaseFile = "/Users/coreyclark/Documents/CSIS 2420 PROJECTS/Artista/DATABASE-Artista/DatabaseVertex.csv";
		String edgeFile = "/Users/coreyclark/Documents/CSIS 2420 PROJECTS/Artista/DATABASE-Artista/Edges.csv";
		String accountFile = "/Users/coreyclark/Documents/CSIS 2420 PROJECTS/Artista/DATABASE-Artista/";
		BufferedReader reader = new BufferedReader(new FileReader(dataBaseFile));
		BufferedReader br1 = new BufferedReader(new FileReader(dataBaseFile));
		BufferedReader br2 = new BufferedReader(new FileReader(edgeFile));

		// counts amount of vertices in database.
		System.out.print("Counting lines in the data file ... ");
		while (reader.readLine() != null) {
			numLines++;
		}
		reader.close();
		System.out.println(numLines);

		// builds arrayList of vertices/accounts.
		for (int i = 0; i < numLines; i++) {
			line = br1.readLine();
			if (line != null) {
				String[] userValues = line.split(",");
				int uID = Integer.parseInt(userValues[0]);
				String un = userValues[1];
				String pw = userValues[2];
				String fn = userValues[3];
				String ln = userValues[4];
				String email = userValues[5];
				adjLists.add(new Vertex(uID, un, pw, fn, ln, email));
			}
		}
		br1.close();

		// sets all edges to each vertex's Linked List.
		while ((line = br2.readLine()) != null) {
			String[] edgeValues = line.split(",");
			int a = Integer.parseInt(edgeValues[0]);
			int b = Integer.parseInt(edgeValues[1]);
			Vertex follower = indexForID(a);
			Vertex following = indexForID(b);
			Edge e = new Edge(follower, following);
			e.neighbors();
		}

	}

	// place try catch if adjList if adjlists is null.
	public Vertex indexForID(int ID) {
		Vertex n = new Vertex(null);
		Vertex l = new Vertex(null);
		try {
			for (Vertex adjList : adjLists) {
				l = adjList;
				if (l.getUserVertex().getUserIDNum() == ID) {
					return l;
				} else
					break;
			}
		} catch (NullPointerException e) {
			System.out.println("there is no user accounts in the graph Structure.");
		}
		return n;
	}

	@Override
	public int createNewVertexAccountProfile(String un, String pw, String fn, String ln, String email, String bioDiscreption)
			throws IOException {
		int account = 0;
		int uID = new Random().nextInt(9000) + 1000;
		while(verifyUserID(uID) == true) {
			uID = new Random().nextInt(9000) + 1000;
		}
		if(verifyRegisteredProfile(un, email) == true) {
			account = 0;
			return account;
		}else {
			adjLists.add(new Vertex(uID, un, pw, fn, ln, email, bioDiscreption));
			account = uID;
		}
		return account;
	}
	
	@Override
	public boolean verifyUserID(int userID) {
		Vertex l = new Vertex(null);
		boolean id = false;
		for (Vertex adjList : adjLists) {
			l = adjList;
			if (l.getUserVertex().getUserIDNum() == userID) {
				id = true;
			}
		}
		return id;
	}

	@Override
	public boolean verifyRegisteredProfile(String un, String em) {
		// traverse using adjLists for loop.
		Vertex l = new Vertex(null);
		boolean accountFound = false;
		boolean userName = false;
		boolean email = false;
		for (Vertex adjList : adjLists) {
			l = adjList;
			
			if (l.getUserVertex().getUserName().equalsIgnoreCase(un)) {
				userName = true;
			}
			if (l.getUserVertex().getEmail().equalsIgnoreCase(em)) {
				email = true;
			}
		}

		if (userName == true || email == true) {
			accountFound = true;
		}

		return accountFound;

	}
	
	@Override
	public boolean verifyProfileLogIn( String un, String pw) {
		// traverse using adjLists for loop.
		Vertex l = new Vertex(null);
		boolean accountFound = false;
		boolean userName = false;
		boolean password = false;
		for (Vertex adjList : adjLists) {
			l = adjList;
			if (l.getUserVertex().getUserName().equals(un)) {
				userName = true;
			}
			if (l.getUserVertex().getPassword().equals(pw)) {
				password = true;
			}
		}

		if (userName == true && password == true) {
			accountFound = true;
		}

		return accountFound;

	}
	
	@Override
	public Vertex getProfileAccount(int ID) {
		Vertex l = new Vertex(null);
		
		for (Vertex adjList : adjLists) {
			l = adjList;
			if (l.getUserVertex().getUserIDNum() == ID) {
				return l;
			}
		}
		return l;
	}
	
	@Override
	public Vertex getProfileAccountLogIn(String ID, String pw) {
		Vertex l = new Vertex(null);
		
		for (Vertex adjList : adjLists) {
			l = adjList;
			if (l.getUserVertex().getUserName().equals(ID) && l.getUserVertex().getPassword().equals(pw)) {
				return l;
			}
		}
		return l;
	}

	@Override
	public void deleteVertexAccount() {
		// traverse using adjLists for loop.
		// because there may be an account that has no followers or people following.

	}

	@Override
	public ArrayList<Vertex> getAllAccountsPicsHomeButton() {
		return null;

	}

	@Override
	public void updateVertexDataBase() {

	}

	@Override
	public void updateEdgeDataBase() {

	}

	@Override
	public void updateUserFolder() {

	}

	@Override
	public void searchFollowers() {

	}

	@Override
	public void getAllFollowers() {

	}

	@Override
	public void searchFollowing() {

	}

	@Override
	public void getAllFollowing() {

	}

	@Override
	public void stopFollowing() {

	}

	@Override
	public void follow() {

	}

	@Override
	public void gerneralSearch() {
		// Use a BFS to search entire graph for photo or name whatever.
		// check level by level for the specific name, pic or whatever starting with the
		// users.

	}

	@Override
	public void getNumberOfVertices() {

	}

	@Override
	public void getNumberOfEdges() {

	}

	@Override
	public void searchPhoto() {
		// TODO Auto-generated method stub

	}


}
