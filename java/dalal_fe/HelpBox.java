/******************************************************************/
////
////	Class Description	:	HelpBox, Help & Feedback Tab of the
////							Application, Basically a Guest Book
////	Components			:	Display area for Q&A, Query submission
////	Created By			:	Abhishek Jain
////	Created on			:	2007-10-06
////	Last Modification 	:
////	Last Modified By 	:
////	Last Modified on	:
////
/******************************************************************/

	import java.util.Iterator;

	import org.eclipse.swt.SWT;
	import org.eclipse.swt.custom.ScrolledComposite;
	import org.eclipse.swt.custom.StyleRange;
	import org.eclipse.swt.custom.StyledText;
	import org.eclipse.swt.events.ControlAdapter;
	import org.eclipse.swt.events.ControlEvent;
	import org.eclipse.swt.events.SelectionAdapter;
	import org.eclipse.swt.events.SelectionEvent;
	import org.eclipse.swt.layout.FillLayout;
	import org.eclipse.swt.layout.GridData;
	import org.eclipse.swt.layout.GridLayout;
	import org.eclipse.swt.widgets.Button;
	import org.eclipse.swt.widgets.Composite;
	import org.eclipse.swt.widgets.Group;
	import org.eclipse.swt.widgets.Label;
	import org.eclipse.swt.widgets.Text;

	public class HelpBox {
		public static Text myFaq;
		static Composite dummyComposite;
		public HelpBox(final Composite comp)
		{
			GridLayout gridLayout = new GridLayout(1,false);
			comp.setLayout(gridLayout);

			Label label = new Label(comp, SWT.BOLD);
			label.setLayoutData(new GridData(SWT.CENTER,SWT.FILL, true, false, 1, 1));
			label.setText("Frequently Asked Questions:");
			label.setFont(WindowManager.bigSystemFont);

			final ScrolledComposite scrollComposite = new ScrolledComposite(comp, SWT.V_SCROLL | SWT.BORDER);
			scrollComposite.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, true, 1, 1));
			scrollComposite.setLayout(new GridLayout(1,false));
			dummyComposite = new Composite(scrollComposite,SWT.NONE);
			dummyComposite.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, true, 1, 1));
			dummyComposite.setLayout(new GridLayout(1,false));

			scrollComposite.setContent(dummyComposite);
			scrollComposite.setExpandVertical(true);
			scrollComposite.setExpandHorizontal(true);
			scrollComposite.addControlListener(new ControlAdapter() {
				public void controlResized(ControlEvent e) {
					org.eclipse.swt.graphics.Rectangle r = scrollComposite.getClientArea();
					scrollComposite.setMinSize(dummyComposite.computeSize(r.width, SWT.DEFAULT));
				}
			});
			if(Manager.gi!=null && Manager.gi.user!=null && Manager.gi.user.loggedin)
			{
				GridData g2 = new GridData(SWT.FILL,SWT.END, true, false, 1, 1);
				Group groupHelp = new Group (comp, SWT.NONE);
				groupHelp.setLayoutData(g2);
				groupHelp.setLayout (new FillLayout ());
				groupHelp.setText ("If you have a question that isn't listed above, you can submit it here for review :");

				Composite helpSubmit = new Composite(groupHelp,SWT.FILL);
				helpSubmit.setLayout(new GridLayout(2,false));

				myFaq = new Text(helpSubmit,SWT.BORDER|SWT.MULTI|SWT.FILL);
				GridData g3 = new GridData(SWT.FILL,SWT.END, true, false, 1, 1);
				myFaq.setVisible(true);
				myFaq.setLayoutData(g3);

				Button b = new Button( helpSubmit,SWT.PUSH|SWT.FILL);
				GridData g4 = new GridData( SWT.END,SWT.END, false, false, 1, 1);
				b.setText("  Submit Question  ");
				b.setLayoutData(g4);

				b.addSelectionListener(new SelectionAdapter(){
		         	public void widgetSelected(SelectionEvent e)
		         	{
		         		Manager.submitFAQ();
		         		myFaq.setText("");
		         	}});
			}

			String q[]=new String[6];
			String a[]=new String[6];
			q[0]="How do I register myself for Dalal Street '07 ?";
			a[0]="Go to http://www.pragyan.org/dalal and follow the instructions";
			HelpBox.add(q[0], a[0]);
			q[1]="How do I log in ?";
			a[1]="Use the email id and password you had provided during registration.";
			HelpBox.add(q[1], a[1]);
			q[2]="How do I configure my proxy settings ?";
			a[2]="You need to configure your proxy settings manually (if applicable)" +
					"\n\t\tIf you are in NITT Internet lab : " +
					"\n\t\t\t\tServer Address : pragyan.org" +
					"\n\t\t\t\tHTTP Proxy : 10.0.2.1" +
					"\n\t\t\t\tPort : 3128"+
	                "\n\t\tIf you are on NITT intranet\t : Server Address : pragyan, No proxy required" +
	                "\n\t\tIf you're outside NITT\t\t\t : Contact your network admin for proxy settings/details.";
			HelpBox.add(q[2], a[2]);

			q[3]="Why do I get a message 'You are banned'?";
			a[3]="This is done if you are caught cheating. You cannot login again with this account.";
			HelpBox.add(q[3], a[3]);
			q[4]="Why do I get a message 'You are already logged in'?";
			a[4]="This happens when you don't logout properly. You will automatically be logged off in 5 " +
					"minutes, or you can use the logoff page provided in the dalal website to log yourself off.";
			HelpBox.add(q[4], a[4]);

			q[5]="My Proxy server requires authentication. What do I do?";
			a[5]="Try HTTP Tunneling.";
			HelpBox.add(q[5], a[5]);


		}
		public static void updateHelp()
		{
			//System.out.println("HelpBox.java : "+Manager.gi.faqs.size()+" "+Manager.gi.ans.size());
			Iterator iterQ = Manager.gi.faqs.iterator();
			Iterator iterA = Manager.gi.ans.iterator();
			while(iterQ.hasNext() || iterA.hasNext())
			{
				String ques =""+iterQ.next();
				String ans = ""+iterA.next();
				add(ques,ans);
			}
		}

		public static void add(String ques,String ans)
		{
			String text1 = "Ques: "+ques+"\n"+"Ans: "+ans;
			StyledText styledText = new StyledText(dummyComposite, SWT.WRAP | SWT.BORDER);
			styledText.setLayoutData(new GridData(SWT.FILL,SWT.END, true,false, 1, 1));
			styledText.setText(text1);
			styledText.setEditable(false);
			styledText.setLineIndent(0, 2, 20);
			styledText.setLineAlignment(0, 2, SWT.LEFT);
			styledText.setLineJustify(0, 2, true);
			StyleRange style1 = new StyleRange();
			style1.start = 0;
			style1.length = 5;
			style1.fontStyle = SWT.BOLD | SWT.ITALIC ;
			styledText.setStyleRange(style1);
			StyleRange style2 = new StyleRange();
			style2.start = 6;
			style2.length = ques.length();;
			style2.fontStyle = SWT.BOLD;
			styledText.setStyleRange(style2);
			StyleRange style3 = new StyleRange();
			style3.start = ques.length()+7;
			style3.length = 5;
			style3.fontStyle = SWT.BOLD | SWT.ITALIC;
			styledText.setStyleRange(style3);
			styledText.setEnabled(false);
		}
	}

