package co;
import java.io.Serializable;
import java.util.*;
public class CommunicationObject implements Serializable
{
	static final long serialVersionUID=17231000;
	public BitSet check;
	public String action;
	public String type;
//	public Company company;
	public User user;
	
	public CommunicationObject()
	{
		check = new BitSet(3);
		check.set(2);
		user= new User();
		action = new String();
//		company = new Company();
	}
}
