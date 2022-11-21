package business_Layer;

import java.io.IOException;

interface Signup_and_Login
{
	public abstract boolean Signup_Check(String username, String password) throws IOException;
	public abstract boolean Login_Check(String username, String password) throws IOException;
}
