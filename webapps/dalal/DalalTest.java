import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class DalalTest extends JApplet implements Runnable
{
	static final long serialVersionUID=2576;
	public URL codebase;
	JTextField name;
	Thread t = null;
	boolean stopFlag;
	public void init() 
	{
		Container contentPane = getContentPane();
		name = new JTextField("Your Name :");
		contentPane.add(name);
		name.setText("pakoda2");
		codebase=getCodeBase();
    }
	public void start()
	{
		t = new Thread(this);
		stopFlag=false;
		t.start();
	}
	public void run()
	{
	while(!stopFlag)
	{
		try	
		{
			repaint();
			Thread.sleep(1000);
			name.setText(name.getText()+"Me in try");
			URLConnection con = getServletConnection();
			InputStream instr = con.getInputStream();
			ObjectInputStream inputFromServlet = new ObjectInputStream(instr);
			String a = (String) inputFromServlet.readObject();
			name.setText(a+name.getText()+"hallalua");
			//repaint();
		}
		catch(Exception ex)
		{
			name.setText(ex.getMessage());
		}
	}
	}
	
	private URLConnection getServletConnection()
	throws MalformedURLException, IOException
	{
		URL urlServlet;
		urlServlet = new URL(codebase, "echo");//+"?querypoint=continue"
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
