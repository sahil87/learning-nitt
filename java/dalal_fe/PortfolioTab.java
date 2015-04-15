import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;




public class PortfolioTab  extends Composite{
	public static Display disp;
	public static CoolTable holdingsTable;
	public static CoolTable bidsTable;
	public static CoolTable investmentsTable;
	public static CoolTable summaryTable;
	/**
	 * @param args
	 */
	public PortfolioTab(Composite parent,int style)
	{
		super(parent,style);
		parent.setLayout(new FillLayout());
		this.dispose();

		Composite cd = new Composite(parent,SWT.NONE);

		cd.setLayout(new FillLayout());
		SashForm vform = new SashForm(cd,SWT.VERTICAL);

		vform.setLayout(new FillLayout());

		SashForm hform = new SashForm(vform,SWT.HORIZONTAL);
		SashForm hform1 = new SashForm(vform,SWT.HORIZONTAL);
		hform.setLayout(new FillLayout());

		Composite c1 = new Composite(hform,SWT.BORDER);
		c1.setLayout(new FillLayout());
		ColorfulTab holdTab = new ColorfulTab(c1,SWT.BORDER,"My Holdings");

		//TabFolder holdTab = new TabFolder(c1,SWT.NONE);
		//TabItem holdItem = new TabItem(holdTab,SWT.NONE);
		//holdItem.setText("My Holdings");
		//holdItem.setControl(holdingsTable);

		String[] holdingsTitles = {"Company","No. of Shares", "Buying Price (Rs.)","Market Price (Rs.)","% gain "};

		holdingsTable=new CoolTable(holdTab.tabComp,holdingsTitles,new int[]{120,90,90,90,90},SWT.NONE);
//		holdTab.setLayout(new FillLayout());

		Composite c2 = new Composite(hform,SWT.BORDER);
		c2.setLayout(new FillLayout());
		ColorfulTab bidsTab = new ColorfulTab(c2,SWT.BORDER,"My Bids");
		//TabFolder bidsTab = new TabFolder(c2,SWT.NONE);
		// TabItem bidItem = new TabItem(bidsTab,SWT.NONE);
		//bidItem.setText("My Bids");
		//bidItem.setControl(bidsTable);


		String[] bidTitles = {" ","Company","Buy/Sell Bid","No. of Shares", "BP/SP (Rs.)","Market Price (Rs.)"};
		bidsTable=new CoolTable(bidsTab.tabComp,bidTitles,new int[]{20,90,90,90,90,120},SWT.CHECK);
//		String[] row = {" ","google","12000","100","100","0","100"};
//		bidsTable.newEntry(row,1);
		bidsTab.tabComp.setLayout(new GridLayout(1,false));
		bidsTable.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));

		Button deleteButton=new Button(bidsTab.tabComp,SWT.PUSH);
		deleteButton.setLayoutData(new GridData(0,0,false,false,1,1));
		deleteButton.setLayoutData(new GridData(0,0,false,false,1,1));
		deleteButton.setSize(20, 10);
		deleteButton.setText("Delete Selected Bids");

		deleteButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e)
			{
				MessageBox m=new MessageBox(Application.rootShell,SWT.ICON_WARNING|SWT.OK|SWT.CANCEL);
				m.setText("Delete Bids");
				m.setMessage("Are you sure you want to delete the selected bids?");
				if (m.open()==SWT.OK)
				{
					TableItem[] sel=bidsTable.table.getItems();
					Manager.co = new CO_CO();
					System.out.println("length = " + sel.length);
					for(int i=0;i<sel.length;i++)
					{
						if(sel[i].getChecked())
						{
							//System.out.println("bid: "+sel[i].getData());;
							Manager.co.deleteBidId.add(sel[i].getData()+"");
							//System.out.println("got it");
						}
					}
					//Manager.query("deletebids");
					Manager.deleteBid();
				}

			}
		});

		Composite c3 = new Composite(hform1,SWT.BORDER);

		c3.setLayout(new FillLayout());


		String[] invTitles = {" ","Fund","Units","NAV","Change"};


		//System.out.println(c3.computeSize(250,250));
		//TabFolder invTab = new TabFolder(c3,SWT.NONE);
		ColorfulTab invTab = new ColorfulTab(c3,SWT.BORDER,"My Mutual Funds");

	     //TabItem item1 = new TabItem(invTab,SWT.NONE);
		//item1.setText("My Mutual Funds");
		//item1.setControl(investmentsTable);
		investmentsTable=new CoolTable(invTab.tabComp,invTitles,new int[]{20,80,50,100,50},SWT.CHECK);
//		String[] row1 = {" ","1000","1000","1988-08-30","today"};
//		investmentsTable.newEntry(row1,1);
//		investmentsTable.newEntry(row1, 2);
		invTab.tabComp.setLayout(new GridLayout(1,false));
		investmentsTable.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,1,1));

		Button Deinvestbutton;
		Deinvestbutton =new Button(invTab.tabComp,SWT.PUSH);
		Deinvestbutton.setLayoutData(new GridData(0,0,false,false,1,1));
		Deinvestbutton.setLayoutData(new GridData(0,0,false,false,1,1));
		Deinvestbutton.setSize(20, 10);
		Deinvestbutton.setText("Delete Selected Bids");

		//invTab.setLayout(new FillLayout());

		Deinvestbutton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e)
			{
				MessageBox m=new MessageBox(Application.rootShell,SWT.ICON_WARNING|SWT.OK|SWT.CANCEL);
				m.setText("Delete Bids");
				m.setMessage("Are you sure you want to delete the selected bids?");
				if (m.open()==SWT.OK)
				{
					TableItem[] sel=investmentsTable.table.getItems();
					Manager.co = new CO_CO();
					System.out.println("length = " + sel.length);
					for(int i=0;i<sel.length;i++)
					{
						if(sel[i].getChecked())
						{
							//System.out.println("bid: "+sel[i].getData());;
							Manager.co.deleteBidId.add(sel[i].getData()+"");
						//	System.out.println("got it");
						}
					}
					Manager.MFDeInvest();
				}

			}
		});

		Composite c4=new Composite(hform1,SWT.BORDER);
		c4.setLayout(new FillLayout());
		ColorfulTab sumTab = new ColorfulTab(c4,SWT.BORDER,"My Recent Transactions");
		//TabFolder sumTab = new TabFolder(c4,SWT.NONE);
	     //TabItem sumItem = new TabItem(sumTab,SWT.NONE);
		//sumItem.setText("Account Summary");

		//sumItem.setControl(summaryTable);

		String[] summaryTitles = {"Trading Time ","Company","Type","Quantity ","Rate "};

		summaryTable=new CoolTable(sumTab.tabComp,summaryTitles,new int[]{150,150,80,80,150},SWT.NONE);

		//sumTab.setLayout(new FillLayout());


		//hform.setWeights(new int[] {50,50});
		//vform.setWeights(new int[] {50,50});
			}
	public static void updateHoldingsTable()
	{
		holdingsTable.table.removeAll();
		investmentsTable.table.removeAll();
		Set set = Manager.gi.user_Holding.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext())
		{
			Map.Entry me = (Map.Entry)i.next();
			CO_Holding a=new CO_Holding((String) me.getValue());
			boolean change = false;
			if(a.holdingType.equals("Share"))
			{
				//CO_Company comp = new CO_Company((String) Manager.gi.company.get(a.compId));
				Set set1  = Manager.gi.company.entrySet();
				Iterator it = set1.iterator();
				String Compname = "";
				int marketprice = 0;
				while(it.hasNext())
				{
					Map.Entry me1 = (Map.Entry)(it.next());
					CO_Company c = new CO_Company(""+me1.getValue());
					if(a.compId == c.compId)
					{
						Compname = c.compName;
						marketprice = c.marketPrice;
						change = c.change;
					}
				}
				String str=new String("Down");
				if(change==true)
					str = "Up";
				String[] row={Compname, a.noOfShares+"", CO_CO.formatMoney(a.purchasedPrice),CO_CO.formatMoney(marketprice)+"",str};
				holdingsTable.newEntry(row, a.compId);
			}
			else
			{

				//CO_MF comp = new CO_MF((String) Manager.gi.MF.get(a.compId));
				Set set1  = Manager.gi.company.entrySet();
				Iterator it = set.iterator();
				String MFName = "";
				int NAV = 0;
				boolean change1 = false;
				while(it.hasNext())
				{
					Map.Entry me1 = (Map.Entry)(it.next());
					CO_MF c = new CO_MF(""+me1.getValue());
					if(Manager.currentTradingCompId == c.MFId)
					{
						MFName = c.MFName;
						change1 = c.change;
						NAV = c.NAV;
					}
				}
				String str=new String("Down");
				if(change1==true)
					str = "Up";
				String[] row={" ",MFName, a.noOfShares+"", CO_CO.formatMoney(NAV)+"",str};
				investmentsTable.newEntry(row, a.compId);
			}
		}
	}

	 public static void UpdateBidsTable()
	 {
		bidsTable.table.removeAll();
			Set set = Manager.gi.user_Bid.entrySet();
			Iterator i = set.iterator();
			while(i.hasNext())
			{
				Map.Entry me = (Map.Entry)i.next();
				CO_Bid a=new CO_Bid((String) me.getValue());
				//CO_Company comp = new CO_Company((String) Manager.gi.company.get(a.compId));
				Set set1  = Manager.gi.company.entrySet();
				Iterator it = set1.iterator();
				String Compname = "";
				int marketprice = 0;
				while(it.hasNext())
				{
					Map.Entry me1 = (Map.Entry)(it.next());
					CO_Company c = new CO_Company(""+me1.getValue());
					if(a.compId == c.compId)
					{
						Compname = c.compName;
						marketprice = c.marketPrice;
					}
				}

				String[] row={" ",Compname, a.bidType, a.noOfShares+"", CO_CO.formatMoney(a.price),CO_CO.formatMoney(marketprice)};
				bidsTable.newEntry(row, a.bidId);
			}
	 }

	 public static void UpdateTransactionTable()
	 {
		summaryTable.table.removeAll();

		String[] fields=CO_CO.getSplit(Manager.gi.recent_trans,CO_CO.delim[8]);
		if(Manager.gi.recent_trans.length() != 0)
		{
			for(int i=0;i<fields.length;i++)
			{
				String[] subfields=CO_CO.getSplit(fields[i],CO_CO.delim[6]);
				int X = Integer.parseInt(subfields[1]);
				String str = "";
				if(subfields[4].equals("Buy")||subfields[4].equals("Sell"))
				{
					//CO_Company comp = new CO_Company((String) Manager.gi.company.get(Integer.parseInt(subfields[1])));
					Set set1  = Manager.gi.company.entrySet();
					Iterator it = set1.iterator();
					String Compname = "";
					int marketprice = 0;
					while(it.hasNext())
					{
						Map.Entry me1 = (Map.Entry)(it.next());
						CO_Company c = new CO_Company(""+me1.getValue());
						if(X == c.compId)
						{
							str = c.compName;
						}
					}
				}
				else
				{
					int Y = Integer.parseInt(subfields[1]);
					//CO_MF comp = new CO_MF((String) Manager.gi.MF.get()));
					CO_MF comp = new CO_MF();
					str = "";
					Set set1  = Manager.gi.MF.entrySet();
					Iterator it = set1.iterator();
					String Compname = "";
					int marketprice = 0;
					while(it.hasNext())
					{
						Map.Entry me1 = (Map.Entry)(it.next());
						CO_MF c = new CO_MF(""+me1.getValue());
						if(Y == c.MFId)
						{
							str = c.MFName;
						}
					}
				}

//				String[] summaryTitles = {"Trading Time ","Type","Company name","Quantity ","Rate "};

				String[] row={subfields[0],str, subfields[4],subfields[2],subfields[3]};
				summaryTable.newEntry(row,i );

			}
		}
	 }


}

