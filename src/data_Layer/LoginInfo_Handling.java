package data_Layer;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import business_Layer.Member;

public class LoginInfo_Handling 
{
	public void Librarian_Info_Handling(String username,String password) throws IOException
	{
		FileWriter myWriter;
		try 
		{
			myWriter = new FileWriter("src/data_Layer/Librarian_LoginInfo.txt",true);
		}
		catch (Exception e)
		{
			myWriter = new FileWriter("src/data_Layer/Librarian_LoginInfo.txt");
		}
		
		myWriter.write(username+" "+password+"\n");
    	myWriter.close();
	}
	
	public void Member_SessionCreation(String username) throws IOException
	{
			
			try 
			{
				File oldfile = new File ("src/data_Layer/MemberInfo.txt");
				File newfile = new File ("src/data_Layer/Member_Session.txt");
				FileWriter newWriter = new FileWriter(newfile);
		    	Scanner sc = new Scanner (oldfile);
		    	
		    	
		    	while (sc.hasNext())
		    	{
		    		String user =sc.next();
		    		String pass = sc.next();
		    		String id =sc.next();
		    		String Name =sc.next();
		    		String address =sc.next();
		    		String pnum =sc.next();
		    		
		    		
		   			if (user.equals(username))
		   			{
		   				newWriter.write(id);
		    			break;
		    		}
		    		
		    	}	
		    	
		    	sc.close();
		    	newWriter.close();
		    		
		    	
			}
			catch (Exception e)
			{
				System.out.println("Creating Session got screwed.");
			}
	}
	
	public String Member_ObtainSession() throws IOException
	{
			String id=null;
			try 
			{
				File file = new File ("src/data_Layer/Member_Session.txt");
		    	Scanner sc = new Scanner (file);
		    	
		    	
		    	id=sc.next();
		    	sc.close();
		    	return id;
		    		
		    	
			}
			catch (Exception e)
			{
				return id;
			}
			
	}
	
	
	public void Member_SessionTermination() throws IOException
	{
		File file;
		try 
		{
			file = new File("src/data_Layer/Member_Session.txt");
			file.delete();
			
		}
		catch (Exception e)
		{}
		
		
	}
	
	public void Admin_Info_Handling(String username,String password) throws IOException
	{
		FileWriter myWriter;
		try 
		{
			myWriter = new FileWriter("src/data_Layer/Admin_LoginInfo.txt",true);
			System.out.println("found it\n");
		}
		catch (Exception e)
		{
			myWriter = new FileWriter("src/data_Layer/Admin_LoginInfo.txt");
			System.out.println("made it\n");
		}
		
		myWriter.write(username+" "+password+"\n");
    	myWriter.close();
	}
	
	
	
	
	
	
	
	public boolean Librarian_Search_Signup_LoginInfo(String username, String password) throws IOException
	{
		try 
		{
			File file = new File ("src/data_Layer/Librarian_LoginInfo.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String user = sc.next();
	    		String pass = sc.next();
	    		
	    		if (username.equalsIgnoreCase(user))
	    		{
	    			sc.close();
	    			return true;
	    		}
	    	}
	    	
	    	sc.close();
	    	Librarian_Info_Handling(username, password);
	    	return false;
		}
		catch (Exception e)
		{
			Librarian_Info_Handling(username, password);
			return false;
		}
		

	}
	
	public boolean Librarian_Search_Login_LoginInfo(String username, String password) throws IOException
	{
		try 
		{
			File file = new File ("src/data_Layer/Librarian_LoginInfo.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String user = sc.next();
	    		String pass = sc.next();
	    		
	    		if (username.equalsIgnoreCase(user) && password.equals(pass))
	    		{
	    			sc.close();
	    			return true;
	    		}
	    	}
	    	
	    	sc.close();
	    	return false;
		}
		catch (Exception e)
		{
			return false;
		}
		
    	
	}
	
	public boolean Admin_Search_Signup_LoginInfo(String username,String password) throws IOException
	{
		try 
		{
			File file = new File ("src/data_Layer/Admin_LoginInfo.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String user = sc.next();
	    		String pass = sc.next();
	    		
	    		if (username.equalsIgnoreCase(user))
	    		{
	    			sc.close();
	    			return true;
	    		}
	    	}
	    	
	    	sc.close();
	    	Admin_Info_Handling(username, password);
	    	return false;
		}
		catch (Exception e)
		{
			Admin_Info_Handling(username, password);
			return false;
		}
    	
	}
	
	public boolean Admin_Search_Login_LoginInfo(String username, String password) throws IOException
	{
		try 
		{
			File file = new File ("src/data_Layer/Admin_LoginInfo.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String user = sc.next();
	    		String pass = sc.next();
	    		
	    		if (username.equalsIgnoreCase(user) && password.equals(pass))
	    		{
	    			sc.close();
	    			return true;
	    		}
	    	}
	    	
	    	sc.close();
	    	return false;
		}
		catch (Exception e)
		{
			return false;
		}
    	
	}
	
	//---------------------------------------------------------------------------------------------------------------
	
	public boolean Member_Signup_LoginInfo(String username, String password, String memberid, String name, String address, String pnum) throws IOException
	{
		try 
		{
			File file = new File ("src/data_Layer/MemberInfo.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String user = sc.next();
	    		String pass = sc.next();
	    		String id =sc.next();
	    		sc.nextLine();
	    		
	    		if (id.equalsIgnoreCase(memberid))
	    		{
	    			sc.close();
	    			return true;
	    		}
	    	}
	    	
	    	sc.close();
	    	Member_Info_Handling(username, password,memberid,name,address,pnum);
	    	return false;
		}
		catch (Exception e)
		{
			Member_Info_Handling(username, password,memberid,name,address,pnum);
			return false;
		}
		

	}
	
	public void Member_Info_Handling(String username, String password, String memberid, String name, String address, String pnum) throws IOException
	{
		FileWriter myWriter;
		try 
		{
			myWriter = new FileWriter("src/data_Layer/MemberInfo.txt",true);
		}
		catch (Exception e)
		{
			myWriter = new FileWriter("src/data_Layer/MemberInfo.txt");
		}
		
		myWriter.write(username+" "+password+" "+memberid+" "+name+" "+address+" "+pnum+" "+"\n");
    	myWriter.close();
	}
	
	public boolean Member_Search_Login_LoginInfo(String username, String password) throws IOException
	{
		try 
		{
			File file = new File ("src/data_Layer/MemberInfo.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String user = sc.next();
	    		String pass = sc.next();
	    		sc.nextLine();
	    		
	    		if (username.equalsIgnoreCase(user) && password.equals(pass))
	    		{
	    			sc.close();
	    			return true;
	    		}
	    	}
	    	
	    	sc.close();
	    	return false;
		}
		catch (Exception e)
		{
			return false;
		}
    	
	}
}
