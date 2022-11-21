package business_Layer;
import java.io.IOException;
import java.util.*;

import data_Layer.LoginInfo_Handling;

public class Librarian implements Signup_and_Login
{
	private String username;
	private String password;
	
	LoginInfo_Handling l = new LoginInfo_Handling();
	
	
	public Librarian()
	{
		
	}
	
	
	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	@Override
	public boolean Signup_Check(String username, String password) throws IOException
	{
		boolean decision = l.Librarian_Search_Signup_LoginInfo(username, password);
		
		if (decision==true)
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}

	@Override
	public boolean Login_Check(String username, String password) throws IOException 
	{
		boolean decision =l.Librarian_Search_Login_LoginInfo(username, password);
		
		if (decision==true)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

}
