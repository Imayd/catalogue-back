package com.internship.catalogue;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		java.util.Date dt = new java.util.Date();
		
		System.out.println(dt);

		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		String currentTime = sdf.format(dt);
		
		System.out.println(currentTime);
		
		Locale locale = new Locale("fr", "FR");
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
		String date = dateFormat.format(new Date());
		System.out.print(date);
		System.out.println("hey");
		System.out.println(new Date());
		//System.out.println(new Date(date));
		System.out.println(Calendar.getInstance().getTime());
	}

}
