import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Spinner;



public class MutualInvestment extends Composite
{
	public static CoolTable bids;
	public static Spinner qty;

	public MutualInvestment(Composite parent)
	{
		super(parent,SWT.NONE);
		this.dispose();
		parent.setLayout(new FillLayout());

		Composite inv = new Composite(parent,SWT.NONE);
		GridLayout invgrid = new GridLayout();
		invgrid.numColumns = 2;
		inv.setLayout(invgrid);

		Label investment = new Label(inv,SWT.NONE);
		investment.setText("Investments");
		investment.setAlignment(SWT.CENTER);

        qty = new Spinner(inv,SWT.NONE);
		qty.setIncrement(1);
		qty.setMaximum(5000);  //kept 50 temparirly.has to be changed acc to game instance

		//Time not required

/*		Label time = new Label(inv,SWT.NONE);
		time.setText("Time");
		time.setAlignment(SWT.CENTER);

		Spinner time_val = new Spinner(inv,SWT.NONE);
		time_val.setDigits(0);
		time_val.setIncrement(1);
		time_val.setMaximum(20); //kept 20 temparirly.has to be changed acc to game instance
*/
		Button buttonInvest = new Button(inv,SWT.NONE);
		buttonInvest.setText("Invest");
		buttonInvest.addSelectionListener(new SelectionAdapter(){
         	public void widgetSelected(SelectionEvent e)
         	{
         		MessageBox m = new MessageBox(Application.rootShell,SWT.ICON_QUESTION|SWT.OK|SWT.CANCEL);
         		Set set  = Manager.gi.company.entrySet();
        		Iterator i = set.iterator();
        		int NAV = 0;
        		String MFName = "";
        		while(i.hasNext())
        		{
        			Map.Entry me = (Map.Entry)(i.next());
        			CO_MF c = new CO_MF(""+me.getValue());
        			if(Manager.currentTradingCompId == c.MFId)
        			{
        				MFName = c.MFName;
        				NAV = c.NAV;
        			}
        		}
				//CO_MF c = new CO_MF(Manager.gi.MF.get(Manager.currentTradingMFId).toString());
				m.setText("Invest");
				m.setMessage("Are you sure you want to invest " + qty.getSelection() + " units in " + MFName +
						" at Rs. " + (double)NAV/100 + " per unit \nfor a total amount of Rs. " + (double)NAV*qty.getSelection()/100 +" at " + (double)Manager.tax/100 + " tax ?");
				if(m.open() == SWT.OK)
				{
					Manager.MFInvest();
				}
         	}
			});


	}
}

