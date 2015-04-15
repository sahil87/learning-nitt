/******************************************************************/
////
////	Class Description	:	Settings Box, display/collect user settings
////	Components			:
////	Created By			:	Abhishek Jain
////	Created on			:	2007-10-06
////	Last Modification 	:
////	Last Modified By 	:
////	Last Modified on	:
////
/******************************************************************/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

public class SettingsBox
{
	public SettingsBox(Composite composite)
	{
		GridLayout g = new GridLayout(2,true);
		composite.setLayout(g);

		final Label serverAdd =  new Label(composite,SWT.END);
		serverAdd.setText("Server Address ");
		serverAdd.setLayoutData(new GridData(SWT.BEGINNING,SWT.CENTER,false,false,1,1));

		final Text serverAddr = new Text(composite,SWT.BORDER);
		GridData gdSAdd = new GridData (SWT.BEGINNING,SWT.BEGINNING,false,false,1,1);
		gdSAdd.widthHint=100;
		serverAddr.setLayoutData(gdSAdd);
		serverAddr.setText(Application.serverAddress);

		final Text t[] = new Text[3];
		// 1st setting option button
		final Button b1 = new Button( composite, SWT.RADIO);
		b1.setText("Direct Connection To The Internet");
		GridData gd = new GridData(SWT.BEGINNING,SWT.CENTER,true,false,2,1);
		b1.setLayoutData(gd);


		//2nd setting option button
		final Button b2 = new Button (composite,SWT.RADIO);
		b2.setText("Manual Proxy Configuration");
		b2.setLayoutData(gd);

		//HTTP Proxy label
		final Label l1 = new Label(composite,SWT.END);
		l1.setText("HTTP Proxy");
		GridData gd1 = new GridData (SWT.BEGINNING,SWT.BEGINNING,false,false,1,1);
		gd1.horizontalIndent=50;
		l1.setEnabled(false);
		l1.setLayoutData(gd1);

		//HTTP Proxy Textfield
		t[1] = new Text(composite,SWT.BORDER);
		GridData gd2 = new GridData (SWT.BEGINNING,SWT.BEGINNING,false,false,1,1);
		gd2.widthHint=150;
		t[1].setEnabled(false);
		t[1].setLayoutData(gd2);

		//Port Label
		final Label l2 = new Label(composite,SWT.END);
		l2.setText("Port");
		GridData gd3 = new GridData (SWT.BEGINNING,SWT.BEGINNING,false,false,1,1);
		gd3.horizontalIndent=50;
		l2.setEnabled(false);
		l2.setLayoutData(gd3);

		//Port Textfield
		t[2] = new Text(composite,SWT.BORDER);
		GridData gd4 = new GridData (SWT.BEGINNING,SWT.BEGINNING,false,false,1,1);
		gd4.widthHint=50;
		t[2].setEnabled(false);
		t[2].setLayoutData(gd4);
		/*
		//Authentication Button
		final Button b3 = new Button ( composite, SWT.CHECK);
		GridData gd5 = new GridData (SWT.BEGINNING,SWT.BEGINNING,false,false,2,1);
		gd5.horizontalIndent=50;
		b3.setText("Authentication");
		b3.setLayoutData(gd5);

		//Username Label
		final Label l3 = new Label(composite,SWT.END);
		l3.setText("Username");
		GridData gd6 = new GridData (SWT.BEGINNING,SWT.BEGINNING,false,false,1,1);
		gd6.horizontalIndent=100;
		l3.setEnabled(false);
		l3.setLayoutData(gd6);


		//Username Textfield
		t[3] = new Text(composite,SWT.BORDER);
		GridData gd7 = new GridData (SWT.BEGINNING,SWT.BEGINNING,false,false,1,1);
		gd7.widthHint=200;
		t[3].setLayoutData(gd7);
		t[3].setEnabled(false);

		//Passord Label
		final Label l4 = new Label(composite,SWT.END);
		l4.setText("Password");
		GridData gd8 = new GridData (SWT.BEGINNING,SWT.BEGINNING,false,false,1,1);
		gd8.horizontalIndent=100;
		l4.setLayoutData(gd8);
		l4.setEnabled(false);

		//Password Textfield
		t[4] = new Text(composite,SWT.BORDER|SWT.PASSWORD);
		GridData gd9 = new GridData (SWT.BEGINNING,SWT.BEGINNING,false,false,1,1);
		gd9.widthHint=200;
		t[4].setLayoutData(gd9);
		t[4].setEnabled(false);
		*/
		final Label timepass = new Label(composite,SWT.END);
		timepass.setEnabled(false);

		final Button saveButton = new Button(composite,SWT.PUSH);
		GridData gd10 = new GridData (SWT.BEGINNING,SWT.BEGINNING,false,false,1,1);
		saveButton.setText("   Save Settings   ");
		saveButton.setLayoutData(gd10);

		//Reads the settings from the file and displays
		/*
		try {
			 BufferedReader in = new BufferedReader(new FileReader("settings"));
			 String fieldsAddr[]=CO_CO.getSplit(in.readLine(),'#');
			 serverAddress=fieldsAddr[1];
			 System.out.println(fieldsAddr[1]);
			 String fieldsType[]=CO_CO.getSplit(in.readLine(),'#');
			 System.out.println(fieldsType[1]);
			 if(!fieldsType[1].equals("DC"))
			 {
				  System.getProperties().put( "http.proxySet", "true" );
				  String fieldsHost[]=CO_CO.getSplit(in.readLine(),'#');
				  proxyHost=fieldsHost[1];
				  String fieldsPort[]=CO_CO.getSplit(in.readLine(),'#');
				  proxyPort=fieldsPort[1];

			      System.getProperties().put( "http.proxyHost", proxyHost);
			      System.getProperties().put( "http.proxyPort",  proxyPort);

			      String fieldsUser[]=CO_CO.getSplit(in.readLine(),'#');
			      if(fieldsUser[1]!=null && !fieldsUser[1].equals(""))
			      {
			    	  proxyUser=fieldsUser[1];
			    	  String fieldsPass[]=CO_CO.getSplit(in.readLine(),'#');
				      proxyPasswd=fieldsPass[1];
			      }
			      System.out.println("Host"+proxyHost+"Port"+proxyPort+"User: "+proxyUser+" Passwd: "+proxyPasswd);
			 }
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("FileNotFound");

		}
		*/
		try {
	        BufferedReader in = new BufferedReader(new FileReader("settings.ini"));
	        //String str= new String(" ");
	        //str=in.readLine();
	        //int i=0;
	        String fieldsAddr[]=CO_CO.getSplit(in.readLine(),'#');
	        serverAddr.setText(fieldsAddr[1]);
			serverAddr.setText(fieldsAddr[1]);
			String fieldsType[]=CO_CO.getSplit(in.readLine(),'#');
			if( !fieldsType[1].equals("DC") )
		    {
	        	b2.setSelection(true);
		       	l1.setEnabled(true);
	 			l2.setEnabled(true);
	 			t[1].setEnabled(true);
	 			t[2].setEnabled(true);
	 			String fieldsHost[]=CO_CO.getSplit(in.readLine(),'#');
				t[1].setText(fieldsHost[1]);
				String fieldsPort[]=CO_CO.getSplit(in.readLine(),'#');
				t[2].setText(fieldsPort[1]);
				try
		        {
					//String fieldsUser[]=CO_CO.getSplit(in.readLine(),'#');
					//String fieldsPass[]=CO_CO.getSplit(in.readLine(),'#');
				   // b3.setSelection(true);
		       	//	l3.setEnabled(true);
		 		//	l4.setEnabled(true);
		 			//t[3].setEnabled(true);
		 			//t[4].setEnabled(true);
		 			//t[3].setText(fieldsUser[1]);
		 			//t[4].setText(fieldsPass[1]);
		       	}
		        catch ( Exception nullException) {
		        	//b3.setSelection(false);
		       		//l3.setEnabled(false);
		 			//l4.setEnabled(false);
		 			//t[3].setEnabled(false);
		 			//t[4].setEnabled(false);
		       	}
		    }
	        else
	        {
	        	b1.setSelection(true);
		    	l1.setEnabled(false);
				l2.setEnabled(false);
				//l3.setEnabled(false);
				//l4.setEnabled(false);
				t[1].setEnabled(false);
				t[2].setEnabled(false);
				//t[3].setEnabled(false);
				//t[4].setEnabled(false);
				//b3.setEnabled(false);
	        }
		    in.close();
	    } catch (IOException e) {

	    	b1.setSelection(true);
	    	l1.setEnabled(false);
			l2.setEnabled(false);
			//l3.setEnabled(false);
		//	l4.setEnabled(false);
			t[1].setEnabled(false);
			t[2].setEnabled(false);
			//t[3].setEnabled(false);
			//t[4].setEnabled(false);
			//b3.setEnabled(false);
	    }


		// Direction Connection option action listner
		b1.addSelectionListener(new SelectionAdapter(){
         	public void widgetSelected(SelectionEvent e)
         	{
         		if ( b1.getSelection()==true)
         			{
         				l1.setEnabled(false);
         				l2.setEnabled(false);
         		//		l3.setEnabled(false);
         			//	l4.setEnabled(false);
         				t[1].setEnabled(false);
         				t[2].setEnabled(false);
         				//t[3].setEnabled(false);
         				//t[4].setEnabled(false);
         				//b3.setEnabled(false);
         			}
         	}
		});

		//Manual Proxy Configuration option action listner
		b2.addSelectionListener(new SelectionAdapter(){
         	public void widgetSelected(SelectionEvent e)
         	{
         		if ( b2.getSelection()==true)
         		{
     				l1.setEnabled(true);
     				l2.setEnabled(true);
     				t[1].setEnabled(true);
     				t[2].setEnabled(true);
     				//b3.setEnabled(true);
         		}

   /*      		if ( b3.getSelection()==true)
         		{
         			l3.setEnabled(true);
         			l4.setEnabled(true);
         			t[3].setEnabled(true);
         			t[4].setEnabled(true);
         		}
     */    	}
		});

		//Authentication checkbox action listner
		/*b3.addSelectionListener(new SelectionAdapter(){
         	public void widgetSelected(SelectionEvent e)
         	{
         		if ( b3.getSelection()==true)
         		{

     				l3.setEnabled(true);
     				l4.setEnabled(true);
     				t[3].setEnabled(true);
     				t[4].setEnabled(true);
         		}
         		else
         		{
         			l3.setEnabled(false);
     				l4.setEnabled(false);
     				t[3].setEnabled(false);
     				t[4].setEnabled(false);
     			}
         	}
		});
		*/
		//Save button action listner
		saveButton.addSelectionListener(new SelectionAdapter(){
         	public void widgetSelected(SelectionEvent e)
         	{
         		MessageBox m = new MessageBox( Application.rootShell,SWT.ICON_INFORMATION|SWT.OK);
         		m.setText("Settings Saved");
         		m.setMessage("Your settings have been saved");
         		if ( m.open()==SWT.OK)
         		{
         			try
         			{
         				 BufferedWriter out = new BufferedWriter(new FileWriter("settings.ini"));
	            		 out.write("Server Address#"+serverAddr.getText()+"\n");
	       				 if ( b1.getSelection()==true)
	       				 {
	       					out.write("Type#DC\n");
	       					out.write("Proxy");
        		        	out.write("\n");
            		        out.write("Port");
            		        out.write("\n");
          			 }
	       				 if ( b2.getSelection()==true)
	       				 {
	       					if ( b1.getSelection()==false )
	            		        {
	       							out.write("Type#MPC\n");
	   	       				 		out.write("Proxy#"+t[1].getText());
	            		        	out.write("\n");
	                		        out.write("Port#"+t[2].getText());
	                		        out.write("\n");

	                		    }
	            		     }
	       			//	 WindowManager.status.setText("Settings saved");
	       				 out.close();
	       			}
	       			catch (IOException ex)
	       			{
	       			}
	       			Application.setProxySettings();
         		}
         	}
		});





	}
}
