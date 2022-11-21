package data_Layer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import business_Layer.Book;
import business_Layer.Book_Issuance_Record;

public class Book_Issuance_Handling 
{
	public boolean DoubleCheck_IssueEligibility(String memberid)
	{
		try 
		{
			File file = new File ("src/data_Layer/Book_Issuance_Record.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String bookid = sc.next();
	    		String Memberid = sc.next();
	    		String issuedate = sc.next();
	    		String duedate = sc.next();
	    		String returndate = sc.next();
	    		
	    		if (Memberid.equalsIgnoreCase(memberid) && ((returndate.equals("no"))))
	    		{
	    			sc.close();
		    		return true;	
	    		}
	    		
	    		sc.next();
	    	}
	    	
	    	sc.close();
	    	return false;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public boolean DoubleCheck_ReturnEligibility(String memberid)
	{
		try 
		{
			File file = new File ("src/data_Layer/Book_Issuance_Record.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String bookid = sc.next();
	    		String Memberid = sc.next();
	    		String issuedate = sc.next();
	    		String duedate = sc.next();
	    		String returndate = sc.next();
	    		
	    		if (Memberid.equalsIgnoreCase(memberid) && (returndate.equals("no")))
	    		{
	    			sc.close();
			    	return true;	
	    		}
	    		
	    		sc.next();
	    	}
	    	
	    	sc.close();
	    	return false;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	
	public void StoreBook_IssuanceRecord(String bookid, String memberid, LocalDate issuedate, LocalDate duedate, LocalDate returndate) throws IOException
	{
		FileWriter myWriter;
		try 
		{
			myWriter = new FileWriter("src/data_Layer/Book_Issuance_Record.txt",true);
		}
		catch (Exception e)
		{
			myWriter = new FileWriter("src/data_Layer/Book_Issuance_Record.txt");
		}
		
		
		myWriter.write(bookid+" "+memberid+" "+issuedate+" "+duedate+" "+"no"+" "+"no"+"\n");
    	myWriter.close();
	}
	
	public ArrayList<String> MEMBERID_BookIssuanceSearch(String memberid)
	{
		ArrayList <String> result = new ArrayList<String>();
		try 
		{
			File file = new File ("src/data_Layer/Book_Issuance_Record.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String bookid=sc.next();
	    		String Memberid =sc.next();
	    		String issuedate =sc.next();
    			String duedate =sc.next();
	    		
	    		if (Memberid.equalsIgnoreCase(memberid) && sc.next().equals("no"))
	    		{
	    			
	    			result.add(bookid);
	    			result.add(Memberid);
	    			result.add(issuedate);
	    			result.add(duedate);
	    			
	    			
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
	
	
	
	public ArrayList<String> MEMBERID_BookRenewenceSearch(String memberid)
	{
		ArrayList <String> result = new ArrayList<String>();
		try 
		{
			File file = new File ("src/data_Layer/Book_Issuance_Record.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String bookid=sc.next();
	    		String Memberid =sc.next();
	    		String issuedate =sc.next();
    			String duedate =sc.next();
    			String returndate = sc.next();
    			String renewflag = sc.next();
	    		
	    		if (Memberid.equalsIgnoreCase(memberid) && returndate.equals("no") && renewflag.equals("no") )
	    		{
	    			
	    			
	    			result.add(bookid);
	    			result.add(Memberid);
	    			result.add(issuedate);
	    			result.add(duedate);
	    			result.add(returndate);
	    			result.add(renewflag);
	    			
	    			
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
	

	
	public void UPDATE_BookIssuanceRecord(ArrayList<Book_Issuance_Record> result)
	{	
		
		try 
		{
			File oldfile = new File ("src/data_Layer/Book_Issuance_Record.txt");
			File newfile = new File ("src/data_Layer/temprecord.txt");
			FileWriter newWriter = new FileWriter(newfile);
	    	Scanner sc = new Scanner (oldfile);
	    	boolean check=false;
	    	
	    	
	    	while (sc.hasNext())
	    	{
	    		String bookisbn =sc.next();
	    		String memberid =sc.next();
	    		String issuedate =sc.next();
	    		String duedate =sc.next();
	    		String returndate =sc.next();
    			String renewflag =sc.next();
	    		
	    		for (int i=0; i<result.size(); i++)
	    		{
	    			if (memberid.equals(result.get(i).getM().getMemberid()) && returndate.equals("no"))
	    			{
	    				check=true;
	    				break;
	    			}
	    		}
	    		
	    		if (check==true)
	    		{
	    			
	    		}
	    		else if (check==false)
	    		{
	    			newWriter.write(bookisbn+" "+memberid+" "+issuedate+" "+duedate+" "+returndate+" "+renewflag+"\n");
	    		}
	    		
	    		check=false;
	    		
	    	}
	    	
	    	for (int i=0; i<result.size(); i++)
	    	{
	    		if (result.get(i).getReturndate()==null)
	    		{
		    		newWriter.write(result.get(i).getB().getISBN()+" "+result.get(i).getM().getMemberid()+" "+result.get(i).getIssuedate()+" "+result.get(i).getDuedate()+" "+"no"+" "+result.get(i).getRenewflag()+"\n");
	    		}
	    		else if (result.get(i).getRenewflag()==null)
	    		{
		    		newWriter.write(result.get(i).getB().getISBN()+" "+result.get(i).getM().getMemberid()+" "+result.get(i).getIssuedate()+" "+result.get(i).getDuedate()+" "+result.get(i).getReturndate()+" "+"no"+"\n");
	    		}
	    		else
	    		{
		    		newWriter.write(result.get(i).getB().getISBN()+" "+result.get(i).getM().getMemberid()+" "+result.get(i).getIssuedate()+" "+result.get(i).getDuedate()+" "+result.get(i).getReturndate()+" "+result.get(i).getRenewflag()+"\n");
	    		}
	    	}
	    	
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
	
	public ArrayList<String> MEMBERID_GetIssuanceRecords(String memberid)
	{
		ArrayList <String> result = new ArrayList<String>();
		try 
		{
			File file = new File ("src/data_Layer/Book_Issuance_Record.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String bookid=sc.next();
	    		String Memberid =sc.next();
	    		String issuedate =sc.next();
    			String duedate =sc.next();
    			String returndate =sc.next();
	    		
	    		if (Memberid.equalsIgnoreCase(memberid))
	    		{
	    			
	    			result.add(bookid);
	    			result.add(Memberid);
	    			result.add(issuedate);
	    			result.add(duedate);
	    			result.add(returndate);
	    			
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
	
	
	
	
	
	public ArrayList<String> BOOKID_GetIssuanceRecords(String Bookid)
	{
		ArrayList <String> result = new ArrayList<String>();
		try 
		{
			File file = new File ("src/data_Layer/Book_Issuance_Record.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String bookid=sc.next();
	    		String Memberid =sc.next();
	    		String issuedate =sc.next();
    			String duedate =sc.next();
    			String returndate =sc.next();
	    		
	    		if (bookid.equalsIgnoreCase(Bookid))
	    		{
	    			
	    			result.add(bookid);
	    			result.add(Memberid);
	    			result.add(issuedate);
	    			result.add(duedate);
	    			result.add(returndate);
	    			
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
	
}
