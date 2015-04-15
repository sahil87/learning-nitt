package src;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import co.CommunicationObject;

public class test_3{

	CommunicationObject gi;
	Manager m;
	
	public test_3(Composite shell) 
	{
		
		gi= new CommunicationObject();
		m = new Manager(); 
				
		 Composite composite1 = new Composite(shell,SWT.FILL);
		 RowLayout rowLayout1 = new RowLayout();
		 rowLayout1.type=SWT.VERTICAL;
		 rowLayout1.marginTop=240;
		 rowLayout1.marginLeft=170;
		 composite1.setLayout(rowLayout1);

		 
		 Composite composite2 = new Composite(composite1,SWT.FILL);
		 GridLayout gridLayout2 = new GridLayout();
		 gridLayout2.makeColumnsEqualWidth=true;
		 gridLayout2.numColumns=2;
		 composite2.setLayout(gridLayout2);
		 Label label1 = new Label(composite2, SWT.BOLD);
		 label1.setText("Email :");
		 Text text1 = new Text(composite2,SWT.BORDER);
		 text1.setLayoutData(new GridData(100,SWT.DEFAULT));
		 
		 Label label2 = new Label(composite2, SWT.BOLD);
		 label2.setText("Password :");
		 Text text2 = new Text(composite2,SWT.BORDER|SWT.PASSWORD);
		 text2.setLayoutData(new GridData(100,SWT.DEFAULT));
		
		 Button button1 = new Button(composite2, SWT.BOLD);
		 button1.setText("Login");	
		
		 button1.addListener(SWT.Selection, new Listener() 
		 {
		 	 public void handleEvent(Event event) 
			 {
		 		 m.co.action="login";
				 m.connect();
				 try
				 {
				 System.out.println(":jgh:"+m.ro.user.userEmail);
				 }
				 catch(Exception e)
				 {
					 System.out.println("test3error:: "+e.getMessage());
				 }
/*					if(m.ro.user.loggedin)
				 {
					event.widget.dispose();				 
					 
				 }
*/		      }

		 });

	
	}
	
	
	public static void main(String[] args) 
	{
		 Display display = new Display ();
		 Shell shell = new Shell(display);
		 shell.setText("Dalal Street");
		 RowLayout rowLayout=new RowLayout();
		 rowLayout.type=SWT.VERTICAL;
		 shell.setLayout(rowLayout);
		 test_3 instance = new test_3(shell);
	
		 
		 
		// shell.pack();
		 shell.open ();
		 while (!shell.isDisposed ()) {
		 if (!display.readAndDispatch ()) display.sleep ();
		 }
		 display.dispose ();
	}

	

}
