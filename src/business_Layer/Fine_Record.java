package business_Layer;

import java.io.IOException;
import java.util.ArrayList;

import data_Layer.Fine_Record_Handling;

public class Fine_Record 
{

	private int fineamount;
	private String finestatus;
	
	Fine_Record_Handling f = new Fine_Record_Handling();
	Book b = new Book();
	Member m = new Member();
	
	public Fine_Record()
	{
		
	}
	
	public Fine_Record(String book_ISBN, String memberID, int fineamount, String finestatus) 
	{
		this.b.setISBN(book_ISBN);
		this.m.setMemberid(memberID);
		this.fineamount = fineamount;
		this.finestatus = finestatus;
	}
	
	public Fine_Record(String finestatus) 
	{
		this.finestatus = finestatus;
	}



	public int getFineamount() 
	{
		return fineamount;
	}
	public void setFineamount(int fineamount) 
	{
		this.fineamount = fineamount;
	}
	public String getFinestatus() 
	{
		return finestatus;
	}
	public void setFinestatus(String finestatus) 
	{
		this.finestatus = finestatus;
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

	public void Store_FineDetails(String bookisbn, String memberid, long days) throws IOException
	{
		int fine = (int) (days*30);
		f.Store_FineDetails(bookisbn, memberid, fine);
	}
	
	public boolean MEMBER_CheckFineStatus(String memberid)
	{
		boolean decision = f.MEMBER_CheckFineStatus(memberid);
		return decision;
	}
	
	public void UPDATE_FineRecord(String bookid, String memberid, int fine, String finestatus)
	{	
		f.UPDATE_FineRecord(bookid, memberid, fine, finestatus);
	}
	
	public ArrayList<Fine_Record> MEMBERID_FineRecordSearch(String memberid)
	{
		ArrayList<String> result = f.MEMBERID_FineRecordSearch(memberid);
		ArrayList<Fine_Record> theresult = new ArrayList <Fine_Record>();
		
		if (result.isEmpty())
		{
			return theresult;
		}
		else
		{
			int fine = Integer.parseInt(result.get(2));
			Fine_Record b = new Fine_Record(result.get(0),result.get(1),fine,result.get(3));
			theresult.add(b);
			return theresult;
		}
	}
	
	public ArrayList<Fine_Record> GetAllMemberFineStatus(ArrayList<String> memberids)
	{
		ArrayList<String> statuses = f.GetAllMemberFineStatus(memberids);
		ArrayList <Fine_Record> statusrecord = new ArrayList <Fine_Record>();
		
		for (int i=0; i<statuses.size(); i++)
		{
			if (statuses.get(i).equals("unpaid"))
			{
				statuses.set(i, "Unpaid");
			}
			else if (statuses.get(i).equals("paid"))
			{
				statuses.set(i, "Paid");
			}
			
			Fine_Record rec = new Fine_Record(statuses.get(i));
			statusrecord.add(rec);
		}
		
		return statusrecord;
	}
	
	
	public ArrayList<Fine_Record> MEMBERID_GetFineHistory(String memberid)
	{
		ArrayList<String> result = f.MEMBERID_GetFineHistory(memberid);
		ArrayList<Fine_Record> theresult = new ArrayList <Fine_Record>();
		
		if (result.isEmpty())
		{
			return theresult;
		}
		else
		{
			int realsize = result.size() / 4;
			for (int i=0; i<realsize; i++)
			{
				int fine = Integer.parseInt(result.get(2+(4*i)));
				Fine_Record b = new Fine_Record(result.get(0+(4*i)),result.get(1+(4*i)),fine,result.get(3+(4*i)));
				theresult.add(b);
			}
			
			return theresult;
		}
	}
	
	
}
