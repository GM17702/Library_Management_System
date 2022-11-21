package business_Layer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import data_Layer.Book_Issuance_Handling;

public class Book_Issuance_Record 
{
	private LocalDate issuedate;
	private LocalDate duedate;
	private LocalDate returndate;
	private String renewflag;
	
	Book b = new Book();
	Member m = new Member();
	Book_Issuance_Handling bh = new Book_Issuance_Handling();
	
	public Book_Issuance_Record()
	{
		
	}
	
	
	public Book_Issuance_Record(String book_ISBN, String member_ID, LocalDate issuedate, LocalDate duedate, LocalDate returndate) 
	{
		this.b.setISBN(book_ISBN);
		this.m.setMemberid(member_ID);
		this.issuedate = issuedate;
		this.duedate = duedate;
		this.returndate = returndate;
	}
	
	public Book_Issuance_Record(String book_ISBN, String member_ID, LocalDate issuedate, LocalDate duedate, LocalDate returndate, String renewflag) 
	{
		this.b.setISBN(book_ISBN);
		this.m.setMemberid(member_ID);
		this.issuedate = issuedate;
		this.duedate = duedate;
		this.returndate = returndate;
		this.renewflag=renewflag;
	}
	
	
	public Book_Issuance_Record(String book_ISBN, String member_ID, LocalDate issuedate, LocalDate duedate) 
	{
		this.b.setISBN(book_ISBN);
		this.m.setMemberid(member_ID);
		this.issuedate = issuedate;
		this.duedate = duedate;
	}
	
	


	public LocalDate getIssuedate() 
	{
		return issuedate;
	}
	public void setIssuedate(LocalDate issuedate) 
	{
		this.issuedate = issuedate;
	}
	public LocalDate getDuedate() 
	{
		return duedate;
	}
	public void setDuedate(LocalDate duedate) 
	{
		this.duedate = duedate;
	}
	public LocalDate getReturndate() 
	{
		return returndate;
	}
	public void setReturndate(LocalDate returndate) 
	{
		this.returndate = returndate;
	}
	


	public String getRenewflag() {
		return renewflag;
	}



	public void setRenewflag(String renewflag) {
		this.renewflag = renewflag;
	}
	
	
	




	public Book getB() {
		return b;
	}



	public void setB(Book b) {
		this.b = b;
	}



	public Member getM() {
		return m;
	}



	public void setM(Member m) {
		this.m = m;
	}



	public boolean DoubleCheck_IssueEligibility(String memberid)
	{
		boolean decision = bh.DoubleCheck_IssueEligibility(memberid);
		return decision;
	}
	
	public boolean DoubleCheck_ReturnEligibility(String memberid)
	{
		boolean decision = bh.DoubleCheck_ReturnEligibility(memberid);
		return decision;
	}
	
	public void StoreBook_IssuanceRecord(String bookid, String memberid, LocalDate issuedate, LocalDate duedate, LocalDate returndate) throws IOException
	{
		bh.StoreBook_IssuanceRecord(bookid, memberid, issuedate, duedate, returndate);
	}
	
	public ArrayList<Book_Issuance_Record> MEMBERID_BookIssuanceSearch(String memberid)
	{
		ArrayList<String> result = bh.MEMBERID_BookIssuanceSearch(memberid);
		ArrayList<Book_Issuance_Record> theresult = new ArrayList <Book_Issuance_Record>();
		
		if (result.isEmpty())
		{
			return theresult;
		}
		else
		{
			LocalDate ISSUE = LocalDate.parse(result.get(2));
			LocalDate DUE = LocalDate.parse(result.get(3));
			Book_Issuance_Record b = new Book_Issuance_Record(result.get(0),result.get(1),ISSUE,DUE);
			theresult.add(b);
			return theresult;
		}
	}
	
	public void UPDATE_BookIssuanceRecord(ArrayList<Book_Issuance_Record> result)
	{
		bh.UPDATE_BookIssuanceRecord(result);
	}
	
	
	
	public ArrayList<Book_Issuance_Record> MEMBERID_BookRenewenceSearch(String memberid)
	{
		ArrayList<String> result = bh.MEMBERID_BookRenewenceSearch(memberid);
		ArrayList<Book_Issuance_Record> theresult = new ArrayList <Book_Issuance_Record>();
		
		if (result.isEmpty())
		{
			return theresult;
		}
		else
		{
			LocalDate ISSUE = LocalDate.parse(result.get(2));
			LocalDate DUE = LocalDate.parse(result.get(3));
			Book_Issuance_Record b = new Book_Issuance_Record(result.get(0),result.get(1),ISSUE,DUE);
			theresult.add(b);
			return theresult;
		}
	}
	
	
	public ArrayList<Book_Issuance_Record> MEMBERID_GetIssuanceRecords(String memberid)
	{
		ArrayList<String> result = bh.MEMBERID_GetIssuanceRecords(memberid);
		ArrayList<Book_Issuance_Record> theresult = new ArrayList <Book_Issuance_Record>();
		
		if (result.isEmpty())
		{
			return theresult;
		}
		else
		{
			int realsize = result.size() / 5;
			
			for (int i=0; i<realsize; i++)
			{
				if (result.get(4+(5*i)).equals("no"))
				{
					LocalDate ISSUE = LocalDate.parse(result.get(2+(5*i)));
					LocalDate DUE = LocalDate.parse(result.get(3+(5*i)));
					LocalDate RETURN = null;
					Book_Issuance_Record b = new Book_Issuance_Record(result.get(0+(5*i)),result.get(1+(5*i)),ISSUE,DUE,RETURN);
					theresult.add(b);
				}
				else
				{
					LocalDate ISSUE = LocalDate.parse(result.get(2+(5*i)));
					LocalDate DUE = LocalDate.parse(result.get(3+(5*i)));
					LocalDate RETURN = LocalDate.parse(result.get(4+(5*i)));
					Book_Issuance_Record b = new Book_Issuance_Record(result.get(0+(5*i)),result.get(1+(5*i)),ISSUE,DUE,RETURN);
					theresult.add(b);
				}
			
			}
			return theresult;
		}
	}
	
	
	
	
	
	
	public ArrayList<Book_Issuance_Record> BOOKID_GetIssuanceRecords(String bookid)
	{
		ArrayList<String> result = bh.BOOKID_GetIssuanceRecords(bookid);
		ArrayList<Book_Issuance_Record> theresult = new ArrayList <Book_Issuance_Record>();
		
		if (result.isEmpty())
		{
			return theresult;
		}
		else
		{
			int realsize = result.size() / 5;
			
			for (int i=0; i<realsize; i++)
			{
				if (result.get(4+(5*i)).equals("no"))
				{
					LocalDate ISSUE = LocalDate.parse(result.get(2+(5*i)));
					LocalDate DUE = LocalDate.parse(result.get(3+(5*i)));
					LocalDate RETURN = null;
					Book_Issuance_Record b = new Book_Issuance_Record(result.get(0+(5*i)),result.get(1+(5*i)),ISSUE,DUE,RETURN);
					theresult.add(b);
				}
				else
				{
					LocalDate ISSUE = LocalDate.parse(result.get(2+(5*i)));
					LocalDate DUE = LocalDate.parse(result.get(3+(5*i)));
					LocalDate RETURN = LocalDate.parse(result.get(4+(5*i)));
					Book_Issuance_Record b = new Book_Issuance_Record(result.get(0+(5*i)),result.get(1+(5*i)),ISSUE,DUE,RETURN);
					theresult.add(b);
				}
			
			}
			return theresult;
		}
	}
	
	
	
}
