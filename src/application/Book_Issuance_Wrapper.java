package application;

import java.time.LocalDate;

public class Book_Issuance_Wrapper 
{
	private String Book_ISBN;
	private String Member_ID;
	private LocalDate issuedate;
	private LocalDate duedate;
	private LocalDate returndate;
	private String renewflag;
	
	public Book_Issuance_Wrapper(String book_ISBN, String member_ID, LocalDate issuedate, LocalDate duedate, LocalDate returndate) 
	{
		this.Book_ISBN = book_ISBN;
		this.Member_ID = member_ID;
		this.issuedate = issuedate;
		this.duedate = duedate;
		this.returndate = returndate;
	}
	
	public Book_Issuance_Wrapper(String book_ISBN, String member_ID, LocalDate issuedate, LocalDate duedate, LocalDate returndate, String renewflag) 
	{
		this.Book_ISBN = book_ISBN;
		this.Member_ID = member_ID;
		this.issuedate = issuedate;
		this.duedate = duedate;
		this.returndate = returndate;
		this.renewflag=renewflag;
	}
	
	public Book_Issuance_Wrapper(String book_ISBN, String member_ID, LocalDate issuedate, LocalDate duedate) 
	{
		this.Book_ISBN = book_ISBN;
		this.Member_ID = member_ID;
		this.issuedate = issuedate;
		this.duedate = duedate;
	}
	
	public String getBook_ISBN() {
		return Book_ISBN;
	}
	public void setBook_ISBN(String book_ISBN) {
		Book_ISBN = book_ISBN;
	}
	public String getMember_ID() {
		return Member_ID;
	}
	public void setMember_ID(String member_ID) {
		Member_ID = member_ID;
	}
	public LocalDate getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(LocalDate issuedate) {
		this.issuedate = issuedate;
	}
	public LocalDate getDuedate() {
		return duedate;
	}
	public void setDuedate(LocalDate duedate) {
		this.duedate = duedate;
	}
	public LocalDate getReturndate() {
		return returndate;
	}
	public void setReturndate(LocalDate returndate) {
		this.returndate = returndate;
	}
	public String getRenewflag() {
		return renewflag;
	}
	public void setRenewflag(String renewflag) {
		this.renewflag = renewflag;
	}

}
