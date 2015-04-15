import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

public class Manager {

	public static CO_CO co,ro,gi;
	public static int currentTradingCompId=1;//used during the game for storing the company to be bought/sold
	public static int currentTradingMFId=1;//used during the game for storing the company to be bought/sold
	public static int currentPortfolioCompId=1;
	public static int currentPortfolioMFId=1;
	public static int tax = 25;
	public static char[] Descriptor;

	static OutputStream outstr;
	static GZIPOutputStream a ;
	static ObjectOutputStream oos;
	public Manager()
	{
		Descriptor = new char[19];
		resetDescriptor();
		gi = new CO_CO();
	}

	public static void resetDescriptor()
	{
		for(int i=0;i<Descriptor.length;i++)
			Descriptor[i]='0';
	}

	public static void query(final String action)
	{
	}
	public static void update()
	{
		if(ro.action.equals("invalid login"))
		{
			MessageBox m = new MessageBox(Application.rootShell,SWT.OK|SWT.ICON_ERROR);
 			m.setMessage("UserEmail and Password do not match !!\n");
 			m.setText("Invalid Login");
 			m.open();
		}
		else
		if(ro.action.equals("loggingin"))
		{
			if(ro.user.userEmail.equals(gi.user.userEmail))
			{
				gi.user = new CO_User(ro.user.getString());
				//System.out.println("In Manager.Update"+gi.user.getString()+" : "+ro.user.getString());
				if(!ro.companyMD5.equals("Company MD5 Ok !!"))
				{
					Application.companyString =ro.companyMD5;
					Application.companyMD5=Application.getMD5(Application.companyString);
				}
				if(!ro.MFMD5.equals("MF MD5 Ok !!"))
				{
					Application.MFString =ro.MFMD5;
					Application.MFMD5=Application.getMD5(Application.MFString);
				}
				CO_CO.setHashMap(gi.company, Application.companyString);
				CO_CO.setHashMap(gi.MF, Application.MFString);

				WindowManager.drawGUI();
				GameUpdater.firstTimeUpdate();

//				Manager.logout();

			}
		}
		else
		{
			if(ro.user.userId == gi.user.userId)
			{
				if(!ro.adminMsg.equals("") && ro.adminMsg!=null && !ro.adminMsg.equals(gi.adminMsg))
				{
					gi.adminMsg = ro.adminMsg;
					MessageBox m = new MessageBox(Application.rootShell,SWT.OK|SWT.ICON_INFORMATION);
		 			m.setMessage(ro.adminMsg);
		 			m.setText("Admin Message");
		 			m.open();
				}
				else
				if(ro.user.status.equals("banned"))
				{
					logout();

					MessageBox m = new MessageBox(Application.rootShell,SWT.OK|SWT.ICON_ERROR);
		 			m.setMessage("You are Banned !!\n" +
		 					"Your Transactions were found to be illegal..");
		 			m.setText("Banned");
		 			m.open();
				}
				else
				if(ro.action.equals("AlreadyLoggedIn"))
				{
					MessageBox m = new MessageBox(Application.rootShell,SWT.OK|SWT.ICON_ERROR);
	         		m.setText("Login Error");
	         		m.setMessage("You are already logged in !!\nTry using the logoff page from the dalal website.");
	         		m.open();
				}
			}
		}



	}
	public static void connect(String str)
	{
		String roString;
		try
		{
			/* Write object to servlet */
			URLConnection con = getServletConnection();
			outstr= con.getOutputStream();
			a = new GZIPOutputStream(outstr);
			oos = new ObjectOutputStream(a);

			System.out.println("\nManager.java : WHAT WE SEND\n"+str);
		    oos.writeObject(str);

		    oos.flush();
		    oos.close();
		    a.close();
		    outstr.close();
		    /* Read Object from Server*/
		    InputStream response = con.getInputStream();
    		GZIPInputStream g=new GZIPInputStream(response);
    		ObjectInputStream inputFromServlet=new ObjectInputStream(g);

			roString= (String) inputFromServlet.readObject();
    		System.out.println("\nManager.java : WHAT WE GOT FROM SERVLET\n"+roString);

			inputFromServlet.close();
			g.close();
			response.close();
			if(!roString.startsWith("Error"))
			{
				System.out.println("in connect : " + roString);
				ro = new CO_CO(roString);
				update();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception in Manager.java at frontend : "+e.getMessage());
			try{
				if(outstr!=null)	outstr.close();
				if(a!=null)			a.close();
				if(oos!=null)		oos.close();
			}
			catch(IOException error)
			{

			}
			MessageBox m = new MessageBox(Application.rootShell,SWT.RETRY|SWT.ICON_ERROR|SWT.CANCEL);


			m.setText("Connection Error");
			m.setMessage("Unable to connect to server.\nMake sure the server address and proxy settings are correct.");

			if ( m.open()==SWT.RETRY)
     		{
     			try
     	 		{
     				root.display.syncExec(new Runnable(){public void run(){
//     					connect();
     				}});
     			}
 				catch(Exception err)
 				{

 				}
     		}
		}
	}

	private static URLConnection getServletConnection()
    throws MalformedURLException, IOException
    {
            URL urlServlet;
            urlServlet = new URL("http://"+Application.serverAddress+":8080/08_Dalal_Servlet/echo");
            URLConnection con = urlServlet.openConnection();

            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestProperty(
                    "Content-Type",
                    "application/x-java-serialized-object");

           return con;
    }

	public static void login()
	{
		if(LoginBox.buttonLogin.isDisposed()) return;
		new Manager();
		co = new CO_CO();
		co.action = "login";
		co.user.userEmail = LoginBox.textLoginEmail.getText();
		co.user.userPassword = LoginBox.textLoginPassword.getText();
		Application.companyString = Application.readCompanyConf();
		Application.MFString = Application.readMFConf();
		gi.user.userEmail = LoginBox.textLoginEmail.getText();
		//gi.user.userPassword = LoginBox.textLoginPassword.getText();
		co.companyMD5 = Application.getMD5(Application.companyString);
		co.MFMD5 = Application.getMD5(Application.MFString);


		ro = new CO_CO();
		ro.action = "login";
		ro.user = new CO_User();
		ro.user.userEmail = LoginBox.textLoginEmail.getText();
		ro.user.userPassword = LoginBox.textLoginPassword.getText();
		ro.companyMD5 = Application.companyMD5;
		ro.MFMD5 = Application.MFMD5;

		resetDescriptor();
		Descriptor[0]='1';
		Descriptor[1]='1';
		Descriptor[11]='1';
		Descriptor[12]='1';


//		System.out.println(""+Descriptor);


		Application.display.syncExec(new Runnable(){public void run(){
			String str=new String(Descriptor);
			connect(str+co.getString(str));
		}});

//		if (gi!=null && gi.user.loggedin)
//		{
//			GameUpdater.firstTimeUpdate();
//		}

	}
	public static void logout()
	{
		co = new CO_CO();
		co.user.userId = gi.user.userId;

		co.action = "logout";

		resetDescriptor();
		Descriptor[0]='1';
		Descriptor[1]='1';

		Application.display.syncExec(new Runnable(){public void run(){
			String str=new String(Descriptor);
			connect(str+co.getString(str));
		}});

		gi=null;
		currentTradingCompId = 1;
		currentTradingMFId = 1;
		currentPortfolioCompId = 1;
		currentPortfolioMFId = 1;
		WindowManager.redrawGUI();
	}

	public static void putBuyBid()
	{
//		connect();
		co = new CO_CO();
		co.action = "buyingbid";
		co.user.userId = gi.user.userId;
		co.bid.bidType="buy";
		co.bid.compId=currentTradingCompId;
		co.bid.noOfShares=FrontendBuy.qty.getSelection();
		co.bid.price=FrontendBuy.rate.getSelection();

		resetDescriptor();
		Descriptor[0]='1';
		Descriptor[1]='1';
		Descriptor[14]='1';

		String str=new String(Descriptor);
		connect(str+co.getString(str));
		//System.out.println("110000000000001"+co.getString("110000000000001"));
		//SELECT * FROM `Bids` WHERE CompanyId='1' AND BidType='sell' AND `Rate` <= 20500' ORDER BY `Rate` ASC,`Time` ASC


		if(ro.user.userId==gi.user.userId)
		{
/*			if(!(ro.bid.bidId==-1))
			{
				gi.user_Bid.put(ro.bid.bidId, ro.bid.getString());
				PortfolioTab.UpdateBidsTable();
			}

			if(!(ro.holding.compId==-1))
			{
				gi.user_Holding.put(ro.holding.compId, ro.holding.getString());
				PortfolioTab.updateHoldingsTable();
			}
*/			getbids();
			getholdings();


		}
	}
	public static void putSellBid()
	{
//		connect();
		co = new CO_CO();
		co.action = "sellingbid";
		co.user.userId = gi.user.userId;
		co.bid.bidType="sell";
		co.bid.compId=currentTradingCompId;
		co.bid.noOfShares=FrontendSell.qty.getSelection();
		co.bid.price=FrontendSell.rate.getSelection();

		resetDescriptor();
		Descriptor[0]='1';
		Descriptor[1]='1';
		Descriptor[14]='1';

		String str=new String(Descriptor);
		connect(str+co.getString(str));

		if(ro.user.userId==gi.user.userId)
		{
/*			if(!(ro.bid.bidId==-1))
			{
				gi.user_Bid.put(ro.bid.bidId, ro.bid.getString());
				PortfolioTab.UpdateBidsTable();
			}

			if(!(ro.holding.compId==-1))
			{
				gi.user_Holding.put(ro.holding.compId, ro.holding.getString());
				PortfolioTab.updateHoldingsTable();
			}
*/
			getbids();
			getholdings();

		}
	}

	public static void MFInvest()
	{
//		connect();

		co = new CO_CO();
		co.action = "invest";
		co.user.userId = gi.user.userId;
		co.bid.bidType="invest";
		co.bid.compId=currentTradingMFId;
		co.bid.noOfShares=MutualInvestment.qty.getSelection();

		resetDescriptor();
		Descriptor[0]='1';
		Descriptor[1]='1';
		Descriptor[14]='1';

		String str=new String(Descriptor);
		connect(str+co.getString(str));

		if(ro.user.userId==gi.user.userId && ro.action.equals("InvestSuccessful"))
		{
		//	gi.user_Holding.put(ro.holding.compId, ro.holding.getString());
		//	PortfolioTab.updateHoldingsTable();
			getbids();
			getholdings();
		}

	}
	public static void MFDeInvest()
	{
//		connect();
		//co=new CO_CO();
		co.action = "Disinvest";
		co.user.userId = gi.user.userId;
		/*co.bid.bidType="Disinvest";
		co.bid.compId = currentTradingMFId;*/
		resetDescriptor();
		Descriptor[0]='1';
		Descriptor[1]='1';
		Descriptor[5]='1';

		String str=new String(Descriptor);
		connect(str+co.getString(str));

		if(ro.user.userId==gi.user.userId)
		{
//			gi.user_Holding.remove(ro.holding.compId);
//			PortfolioTab.updateHoldingsTable();

			getbids();
			getholdings();
		}
	}

	public static void deleteBid()
	{
		co.action = "DeleteBid";
		co.user.userId = gi.user.userId;
		/*co.bid.bidType="Disinvest";
		co.bid.compId = currentTradingMFId;*/
		resetDescriptor();
		Descriptor[0]='1';
		Descriptor[1]='1';
		Descriptor[5]='1';

		String str=new String(Descriptor);
		connect(str+co.getString(str));

		System.out.println(ro.user.userId+" "+gi.user.userId+" "+ro.action);

		if(ro.user.userId==gi.user.userId && ro.action.equals("DeleteBidSucessful"))
		 {
			getbids();
			getholdings();
	     }
	}

	public static void updatecapital()
	{
//		connect();
		co=new CO_CO();
		co.action = "Update Capital";
		co.user.userId = gi.user.userId;
		co.user.cashMF=MoneyManager.mfamount.getSelection();
		co.user.cashShare=MoneyManager.shareamount.getSelection();
		co.user.cashWithdrawal=(int)((100*Float.parseFloat(MoneyManager.wamount.getText())));

		resetDescriptor();
		Descriptor[0]='1';
		Descriptor[1]='1';

		String str=new String(Descriptor);
		connect(str+co.getString(str));
		if(ro.user.userId==gi.user.userId && ro.action.equals("Updated Capital"))
		{
			gi.user.cashMF=ro.user.cashMF;
			gi.user.cashShare=ro.user.cashShare;
			gi.user.cashWithdrawal=ro.user.cashWithdrawal;
			gi.user.cashTotal=(gi.user.cashMF+gi.user.cashShare+gi.user.cashWithdrawal);
		}

		MoneyManager.updatedata();
		WindowManager.updateLeftPane();
	}

	public static void getCompanyInfo()
	{
//		connect();
		co=new CO_CO();
		co.action="GetCompanyInfo";
		co.user.userId = gi.user.userId;
		//co.company=currentTradingCompId;  //how to set the current company in the Get Info..
		resetDescriptor();
		Descriptor[0]='1';
		Descriptor[1]='1';

		String str=new String(Descriptor);
		connect(str+co.getString(str));

		CO_Company comp,rcomp;
		for(int i=1;i<=30;i++)
		{
			//comp=new CO_Company((String)gi.company.get(i));
			//rcomp=new CO_Company((String)ro.company.get(i));
			comp = new CO_Company();
			rcomp = new CO_Company();
			CO_Company tmp = new CO_Company();
			//boolean change;
			Set set  = Manager.gi.company.entrySet();
			Set set1 = Manager.ro.company.entrySet();
			Iterator it = set1.iterator(),it1 = set.iterator();

			String Compname = "";

			while(it1.hasNext())
			{
				Map.Entry me = (Map.Entry)(it1.next());
				CO_Company c = new CO_Company(""+me.getValue());
				if(c.compId == i)
				{
					comp = c;
					break;
				}
			}

			while(it.hasNext())
			{
				Map.Entry me = (Map.Entry)(it.next());
				CO_Company c = new CO_Company(""+me.getValue());
				if(c.compId == i)
				{
					comp.change = c.change;
					comp.dayHigh = c.dayHigh;
					comp.dayLow = c.dayLow;
					comp.marketPrice = c.marketPrice;
				}
			}

			//comp.noOfShares = rcomp.noOfShares;
			//set = Man
			gi.company.put(new Integer(i), new String(comp.getString()));
			/*Set set2  = Manager.gi.company.entrySet();
			Iterator it2 = set2.iterator();
			int marketprice = 0;
			String Compname = "";
			while(it2.hasNext())
			{
				Map.Entry me2 = (Map.Entry)(it.next());
				CO_Company c = new CO_Company(""+me2.getValue());
				if(!(Manager.gi.company.containsKey(me2.get)))
				Manager.gi.company.put((Integer)(me2.getKey()), (String)(me2.getValue()));


			}*/


		}

		TradingBox.updateCompanyTable();
		FrontendBuy.resetBuy();
		FrontendSell.resetSell();
		InformationBox.updateCompanyListTable();

		TradingBox.updateCompanyTable();
		InformationBox.updateCompanyListTable();

	}
	public static void getMFinfo()
	{
//		connect();
		co=new CO_CO();
		co.action="GetMFInfo";
		//co.company=currentTradingMFId;  //how to set the current company in the Get Info..
		resetDescriptor();
		Descriptor[0]='1';
		Descriptor[1]='1';

		String str=new String(Descriptor);
		connect(str+co.getString(str));

		CO_MF mut,rmut;
		for(int i=1;i<=2;i++)
		{
			/*mut=new CO_MF((String)gi.MF.get(i));
			rmut=new CO_MF((String)ro.MF.get(i));*/
			mut=new CO_MF();
			//rmut=new CO_MF((String)ro.MF.get(i));
			Set set = Manager.gi.MF.entrySet();
			Set set1 = Manager.ro.MF.entrySet();
			Iterator it = set1.iterator();
			Iterator it1 = set1.iterator();
			String Compname = "";

			while(it1.hasNext())
			{
				Map.Entry me = (Map.Entry)(it1.next());
				CO_MF c = new CO_MF(""+me.getValue());
				if(c.MFId == i)
				{
					mut = c;
					break;
				}
			}

			while(it.hasNext())
			{
				Map.Entry me = (Map.Entry)(it.next());
				CO_MF c = new CO_MF(""+me.getValue());
				if(c.MFId == i)
				{
					mut.change = c.change;
					mut.NAV = c.NAV;
					mut.EntryLoad = c.EntryLoad;
					mut.ExitLoad = c.ExitLoad;
					mut.MFDayHigh = c.MFDayHigh;
					mut.MFDayLow = c.MFDayLow;
				}
			}
			gi.MF.put(new Integer(i), new String(mut.getString()));
		}
		Mutualfunds.updateMFTable();
		InformationBox.updateMFListTable();
	}

	public static void submitFAQ()
	{
		co = new CO_CO();
		co.action = "sendFAQ";
		co.user.userId = gi.user.userId;
		co.faqs.add(HelpBox.myFaq.getText());

		resetDescriptor();
		Descriptor[0]='1';
		Descriptor[1]='1';
		Descriptor[7]='1';

		String str=new String(Descriptor);
		ro = new CO_CO(str+co.getString(str));
		connect(str+co.getString(str));

		HelpBox.myFaq.setText("");
		HelpBox.myFaq.setText("");

	}
	public static void getFAQ()
	{
		//co = new CO_CO();
		co.action = "getFAQ";
		co.user.userId = gi.user.userId;
		resetDescriptor();
		Descriptor[0]='1';
		Descriptor[1]='1';

		String str=new String(Descriptor);
		connect(str+co.getString(str));

		gi.faqs.clear();
		gi.ans.clear();
		for(int i = 0;i < ro.faqs.size();i++)
		{
			gi.faqs.add(ro.faqs.get(i));
			gi.ans.add(ro.ans.get(i));
		}
		HelpBox.updateHelp();
	}

	public static void getRecentTransactions()
	{
		co = new CO_CO();
		co.action = "GetRecentTransactions";
		co.user.userId = gi.user.userId;
		resetDescriptor();
		Descriptor[0]='1';
		Descriptor[1]='1';

		String str=new String(Descriptor);
		connect(str+co.getString(str));

		gi.recent_trans = ro.recent_trans;
		PortfolioTab.UpdateTransactionTable();
	}
	public void getgraph(String type,int fromtime,int totime)
	{
		co = new CO_CO();
		if(type == "MF")
			co.action = "MFGetGraph";
		else if(type == "Sensex")
			co.action = "SensexGetGraph";
		else if(type == "Share")
			co.action = "SensexGetGraph";
		co.user.userId = gi.user.userId;
		resetDescriptor();
		co.fromtime=fromtime;
		co.totime=totime;
		Descriptor[0]='1';
		Descriptor[1]='1';
		Descriptor[17]='1';
		Descriptor[18]='1';
		String str=new String(Descriptor);
		connect(str+co.getString(str));
	}
	public static void gettopbids()
	{
		co=new CO_CO();
		co.action="getTopBids";
		co.user.userId = gi.user.userId;

		resetDescriptor();
		Descriptor[0]='1';
		Descriptor[1]='1';

		String str=new String(Descriptor);
		connect(str+co.getString(str));

		if(ro.user.userId==gi.user.userId && !ro.topBids.isEmpty())
		{
			gi.topBids.clear();
			CO_CO.setHashMap(gi.topBids, CO_CO.getHashMapString(ro.topBids));
			FrontendSell.updateTopBids();
			FrontendBuy.updateTopBids();
		}
	}

	public static void getbids()
	{
		co=new CO_CO();
		co.action="GetBids";
		co.user.userId = gi.user.userId;

		resetDescriptor();
		Descriptor[0]='1';
		Descriptor[1]='1';

		String str=new String(Descriptor);
		connect(str+co.getString(str));

		gi.user_Bid.clear();
		if(ro.user.userId==gi.user.userId && !ro.user_Bid.isEmpty())
		{
			CO_CO.setHashMap(gi.user_Bid, CO_CO.getHashMapString(ro.user_Bid));
			PortfolioTab.UpdateBidsTable();
		}
		PortfolioTab.UpdateBidsTable();

	}
	public static void getholdings()
	{
		co=new CO_CO();
		co.action="GetHoldings";
		co.user.userId = gi.user.userId;

		resetDescriptor();
		Descriptor[0]='1';
		Descriptor[1]='1';

		String str=new String(Descriptor);
		connect(str+co.getString(str));

		gi.user_Holding.clear();
		if(ro.user.userId==gi.user.userId && !ro.user_Holding.isEmpty())
		{
			CO_CO.setHashMap(gi.user_Holding, CO_CO.getHashMapString(ro.user_Holding));
			PortfolioTab.updateHoldingsTable();
		}

	}
	public static void gettoprankings()
	{
		co=new CO_CO();
		co.action="GetTopRankings";
		co.user.userId = gi.user.userId;
		//co.company=currentTradingCompId;  //how to set the current company in the Get Info..
		resetDescriptor();
		Descriptor[0]='1';
		Descriptor[1]='1';

		String str=new String(Descriptor);
		connect(str+co.getString(str));

		CO_CO.setArrayList(gi.rankings, CO_CO.getArrayListString(ro.rankings));
		HomeBox.UpdateRanksTable();

	}
}

