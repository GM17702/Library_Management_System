package business_Layer;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import data_Layer.*;
public class LMS_Controller 
{
	IdentityHandling d = new IdentityHandling();
	LoginInfo_Handling l = new LoginInfo_Handling();
	User_Catalog u = new User_Catalog();
	Book_Catalog book = new Book_Catalog();
	Payment_Handling p = new Payment_Handling();
	
	public void Identity_Handling(String membervalue, String librarianvalue, String adminvalue) throws IOException
	{
		d.Identity_Handling(membervalue,librarianvalue,adminvalue);
	}
	
	public String FindUsers() throws IOException
	{
		String decision =d.Read_Identity();
		return decision;
	}
	
	public void Member_SessionCreation (String user) throws IOException
	{
		u.Member_SessionCreation(user);
		
	}
	
	public String Member_ObtainSession() throws IOException
	{
		String result=u.Member_ObtainSession();
		return result;
	}
	
	public void Member_SessionTermination () throws IOException
	{
		u.Member_SessionTermination();
		
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------
	
	public boolean L_Check_Signup(String username, String password) throws IOException
	{
		boolean decision = l.Librarian_Search_Signup_LoginInfo(username, password);
		return decision;
	}
	
	public boolean L_Check_Login(String username, String password) throws IOException
	{
		boolean decision = l.Librarian_Search_Login_LoginInfo(username, password);
		return decision;
	}
	
	public boolean A_Check_Signup(String username, String password) throws IOException
	{
		boolean decision = l.Admin_Search_Signup_LoginInfo(username, password);
		return decision;
	}
	
	public boolean A_Check_Login(String username, String password) throws IOException
	{
		boolean decision = l.Admin_Search_Login_LoginInfo(username, password);
		return decision;
	}
	
	
	//--------------------------------------------------------------------------------------------------
	
	public boolean M_Check_Signup(String username, String password, String memberid, String name, String address, String pnum) throws IOException
	{
		boolean decision = u.M_Check_Signup(username, password,memberid,name,address,pnum);
		return decision;
	}
	
	public boolean M_Check_Login(String username, String password) throws IOException
	{
		boolean decision = u.M_Check_Login(username, password);
		return decision;
	}
	
	//---------------------------------------------------------------------------------------------------
	
	/**
	 * This can add book into database and return book id in case of success else error returned
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
	public boolean Check_BookAddition(String ISBN, String name, String author, String subject, String quantity, String publisher, LocalDate publishingdate) throws IOException
	{
		
		boolean decision = book.StoreBook(ISBN, name, author, subject, quantity, publisher, publishingdate);
		return decision;
	}
	
	//-----------------------------------------------------------------------------------------------------
	
	public ArrayList<Book> ISBN_BookSearch(String ISBN)
	{
		ArrayList<Book> result = book.ISBN_BookSearch(ISBN);
		return result;
	}
	
	public ArrayList<Book> NAME_BookSearch(String name)
	{
		ArrayList<Book> result = book.NAME_BookSearch(name);
		return result;
	}
	
	public ArrayList<Book> AUTHOR_BookSearch(String author)
	{
		ArrayList<Book> result = book.AUTHOR_BookSearch(author);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------
	
	
	public void UPDATE_BookRecord(ArrayList<Book> result)
	{
		book.UPDATE_BookRecord(result);
	}
	
	public void DELETE_BookRecord(Book result)
	{
		book.DELETE_BookRecord(result);
	}
	
	//-----------------------------------------------------------------------------------------------------
	
	public ArrayList<Member> ID_MemberSearch(String ID)
	{
		ArrayList<Member> result = u.ID_MemberSearch(ID);
		return result;
	}
	
	public ArrayList<Member> NAME_MemberSearch(String name)
	{
		ArrayList<Member> result = u.NAME_MemberSearch(name);
		return result;
	}
	
	public void UPDATE_MemberRecord(ArrayList<Member> result)
	{
		u.UPDATE_MemberRecord(result);
	}
	
	//-----------------------------------------------------------------------------------------------------
	
	public boolean DoubleCheck_IssueEligibility(String memberid)
	{
		boolean decision = book.DoubleCheck_IssueEligibility(memberid);
		return decision;
	}
	
	public boolean DoesMemberExists(String memberid)
	{
		boolean decision = u.DoesMemberExists(memberid);
		return decision;
	}
	
	public void StoreBook_IssuanceRecord(String bookid, String memberid, LocalDate issuedate, LocalDate duedate, LocalDate returndate) throws IOException
	{
		book.StoreBook_IssuanceRecord(bookid, memberid, issuedate, duedate, returndate);
	}
	
	//----------------------------------------------------------------------------------------------------------
	
	public boolean DoubleCheck_ReturnEligibility(String memberid)
	{
		boolean decision = book.DoubleCheck_ReturnEligibility(memberid);
		return decision;
	}
	
	public ArrayList<Book_Issuance_Record> MEMBERID_BookIssuanceSearch(String memberid)
	{
		ArrayList<Book_Issuance_Record> result = book.MEMBERID_BookIssuanceSearch(memberid);
		return result;
	}
	
	public void UPDATE_BookIssuanceRecord(ArrayList<Book_Issuance_Record> result)
	{
		book.UPDATE_BookIssuanceRecord(result);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	
	public void Store_FineDetails(String bookisbn, String memberid, long days) throws IOException
	{
		book.Store_FineDetails(bookisbn,memberid,days);
	}
	
	public boolean MEMBER_CheckFineStatus(String memberid)
	{
		boolean decision = book.MEMBER_CheckFineStatus(memberid);
		return decision;
	}
	
	public ArrayList<Fine_Record> MEMBERID_FineRecordSearch(String memberid)
	{
		ArrayList<Fine_Record> result = book.MEMBERID_FineRecordSearch(memberid);
		return result;
	}
	
	public ArrayList<Fine_Record> MEMBERID_GetFineHistory(String memberid)
	{
		ArrayList<Fine_Record> result = book.MEMBERID_GetFineHistory(memberid);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	
	public ArrayList<Book_Issuance_Record> MEMBERID_BookRenewenceSearch(String memberid)
	{
		ArrayList<Book_Issuance_Record> result = book.MEMBERID_BookRenewenceSearch(memberid);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	
	public boolean MEMBER_CheckCardStatus(String memberid)
	{
		boolean decision = p.MEMBER_CheckCardStatus(memberid);
		return decision;
	}
	
	public void UPDATE_FineRecord(String bookid, String memberid, int fine, String finestatus)
	{	
		book.UPDATE_FineRecord(bookid, memberid, fine, finestatus);
	}
	
	public void Store_CardDetails(String memberid, String cardtype, String cardno, String cardexpirationdate, int cvv) throws IOException
	{
		p.Store_CardDetails(memberid, cardtype, cardno, cardexpirationdate, cvv);
	}
	
	public void DELETE_PaymentMethod(String memberid)
	{
		p.DELETE_PaymentMethod(memberid);
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------
	
	public ArrayList<Member> GetAllMemberDetails()
	{
		ArrayList<Member> result = u.GetAllMemberDetails();
		return result;
	}
	
	public ArrayList<Fine_Record> GetAllMemberFineStatus(ArrayList<String> memberids)
	{
		ArrayList<Fine_Record> statuses = book.GetAllMemberFineStatus(memberids);
		return statuses;
	}
	
	public void DELETE_MemberRecord(Member m)
	{
		u.DELETE_MemberRecord(m);
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------
	
	public ArrayList<Book_Issuance_Record> MEMBERID_GetIssuanceRecords(String memberid)
	{
		ArrayList<Book_Issuance_Record> result = book.MEMBERID_GetIssuanceRecords(memberid);
		return result;
	}
	
	public ArrayList<Book_Issuance_Record> BOOKID_GetIssuanceRecords(String bookid)
	{
		ArrayList<Book_Issuance_Record> result = book.BOOKID_GetIssuanceRecords(bookid);
		return result;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------
	
	public void Store_Request (String memberid, String bookname, String authorname) throws IOException
	{
		book.Store_Request(memberid, bookname, authorname);
	}
	
	public ArrayList<Request_Book> GetAllRequests()
	{
		ArrayList<Request_Book> result = book.GetAllRequests();
		return result;
	}
	
	public void DELETE_PendingRequest(Request_Book result)
	{
		book.DELETE_PendingRequest(result);
	}
	
	public void Store_AcceptedRequest (String memberid, String bookname, String authorname) throws IOException
	{
		book.Store_AcceptedRequest(memberid, bookname, authorname);
	}
	
	public void Store_RejectedRequest (String memberid, String bookname, String authorname) throws IOException
	{
		book.Store_RejectedRequest(memberid, bookname, authorname);
	}
	//-------------------------------------------------------------------------------------------------------------------------------------------

}
