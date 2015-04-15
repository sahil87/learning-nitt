import java.io.*;
import javax.servlet.*;
//import co.*;

import co.CommunicationObject;


import java.sql.*;
import java.lang.Byte;

public class EchoServlet extends GenericServlet 
{
    static final long serialVersionUID=124;
    Byte bigbox[]=new Byte[15000];
    int c_row,java_row,cQueryInterval=400;
    CommunicationObject co,ro;
    String msg;
    
    public void service(ServletRequest request,	ServletResponse response)
	throws ServletException, IOException 
    {	
    	Connection con = null;
    	Statement st = null;
    	ResultSet rs = null;

	try 
	{

	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	    con = DriverManager.getConnection("jdbc:mysql://178.1.3.207:3306/dalal","dalal", "fire");
	    st = con.createStatement();

	    ServletInputStream servletInputFromApp = request.getInputStream(); 
    	ObjectInputStream inputFromApp=new ObjectInputStream(servletInputFromApp);
    	CommunicationObject co  =(CommunicationObject) inputFromApp.readObject();
//    	inputFromApp.close();
//    	servletInputFromApp.close();     
    	//String a  =(String) inputFromApp.readObject();
    	 /*
	    if(co.action.equals("login"))
	    {
	    	 rs = st.executeQuery("SELECT * FROM `users` WHERE user_email='"+co.user.userEmail+"' AND user_password='"+co.user.userPassword+"'");
	    	 if(rs.first())
	    	 {
	    		 //user exists
	    		 CommunicationObject ro = new CommunicationObject();
	    		 ro.user.check.set(2);
	    		 ro.user.loggedin=true;
	    	 }
	    	 else
	    	 {
	    		 //user doesn't exist
	    		 CommunicationObject ro = new CommunicationObject();
	    		 ro.user.check.set(2);
	    		 ro.user.loggedin=false;
	    	 } 
	    }*/
		CommunicationObject ro = new CommunicationObject();
	    ro.user.loggedin=true;
	    ro.user.userEmail="Welcome : Anshu"+co.action;
/*	    rs = st.executeQuery("SELECT * FROM `users`");
	    if(	rs.first() )
		msg=rs.getString("name");
	    else
		msg="oops!";
	    msg+="Me not in errorServlet";
	*/				
	} 
	catch (Exception e) 
	{
	   	msg+=e.getMessage()+"sahil";
	}
	finally 
	{
	    try 
	    {
		if(rs != null)
		    rs.close();
		if(st != null)
		    st.close();
		if(con != null)
		    con.close();
	    } 
	  //  catch (SQLException e)
	    catch (Exception e)
	    {
	  //  	CommunicationObject ro = new CommunicationObject();
	//    	ro.user.userEmail="Error in Servlet :"+e.getMessage();
	    	msg="hello";
	    }
	}
	response.setContentType("application/x-java-serialized-object");
	OutputStream outstr = response.getOutputStream();
	ObjectOutputStream oos = new ObjectOutputStream(outstr);
	oos.writeObject(msg);
	oos.flush();
	oos.close();

    }
}

