import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

/******************************************************************/
////
////	Class Description	:	Home Box,
////
////	Components			:	Top 10 Rankers Table, Sensex Graph,
////	Created By			:	Abhishek Jain
////	Created on			:	2007-10-06
////	Last Modification 	:
////	Last Modified By 	:
////	Last Modified on	:
////
/******************************************************************/

public class HomeBox extends Composite{
	public static CoolTable RankTable;
	public static ColorfulTab RankingsTab;
	public static GraphManager graphManager;
	public static MoneyManager moneymanage;
	//public static accSummary accSum;  to be written...
	public HomeBox(Composite parent,int style){
		super(parent,style);
		parent.setLayout(new FillLayout());
		this.dispose();

//		Main SashForm for left/right parts
		SashForm sform = new SashForm(parent,SWT.VERTICAL);
		sform.setLayout(new FillLayout());

//		SashForm for the top part for user listing and graph
		SashForm top = new SashForm(sform,SWT.HORIZONTAL);
		top.setLayout(new FillLayout());

		Composite c1=new Composite(top,SWT.FILL);
		c1.setLayout(new FillLayout());

		RankingsTab=new ColorfulTab(c1,SWT.NONE,"Top Rankings");
		Composite rankComp = new Composite(RankingsTab.tabComp,SWT.NONE);
		rankComp.setLayout(new FillLayout());
		String[] rankTitles = {"Rank","User Name","Net Worth(Rs.)"};
		RankTable = new CoolTable(rankComp,rankTitles,new int[]{100,200,150},SWT.NONE);

		Composite c2=new Composite(top,SWT.FILL);
		c2.setLayout(new FillLayout());

		graphManager = new GraphManager(c2,SWT.BORDER,"Graph (Sensex)");
		//graphManager.setLayoutData(new GridData(SWT.FILL,SWT.BEGINNING,true,true,1,1));
		top.setWeights(new int[]{60,40});

//		SashForm for the right side for graph and news table
		SashForm bottom = new SashForm(sform,SWT.HORIZONTAL);
		bottom.setLayout(new FillLayout());

		Composite c3=new Composite(bottom,SWT.FILL);
		c3.setLayout(new FillLayout());
		CTabFolder capitalTab;
		capitalTab = new CTabFolder(c3, SWT.BORDER);
		capitalTab.setSelectionBackground(ThemeManager.currentTheme.colorGradient,ThemeManager.currentTheme.colorPercent, true);
		capitalTab.setSelectionForeground(ThemeManager.currentTheme.colorTabForeground);
		capitalTab.setSimple(false); //For curved tabs
		capitalTab.setBackground(ThemeManager.currentTheme.colorTabBackground);

		CTabItem invitem;
		invitem = new CTabItem(capitalTab,SWT.NONE);
		invitem.setText("Capital Management");
		Composite invcomp=new Composite(capitalTab,SWT.NONE);
		moneymanage=new MoneyManager(invcomp);
	    invitem.setControl(invcomp);

	    capitalTab.setSelection(invitem);

		//Composite accComp = new Composite(c4,SWT.NONE);
		//accComp.setLayout(new FillLayout());

	    Composite c4=new Composite(bottom,SWT.FILL);
		c4.setLayout(new FillLayout());

		Composite emptyComp = new Composite(c4,SWT.NONE);
		emptyComp.setLayout(new FillLayout());

		bottom.setWeights(new int[]{60,40});
	    sform.setWeights(new int[]{60,40});

	}

	 public static void UpdateRanksTable()
	 {
		 	RankTable.table.removeAll();
			int x=1;
			for(Iterator i=Manager.gi.rankings.iterator();i.hasNext();)
			{
				CO_Ranking a=new CO_Ranking((String)i.next());

				String[] row={""+x, a.UserName,CO_CO.formatMoney(a.netWorth)};
				RankTable.newEntry(row, x++);
			}
	 }

}
