import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import co.CommunicationObject;

public class Manager {
public CommunicationObject co;
public CommunicationObject ro;	
	public Manager()
	{
		co = new CommunicationObject();
		ro = new CommunicationObject();
	}

	public void connect()
	{
		try
		{
			URLConnection con = getServletConnection();
			
			 OutputStream outstr= con.getOutputStream();
	            ObjectOutputStream oos = new ObjectOutputStream(outstr); 
	            //String a="10";
	            oos.writeObject(co);
	            oos.flush();
	            oos.close();
			
	        InputStream instr = con.getInputStream();
			ObjectInputStream inputFromServlet = new ObjectInputStream(instr);
  			ro = (CommunicationObject) inputFromServlet.readObject();
			//ro.user.userEmail = (String) inputFromServlet.readObject();
			inputFromServlet.close();
			instr.close();
		}
		catch(Exception e)
		{
			System.out.println("::"+e.getMessage());
		}
	}
 
	private URLConnection getServletConnection()
    throws MalformedURLException, IOException
    {
            URL urlServlet;
            urlServlet = new URL("http://10.1.60.1:8080/ascomm/echo");
            URLConnection con = urlServlet.openConnection();

            // configuration
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestProperty(
                    "Content-Type",
                    "application/x-java-serialized-object");
           
           return con;

        }

}
