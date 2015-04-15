//import java.io.*;
//import java.net.*;
//import java.sql.*;
//import java.lang.Byte;

class Value
{
 // To make it a sine wave generator - 
	int current;
	byte val;
	Value()
	{
		current = 0;
	}
	byte call()
	{
		current++;
		val = (byte) ((Math.sin(current*Math.PI/24))*100);
		return  val;
	}

/*	public Byte[] bigbyteBox;
	public int bigboxSize;
	public ResultSet rs;
	public URL codebase;
//	public String appy;

	public Byte[] call() 
	{
		try
		{
			URLConnection con = getServletConnection();
			InputStream instr = con.getInputStream();
			ObjectInputStream inputFromServlet = new ObjectInputStream(instr);
			bigbyteBox = (Byte[]) inputFromServlet.readObject();
			inputFromServlet.close();
			instr.close();
			bigboxSize=(bigbyteBox[0].intValue()+128)*256+(bigbyteBox[1].intValue()+128);
		//	int i=bigboxSize+1;
			//while(i--)	box.in(bigbox[i]);//IMPROVE THIS	
		}
		catch(Exception ex)
		{
//			appy="Error in applet!!! : "+ex.toString();
		}
		return bigbyteBox;
	/*	try {
			// get input data for sending
			String input = inputField.getText();

			// send data to the servlet
			URLConnection con = getServletConnection();
			OutputStream outstream = con.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(outstream);
			oos.writeObject(input);
			oos.flush();
			oos.close();

			// receive result from servlet
			InputStream instr = con.getInputStream();
			ObjectInputStream inputFromServlet = new ObjectInputStream(instr);
			ResultSet rs = (ResultSet) inputFromServlet.readObject();
			inputFromServlet.close();
			instr.close();
			int userId = rs.getInt(1);
			String firstName = rs.getString(2);
			// show result
		//	outputField.setText(result);

		} catch (Exception ex) {
			ex.printStackTrace();
			exceptionArea.setText(ex.toString());
		}
	}

	private URLConnection getServletConnection()
	throws MalformedURLException, IOException 
	{
		// Connection zum Servlet öffnen
		URL urlServlet = new URL(codebase, "echo");
		URLConnection con = urlServlet.openConnection();
		// configuration
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false);
		con.setRequestProperty(
			"Content-Type",
			"application/x-java-serialized-object");
		// und zurückliefern
		return con;
	}*/
}


