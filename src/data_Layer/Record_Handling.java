package data_Layer;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import business_Layer.Book;

public class Record_Handling 
{
	
	public boolean StoreBook_GeneralRecord(String ISBN, String name, String author, String subject, String quantity, String publisher, LocalDate publishingdate) throws IOException
	{
		try 
		{
			File file = new File ("src/data_Layer/Book_GeneralRecord.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String isbn =sc.next();
	    		sc.nextLine();
	    		
	    		if (isbn.equalsIgnoreCase(ISBN))
	    		{
	    			sc.close();
	    			return true;
	    		}
	    	}
	    	
	    	sc.close();
	    	WriteBook_GeneralRecord(ISBN,name,author,subject,quantity,publisher,publishingdate);
	    	return false;
		}
		catch (Exception e)
		{
			WriteBook_GeneralRecord(ISBN,name,author,subject,quantity,publisher,publishingdate);
			return false;
		}
		

	}
	
	public void WriteBook_GeneralRecord(String ISBN, String name, String author, String subject, String quantity, String publisher, LocalDate publishingdate) throws IOException
	{
		FileWriter myWriter;
		try 
		{
			myWriter = new FileWriter("src/data_Layer/Book_GeneralRecord.txt",true);
		}
		catch (Exception e)
		{
			myWriter = new FileWriter("src/data_Layer/Book_GeneralRecord.txt");
		}
		
		
		myWriter.write(ISBN+" "+name+" "+author+" "+subject+" "+quantity+" "+publisher+" "+publishingdate.toString()+"\n");
    	myWriter.close();
	}
	
	public ArrayList<String> ISBN_BookSearch(String ISBN)
	{
		ArrayList <String> result = new ArrayList<String>();
		try 
		{
			File file = new File ("src/data_Layer/Book_GeneralRecord.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String isbn =sc.next();
	    		
	    		if (isbn.equalsIgnoreCase(ISBN))
	    		{
	    			String name =sc.next();
	    			String author =sc.next();
	    			sc.next();
	    			String quantity =sc.next();
	    			String publisher =sc.next();
	    			
	    			result.add(isbn);
	    			result.add(name);
	    			result.add(author);
	    			result.add(publisher);
	    			result.add(quantity);
	    			
	    			
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
	
	public ArrayList<String> NAME_BookSearch(String name)
	{
		ArrayList <String> result = new ArrayList<String>();
		try 
		{
			File file = new File ("src/data_Layer/Book_GeneralRecord.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String isbn =sc.next();
	    		String Name =sc.next();
	    		
	    		if (Name.equalsIgnoreCase(name))
	    		{
	    			String author =sc.next();
	    			sc.next();
	    			String quantity =sc.next();
	    			String publisher =sc.next();
	    			
	    			result.add(isbn);
	    			result.add(Name);
	    			result.add(author);
	    			result.add(publisher);
	    			result.add(quantity);
	    	
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
	
	
	
	public ArrayList<String> AUTHOR_BookSearch(String author)
	{
		ArrayList <String> result = new ArrayList<String>();
		try 
		{
			File file = new File ("src/data_Layer/Book_GeneralRecord.txt");
	    	Scanner sc = new Scanner (file);
	    	
	    	while (sc.hasNext())
	    	{
	    		String isbn =sc.next();
	    		String Name =sc.next();
	    		String Author =sc.next();
	    		
	    		if (Author.equalsIgnoreCase(author))
	    		{
	    			sc.next();
	    			String quantity =sc.next();
	    			String publisher =sc.next();
	    			
	    			result.add(isbn);
	    			result.add(Name);
	    			result.add(Author);
	    			result.add(publisher);
	    			result.add(quantity);
	    	
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
	
	
	
	
	public void UPDATE_BookRecord(ArrayList<Book> result)
	{	
		
		try 
		{
			File oldfile = new File ("src/data_Layer/Book_GeneralRecord.txt");
			File newfile = new File ("src/data_Layer/temprecord.txt");
			FileWriter newWriter = new FileWriter(newfile);
	    	Scanner sc = new Scanner (oldfile);
	    	boolean check=false;
	    	
	    	
	    	while (sc.hasNext())
	    	{
	    		String isbn =sc.next();
	    		String Name =sc.next();
	    		String author =sc.next();
	    		String subject =sc.next();
	    		String quantity =sc.next();
    			String publisher =sc.next();
    			String publishdate =sc.next();
	    		
	    		for (int i=0; i<result.size(); i++)
	    		{
	    			if (isbn.equals(result.get(i).getISBN()))
	    			{
	    				LocalDate d =LocalDate.parse(publishdate);
	    				result.get(i).setSubject(subject);
	    				result.get(i).setPublicationdate(d);
	    				check=true;
	    				break;
	    			}
	    		}
	    		
	    		if (check==true)
	    		{
	    			
	    		}
	    		else if (check==false)
	    		{
	    			newWriter.write(isbn+" "+Name+" "+author+" "+subject+" "+quantity+" "+publisher+" "+publishdate+"\n");
	    		}
	    		
	    		check=false;
	    		
	    	}
	    	
	    	for (int i=0; i<result.size(); i++)
	    	{
	    		newWriter.write(result.get(i).getISBN()+" "+result.get(i).getName()+" "+result.get(i).getAuthor()+" "+result.get(i).getSubject()+" "+result.get(i).getQuantity()+" "+result.get(i).getPublisher()+" "+result.get(i).getPublicationdate()+"\n");
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
	
	
	public void DELETE_BookRecord(Book record)
	{	
		
		try 
		{
			File oldfile = new File ("src/data_Layer/Book_GeneralRecord.txt");
			File newfile = new File ("src/data_Layer/temprecord.txt");
			FileWriter newWriter = new FileWriter(newfile);
	    	Scanner sc = new Scanner (oldfile);
	    	
	    	while (sc.hasNext())
	    	{
	    		String isbn =sc.next();
	    		String Name =sc.next();
	    		String author =sc.next();
	    		String subject =sc.next();
	    		String quantity =sc.next();
    			String publisher =sc.next();
    			String publishdate =sc.next();
	    		
    			if (isbn.equals(record.getISBN()))
    			{
   
    			}
	    		
	    		else
	    		{
	    			newWriter.write(isbn+" "+Name+" "+author+" "+subject+" "+quantity+" "+publisher+" "+publishdate+"\n");
	    		}
	    		
	    	}
	    	
	    	sc.close();
	    	newWriter.close();
	    	oldfile.delete();
	    	newfile.renameTo(oldfile);
	    		
	    	
		}
		catch (Exception e)
		{
			System.out.println("Deletion got screwed.");
		}
	}
	
	//------------------------------------------------------------------------------------------------------------
	
	

}
