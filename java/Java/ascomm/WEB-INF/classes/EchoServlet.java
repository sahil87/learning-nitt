import java.io.*;
import javax.servlet.*;
//import co.*;

import co.CommunicationObject;


import java.sql.*;
import java.lang.Byte;

public class EchoServlet extends GenericServlet 
{
    static final long serialVersionUID=124;
    CommunicationObject co,ro;
    String msg="default";
    
    public void service(ServletRequest request,	ServletResponse response)
	throws ServletException, IOException 
    {	
    	Connection con = null;
    	Statement st = null;
    	ResultSet rs = null;

    	try 
    	{
			co = new CommunicationObject();
			ro = new CommunicationObject();
			/*
			 * Make mysql connection
			 */
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    con = DriverManager.getConnection("jdbc:mysql://10.1.60.1:3306/dalal","dalal", "fire");
		    st = con.createStatement();
		    /*
		     * Start reading the communication object
		     */
		    ServletInputStream servletInputFromApp = request.getInputStream(); 
	    	ObjectInputStream inputFromApp=new ObjectInputStream(servletInputFromApp);
	    	co  = (CommunicationObject) inputFromApp.readObject();
	    	inputFromApp.close();
	    	servletInputFromApp.close();
	    	/*
	    	 * Decide the return object (with help of db)
	    	 */
	    	if(co.action=="login")
	    	{
	    		 rs = st.executeQuery("SELECT * FROM `users` WHERE user_email='"+co.user.userEmail+"' AND user_password='"+co.user.userPassword+"'");
		    	 if(rs.first())
		    	 {
		    		 //user exists
		    		 ro.user=co.user;
		    		 ro.user.check.set(2);
		    		 ro.user.loggedin=true;
		    	 }
		    	 else
		    	 {
		    		 //user doesn't exist
		    		 ro.user=co.user;
		    		 ro.user.check.set(2);
		    		 ro.user.loggedin=false;
		    	 } 
	    	}
    	} 
	catch (Exception e) 
	{
	   	msg=e.getMessage()+"Error in main catch of servlet";
	}
	finally 
	{
	    try 
	    {
	    	if(rs != null)  rs.close();
	    	if(st != null)  st.close();
	    	if(con != null) con.close();
	    }
	    catch (Exception e)
	    {
	    	msg="Error in finally in servlet";
	    }
	}
	response.setContentType("application/x-java-serialized-object");
	OutputStream outstr = response.getOutputStream();
	ObjectOutputStream oos = new ObjectOutputStream(outstr);
	oos.writeObject(ro);
	oos.flush();
	oos.close();

    }
}

