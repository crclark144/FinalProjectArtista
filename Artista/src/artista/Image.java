package artista;

import java.io.*;
import java.util.*;


public class Image {
	
	String imageFilePath;
	private String imageName;
	private String imageDiscreption;
	private int likeCount;
	private List<String> comments;
	
	//create an image from the database.
	public Image( String name, String dis, int like, String imFilePath) {
		this.imageName = name;
		this.imageDiscreption = dis;
		this.likeCount = like;
		comments = new ArrayList<String>();
		this.imageFilePath = imFilePath;
	}
	
	
	public void setImageName(String s) {
		this.imageName = s;
	}
	public String getImageName() {
		return imageName;
	}
	
	public String getFilePath() {
		return this.imageFilePath;
	}
	
	
	public void setImageLike() {
		this.likeCount++;
	}
	
	public int getImageLikeCount() {
		return likeCount;
	}
	
	//adds a new comment and stores into database under profiles folder.
	public void addComment(String userName, String com) {
		comments.add(userName +"/n" + com);
	}
	
	//adds comments from database for that specified profile.
	public void addComment(String com) {
		comments.add(com);
	}
	
	
	public ArrayList<String> getComments(){
		return (ArrayList<String>) comments;
	}
	

	public String getImageDiscreption() {
		return imageDiscreption;
	}

	public void setImageDiscreption(String imageDiscreption) {
		this.imageDiscreption = imageDiscreption;
	}
	
}
