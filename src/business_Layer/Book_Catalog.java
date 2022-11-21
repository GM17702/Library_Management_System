package business_Layer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Book_Catalog 
{
	Book b = new Book();
	Book_Issuance_Record bh = new Book_Issuance_Record();
	Fine_Record f = new Fine_Record();
	Request_Book r = new Request_Book();
	
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
	public boolean StoreBook(String ISBN, String name, String author, String subject, String quantity, String publisher, LocalDate publishingdate) throws IOException
	{
		boolean decision = b.BookAddition(ISBN,name,author,subject,quantity,publisher,publishingdate);
		return decision;
	}
	
	
	//-----------------------------------------------------------------------------------------------
	
	
	public ArrayList<Book> ISBN_BookSearch(String ISBN)
	{
		ArrayList<Book> result = b.ISBN_BookSearch(ISBN);
		return result;
	}
	
	public ArrayList<Book> NAME_BookSearch(String name)
	{
		ArrayList<Book> result = b.NAME_BookSearch(name);
		return result;
	}
	
	public ArrayList<Book> AUTHOR_BookSearch(String author)
	{
		ArrayList<Book> result = b.AUTHOR_BookSearch(author);
		return result;
	}
	
	//--------------------------------------------------------------------------------------------------
	
	
	public void UPDATE_BookRecord(ArrayList<Book> result)
	{
		b.UPDATE_BookRecord(result);
	}
	
	public void DELETE_BookRecord(Book result)
	{
		b.DELETE_BookRecord(result);
	}
	
	//----------------------------------------------------------------------------------------------------
	
	public boolean DoubleCheck_IssueEligibility(String memberid)
	{
		boolean decision = bh.DoubleCheck_IssueEligibility(memberid);
		return decision;
	}
	
	public void StoreBook_IssuanceRecord(String bookid, String memberid, LocalDate issuedate, LocalDate duedate, LocalDate returndate) throws IOException
	{
		bh.StoreBook_IssuanceRecord(bookid, memberid, issuedate, duedate, returndate);
	}
	
	//------------------------------------------------------------------------------------------------
	
	public boolean DoubleCheck_ReturnEligibility(String memberid)
	{
		boolean decision = bh.DoubleCheck_ReturnEligibility(memberid);
		return decision;
	}
	
	public ArrayList<Book_Issuance_Record> MEMBERID_BookIssuanceSearch(String memberid)
	{
		ArrayList<Book_Issuance_Record> result = bh.MEMBERID_BookIssuanceSearch(memberid);
		return result;
	}
	
	public void UPDATE_BookIssuanceRecord(ArrayList<Book_Issuance_Record> result)
	{
		bh.UPDATE_BookIssuanceRecord(result);
	}
	
	public void Store_FineDetails(String bookisbn, String memberid, long days) throws IOException
	{
		f.Store_FineDetails(bookisbn, memberid, days);
	}
	
	public boolean MEMBER_CheckFineStatus(String memberid)
	{
		boolean decision = f.MEMBER_CheckFineStatus(memberid);
		return decision;
	}
	
	//-----------------------------------------------------------------------------------------------
	
	public ArrayList<Book_Issuance_Record> MEMBERID_BookRenewenceSearch(String memberid)
	{
		ArrayList<Book_Issuance_Record> result = bh.MEMBERID_BookRenewenceSearch(memberid);
		return result;
	}
	
	public ArrayList<Fine_Record> MEMBERID_FineRecordSearch(String memberid)
	{
		ArrayList<Fine_Record> result = f.MEMBERID_FineRecordSearch(memberid);
		return result;
	}
	
	public void UPDATE_FineRecord(String bookid, String memberid, int fine, String finestatus)
	{	
		f.UPDATE_FineRecord(bookid, memberid, fine, finestatus);
	}
	
	public ArrayList<Fine_Record> GetAllMemberFineStatus(ArrayList<String> memberids)
	{
		ArrayList<Fine_Record> statuses = f.GetAllMemberFineStatus(memberids);
		return statuses;
	}
	
	//-------------------------------------------------------------------------------------------------------
	
	
	public ArrayList<Book_Issuance_Record> MEMBERID_GetIssuanceRecords(String memberid)
	{
		ArrayList<Book_Issuance_Record> result = bh.MEMBERID_GetIssuanceRecords(memberid);
		return result;
	}
	
	public ArrayList<Book_Issuance_Record> BOOKID_GetIssuanceRecords(String bookid)
	{
		ArrayList<Book_Issuance_Record> result = bh.BOOKID_GetIssuanceRecords(bookid);
		return result;
	}
	
	public ArrayList<Fine_Record> MEMBERID_GetFineHistory(String memberid)
	{
		ArrayList<Fine_Record> result = f.MEMBERID_GetFineHistory(memberid);
		return result;
	}
	
	//-------------------------------------------------------------------------------------------------------
	
	public void Store_Request (String memberid, String bookname, String authorname) throws IOException
	{
		r.Store_Request(memberid, bookname, authorname);
	}
	
	public ArrayList<Request_Book> GetAllRequests()
	{
		ArrayList<Request_Book> result = r.GetAllRequests();
		return result;
	}
	
	public void DELETE_PendingRequest(Request_Book result)
	{
		r.DELETE_PendingRequest(result);
	}
	
	public void Store_AcceptedRequest (String memberid, String bookname, String authorname) throws IOException
	{
		r.Store_AcceptedRequest(memberid, bookname, authorname);
	}
	
	public void Store_RejectedRequest (String memberid, String bookname, String authorname) throws IOException
	{
		r.Store_RejectedRequest(memberid, bookname, authorname);
	}
	
	//----------------------------------------------------------------------------------------------------
	
}
