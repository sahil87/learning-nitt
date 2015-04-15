import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;



public class Mutualfunds extends Composite
{
	public static CoolTable MFlisting;
	public CoolTable newsTable;
	public ColorfulTab investTab;
	public static MutualInvestment mutualinvestment;
	public Mutualfunds(Composite parent,int style)
	{
		super(parent,style);
		parent.setLayout(new FillLayout());
		this.dispose();
		Composite composite=new Composite(parent,SWT.FILL);
		  composite.setLayout(new FillLayout());

		  SashForm vcomp = new SashForm(composite,SWT.VERTICAL);
		  vcomp.setLayout(new FillLayout());

		  SashForm hcomp = new SashForm(vcomp,SWT.HORIZONTAL);
		  hcomp.setLayout(new FillLayout());

		  SashForm hcomp1 = new SashForm(vcomp,SWT.HORIZONTAL);
			hcomp1.setLayout(new FillLayout());


		Composite composite1=new Composite(hcomp,SWT.FILL);
		composite1.setLayout(new FillLayout());
		//composite1.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));
		ColorfulTab listingTab = new ColorfulTab(composite1,SWT.BORDER,"Mutual Funds");
		//htab.setToolTipText("Tells about the holdings of the user ");



		String[] listingTitles = {"Name","Type", "Risk Type","NAV","Entry load","Exit load","Trend","Day High","Day Low"};
		 MFlisting=new CoolTable(listingTab.tabComp,listingTitles,new int[]{90,90,80,80,70,70,50,80,80},SWT.NONE);

		// listingTab.setLayoutData(new GridLayout(1,false));
		 //listingTable.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));



		 //listingTab.setLayout(new FillLayout());



		Composite composite2=new Composite(hcomp,SWT.BORDER);
		composite2.setLayout(new FillLayout());
		ColorfulTab graphTab = new ColorfulTab (composite2, SWT.BORDER," Graph");
		Canvas canvas=new Canvas(graphTab.tabComp,SWT.FILL);
		//composite2.setLayout(new GridLayout());
		//graphTab.setLayout(new FillLayout());

		Composite composite3=new Composite(hcomp1,SWT.BORDER);
		composite3.setLayout(new FillLayout());
		investTab = new ColorfulTab(composite3,SWT.NONE,"Invest");
		Composite investTabComp = new Composite(investTab.tabComp,SWT.NONE);
		mutualinvestment = new MutualInvestment(investTabComp);

		Composite composite4=new Composite(hcomp1,SWT.BORDER);
		composite4.setLayout(new FillLayout());
		ColorfulTab newsTab = new ColorfulTab (composite4, SWT.BORDER," News");
		String[] newstitles = {"Time","News"};
        newsTable=new CoolTable(newsTab.tabComp,newstitles,new int[]{90,400},SWT.NONE);
        vcomp.setWeights(new int[]{60,40});

        //newsTab.setLayout(new FillLayout());

        MFlisting.table.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {

				//For the MF Tab
				TableItem[] items = MFlisting.table.getSelection();
				try
				{
					String comp=items[0].getText(0);
					//System.out.println("Clicked on company: "+items[0].getData());
					{
						Manager.currentTradingMFId = Integer.parseInt(items[0].getData().toString());
						investTab.appendTitle(" : "+comp);
						//FrontendSell.resetSell();
						//FrontendBuy.resetBuy();
						//Top5Buy.updateTopBids();
						//Top5Sell.updateTopBids();
						//TradingBox.graphManager.putNewGraph((new CO_Company((String)Manager.gi.company.get(new Integer(Manager.currentTradingCompId)))).priceMap);
						//if((new CO_Company((String) Manager.gi.company.get(new Integer(Manager.currentTradingCompId)))).priceMapHistoryLimitReached)	TradingBox.graphManager.setPriceMapHistoryLimitReached();
						//TradingBox.graph.putNewGraph(((CO_Company)Manager.gi.company.get(new Integer(Manager.currentTradingCompId))).priceMap);
						//if(((CO_Company) Manager.gi.company.get(new Integer(Manager.currentTradingCompId))).priceMapHistoryLimitReached)	TradingBox.graph.setPriceMapHistoryLimitReached();
						//TradingBox.graphManager.replaceGraph();
					}
				}
				catch(Exception excep)
				{
				}
			}
		});

		}

	public static void updateMFTable()
	{
		MFlisting.table.removeAll();
		Set set = Manager.gi.MF.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext())
		{
			Map.Entry me = (Map.Entry)i.next();
			CO_MF a=new CO_MF((String) me.getValue());
//				CO_Company a=(CO_Company) me.getValue();
			String str=new String("Down");
			if(a.change==true)
				str = "Up";

			String[] row={a.MFName, a.MFType, a.MFRiskType+"",CO_CO.formatMoney(a.NAV)+"",CO_CO.formatMoney(a.EntryLoad)+"",CO_CO.formatMoney(a.ExitLoad)+"",str ,CO_CO.formatMoney(a.MFDayHigh)+"",CO_CO.formatMoney(a.MFDayLow)+"" };
			MFlisting.newEntry(row, a.MFId);
		}
	}

	}


