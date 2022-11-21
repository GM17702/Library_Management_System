package business_Layer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import data_Layer.Record_Handling;

public class Book 
{
	private String ISBN;
	private String name;
	private String author;
	private String subject;
	private String quantity;
	private String publisher;
	private LocalDate publicationdate;
	
	Record_Handling r = new Record_Handling();
	
	
	public Book()
	{
		
	}
	
	public Book(String isbn, String name, String author, String publisher, String quantity)
	{
		this.ISBN=isbn;
		this.name=name;
		this.author=author;
		this.quantity=quantity;
		this.publisher=publisher;
	}
	
	
	public String getISBN() 
	{
		return ISBN;
	}
	public void setISBN(String iSBN) 
	{
		ISBN = iSBN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) 
	{
		this.author = author;
	}
	public String getSubject() 
	{
		return subject;
	}
	public void setSubject(String subject) 
	{
		this.subject = subject;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) 
	{
		this.quantity = quantity;
	}
	public String getPublisher() 
	{
		return publisher;
	}
	public void setPublisher(String publisher) 
	{
		this.publisher = publisher;
	}
	public LocalDate getPublicationdate() 
	{
		return publicationdate;
	}
	public void setPublicationdate(LocalDate publicationdate) 
	{
		this.publicationdate = publicationdate;
	}
	
	/**
	 * 
	 * @param ISBN
	 * @param name
	 * @param author
	 * @param subject
	 * @param quantity
	 * @param publisher
	 * @param publishingdate
	 * @return
	 * @throws IOException
	 */
	public boolean BookAddition(String ISBN, String name, String author, String subject, String quantity, String publisher, LocalDate publishingdate) throws IOException
	{
		String ename = name.replace(" ", "`");
		String eauthor = author.replace(" ", "`");
		String esubject = subject.replace(" ", "`");
		String epublisher = publisher.replace(" ", "`");
		
		boolean decision = r.StoreBook_GeneralRecord(ISBN,ename,eauthor,esubject,quantity,epublisher,publishingdate);
		
		if (decision==true)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public ArrayList<Book> ISBN_BookSearch(String ISBN)
	{
		ArrayList<String> result = r.ISBN_BookSearch(ISBN);
		ArrayList<Book> theresult = new ArrayList <Book>();
		
		if (result.isEmpty())
		{
			return theresult;
		}
		else
		{
			String newname = result.get(1).replace("`"," ");
			String newauthor = result.get(2).replace("`"," ");
			String newpublisher = result.get(3).replace("`"," ");
			
			Book b = new Book(result.get(0),newname,newauthor,newpublisher,result.get(4));
			theresult.add(b);
			return theresult;
		}
	}
	
	public ArrayList<Book> NAME_BookSearch(String name)
	{
		String ename = name.replace(" ", "`");
		ArrayList<String> result = r.NAME_BookSearch(ename);
		ArrayList<Book> theresult = new ArrayList <Book>();
		
		if (result.isEmpty())
		{
			return theresult;
		}
		else
		{
			int realsize = result.size() / 5;
			
			for (int i=0; i<realsize; i++)
			{
				String newname = result.get(1 + (5*i)).replace("`"," ");
				String newauthor = result.get(2 + (5*i)).replace("`"," ");
				String newpublisher = result.get(3 + (5*i)).replace("`"," ");
				
				Book b = new Book(result.get(0 + (5*i)),newname,newauthor,newpublisher,result.get(4 + (5*i)));
				theresult.add(b);
			}
			
			return theresult;
		}
	}
	
	
	public ArrayList<Book> AUTHOR_BookSearch(String author)
	{
		String eauthor = author.replace(" ", "`");
		ArrayList<String> result = r.AUTHOR_BookSearch(eauthor);
		ArrayList<Book> theresult = new ArrayList <Book>();
		
		if (result.isEmpty())
		{
			return theresult;
		}
		else
		{
			int realsize = result.size() / 5;
			
			for (int i=0; i<realsize; i++)
			{
				String newname = result.get(1 + (5*i)).replace("`"," ");
				String newauthor = result.get(2 + (5*i)).replace("`"," ");
				String newpublisher = result.get(3 + (5*i)).replace("`"," ");
				
				Book b = new Book(result.get(0 + (5*i)),newname,newauthor,newpublisher,result.get(4 + (5*i)));
				theresult.add(b);
			}
			
			return theresult;
		}
	}
	
	
	
//--------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	public void UPDATE_BookRecord(ArrayList<Book> result)
	{
		for (int i=0; i<result.size(); i++)
		{
			result.get(i).setName(result.get(i).getName().replace(" ", "`"));
			result.get(i).setAuthor(result.get(i).getAuthor().replace(" ", "`"));
			result.get(i).setPublisher(result.get(i).getPublisher().replace(" ", "`"));
		}
		
		r.UPDATE_BookRecord(result);
	}
	
	public void DELETE_BookRecord(Book result)
	{
		result.setName(result.getName().replace(" ", "`"));
		result.setAuthor(result.getAuthor().replace(" ", "`"));
		result.setPublisher(result.getPublisher().replace(" ", "`"));
		
		r.DELETE_BookRecord(result);
	}
}
