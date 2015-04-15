////
////	Class Description	:	Application, to initialize the shell,
////							display components of the Application
////	Components			:	Shell, Display, ImageLoader,
////	Created By			:	Abhishek Jain
////	Created on			:	2007-10-06
////	Last Modification 	:
////	Last Modified By 	:
////	Last Modified on	:
////


/* Dalal '08
 *
 * Read the "progress" file in the project to find out whats being done and whats left
 *
 * Dont feel shy to raise/issue tickets relating dalal in http://delta.nitt.edu/trac/dalal
 *
 * Also keep checking http://delta.nitt.edu/doku/doku.php/dalal/welcome
 *
 *
 */

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.zip.GZIPInputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class Application {

	/**
	 * @param args
	 */

	public static Shell rootShell;
	public static Display display;
	public static String proxyUser="",proxyPasswd="",proxyHost="",proxyPort="",serverAddress="";
	public static String companyMD5,MFMD5,companyString,MFString;
	static ServerSocket s;

	public static TimerThread timerThread;
	public static GameUpdater updaterThread;

//// Load Image into the Application
	static Image loadImage (String string)
	{
        InputStream stream = Application.class.getResourceAsStream("Images/"+string);
        /* Path Images to be changed in AnimateGIF Line : 25 */
        if (stream == null) return null;
        Image image = null;

        try
        {
             image = new Image (display, stream);
        }
        catch (SWTException ex) {   }
        finally
        {
            try {
                  stream.close ();
             } catch (IOException ex) {}
        }
        return image;
	}
//// Get The MD5 Conversion of a String
	public static String getMD5(String sessionid)
	{
		byte[] defaultBytes = sessionid.getBytes();
		try{
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();

			StringBuffer hexString = new StringBuffer();
			for (int i=0;i<messageDigest.length;i++) {
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}
			sessionid=hexString+"";
		}
		catch(NoSuchAlgorithmException nsae){
		}
		return sessionid;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean alreadyRunning=false;
		serverAddress="localhost";
		//Check if an instance is already running
		try
		{
			s =new ServerSocket(8899);
			//Dont remove the pointer s because it ensures the ServerSocket doesn't get closed;
		}
		catch(Exception e)
		{
			alreadyRunning=true;
		}


		timerThread=new TimerThread();
		timerThread.setDaemon(true);

		updaterThread=new GameUpdater();
		updaterThread.setDaemon(true);

		timerThread.setTime(new Timestamp(System.currentTimeMillis()));
		TimerThread.running=true;

		display=new Display();
		rootShell=new Shell(display);
/*		final DisposeListener a= new DisposeListener(){
			public void widgetDisposed(DisposeEvent e)
			{
				System.out.println("In Application : At the Disposal of Root Shell");
				if(Manager.gi!=null && Manager.gi.user.loggedin)
					Manager.logout();
			}
		};
		rootShell.addDisposeListener(a);
*/
//		companyMD5 = "Company MD5";
//		MFMD5 = "Mutual Funds MD5";
//		companyString = "Company String";
//		MFString = "Mutual Funds String";
//		readCompanyConf();
//		readMFConf();
		//System.out.println(companyString.length()+" : "+companyString);
		//System.out.println(MFString.length()+" : "+MFString);

		if(alreadyRunning)
		{
			MessageBox m=new MessageBox(rootShell,SWT.ICON_ERROR|SWT.OK);
			m.setText("Instance already running");
			m.setMessage("One instance of the game is already running!");
			m.open();
		}
		else
		{
			timerThread.start();
			updaterThread.start();
			setProxySettings();
			new WindowManager(rootShell);
			rootShell.open();
			rootShell.setMaximized(true);
			while (!rootShell.isDisposed())
			{
				if (!display.readAndDispatch())
				{
					display.sleep();
				}
			}
		}
		display.dispose();
	}
//// Get Proxy Settings from File and Set it to System Variables
	public static void setProxySettings()
	{
		 try {
			 BufferedReader in = new BufferedReader(new FileReader("settings.ini"));
			 String fieldsAddr[]=CO_CO.getSplit(in.readLine(),'#');
			 serverAddress=fieldsAddr[1];
			// System.out.println(fieldsAddr[1]);
			 String fieldsType[]=CO_CO.getSplit(in.readLine(),'#');
			// System.out.println(fieldsType[1]);
			 if(!fieldsType[1].equals("DC"))
			 {
				  System.getProperties().put( "http.proxySet", "true" );
				  String fieldsHost[]=CO_CO.getSplit(in.readLine(),'#');
				  proxyHost=fieldsHost[1];
				  String fieldsPort[]=CO_CO.getSplit(in.readLine(),'#');
				  proxyPort=fieldsPort[1];

			      System.getProperties().put( "http.proxyHost", proxyHost);
			      System.getProperties().put( "http.proxyPort",  proxyPort);

			   //   String fieldsUser[]=CO_CO.getSplit(in.readLine(),'#');
			   //   System.out.println("Host"+proxyHost+"Port"+proxyPort+"User: "+proxyUser+" Passwd: "+proxyPasswd);
			 }
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("FileNotFound");

		}
	}
//// Read Company Configuration File..
	public static String readCompanyConf()
	{
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedReader br;
		String sr = "";
		try
		{
			br = new BufferedReader(new FileReader("Conf/Company.conf"));
			sr = br.readLine();
			String[] s = sr.split(" ");
			byte[] h = new byte[s.length];
			for(int i=0;i<s.length;i++)
				h[i] = (byte)Integer.parseInt(s[i]);
		//// decompression
		ByteArrayInputStream bais = new ByteArrayInputStream(h);
		GZIPInputStream zis = null;
		byte[] buffer = new byte[ 8192 ];
		byte[] baFileContentDecompressed = null;
		baos = new ByteArrayOutputStream();
			try
			{
			zis = new GZIPInputStream(bais);
			for(int len; (len = zis.read(buffer)) != -1; )
				baos.write(buffer,0,len);
			zis.close();
			bais.close();
			baos.close();
			baFileContentDecompressed = baos.toByteArray();
			}
			catch (IOException e) {
			System.out.println(e.getMessage());
			}
		String output="";
		for(int i = 0; i < baFileContentDecompressed.length; i++)
			output = output + (char) baFileContentDecompressed[i];

//		companyString = output;
//		companyMD5 = getMD5(companyString);
		return output;

//		String[] fields = CO_CO.getSplit(output, CO_CO.delim[0]);
//		for(int i=0;i<fields.length;i++)
//			Manager.gi.company.put(i+1, fields[1]);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
////Read Company Configuration File..
	public static String readMFConf()
	{
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedReader br;
		String sr = "";
		try
		{
			br = new BufferedReader(new FileReader("Conf/MF.conf"));
			sr = br.readLine();
			String[] s = sr.split(" ");
			byte[] h = new byte[s.length];
			for(int i=0;i<s.length;i++)
				h[i] = (byte)Integer.parseInt(s[i]);
		//// decompression
		ByteArrayInputStream bais = new ByteArrayInputStream(h);
		GZIPInputStream zis = null;
		byte[] buffer = new byte[ 8192 ];
		byte[] baFileContentDecompressed = null;
		baos = new ByteArrayOutputStream();
			try
			{
			zis = new GZIPInputStream(bais);
			for(int len; (len = zis.read(buffer)) != -1; )
				baos.write(buffer,0,len);
			zis.close();
			bais.close();
			baos.close();
			baFileContentDecompressed = baos.toByteArray();
			}
			catch (IOException e) {
			System.out.println(e.getMessage());
			}
		String output="";
		for(int i = 0; i < baFileContentDecompressed.length; i++)
			output = output + (char) baFileContentDecompressed[i];

//		MFString = output;
//		MFMD5 = getMD5(MFString);
		return output;

//		String[] fields = CO_CO.getSplit(output, CO_CO.delim[0]);
//		for(int i=0;i<fields.length;i++)
//			Manager.gi.company.put(i+1, fields[1]);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
}
