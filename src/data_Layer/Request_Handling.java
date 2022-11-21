package data_Layer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import business_Layer.Request_Book;

public class Request_Handling 
{
	public void Store_Request(String memberid, String bookname, String authorname) throws IOException
	{
		FileWriter myWriter;
		try 
		{
			myWriter = new FileWriter("src/data_Layer/Pending_Requests.txt",true);
		}
		catch (Exception e)
		{
			myWriter = new FileWriter("src/data_Layer/Pending_Requests.txt");
		}
		
		
		myWriter.write(memberid+" "+bookname+" "+authorname+"\n");
    	myWriter.close();
	}
	
	public ArrayList<String> GetAllRequests()
	{
		ArrayList <String> result = new ArrayList<String>();
		try 
		{
			File file = new File ("src/data_Layer/Pending_Requests.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String memberid =sc.next();
	    		String bookname =sc.next();
	    		String authorname =sc.next();
	    			
	   			result.add(memberid);
	    		result.add(bookname);
	    		result.add(authorname);
	    		
	    	}
	    	
	    	sc.close();
	    	return result;
		}
		catch (Exception e)
		{
			return result;
		}
	}
	
	
	
	
	public void DELETE_PendingRequest(Request_Book result)
	{	
		
		try 
		{
			File oldfile = new File ("src/data_Layer/Pending_Requests.txt");
			File newfile = new File ("src/data_Layer/temprecord.txt");
			FileWriter newWriter = new FileWriter(newfile);
	    	Scanner sc = new Scanner (oldfile);
	    	boolean check=false;
	    	
	    	
	    	while (sc.hasNext())
	    	{
	    		String memberid =sc.next();
	    		String bookName =sc.next();
	    		String authorname =sc.next();
	    		
	    		
	    			if (memberid.equals(result.getM().getMemberid()) && bookName.equals(result.getBook_Name()) && authorname.equals(result.getAuthor_Name()))
	    			{
	    				check=true;
	    				break;
	    			}
	    		
	    		
	    		if (check==true)
	    		{
	    			
	    		}
	    		else if (check==false)
	    		{
	    			newWriter.write(memberid+" "+bookName+" "+authorname+"\n");
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
			System.out.println("Updating pending list got screwed.");
		}
	}
	
	public void Store_AcceptedRequest(String memberid, String bookname, String authorname) throws IOException
	{
		FileWriter myWriter;
		try 
		{
			myWriter = new FileWriter("src/data_Layer/Accepted_Requests.txt",true);
		}
		catch (Exception e)
		{
			myWriter = new FileWriter("src/data_Layer/Accepted_Requests.txt");
		}
		
		
		myWriter.write(memberid+" "+bookname+" "+authorname+"\n");
    	myWriter.close();
	}
	
	
	
	public void Store_RejectedRequest(String memberid, String bookname, String authorname) throws IOException
	{
		FileWriter myWriter;
		try 
		{
			myWriter = new FileWriter("src/data_Layer/Rejected_Requests.txt",true);
		}
		catch (Exception e)
		{
			myWriter = new FileWriter("src/data_Layer/Rejected_Requests.txt");
		}
		
		
		myWriter.write(memberid+" "+bookname+" "+authorname+"\n");
    	myWriter.close();
	}
}
