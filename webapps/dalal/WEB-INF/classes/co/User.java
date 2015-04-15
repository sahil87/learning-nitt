package co;
import java.io.Serializable;
import java.util.*;
public class User implements Serializable 
{
	static final long serialVersionUID = 17231001;
	public BitSet check;
	public String userEmail;
	public String userPassword;
	public boolean loggedin;
	public User()
	{
		check = new BitSet(3);
		userEmail=new String("defaultDalalServlet");
		userPassword=new String();
		loggedin=false;
	}
}

