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
import java.util.Iterator;
import java.util.Set;
import java.util.Map;

public class FrontendBuy extends Composite
{
	public static CoolTable bidsTable;
	public static Spinner rate;
	public static Spinner qty;

	public FrontendBuy(Composite parent)
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
		rate.setLayoutData(gdrate);
		//CO_Company c = new CO_Company(Manager.gi.company.get(Manager.currentTradingCompId).toString());
		int marketPrice = 0;
		Set set  = Manager.gi.company.entrySet();
		Iterator i = set.iterator();
		String Compname = "";
		while(i.hasNext())
		{
			Map.Entry me = (Map.Entry)(i.next());
			CO_Company c = new CO_Company(""+me.getValue());
			if(Manager.currentTradingCompId == c.compId)
			{
				marketPrice = c.marketPrice;
			}
		}
		System.out.println(""+marketPrice);
		rate.setMinimum(marketPrice);
		rate.setMaximum((int)((double)marketPrice*(1+0.1)));
		rate.setDigits(2);
		rate.setIncrement(10);

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
		qty.setMinimum(0); // To be changed dynamically
		qty.setLayoutData(gdqty);

		Button buttonBuy = new Button(left,SWT.NONE);
		GridData gdbuttonBuy = new GridData();
		gdbuttonBuy.horizontalIndent = 50;
		gdbuttonBuy.verticalIndent = 40;
		buttonBuy.setText("Buy");
		buttonBuy.setLayoutData(gdbuttonBuy);
		buttonBuy.addSelectionListener(new SelectionAdapter(){

			public void widgetSelected(SelectionEvent e)
         	{
				MessageBox m = new MessageBox(Application.rootShell,SWT.ICON_QUESTION|SWT.OK|SWT.CANCEL);
				//CO_Company c = new CO_Company(Manager.gi.company.get(Manager.currentTradingCompId).toString());
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
				m.setText("Buy");
				m.setMessage("Are you sure you want to buy " + qty.getSelection() + " shares of " + Compname +
						" at Rs. " + (double)rate.getSelection()/100 + " per share \nfor a total amount of Rs. " + (double)rate.getSelection()*qty.getSelection()/100 + " at " + (double)Manager.tax/100 + " tax ?");
				if(m.open() == SWT.OK)
				{
					globalCheck();
					Manager.putBuyBid();
				}
         	}
			});

		Composite right = new Composite(sform,SWT.NONE);
		GridLayout gright = new GridLayout();
		gright.numColumns = 1;
		right.setLayout(gright);

		Group groupBuy = new Group(right,SWT.NONE);
		String[] bidTitles = {"Rate","Quantity"};
		groupBuy.setText("Top 5 Selling Bids (for you to buy from)");
		groupBuy.setLayout(new FillLayout());
		bidsTable = new CoolTable(groupBuy,bidTitles,new int[]{150,150},SWT.NONE);
			updateTopBids();

	}

	public static void updateTopBids()
	{

		bidsTable.table.removeAll();
		CO_Bid bids = new CO_Bid();
		int count=5;
/*		Set set = Manager.gi.topBids.entrySet();
		Iterator i = set.iterator();
		System.out.println("Inside Top5Sell");
		while(i.hasNext())
		{

			Map.Entry me = (Map.Entry)i.next();
			bids=new CO_Bid((String) (me.getValue()));

//			bids=(CO_Bid) (me.getValue());
			if(Manager.currentTradingCompId==bids.compId && bids.bidType.equals("Sell"))
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
	public static void resetBuy()
	{
		//CO_Company c = new CO_Company(Manager.gi.company.get(Manager.currentTradingCompId).toString());
		Set set  = Manager.gi.company.entrySet();
		Iterator i = set.iterator();
		String Compname = "";
		int marketprice = 0;
		while(i.hasNext())
		{
			Map.Entry me = (Map.Entry)(i.next());
			CO_Company c = new CO_Company(""+me.getValue());
			if(Manager.currentTradingCompId == c.compId)
			{
				marketprice = c.marketPrice;
			}
		}
		rate.setMinimum(marketprice);
		rate.setMaximum((int)((double)marketprice*1.1));
		rate.setSelection(marketprice);
		rate.setDigits(2);
		rate.setIncrement(10);

		qty.setMinimum(1);
		qty.setMaximum(Manager.gi.user.cashShare/(rate.getSelection()*(1+Manager.tax/100)));
		qty.setSelection(1);
		qty.setDigits(0);
		qty.setIncrement(1);
	}
	public static void globalCheck()
	{
		//CO_Company c = new CO_Company(Manager.gi.company.get(Manager.currentTradingCompId).toString());
		Set set  = Manager.gi.company.entrySet();
		Iterator i = set.iterator();
		String Compname = "";
		int marketprice = 0;
		while(i.hasNext())
		{
			Map.Entry me = (Map.Entry)(i.next());
			CO_Company c = new CO_Company(""+me.getValue());
			if(Manager.currentTradingCompId == c.compId)
			{
				marketprice = c.marketPrice;
			}
		}

		if(rate.getSelection() < marketprice)
			rate.setSelection(marketprice);
		if(rate.getSelection() > (int)((double)marketprice*1.1))
			rate.setSelection((int)((double)marketprice*1.1));
		if(rate.getSelection()*qty.getSelection() > Manager.gi.user.cashShare)
			qty.setSelection(Manager.gi.user.cashShare/rate.getSelection());
	}
}
