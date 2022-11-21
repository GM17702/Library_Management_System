package business_Layer;

import java.io.IOException;
import java.util.ArrayList;

import data_Layer.LoginInfo_Handling;
import data_Layer.Member_Handling;

public class Member 
{
	private String username;
	private String password;
	private String memberid;
	private String name;
	private String address;
	private String phonenumber;
	
	LoginInfo_Handling l = new LoginInfo_Handling();
	Member_Handling m = new Member_Handling();
	
	public Member()
	{
		
	}
	
	public Member (String id, String name, String address, String pnum)
	{
		this.memberid=id;
		this.name=name;
		this.address=address;
		this.phonenumber=pnum;
	}
	
	
	
	public String getMemberid() 
	{
		return memberid;
	}
	public void setMemberid(String memberid) 
	{
		this.memberid = memberid;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getAddress() 
	{
		return address;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}
	public String getPhonenumber() 
	{
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) 
	{
		this.phonenumber = phonenumber;
	}
	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public boolean Member_Registeration(String username, String password, String memberid, String name, String address, String pnum) throws IOException
	{
		String ename = name.replace(" ", "`");
		String eaddress = address.replace(" ", "`");
		
		boolean decision = l.Member_Signup_LoginInfo(username, password,memberid,ename,eaddress,pnum);
		
		if (decision==true)
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}

	public boolean Member_Login_Check(String username, String password) throws IOException 
	{
		boolean decision =l.Member_Search_Login_LoginInfo(username, password);
		
		if (decision==true)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public ArrayList<Member> MEMBERID_MemberSearch(String ID)
	{
		ArrayList<String> result = m.MEMBERID_MemberSearch(ID);
		ArrayList<Member> theresult = new ArrayList <Member>();
		
		if (result.isEmpty())
		{
			return theresult;
		}
		else
		{
			String newname = result.get(1).replace("`"," ");
			String newaddress = result.get(2).replace("`"," ");
			
			Member m1 = new Member(result.get(0),newname,newaddress,result.get(3));
			theresult.add(m1);
			return theresult;
		}
	}
	
	public ArrayList<Member> NAME_MemberSearch(String name)
	{
		String ename = name.replace(" ", "`");
		
		ArrayList<String> result = m.NAME_MemberSearch(ename);
		ArrayList<Member> theresult = new ArrayList <Member>();
		
		if (result.isEmpty())
		{
			return theresult;
		}
		else
		{
			int realsize = result.size() / 4;
			
			
			
			for (int i=0; i<realsize; i++)
			{
				String newname = result.get(1 + (4*i)).replace("`"," ");
				String newaddress = result.get(2 + (4*i)).replace("`"," ");
				
				Member b = new Member(result.get(0 + (4*i)),newname,newaddress,result.get(3 + (4*i)));
				theresult.add(b);
			}
			
			return theresult;
		}
	}
	
	
	//---------------------------------------------------------------------------------------------------------------------------
	
	public void UPDATE_MemberRecord(ArrayList<Member> result)
	{
		for (int i=0; i<result.size(); i++)
		{
			result.get(i).setName(result.get(i).getName().replace(" ", "`"));
			result.get(i).setAddress(result.get(i).getAddress().replace(" ", "`"));
		}
		
		m.UPDATE_MemberRecord(result);
	}
	
	public void DELETE_MemberRecord(Member m)
	{
		m.setName(m.getName().replace(" ", "`"));
		m.setAddress(m.getAddress().replace(" ", "`"));
		
		this.m.DELETE_MemberRecord(m);
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	
	public boolean DoesMemberExists(String memberid)
	{
		boolean decision = m.DoesMemberExists(memberid);
		return decision;
	}
	
	public void Member_SessionCreation (String user) throws IOException
	{
		l.Member_SessionCreation(user);
		
	}
	
	public String Member_ObtainSession() throws IOException
	{
		String result=l.Member_ObtainSession();
		return result;
	}
	
	public void Member_SessionTermination () throws IOException
	{
		l.Member_SessionTermination();
		
	}
	
	public ArrayList<Member> GetAllMemberDetails()
	{
		ArrayList<String> result = m.GetAllMemberDetails();
		ArrayList<Member> theresult = new ArrayList <Member>();
		
		if (result.isEmpty())
		{
			return theresult;
		}
		else
		{
			int realsize = result.size() / 4;
			
			for (int i=0; i<realsize; i++)
			{
				String newname = result.get(1 + (4*i)).replace("`"," ");
				String newaddress = result.get(2 + (4*i)).replace("`"," ");
				
				
				Member b = new Member(result.get(0 + (4*i)),newname,newaddress,result.get(3 + (4*i)));
				theresult.add(b);
			}
			
			return theresult;
		}
	}
	
}
