package application;

public class Fine_Record_Wrapper 
{
	
	private String Book_ISBN;
	private String MemberID;
	private int fineamount;
	private String finestatus;
	
	public Fine_Record_Wrapper(String book_ISBN, String memberID, int fineamount, String finestatus) 
	{
		Book_ISBN = book_ISBN;
		MemberID = memberID;
		this.fineamount = fineamount;
		this.finestatus = finestatus;
	}
	
	
	public String getBook_ISBN() {
		return Book_ISBN;
	}
	public void setBook_ISBN(String book_ISBN) {
		Book_ISBN = book_ISBN;
	}
	public String getMemberID() {
		return MemberID;
	}
	public void setMemberID(String memberID) {
		MemberID = memberID;
	}
	public int getFineamount() {
		return fineamount;
	}
	public void setFineamount(int fineamount) {
		this.fineamount = fineamount;
	}
	public String getFinestatus() {
		return finestatus;
	}
	public void setFinestatus(String finestatus) {
		this.finestatus = finestatus;
	}

}
