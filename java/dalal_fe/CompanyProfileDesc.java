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

public class CompanyProfileDesc extends Composite{
	//public static Label compName;
	public static StyledText compName,mp,sector,compInfo;
//	public static Canvas compIcon;
	public static Composite numberComposite;
	public static StyleRange style1,style2,style3,style5,style6;
	Composite comp;

public CompanyProfileDesc(Composite parent,int style)
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

		compName = new StyledText(comp,SWT.BOLD|SWT.WRAP);
		compName.setText("Company Descriptions");
		compName.setFont(WindowManager.bigSystemFont);
		compName.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,2,1));
		compName.setEditable(false);
		compName.setEnabled(true);

		compInfo = new StyledText(comp, SWT.WRAP);
		compInfo.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true,true, 2, 1));
		compInfo.setText("CompanyInfo");
		compInfo.setEditable(false);
		compInfo.setEnabled(false);

		numberComposite = new Composite(comp,SWT.FILL);
		numberComposite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,2,1));
		GridLayout gd2 = new GridLayout(2,false);
		gd2.horizontalSpacing=0;
		gd2.verticalSpacing=0;
		gd2.marginHeight=0;
		gd2.marginWidth=0;
		numberComposite.setLayout(gd2);

		mp = new StyledText(numberComposite,SWT.WRAP);
		mp.setText("Market Price : Rs ");
		mp.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		mp.setEditable(false);
		mp.setEnabled(false);
		style1 = new StyleRange();
		style1.start = 0;
		style1.length = 15;
		style1.fontStyle = SWT.BOLD | SWT.ITALIC ;
		style2 = new StyleRange();
		style2.start = 15;
		style2.length = 3;
		style2.fontStyle = SWT.BOLD;

		sector = new StyledText(numberComposite,SWT.WRAP);
		sector.setText("Sector : ");
		sector.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,1,1));
		sector.setEditable(false);
		sector.setEnabled(false);
		style6 = new StyleRange();
		style6.start = 0;
		style6.length = sector.getText().length();
		style6.fontStyle = SWT.BOLD | SWT.ITALIC ;

		mp.setStyleRange(style1);
		mp.setStyleRange(style2);
		sector.setStyleRange(style6);
	}

	public void update(CO_Company a)
	{
		try
		{
			compName.setText(a.compName);

			compInfo.setText("\n"+a.compInfo);
			compInfo.setLineIndent(1, 1, 20);
			//compInfo.setLineAlignment(0, 1, SWT.);
			compInfo.setLineJustify(1, 1, true);

			mp.setText("Market Price : Rs "+ CO_CO.formatMoney(a.marketPrice));
			mp.setStyleRange(style1);
			mp.setStyleRange(style2);

			sector.setText("Sector : "+ a.sector);
			sector.setStyleRange(style6);

	}catch ( Exception e) {
			System.out.print("updating company profile error");
		}
	}
}