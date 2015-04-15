/******************************************************************/
////
////	Class Description	:	LoginBox, to connect to the Dalal Street
////							Server, using pragyan username & password
////	Components			:	UserName field, Password field, Login button
////	Created By			:	Abhishek Jain
////	Created on			:	2007-10-06
////	Last Modification 	:
////	Last Modified By 	:
////	Last Modified on	:
////
/******************************************************************/

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class LoginBox  extends Composite
{
//// Declare Login Screen Components
	static Button buttonLogin;
	static Text textLoginPassword;
	static Text textLoginEmail;

	public LoginBox (Composite parent,int style)
	{
		super(parent,style);

		parent.setLayout(new FillLayout());

		this.setLayout(new GridLayout(3,false));

		//// Load Back Ground Image for the Tab.
		//Image m = Application.loadImage("login.jpg");
		int w = Application.display.getClientArea().width-175;
		int h = Application.display.getClientArea().height-160;
		//m = new Image(Application.display , m.getImageData().scaledTo(w,h ));
		//this.setBackgroundImage(m);
		this.setBackgroundMode(SWT.INHERIT_DEFAULT);

		//// Position the Login Components
		Composite innerComposite=new Composite(this,SWT.NONE);
		GridData gd = new GridData(SWT.LEFT,SWT.LEFT,true,true,3,3);
		gd.verticalIndent=(h*12)/36;
		gd.horizontalIndent=(w*17)/36;
		innerComposite.setLayoutData(gd);
		innerComposite.setLayout(new GridLayout(2, true));

		//// Initialize Login Components
		//// Label for Email
			Label label1 = new Label(innerComposite, SWT.BOLD);
			label1.setText("E-mail:");

		//// Textbox for Email
			textLoginEmail = new Text(innerComposite, SWT.BORDER);
			textLoginEmail.setLayoutData(new GridData(100, SWT.DEFAULT));
			textLoginEmail.setFocus();

		//// Label for Password
			Label label2 = new Label(innerComposite, SWT.BOLD);
			label2.setText("Password:");

		//// Textbox for Password
			textLoginPassword  = new Text(innerComposite, SWT.BORDER | SWT.PASSWORD);
			textLoginPassword.setLayoutData(new GridData(100,SWT.DEFAULT));

			/*KeyListener keyLogin = new KeyListener(){
			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				if(e.character==SWT.CR)
					Manager.login();
			}

			};
			textLoginPassword.addKeyListener(keyLogin);
			textLoginEmail.addKeyListener(keyLogin);
			 */

		//// Login Button
			buttonLogin = new Button(innerComposite, SWT.PUSH);
			buttonLogin.setLayoutData(new GridData(SWT.CENTER,SWT.FILL,true,true,2,1));
			buttonLogin.setImage(Application.loadImage("login.png"));
			buttonLogin.setText(" &Login ");
			buttonLogin.addSelectionListener(new SelectionAdapter(){
         	public void widgetSelected(SelectionEvent e)
         	{
				Manager.login();
         	}
			});
	}
}