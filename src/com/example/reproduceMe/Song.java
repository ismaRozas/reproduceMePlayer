package com.example.reproduceMe;



public class Song {
	
	private String image;
	private String author;
	private String title;
	
	Song(String au, String ti){
		this.author = au;
		this.title = ti;
	}
	
	public void setImage(String img){
		this.image = img;
	}
	
	public void setAuthor(String auth){
		this.author = auth;
	}
	
	public void setTitle(String tit){
		this.title = tit;
	}
	
	public String getImage(){
		return this.image;
	}
	
	public String getAuthor(){
		return this.author;
	}

	public String getTitle(){
		return this.title;
	}
}
