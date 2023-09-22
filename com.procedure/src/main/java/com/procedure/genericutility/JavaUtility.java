package com.procedure.genericutility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

public class JavaUtility {
	public String getSystemTime() {
		SimpleDateFormat f = new SimpleDateFormat("hh:mm a");
		Date d = new Date();
		long systemTime = d.getTime();
		String formatedSystemTime = f.format(systemTime);
		return formatedSystemTime;
		//System.out.println("System Time: "+systemTime);
		//System.out.println(formatedSystemTime);
		
	}
//	public static void main(String[] args) {
//		new JavaUtility().getSystemTime();
//		
//	}
	public String getTodaysDate(String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		Date d = new Date();
		return f.format(d);
	}
}
