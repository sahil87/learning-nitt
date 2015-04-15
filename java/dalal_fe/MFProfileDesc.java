import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class MFProfileDesc extends Composite{
	//public static Label compName;
	public static StyledText MFName,NAV,type,riskType,MFInfo,entryLoad,exitLoad;
//	public static Canvas compIcon;
	public static Composite numberComposite;
	public static StyleRange style1,style2,style3,style5,style6,style7;
	Composite comp;

public MFProfileDesc(Composite parent,int style)
	{
		super(parent,style);
		this.setLayout(new FillLayout());

		final ScrolledComposite scrollComposite = new ScrolledComposite(this, SWT.V_SCROLL );
		//scrollComposite.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, true, 1, 1));
		scrollComposite.setLayout(new FillLayout());
		final Composite dummyComposite = new Composite(scrollComposite,SWT.NONE);
		//dummyComposite.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, true, 1, 1));
		dummyComposite.setLayout(new FillLayout());

		scrollComposite.setContent(dummyComposite);
		scrollComposite.setExpandVertical(true);
		scrollComposite.setExpandHorizontal(true);
		scrollComposite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				org.eclipse.swt.graphics.Rectangle r = scrollComposite.getClientArea();
				scrollComposite.setMinSize(dummyComposite.computeSize(r.width, SWT.DEFAULT));
			}
		});


		comp = new Composite(dummyComposite, SWT.FILL);
		GridLayout gd = new GridLayout(2,false);
		gd.horizontalSpacing=0;
		gd.verticalSpacing=0;
		comp.setLayout(gd);

		MFName = new StyledText(comp,SWT.BOLD|SWT.WRAP);
		MFName.setText("Mutual Fund Descriptions");
		MFName.setFont(WindowManager.bigSystemFont);
		MFName.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,2,1));
		MFName.setEditable(false);
		MFName.setEnabled(false);

		MFInfo = new StyledText(comp, SWT.WRAP);
		MFInfo.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true,true, 2, 1));
		MFInfo.setText("Mutual Fund Info");
		MFInfo.setEditable(false);
		MFInfo.setEnabled(false);

		numberComposite = new Composite(comp,SWT.FILL);
		numberComposite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,2,1));
		GridLayout gd2 = new GridLayout(2,false);
		gd2.horizontalSpacing=0;
		gd2.verticalSpacing=0;
		gd2.marginHeight=0;
		gd2.marginWidth=0;
		numberComposite.setLayout(gd2);

		type = new StyledText(numberComposite,SWT.WRAP);
		type.setText("Type : ");
		type.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		type.setEditable(false);
		type.setEnabled(false);
		style3 = new StyleRange();
		style3.start = 0;
		style3.length = type.getText().length();
		style3.fontStyle = SWT.BOLD | SWT.ITALIC ;

		riskType= new StyledText(numberComposite,SWT.WRAP);
		riskType.setText("Risk Type : ");
		riskType.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		riskType.setEditable(false);
		riskType.setEnabled(false);
		style5 = new StyleRange();
		style5.start = 0;
		style5.length = riskType.getText().length();
		style5.fontStyle = SWT.BOLD | SWT.ITALIC ;

		entryLoad = new StyledText(numberComposite,SWT.WRAP);
		entryLoad.setText("Entry Load : ");
		entryLoad.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		entryLoad.setEditable(false);
		entryLoad.setEnabled(false);
		style6 = new StyleRange();
		style6.start = 0;
		style6.length = entryLoad.getText().length();
		style6.fontStyle = SWT.BOLD | SWT.ITALIC ;

		exitLoad = new StyledText(numberComposite,SWT.WRAP);
		exitLoad.setText("Exit Load : ");
		exitLoad.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		exitLoad.setEditable(false);
		exitLoad.setEnabled(false);
		style7 = new StyleRange();
		style7.start = 0;
		style7.length = exitLoad.getText().length();
		style7.fontStyle = SWT.BOLD | SWT.ITALIC ;

		NAV = new StyledText(numberComposite,SWT.WRAP);
		NAV.setText("NAV : ");
		NAV.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		NAV.setEditable(false);
		NAV.setEnabled(false);
		style1 = new StyleRange();
		style1.start = 0;
		style1.length = NAV.getText().length();
		style1.fontStyle = SWT.BOLD | SWT.ITALIC ;
		style2 = new StyleRange();
		style2.start = 15;
		style2.length = 3;
		style2.fontStyle = SWT.BOLD;

		NAV.setStyleRange(style1);
		type.setStyleRange(style3);
		riskType.setStyleRange(style5);
		entryLoad.setStyleRange(style6);
		exitLoad.setStyleRange(style7);

		//	mp.setStyleRange(style1);
	//	mp.setStyleRange(style2);rating.setStyleRange(style3);
	//	scrip.setStyleRange(style6);
	//	percentagechange.setStyleRange(style5);
	}

	public void update(CO_MF b)
	{
		try
		{
			MFName.setText(b.MFName);
			MFInfo.setText(b.MFInfo);
			type.setText("Type : "+b.MFType);
			riskType.setText("Risk Type : "+b.MFRiskType);
			entryLoad.setText("Entry Load : "+b.EntryLoad);
			exitLoad.setText("Exit Load : "+b.ExitLoad);
			NAV.setText("NAV : "+b.NAV);



	}catch ( Exception e) {
			System.out.print("updating company profile error");
		}
	}
}