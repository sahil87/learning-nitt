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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Spinner;
public class FrontendSell extends Composite
{
	public static CoolTable bidsTable;
	public static Spinner rate;
	public static Spinner qty;

	public FrontendSell(Composite parent)
	{
		super(parent,SWT.NONE);
		this.dispose();
		parent.setLayout(new FillLayout());

		SashForm sform = new SashForm(parent,SWT.HORIZONTAL);
		sform.setLayout(new FillLayout());

		Composite left = new Composite(sform,SWT.NONE);
		GridLayout gleft = new GridLayout();
		gleft.numColumns = 2;
		left.setLayout(gleft);

		Label lrate = new Label(left,SWT.NONE);
		GridData gdlrate = new GridData();
		gdlrate.verticalIndent = 40;
		gdlrate.horizontalIndent = 20;
		lrate.setText("Rate");
		lrate.setAlignment(SWT.CENTER);
		lrate.setLayoutData(gdlrate);

		 rate = new Spinner(left,SWT.NONE);
		rate.setBounds(60, 60, 50, 30);
		GridData gdrate = new GridData();
		gdrate.verticalIndent = 40;
		gdrate.horizontalIndent = 20;
		rate.setDigits(2);
		rate.setIncrement(1);
		rate.setMaximum(100); // To be changed dynamically
		rate.setLayoutData(gdrate);

		Label lqty = new Label(left,SWT.NONE);
		GridData gdlqty = new GridData();
		gdlqty.verticalIndent = 40;
		gdlqty.horizontalIndent = 20;

		lqty.setText("Qty");
		lqty.setAlignment(SWT.CENTER);
		lqty.setLayoutData(gdlqty);

		qty = new Spinner(left,SWT.NONE);
		GridData gdqty = new GridData();
		gdqty.horizontalIndent = 25;
		gdqty.verticalIndent = 40;
		qty.setDigits(0);
		qty.setIncrement(1);
		qty.setMaximum(100); // To be changed dynamically
		qty.setLayoutData(gdqty);

		Button buttonSell = new Button(left,SWT.NONE);
		GridData gdbuttonSell = new GridData();
		gdbuttonSell.horizontalIndent = 50;
		gdbuttonSell.verticalIndent = 40;
		buttonSell.setText("Sell");
		buttonSell.setLayoutData(gdbuttonSell);
		buttonSell.addSelectionListener(new SelectionAdapter(){
         	public void widgetSelected(SelectionEvent e)
         	{
         		Set set  = Manager.gi.company.entrySet();
				Iterator i = set.iterator();
				String Compname = "";
				while(i.hasNext())
				{
					Map.Entry me = (Map.Entry)(i.next());
					CO_Company c = new CO_Company(""+me.getValue());
					if(Manager.currentTradingCompId == c.compId)
					{
						Compname = c.compName;
					}
				}
				if(qty.getSelection() != 0)
				{
					MessageBox m = new MessageBox(Application.rootShell,SWT.ICON_QUESTION|SWT.OK|SWT.CANCEL);
					m.setText("Sell");
					m.setMessage("Are you sure you want to sell " + qty.getSelection() + " shares of " + Compname +
							" at Rs. " + (double)rate.getSelection()/100 + " per share \nfor a total amount of Rs. " + (double)rate.getSelection()*qty.getSelection()/100 +
							" at " + (double)Manager.tax/100 + " tax ?");
					if(m.open() == SWT.OK)
					{
						Manager.putSellBid();
					}
				}
				else
				{
					MessageBox m = new MessageBox(Application.rootShell,SWT.ICON_ERROR|SWT.OK);
					m.setText("Sell");
					m.setMessage("You do not possess shares of this company !!");
				}
         	}
			});
		Composite right = new Composite(sform,SWT.NONE);
		GridLayout gright = new GridLayout();
		gright.numColumns = 1;
		right.setLayout(gright);

		Group groupSell = new Group(right,SWT.NONE);
		String[] bidTitles = {"Rate","Quantity"};
		groupSell.setText("Top 5 Buying Bids (for you to Sell )");
		groupSell.setLayout(new FillLayout());
		bidsTable = new CoolTable(groupSell,bidTitles,new int[]{150,150},SWT.NONE);
			updateTopBids();
	}
	public static void updateTopBids()
	{

		bidsTable.table.removeAll();
		CO_Bid bids = new CO_Bid();
		int count=5;
/*		Set set = Manager.gi.topBids.entrySet();
		Iterator i = set.iterator();
		System.out.println("Inside Top5Buy");
		while(i.hasNext())
		{

			Map.Entry me = (Map.Entry)i.next();
			bids=new CO_Bid((String) (me.getValue()));

//			bids=(CO_Bid) (me.getValue());
			if(Manager.currentTradingCompId==bids.compId && bids.bidType.equals("Buy"))
			{

				String[] row={bids.noOfShares+"",""+CO_CO.formatMoney(bids.price) };
				bidsTable.newEntry(row,bids.bidId);
				count--;
			}
		}
*/
		while(--count >= 0)
		{
			String[] row={"",""};
			bidsTable.newEntry(row,bids.bidId);
		}
		bidsTable.sortByColumn2(-1);
	}
	public static void resetSell()
	{
		Set set  = Manager.gi.company.entrySet();
		Iterator i = set.iterator();
		int marketprice = 0;
		String Compname = "";
		while(i.hasNext())
		{
			Map.Entry me = (Map.Entry)(i.next());
			CO_Company c = new CO_Company(""+me.getValue());
			if(Manager.currentTradingCompId == c.compId)
			{
				Compname = c.compName;
				marketprice = c.marketPrice;
			}
		}
		rate.setMinimum((int)((double)marketprice*0.9));
		rate.setMaximum((int)((double)marketprice*1.1));
		rate.setSelection((int)((double)marketprice*0.9));
		rate.setDigits(2);
		rate.setIncrement(10);


		Set hset  = Manager.gi.user_Holding.entrySet();
		Iterator it = set.iterator();
		CO_Holding C = new CO_Holding();
		C = null;
		String hname = "";
		while(it.hasNext())
		{
			Map.Entry me = (Map.Entry)(it.next());
			CO_Holding c = new CO_Holding(""+me.getValue());
			if(Manager.currentTradingCompId == c.compId)
			{
				C = c;
				break;
			}
		}
		/*if(Manager.gi.user_Holding.get(Manager.currentTradingCompId) != null)
		{
			CO_Holding h = new CO_Holding(Manager.gi.user_Holding.get(Manager.currentTradingCompId).toString());
			qty.setMinimum(1);
			qty.setMaximum(h.noOfShares);
			qty.setSelection(1);
		}*/
		if(C != null)
		{
			//CO_Holding h = new CO_Holding(Manager.gi.user_Holding.get(Manager.currentTradingCompId).toString());
			CO_Holding h = new CO_Holding();
			h = C;
			qty.setMinimum(1);
			qty.setMaximum(h.noOfShares);
			qty.setSelection(1);
		}
		else
		{
			qty.setMaximum(0);
		}
		qty.setDigits(0);
		qty.setIncrement(1);
	}
	public static void globalCheck()
	{
		Set set  = Manager.gi.company.entrySet();
		Iterator i = set.iterator();
		int marketprice = 0;
		String Compname = "";
		while(i.hasNext())
		{
			Map.Entry me = (Map.Entry)(i.next());
			CO_Company c = new CO_Company(""+me.getValue());
			if(Manager.currentTradingCompId == c.compId)
			{
				Compname = c.compName;
				marketprice = c.marketPrice;
			}
		}
		if(rate.getSelection() < (int)((double)marketprice*0.9))
			rate.setSelection((int)((double)marketprice*0.9));
		if(rate.getSelection() > (int)((double)marketprice*1.1))
			rate.setSelection((int)((double)marketprice*1.1));
		//Manager.gi.user_Holding.get(Manager.currentTradingCompId) != null
		boolean ok = false;
		i = set.iterator();
		while(i.hasNext())
		{
			Map.Entry me = (Map.Entry)(i.next());
			CO_Company c = new CO_Company(""+me.getValue());
			if(Manager.currentTradingCompId == c.compId)
			{
				ok = true;
			}
		}
		if(ok)
		{
		//	CO_Holding h = new CO_Holding(Manager.gi.user_Holding.get(Manager.currentTradingCompId).toString());
			i = set.iterator();
			int noshares = 0;
			while(i.hasNext())
			{
				Map.Entry me = (Map.Entry)(i.next());
				CO_Company c = new CO_Company(""+me.getValue());
				if(Manager.currentTradingCompId == c.compId)
				{
					noshares = c.noOfShares;
				}
			}

			if(qty.getSelection() > noshares)
				qty.setSelection(noshares);
		}
		else
		{
			qty.setMaximum(0);
		}
	}
}