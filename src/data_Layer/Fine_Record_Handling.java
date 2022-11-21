package data_Layer;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Fine_Record_Handling 
{
	public void UPDATE_FineRecord(String bookid, String memberid, int fine, String finestatus)
	{	
		
		try 
		{
			File oldfile = new File ("src/data_Layer/Fine_Details.txt");
			File newfile = new File ("src/data_Layer/temprecord.txt");
			FileWriter newWriter = new FileWriter(newfile);
	    	Scanner sc = new Scanner (oldfile);
	    	boolean check=false;
	    	
	    	
	    	while (sc.hasNext())
	    	{
	    		String bookisbn =sc.next();
	    		String Memberid =sc.next();
	    		String Fine =sc.next();
	    		String Finestatus =sc.next();
	    		
	    		
	    		if (Memberid.equalsIgnoreCase(memberid) && Finestatus.equals("unpaid"))
	    		{
	    			check=true;
	    			break;
	    		}
	    		
	    		if (check==true)
	    		{
	    			
	    		}
	    		else if (check==false)
	    		{
	    			newWriter.write(bookisbn+" "+Memberid+" "+Fine+" "+Finestatus+"\n");
	    		}
	    		
	    		check=false;
	    		
	    	}
	    	
	    	newWriter.write(bookid+" "+memberid+" "+fine+" "+finestatus+"\n");
	    	
	    	sc.close();
	    	newWriter.close();
	    	oldfile.delete();
	    	newfile.renameTo(oldfile);
	    		
	    	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	public ArrayList<String> MEMBERID_FineRecordSearch(String memberid)
	{
		ArrayList <String> result = new ArrayList<String>();
		try 
		{
			File file = new File ("src/data_Layer/Fine_Details.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String bookid=sc.next();
	    		String Memberid =sc.next();
	    		String fine =sc.next();
    			String finestatus =sc.next();
	    		
	    		if (Memberid.equalsIgnoreCase(memberid) && finestatus.equals("unpaid"))
	    		{
	    			
	    			
	    			result.add(bookid);
	    			result.add(Memberid);
	    			result.add(fine);
	    			result.add(finestatus);
	    			
	    			
	    			sc.close();
	    			return result;
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
	
	
	
	public void Store_FineDetails(String bookisbn, String memberid, int fine) throws IOException
	{
		FileWriter myWriter;
		try 
		{
			myWriter = new FileWriter("src/data_Layer/Fine_Details.txt",true);
		}
		catch (Exception e)
		{
			myWriter = new FileWriter("src/data_Layer/Fine_Details.txt");
		}
		
		
		myWriter.write(bookisbn+" "+memberid+" "+fine+" "+"unpaid"+"\n");
    	myWriter.close();
	}
	
	public boolean MEMBER_CheckFineStatus(String memberid)
	{
		try 
		{
			File file = new File ("src/data_Layer/Fine_Details.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String bookid = sc.next();
	    		String Memberid = sc.next();
	    		String fine =sc.next();
	    		String finestatus = sc.next();
	    		if (Memberid.equalsIgnoreCase(memberid) && finestatus.equals("unpaid"))
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
	
	
	
	
	
	public ArrayList<String> GetAllMemberFineStatus(ArrayList<String> memberids)
	{
		ArrayList<String> statuses = memberids;
		try 
		{
			File file = new File ("src/data_Layer/Fine_Details.txt");
			Scanner sc = null;
	    	int membercheck=0;
	    	
	    	for (int i=0; i<memberids.size(); i++)
	    	{
	    		sc = new Scanner (file);
	    		
	    		while (sc.hasNext())
		    	{
		    		String bookid = sc.next();
		    		String Memberid = sc.next();
		    		String fine =sc.next();
		    		String finestatus = sc.next();
		    		
		    		if (Memberid.equalsIgnoreCase(memberids.get(i)))
		    		{
		    			membercheck++;
		    			statuses.set(i, finestatus);	
		    		}
		    		
		    		if (membercheck==0 && sc.hasNext()==false)
		    		{
		    			statuses.set(i, "Not Due");
		    			membercheck=0;
		    		}
		    		else if (sc.hasNext()==false)
		    		{
		    			membercheck=0;
		    		}
		    	}
	    	}
	    	
	    	sc.close();
	    	return statuses;
		}
		catch (Exception e)
		{
			return statuses;
		}
	}
	
	public ArrayList<String> MEMBERID_GetFineHistory(String memberid)
	{
		ArrayList <String> result = new ArrayList<String>();
		try 
		{
			File file = new File ("src/data_Layer/Fine_Details.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String bookid=sc.next();
	    		String Memberid =sc.next();
	    		String fine =sc.next();
    			String finestatus =sc.next();
	    		
	    		if (Memberid.equalsIgnoreCase(memberid))
	    		{
	    			
	    			
	    			result.add(bookid);
	    			result.add(Memberid);
	    			result.add(fine);
	    			result.add(finestatus);
	    			
	    			
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
	
}
