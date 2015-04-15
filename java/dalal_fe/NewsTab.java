import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;


public class NewsTab extends Composite{

	public static CoolTable newsTable;

	public NewsTab(Composite parent,int style)
	{
		super(parent,style);
		parent.setLayout(new FillLayout());
		this.dispose();

		Composite comp = new Composite(parent,SWT.BORDER);
		comp.setLayout(new FillLayout());
		ColorfulTab newsTab = new ColorfulTab(comp,SWT.BORDER,"Companies");
		String[] newsTitles = {"Time","Company","Sector","News"};

		newsTable = new CoolTable(newsTab.tabComp,newsTitles,new int[]{120,120,120,360},SWT.NONE);
	}

}
