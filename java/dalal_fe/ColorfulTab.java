

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;


public class ColorfulTab extends CTabFolder{
	private final int WIDTH=40;
	public Composite tabComp;
	public CTabItem tabItem;
	public String title;
	/** 
	 * 	ColorfulTab col = new ColorfulTab(parent,"GRAPH : ");
	 *	Graph graph = new Graph(col.getTab(),SWT.FILL);
	 *
	 *  Graph extends ColorfulTab
	*/
	public ColorfulTab(Composite parent,int style, String tit)
	{
		super(parent,style);
		this.setSelectionBackground(ThemeManager.currentTheme.colorGradient,ThemeManager.currentTheme.colorPercent, true);
		this.setSelectionForeground(ThemeManager.currentTheme.colorTabForeground);
		this.setBackground(ThemeManager.currentTheme.colorTabBackground);
		//this.setBackgroundMode(SWT.INHERIT_FORCE);
		this.setFont(WindowManager.mediumSystemFont);
		this.setSimple(false);
		this.setLayout(new FillLayout());
		title=tit;
		tabItem = new CTabItem(this, SWT.NONE);
		tabItem.setText	(spaceTitle(title));
		tabComp = new Composite(this,SWT.NONE);
		tabComp.setLayout(new FillLayout());
		tabItem.setControl(tabComp);
		this.setSelection(tabItem);
	}
	public void appendTitle(String t)
	{
		tabItem.setText(spaceTitle(title+t));
	}
	String spaceTitle(String title)
	{
		String newTitle=title;
		int k=(WIDTH-title.length())/2;
		for(int i=0;i<k;i++)
		{
			newTitle=" "+newTitle+" ";
		}
		return newTitle;
	}
}
