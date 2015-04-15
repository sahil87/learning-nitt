import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;


public class MoneyManager extends Composite {
	public static Label totalamount,wamount;
	public static Spinner shareamount,mfamount;
	public MoneyManager(Composite parent)
	{
		super(parent,SWT.NONE);
		this.dispose();
		parent.setLayout(new FillLayout());

		Composite money= new Composite(parent,SWT.NONE);
		GridLayout moneyLayout=new GridLayout();
		moneyLayout.numColumns = 2;
		money.setLayout(moneyLayout);

		Label totalCash = new Label(money,SWT.NONE);
		totalCash.setText("Total Cash");
		totalCash.setAlignment(SWT.CENTER);

		totalamount = new Label(money,SWT.NONE);
		totalamount.setText("20000000");//set the amount here
		totalamount.setAlignment(SWT.CENTER);

		Label withdrawl = new Label(money,SWT.NONE);
		withdrawl.setText("Withdrawl amount");
		withdrawl.setAlignment(SWT.CENTER);

		wamount = new Label(money,SWT.NONE);
		wamount.setText("20000000");
		wamount.setAlignment(SWT.CENTER);

		Label sharecash = new Label(money,SWT.NONE);
		sharecash.setText("Cash Share");
		sharecash.setAlignment(SWT.CENTER);

		shareamount = new Spinner(money,SWT.NONE);
		shareamount.setDigits(2);
		shareamount.setIncrement(1000);
		shareamount.setSelection(0);

		Label cashmf  = new Label(money,SWT.NONE);
		cashmf.setText("Cash Mutual Funds");
		cashmf.setAlignment(SWT.CENTER);

		mfamount = new Spinner(money,SWT.NONE);
		mfamount.setDigits(2);
		mfamount.setIncrement(1000);
		mfamount.setSelection(0);

		ModifyListener mfamountModifyListener = new ModifyListener(){
			public void modifyText(ModifyEvent e){
				mfamount.setMaximum(
						(int)(Float.parseFloat(totalamount.getText())*100)-
						shareamount.getSelection()
						);
/*				System.out.println("Money : total : "+(int)(Float.parseFloat(totalamount.getText())*100));
				System.out.println("Money : share : "+shareamount.getSelection());
				System.out.println("Money : mf    : "+mfamount.getSelection());
				System.out.println("Money : withd : "+(int)(Float.parseFloat(wamount.getText())*100));
*/
				wamount.setText(CO_CO.formatMoney(((int)((Float.parseFloat(totalamount.getText())*100))-mfamount.getSelection()-shareamount.getSelection())));
				mfamount.setMaximum(
						(int)(Float.parseFloat(totalamount.getText())*100)-
						shareamount.getSelection()
						);
				shareamount.setMaximum(
						(int)(Float.parseFloat(totalamount.getText())*100)-
						mfamount.getSelection()
						);

			}
		};
		mfamount.addModifyListener(mfamountModifyListener);

		ModifyListener shareamountModifyListener = new ModifyListener(){			public void modifyText(ModifyEvent e){
				shareamount.setMaximum(
						(int)(Float.parseFloat(totalamount.getText())*100)-
						mfamount.getSelection()
						);
				wamount.setText(CO_CO.formatMoney(((int)((Float.parseFloat(totalamount.getText())*100))-mfamount.getSelection()-shareamount.getSelection())));
				mfamount.setMaximum(
						(int)(Float.parseFloat(totalamount.getText())*100)-
						shareamount.getSelection()
						);
				shareamount.setMaximum(
						(int)(Float.parseFloat(totalamount.getText())*100)-
						mfamount.getSelection()
						);

			}
		};
		shareamount.addModifyListener(shareamountModifyListener);


		Button buttonsubmit = new Button(money,SWT.NONE);
		GridData gdbuttonsubmit = new GridData();
		gdbuttonsubmit.horizontalIndent = 50;
		gdbuttonsubmit.verticalIndent = 20;
		buttonsubmit.setText("Submit");
		buttonsubmit.setLayoutData(gdbuttonsubmit);

		buttonsubmit.addSelectionListener(new SelectionAdapter(){

			public void widgetSelected(SelectionEvent e)
         	{
				Manager.updatecapital();
         	}
			});

	}

	public static void updatedata()
	{
		try
		{
			System.out.println(CO_CO.formatMoney(Manager.gi.user.cashTotal)+"aaaa"+Manager.gi.user.cashWithdrawal+"bbb"+Manager.gi.user.cashShare+"ccc"+Manager.gi.user.cashMF);

			mfamount.setMaximum(Manager.gi.user.cashMF+Manager.gi.user.cashWithdrawal);
			shareamount.setMaximum(Manager.gi.user.cashShare+Manager.gi.user.cashWithdrawal);

			totalamount.setText(CO_CO.formatMoney(Manager.gi.user.cashTotal));
			wamount.setText(CO_CO.formatMoney(Manager.gi.user.cashWithdrawal));

			shareamount.setSelection(Manager.gi.user.cashShare);
			mfamount.setSelection(Manager.gi.user.cashMF);

			totalamount.pack();
			wamount.pack();
			shareamount.pack();
			mfamount.pack();
		}
		catch(Exception e)
		{
			System.out.println("At MoneyManager Update : "+e.getMessage());
		}
	}

}
