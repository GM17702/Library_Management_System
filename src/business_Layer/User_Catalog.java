package business_Layer;

import java.io.IOException;
import java.util.ArrayList;

public class User_Catalog 
{
	Librarian lib = new Librarian();
	Admin admin = new Admin();
	Member mem = new Member();
	
	//---------------------------------------------------------------------------------
	
	public boolean L_Check_Signup(String username, String password) throws IOException
	{
		boolean decision = lib.Signup_Check(username, password);
		return decision;
	}
	
	public boolean L_Check_Login(String username, String password) throws IOException
	{
		boolean decision = lib.Login_Check(username, password);
		return decision;
	}
	
	public boolean A_Check_Signup(String username,String password) throws IOException
	{
		boolean decision = admin.Signup_Check(username, password);
		return decision;
	}
	
	public boolean A_Check_Login(String username, String password) throws IOException
	{
		boolean decision = admin.Login_Check(username, password);
		return decision;
	}
	
	//----------------------------------------------------------------------------------
	
	public boolean M_Check_Signup(String username, String password, String memberid, String name, String address, String pnum) throws IOException
	{
		boolean decision = mem.Member_Registeration(username, password,memberid,name,address,pnum);
		return decision;
	}
	
	public boolean M_Check_Login(String username, String password) throws IOException
	{
		boolean decision = mem.Member_Login_Check(username, password);
		return decision;
	}
	
	//-----------------------------------------------------------------------------------
	
	public ArrayList<Member> ID_MemberSearch(String ID)
	{
		ArrayList<Member> result = mem.MEMBERID_MemberSearch(ID);
		return result;
	}
	
	public ArrayList<Member> NAME_MemberSearch(String name)
	{
		ArrayList<Member> result = mem.NAME_MemberSearch(name);
		return result;
	}
	
	public void UPDATE_MemberRecord(ArrayList<Member> result)
	{
		mem.UPDATE_MemberRecord(result);
	}
	
	public void DELETE_MemberRecord(Member m)
	{
		mem.DELETE_MemberRecord(m);
	}
	
	//--------------------------------------------------------------------------------------
	
	public boolean DoesMemberExists(String memberid)
	{
		boolean decision = mem.DoesMemberExists(memberid);
		return decision;
	}
	
	public ArrayList<Member> GetAllMemberDetails()
	{
		ArrayList<Member> result = mem.GetAllMemberDetails();
		return result;
	}
	
	public void Member_SessionCreation (String user) throws IOException
	{
		mem.Member_SessionCreation(user);
		
	}
	
	public String Member_ObtainSession() throws IOException
	{
		String result=mem.Member_ObtainSession();
		return result;
	}
	
	public void Member_SessionTermination () throws IOException
	{
		mem.Member_SessionTermination();
		
	}
}	
