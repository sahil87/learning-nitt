import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/******************************************************************/
////
////	Class Description	:	Information Box, General Details &
////							Background of all Companies & Mutual Funds
////	Components			:	CompanyList Table, MutualFundList Table,
////							Company Detail Box, MutualFund Detail Box
////	Created By			:	Abhishek Jain
////	Created on			:	2007-10-06
////	Last Modification 	:
////	Last Modified By 	:
////	Last Modified on	:
////
/******************************************************************/

public class InformationBox extends Composite{
	public static Display disp;
	public static CoolTable companyListTable;
	public static CoolTable MFListTable;
	public static CompanyProfileDesc companyProfile;
	public static MFProfileDesc MFProfile;
	/**
	 * @param args
	 */
	public InformationBox(Composite parent,int style)
	{
		super(parent,style);
		parent.setLayout(new FillLayout());
		this.dispose();

		Composite cd = new Composite(parent,SWT.HORIZONTAL);

		cd.setLayout(new FillLayout());

		SashForm vform = new SashForm(cd,SWT.VERTICAL);
		vform.setLayout(new FillLayout());

		SashForm hform = new SashForm(vform,SWT.HORIZONTAL);
		hform.setLayout(new FillLayout());

		SashForm hform1 = new SashForm(vform,SWT.HORIZONTAL);
		hform1.setLayout(new FillLayout());

		Composite c1 = new Composite(hform,SWT.BORDER);
		c1.setLayout(new FillLayout());
		ColorfulTab companyListTab = new ColorfulTab(c1,SWT.BORDER,"Companies");
		String[] compTitles = {"Company"};

		companyListTable=new CoolTable(companyListTab.tabComp,compTitles,new int[]{120},SWT.NONE);

		Composite c2 = new Composite(hform,SWT.BORDER);
		c2.setLayout(new FillLayout());
		companyProfile = new CompanyProfileDesc(c2,SWT.BORDER);

		hform.setWeights(new int[]{25,75});

		companyListTable.table.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try{
					Manager.currentPortfolioCompId=Integer.parseInt(companyListTable.table.getSelection()[0].getData().toString());
					CO_Company a = new CO_Company();
					//CO_Company a = new CO_Company((String) Manager.gi.company.get(new Integer(Manager.currentPortfolioCompId)));

					Set set1  = Manager.gi.company.entrySet();
					Iterator it = set1.iterator();
					String Compname = "";
					int marketprice = 0;
					while(it.hasNext())
					{
						Map.Entry me1 = (Map.Entry)(it.next());
						CO_Company c = new CO_Company(""+me1.getValue());
						if(c.compId == Manager.currentPortfolioCompId)
						{
							a = c;
						}
					}
					companyProfile.update(a);

			}
				catch(Exception excep){
				}
			}
		});

		Composite c3 = new Composite(hform1,SWT.BORDER);
		c3.setLayout(new FillLayout());
		ColorfulTab MFListTab = new ColorfulTab(c3,SWT.BORDER,"Mutual Funds");
		String[] MFTitles = {"Mutual Funds"};

		MFListTable=new CoolTable(MFListTab.tabComp,MFTitles,new int[]{120},SWT.NONE);

		Composite c4 = new Composite(hform1,SWT.BORDER);
		c4.setLayout(new FillLayout());
		MFProfile = new MFProfileDesc(c4,SWT.BORDER);
		hform1.setWeights(new int[]{25,75});

		MFListTable.table.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try{
					Manager.currentPortfolioMFId=Integer.parseInt(MFListTable.table.getSelection()[0].getData().toString());
					CO_MF b = new CO_MF();
					//CO_MF b = new CO_MF((String) Manager.gi.MF.get(new Integer(Manager.currentPortfolioMFId)));
					Set set1  = Manager.gi.MF.entrySet();
					Iterator it = set1.iterator();
					String Compname = "";
					int marketprice = 0;
					while(it.hasNext())
					{
						Map.Entry me1 = (Map.Entry)(it.next());
						CO_MF c = new CO_MF(""+me1.getValue());
						if(Manager.currentPortfolioMFId == c.MFId)
						{
							b = c;
						}
					}
					//System.out.print("desc update called for company " + a.compName);
					MFProfile.update(b);
				}
				catch(Exception excep){
				}
			}
		});

	}
	public static void updateCompanyListTable()
	{
		companyListTable.table.removeAll();
		Set set = Manager.gi.company.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext())
		{
			Map.Entry me = (Map.Entry)i.next();
			CO_Company a=new CO_Company((String) me.getValue());
			String[] row={a.compName};
			companyListTable.newEntry(row, a.compId);
		}
	}
	public static void updateMFListTable()
	{
		MFListTable.table.removeAll();
		Set set = Manager.gi.MF.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext())
		{
			Map.Entry me = (Map.Entry)i.next();
			CO_MF a=new CO_MF((String) me.getValue());
			String[] row={a.MFName };
			MFListTable.newEntry(row, a.MFId);
		}
	}
}
