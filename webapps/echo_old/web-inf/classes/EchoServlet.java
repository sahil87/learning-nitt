
import java.io.*;
import javax.servlet.*;
import java.sql.*;
//import java.lang.Byte;

public class EchoServlet extends GenericServlet 
{
    static final long serialVersionUID=124;
    int c_row,java_row,cQueryInterval=400;
    String msg="";
    public void service(ServletRequest request,	ServletResponse response)
	throws ServletException, IOException 
    {	
	PrintWriter pw = response.getWriter();

	Connection con = null;
	Statement st = null;//,st1=null;
	ResultSet rs = null;//,rs1=null;
	msg="";

	try 
	    {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://178.1.3.201:3306/dalal",
						  "dalal", "fire");
		st = con.createStatement();//st1=con.createStatement();
		rs = st.executeQuery("SELECT * FROM `dalal_user`");
		
	//	if(request.getParameter("querypoint").equals("start"))
	/*	{
			msg+="i am here in querypoint = start for loop!!<br>";
			while(rs.next())
			if(rs.getString(1).substring(0,5).equals("c_row"))
			{	
				c_row=rs.getInt(2);
				java_row=c_row-3;
				st1.executeUpdate("UPDATE `CONTROL` SET `VALUE` = "+c_row+" WHERE `PARAMETER` = 'java_row'");
				
				if(c_row>3)
					rs1=st1.executeQuery("SELECT `data` FROM DATA LIMIT "+(java_row*cQueryInterval)+","+(3000));
			}
		}*/
	/*	else if(request.getParameter("querypoint").equals("continue"))
		{
			while(rs.next())
			{	if(rs.getString(1).equals("c_row"))
					c_row=rs.getInt(2);
				else if(rs.getString(1).equals("java_row"))
					java_row=rs.getInt(2);
			}
			if(c_row==java_row) java_row=c_row-1;
			st1.executeUpdate("UPDATE `CONTROL` SET `VALUE` = "+c_row+" WHERE `PARAMETER` = 'java_row'");
			rs1=st1.executeQuery("SELECT `data` FROM DATA LIMIT "+java_row*cQueryInterval+","+(c_row - java_row)*cQueryInterval);
		}
		int i=1;
		//if(rs==null) msg+="\nBut the result is NULL!";	else msg+="\nAnd the result isn't even null!!";
		while(rs1.next()) 
		{
			bigbox[++i] = new Byte(rs1.getByte(1));
		}
		i--;
		bigbox[0]=new Byte((byte) (i/256 -128)); // number of rows!
		bigbox[1]=new Byte((byte) ((i%256) -128));	*/
		
		if(rs.first())
		    {
		    rs.next();
		msg+=rs.getString("user_password");
		    }
		else msg+="Not connected";
	    } 
	catch (Exception e) 
	    {
		msg+=e.getMessage()+"I am in error.";
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
		catch (SQLException e) 
		    {
		    }
	    }
/*response.setContentType("application/x-java-serialized-object");
			//instead of "text/html"
OutputStream outstr = response.getOutputStream();
ObjectOutputStream oos = new ObjectOutputStream(outstr);
			oos.writeObject(bigbox);
			oos.flush();
			oos.close();
*/
//	
//	int bigByteBoxSize=bigbox[0].intValue();
//	int bigByteBoxSize=(bigbox[0].intValue()+128)*256+(bigbox[1].intValue()+128);
//	for(int i=0;i<bigByteBoxSize;++i)
//		msg+="  "+bigbox[i+2];
//	pw.println("<B>Hello</B>!"+msg+" bigByteBoxSize = ");//+bigByteBoxSize);
//	pw.close();
	response.setContentType("text/html");
	pw.println(msg);
	pw.close();
    }
}

