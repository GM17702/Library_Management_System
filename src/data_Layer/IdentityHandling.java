package data_Layer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class IdentityHandling 
{
	public void Identity_Handling(String membervalue,String librarianvalue,String adminvalue) throws IOException
	{
		FileWriter myWriter = new FileWriter("src/data_Layer/Identity_Recognizer.txt");
		myWriter.write("Member_flag: "+membervalue+"\n");
    	myWriter.write("Librarian_flag: "+librarianvalue+"\n");
    	myWriter.write("Admin_flag: "+adminvalue+"\n");
    	
    	myWriter.close();
	}
	
	public String Read_Identity() throws IOException
	{
		File file = new File ("src/data_Layer/Identity_Recognizer.txt");
    	Scanner sc = new Scanner (file);
    	
    	sc.next();
    	String membervalue=sc.next();
    	sc.next();
    	String librarianvalue=sc.next();
    	sc.next();
    	String adminvalue=sc.next();
    	
    	sc.close();
    	
    	if (membervalue.contains("1") && librarianvalue.contains("0") && adminvalue.contains("0"))
    	{
    		return "1";
    	}
    	
    	else if (membervalue.contains("0") && librarianvalue.contains("1") && adminvalue.contains("0"))
    	{
    		return "2";
    	}
    	
    	else if (membervalue.contains("0") && librarianvalue.contains("0") && adminvalue.contains("1"))
    	{
    		return "3";
    	}
    	
    	else
    	{
    		System.out.println("The Identity_Checker failed.\n");
    		return "0";
    	}
    	
    	
    	
	}
}
