package data_Layer;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import business_Layer.Book;
import business_Layer.Member;

public class Member_Handling 
{
	public ArrayList<String> MEMBERID_MemberSearch(String id)
	{
		ArrayList <String> result = new ArrayList<String>();
		try 
		{
			File file = new File ("src/data_Layer/MemberInfo.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{	
	    		sc.next();
	    		sc.next();
	    		String ID =sc.next();
	    		
	    		if (ID.equalsIgnoreCase(id))
	    		{
	    			String name =sc.next();
	    			String address =sc.next();
	    			String pnum =sc.next();
	    			
	    			result.add(ID);
	    			result.add(name);
	    			result.add(address);
	    			result.add(pnum);
	    			
	    			
	    			sc.close();
	    			return result;
	    		}
	    		else
	    		{
	    			sc.nextLine();
	    		}
	    	}
	    	
	    	sc.close();
	    	return result;
		}
		catch (Exception e)
		{
			return result;
		}
	}
	
	public ArrayList<String> NAME_MemberSearch(String name)
	{
		ArrayList <String> result = new ArrayList<String>();
		try 
		{
			File file = new File ("src/data_Layer/MemberInfo.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{	
	    		sc.next();
	    		sc.next();
	    		String id =sc.next();
	    		String Name =sc.next();
	    		
	    		if (Name.equalsIgnoreCase(name))
	    		{
	    			String address =sc.next();
	    			String pnum =sc.next();
	    			
	    			result.add(id);
	    			result.add(Name);
	    			result.add(address);
	    			result.add(pnum);
	    	
	    		}
	    		sc.nextLine();
	    	}
	    	
	    	sc.close();
	    	return result;
		}
		catch (Exception e)
		{
			return result;
		}
	}
	
	public void UPDATE_MemberRecord(ArrayList<Member> result)
	{	
		
		try 
		{
			File oldfile = new File ("src/data_Layer/MemberInfo.txt");
			File newfile = new File ("src/data_Layer/temprecord.txt");
			FileWriter newWriter = new FileWriter(newfile);
	    	Scanner sc = new Scanner (oldfile);
	    	boolean check=false;
	    	
	    	
	    	while (sc.hasNext())
	    	{
	    		String user =sc.next();
	    		String pass = sc.next();
	    		String id =sc.next();
	    		String Name =sc.next();
	    		String address =sc.next();
	    		String pnum =sc.next();
	    		
	    		for (int i=0; i<result.size(); i++)
	    		{
	    			if (id.equals(result.get(i).getMemberid()))
	    			{
	    				result.get(i).setUsername(user);
	    				result.get(i).setPassword(pass);
	    				check=true;
	    				break;
	    			}
	    		}
	    		
	    		if (check==true)
	    		{
	    			
	    		}
	    		else
	    		{
	    			newWriter.write(user+" "+pass+" "+id+" "+Name+" "+address+" "+pnum+"\n");
	    		}
	    		
	    		check=false;
	    		
	    	}
	    	
	    	for (int i=0; i<result.size(); i++)
    		{
	    		newWriter.write(result.get(i).getUsername()+" "+result.get(i).getPassword()+" "+result.get(i).getMemberid()+" "+result.get(i).getName()+" "+result.get(i).getAddress()+" "+result.get(i).getPhonenumber()+"\n");
    		}
	    	
	    	sc.close();
	    	newWriter.close();
	    	oldfile.delete();
	    	newfile.renameTo(oldfile);
	    		
	    	
		}
		catch (Exception e)
		{
			System.out.println("Updating got screwed.");
		}
	}
	
	
	//--------------------------------------------------------------------
	
	public boolean DoesMemberExists(String memberid)
	{
		try 
		{
			File file = new File ("src/data_Layer/MemberInfo.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String user = sc.next();
	    		String pass = sc.next();
	    		String id = sc.next();
	    		
	    		if (id.equalsIgnoreCase(memberid))
	    		{
	    			sc.close();
		    		return true;
	    			
	    		}
	    		
	    		sc.nextLine();
	    	}
	    	
	    	sc.close();
	    	return false;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public ArrayList<String> GetAllMemberDetails()
	{
		ArrayList<String> members = new ArrayList<String>();
		try 
		{
			File file = new File ("src/data_Layer/MemberInfo.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	
	    	while (sc.hasNext())
	    	{
	    		String user = sc.next();
	    		String pass = sc.next();
	    		String id = sc.next();
	    		String name = sc.next();
	    		String address = sc.next();
	    		String pnum = sc.next();
	    		
	    		members.add(id);
	    		members.add(name);
	    		members.add(address);
	    		members.add(pnum);
	    		
	    	}
	    	
	    	sc.close();
	    	return members;
		}
		catch (Exception e)
		{
			return members;
		}
	}
	
	
	public void DELETE_MemberRecord(Member m)
	{	
		
		try 
		{
			File oldfile = new File ("src/data_Layer/MemberInfo.txt");
			File newfile = new File ("src/data_Layer/temprecord.txt");
			FileWriter newWriter = new FileWriter(newfile);
	    	Scanner sc = new Scanner (oldfile);
	    	boolean check=false;
	    	
	    	
	    	while (sc.hasNext())
	    	{
	    		String user =sc.next();
	    		String pass = sc.next();
	    		String id =sc.next();
	    		String Name =sc.next();
	    		String address =sc.next();
	    		String pnum =sc.next();
	    		
	    		if (id.equals(m.getMemberid()))
	    		{
	    			check=true;
	    		}
	    		
	    		
	    		if (check==true)
	    		{
	    			
	    		}
	    		else
	    		{
	    			newWriter.write(user+" "+pass+" "+id+" "+Name+" "+address+" "+pnum+"\n");
	    		}
	    		
	    		check=false;
	    		
	    	}
	    	
	    	
	    	
	    	sc.close();
	    	newWriter.close();
	    	oldfile.delete();
	    	newfile.renameTo(oldfile);
	    		
	    	
		}
		catch (Exception e)
		{
			System.out.println("Deleting member got screwed.");
		}
	}
	
}
