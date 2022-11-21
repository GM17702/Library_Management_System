package business_Layer;

import java.io.IOException;
import java.util.ArrayList;

import data_Layer.Request_Handling;

public class Request_Book 
{
	private String Book_Name;
	private String Author_Name;
	
	Member m = new Member();
	
	Request_Handling r = new Request_Handling();
	
	public Request_Book()
	{
		
	}
	
	
	public Request_Book(String memberid, String book_Name, String author_Name) 
	{
		this.m.setMemberid(memberid);
		this.Book_Name = book_Name;
		this.Author_Name = author_Name;
	}


	public String getBook_Name() 
	{
		return Book_Name;
	}
	public void setBook_Name(String book_Name) 
	{
		Book_Name = book_Name;
	}
	public String getAuthor_Name() 
	{
		return Author_Name;
	}
	public void setAuthor_Name(String author_Name) 
	{
		Author_Name = author_Name;
	}
	


	public Member getM() {
		return m;
	}


	public void setM(Member m) {
		this.m = m;
	}


	public void Store_Request (String memberid, String bookname, String authorname) throws IOException
	{
		String ebookname = bookname.replace(" ", "`");
		String eauthorname = authorname.replace(" ", "`");
		
		r.Store_Request(memberid, ebookname, eauthorname);
		
	}
	
	public ArrayList<Request_Book> GetAllRequests()
	{
		ArrayList<String> result = r.GetAllRequests();
		ArrayList<Request_Book> theresult = new ArrayList <Request_Book>();
		
		if (result.isEmpty())
		{
			return theresult;
		}
		else
		{
			int realsize = result.size() / 3;
			
			for (int i=0; i<realsize; i++)
			{
				String newbookname = result.get(1 + (3*i)).replace("`"," ");
				String newauthorname = result.get(2 + (3*i)).replace("`"," ");
				
				Request_Book b = new Request_Book(result.get(0 + (3*i)),newbookname,newauthorname);
				theresult.add(b);
			}
			
			return theresult;
		}
	}
	
	
	public void DELETE_PendingRequest(Request_Book result)
	{
		result.setAuthor_Name(result.getAuthor_Name().replace(" ", "`"));
		result.setBook_Name(result.getBook_Name().replace(" ", "`"));
		
		r.DELETE_PendingRequest(result);
	}
	
	
	public void Store_AcceptedRequest (String memberid, String bookname, String authorname) throws IOException
	{
		String ebookname = bookname.replace(" ", "`");
		String eauthorname = authorname.replace(" ", "`");
		
		r.Store_AcceptedRequest(memberid, ebookname, eauthorname);
		
	}
	
	
	public void Store_RejectedRequest (String memberid, String bookname, String authorname) throws IOException
	{
		String ebookname = bookname.replace(" ", "`");
		String eauthorname = authorname.replace(" ", "`");
		
		r.Store_RejectedRequest(memberid, ebookname, eauthorname);
		
	}
	
	
}
