package com.internship.catalogue;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		String currentTime = sdf.format(dt);
		
		System.out.println(currentTime);
	}

}
