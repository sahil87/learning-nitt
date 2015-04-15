/******************************************************************/
////
////	Class Description	:	Trading Box, Buy/Sell Interface for
////							Share Trading
////	Components			:	Company List Table, Graph, News,
////							Buy/Sell Box.
////	Created By			:	Abhishek Jain
////	Created on			:	2007-10-06
////	Last Modification 	:
////	Last Modified By 	:
////	Last Modified on	:
////
/******************************************************************/


import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;


public class TradingBox extends Composite
{
	public static CoolTable news,companyListing;
	public static ColorfulTab companyListingTab,newsTab,graphTab;
	public static ColorfulTab buyTab,sellTab;
	public static FrontendBuy frontendbuy;
	public static FrontendSell frontendsell;
	public static GraphManager graphManager;

	public TradingBox(Composite parent,int style)
	{
		super(parent,style);
		parent.setLayout(new FillLayout());
		this.dispose();

		//Main SashForm for left/right parts
		SashForm sform = new SashForm(parent,SWT.VERTICAL);
		sform.setLayout(new FillLayout());

		////////////////////////////////top starts here/////////////////////
		//SashForm for the top part for company listing and graph
		SashForm top = new SashForm(sform,SWT.HORIZONTAL);
		top.setLayout(new FillLayout());

		//Composite housing the company listing
		companyListingTab = new ColorfulTab(top,SWT.NONE,"Company Listing");
		Composite listingComp = new Composite(companyListingTab.tabComp,SWT.NONE);
		listingComp.setLayout(new FillLayout());
		String[] listingTitles = {"Companies","Scrip","No. Of Shares","Market Price (Rs.)","Trend","DayHigh","DayLow"};
		companyListing = new CoolTable(listingComp,listingTitles,new int[]{130,70,25,100,50,80,80},SWT.NONE);
		companyListing.table.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {

				//For the Companies Tab
				TableItem[] items = companyListing.table.getSelection();
				try
				{
					String comp=items[0].getText(0);
					//System.out.println("Clicked on company: "+items[0].getData());
					{
						Manager.currentTradingCompId = Integer.parseInt(items[0].getData().toString());
						TradingBox.buyTab.appendTitle(" : "+comp);
						TradingBox.sellTab.appendTitle(" : "+comp);
						TradingBox.graphManager.appendTitle(" : "+comp);
						FrontendSell.resetSell();
						FrontendBuy.resetBuy();
						FrontendSell.updateTopBids();
						FrontendBuy.updateTopBids();
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

		//Composite housing the Company graph
//		graphTab = new ColorfulTab(top,SWT.NONE,"Company Graph");
//		Composite graphComp = new Composite(graphTab.tabComp,SWT.NONE);
//		graphComp.setLayout(new FillLayout());
		//Create graph instance here...
		graphManager = new GraphManager(top,SWT.BORDER,"Graph (price Vs. time)");
		graphManager.setLayoutData(new GridData(SWT.FILL,SWT.BEGINNING,false,false,1,1));
		top.setWeights(new int[]{60,40});
		////////////////////////////////top ends here/////////////////////



		////////////////////////////////bottom starts here/////////////////////
		//SashForm for the right side for graph and news table
		SashForm bottom = new SashForm(sform,SWT.HORIZONTAL);
		bottom.setLayout(new FillLayout());

		//Composite housing buy/sell tabs
/*		Composite bComp = new Composite(bottom,SWT.NONE);
		bComp.setLayout(new FillLayout());

		bTab = new CTabFolder(bComp, SWT.BORDER);
		bTab.setSelectionBackground(ThemeManager.currentTheme.colorGradient,ThemeManager.currentTheme.colorPercent, true);
		bTab.setSelectionForeground(ThemeManager.currentTheme.colorTabForeground);
		bTab.setSimple(false); //For curved tabs
		bTab.setBackground(ThemeManager.currentTheme.colorTabBackground);
*/
		buyTab = new ColorfulTab(bottom,SWT.NONE,"Buy");
		Composite buyTabComp = new Composite(buyTab.tabComp,SWT.NONE);
		frontendbuy = new FrontendBuy(buyTabComp);

		//Composite housing the news table

		sellTab = new ColorfulTab(bottom,SWT.NONE,"Sell");
		Composite sellTabComp = new Composite(sellTab.tabComp,SWT.NONE);
		frontendsell = new FrontendSell(sellTabComp);
/*
		Composite sComp = new Composite(bottom,SWT.NONE);
		sComp.setLayout(new FillLayout());

		sTab = new CTabFolder(sComp, SWT.BORDER);
		sTab.setSelectionBackground(ThemeManager.currentTheme.colorGradient,ThemeManager.currentTheme.colorPercent, true);
		sTab.setSelectionForeground(ThemeManager.currentTheme.colorTabForeground);
		sTab.setSimple(false); //For curved tabs
		sTab.setBackground(ThemeManager.currentTheme.colorTabBackground);

		sellTab = new CTabItem(sTab,SWT.NONE);
		sellTab.setText("  Sell ");
		Composite sellTabComp = new Composite(sTab,SWT.NONE);
		frontendsell = new FrontendSell(sellTabComp);
		sellTab.setControl(sellTabComp);

		sTab.setSelection(sellTab);
*/
/*		newsTab = new ColorfulTab(bottom,SWT.NONE,"News");
		Composite newsComp = new Composite(newsTab.tabComp,SWT.NONE);
		newsComp.setLayout(new FillLayout());
		//News table
		String[] newsTitles = {"Date Time", "News"};
		news = new CoolTable(newsComp,newsTitles,new int[]{150,350},SWT.NONE);
*/
		bottom.setWeights(new int[]{50,50});
		////////////////////////////////bottom ends here/////////////////////

		sform.setWeights(new int[]{60,40});
	}

	public static void updateCompanyTable()
	{
		companyListing.table.removeAll();
		Set set = Manager.gi.company.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext())
		{
			Map.Entry me = (Map.Entry)i.next();
			CO_Company a=new CO_Company((String) me.getValue());
//				CO_Company a=(CO_Company) me.getValue();
			String str=new String("Down");
			if(a.change==true)
				str = "Up";

			String[] row={a.compName, a.sector, a.noOfShares+"",CO_CO.formatMoney(a.marketPrice)+"",str ,CO_CO.formatMoney(a.dayHigh)+"",CO_CO.formatMoney(a.dayLow)+"" };
			companyListing.newEntry(row, a.compId);
		}
	}
}