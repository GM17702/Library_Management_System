package application;

public class Request_Book_Wrapper 
{
	private String MemberID;
	private String Book_Name;
	private String Author_Name;
	
	public Request_Book_Wrapper(String memberid, String book_Name, String author_Name) 
	{
		this.MemberID = memberid;
		this.Book_Name = book_Name;
		this.Author_Name = author_Name;
	}
	
	public String getMemberID() 
	{
		return MemberID;
	}
	public void setMemberID(String memberID) 
	{
		MemberID = memberID;
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
}
