package data_Layer;
import java.io.*;
import java.util.Scanner;


public class Payment_Handling 
{
	public void Store_CardDetails(String memberid, String cardtype, String cardno, String cardexpirationdate, int cvv) throws IOException
	{
		FileWriter myWriter;
		try 
		{
			myWriter = new FileWriter("src/data_Layer/Payment_Info.txt",true);
		}
		catch (Exception e)
		{
			myWriter = new FileWriter("src/data_Layer/Payment_Info.txt");
		}
		
		
		myWriter.write(memberid+" "+cardtype+" "+cardno+" "+ cardexpirationdate+" "+cvv+"\n");
    	myWriter.close();
	}
	
	
	public boolean MEMBER_CheckCardStatus(String memberid)
	{
		try 
		{
			File file = new File ("src/data_Layer/Payment_Info.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String Memberid = sc.next();
	  
	    		if (Memberid.equalsIgnoreCase(memberid))
	    		{
	    			sc.close();
			    	return true;	
	    		}
	    		else
	    		{
	    			sc.nextLine();
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
	
	public void DELETE_PaymentMethod(String memberid)
	{	
		
		try 
		{
			File oldfile = new File ("src/data_Layer/Payment_Info.txt");
			File newfile = new File ("src/data_Layer/temprecord.txt");
			FileWriter newWriter = new FileWriter(newfile);
	    	Scanner sc = new Scanner (oldfile);
	    	boolean check=false;
	    	
	    	
	    	while (sc.hasNext())
	    	{
	    		String Memberid =sc.next();
	    		String cardtype1=sc.next();
	    		String cardtype2=sc.next();
	    		String cardno=sc.next();
	    		String cardexpirationdate=sc.next();
	    		String cvv= sc.next();
	    		
	    		
	    		
	    		if (Memberid.equalsIgnoreCase(memberid))
	    		{
	    			check=true;
	    		}
	    		
	    		if (check==true)
	    		{
	    			
	    		}
	    		else if (check==false)
	    		{
	    			newWriter.write(Memberid+" "+cardtype1+" "+cardtype2+" "+cardno+" "+ cardexpirationdate+" "+cvv+"\n");
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
			e.printStackTrace();
		}
	}
}
