import java.sql.Timestamp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;


public class GameUpdater extends Thread
{
	public boolean running=false;
	public static int sleepInterval=10000;
	public static int i=0;
	public static Canvas splashImg;
	public static String splashText;
	public static boolean gettingFirstData;
	public static Shell shell=null;
	public void run()
	{
	//	System.out.println("TimerThread.java : In run()");
		try
		{
			while(!Application.rootShell.isDisposed())
			{
				Application.display.asyncExec(new Runnable() {
				public void run()
				{
					if(running)
					{
						reUpdateGame();
					}
				}});
				try
				{
					Thread.sleep(sleepInterval);
				}
				catch(InterruptedException e)  //so that when game ends doesn't throw exception
				{
					e.printStackTrace();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void reUpdateGame()
	{

		Manager.getCompanyInfo();
		Manager.getMFinfo();
/*
		Manager.gettopbids();
*/		Manager.getRecentTransactions();
		Manager.getbids();
		Manager.getholdings();


//		i++;
//		if(i%Manager.gi.graphUpdateFactor==0)
//		getNewerGraph();
		//if(i==0)
	}

	public static void firstTimeUpdate()
	{

		boolean doit=true;
		while(doit)
		{
			doit=false;

			shell = new Shell(SWT.ON_TOP|SWT.NO_TRIM);
			GridLayout gd=new GridLayout(1,true);
			gd.horizontalSpacing=0;
			gd.verticalSpacing=0;
			gd.marginHeight=0;
			gd.marginWidth=0;
			shell.setLayout(gd);
			splashImg=new Canvas(shell,SWT.NONE);
			final Image m = Application.loadImage("splash.gif");
			splashText="Welcome to Dalal Street!";
			splashImg.addPaintListener (new PaintListener () {
				public void paintControl (PaintEvent e) {
					e.gc.drawImage (m, 0, 0);
					e.gc.setForeground(new Color(Application.display,0,0,0));
					e.gc.drawText(splashText, 5, 338,true);
				}
			});

			GridData gdData = new GridData(SWT.FILL, SWT.FILL,false,false, 1, 1);
			splashImg.setLayoutData(gdData);
			gdData.heightHint=354;
			gdData.widthHint=500;

			ProgressBar bar = new ProgressBar (shell, SWT.SMOOTH);
			bar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));

			shell.pack();
			Rectangle splashRect = shell.getBounds();
			Rectangle displayRect = Application.display.getBounds();
			int x = (displayRect.width - splashRect.width) / 2;
			int y = (displayRect.height - splashRect.height) / 2;
			shell.setLocation(x, y);
			shell.open();

			int k=0,max=bar.getMaximum()/4;
			gettingFirstData=true;
			LoginBox.buttonLogin.setSelection(false);
			System.out.println("GameUpdater.java before update : Debug Logout :" + Manager.gi.user.getString());
			bar.setSelection(max*k);k++;splashText="Logging in...";splashImg.redraw();splashImg.update();
			Manager.getCompanyInfo();
			Manager.getMFinfo();

			bar.setSelection(max*k);k++;splashText="Loading your Portfolio...";splashImg.redraw();splashImg.update();
			Manager.getFAQ();
			Manager.gettoprankings();

			bar.setSelection(max*k);k++;splashText="Loading Market Status...";splashImg.redraw();splashImg.update();

			Manager.gettopbids();

			Manager.getRecentTransactions();

			bar.setSelection(max*k);k++;splashText="Loading Share Prices...";splashImg.redraw();splashImg.update();
////			Manager.query("getallgraph"); if(!Manager.keepLoggingIn) break;

				Manager.getholdings();
				Manager.getbids();

			bar.setSelection(max*k);splashText="Initializing Game...";splashImg.redraw();splashImg.update();
			gettingFirstData=false;
			Application.updaterThread.running=true;
		shell.close();
		}
		gettingFirstData=false;
	}

/*	public void getNewerGraph()
	{
		Manager.resetco();
		for(int i=1;i<=30;i++)
		{
			CO_Company a = new CO_Company();
			a.toTime = new Timestamp(1);//TimerThread.currentTime - TimerThread.currentTime;
			a.fromTime = new Timestamp(TradingBox.graphManager.graph.lastTime.getTime() - TimerThread.currentTime.getTime());
			//if(i==3) System.out.println("\nGameUpdater.java : for comp3 :"+TradingBox.graphManager.graph.lastTime.toString());
			Manager.co.company.put(new Integer(i),a.getString());
		}
		Manager.co.toTime=new Timestamp(1);//TimerThread.currentTime - TimerThread.currentTime.getTime();
		Manager.co.fromTime=new Timestamp(HomeBox.graphManager.graph.lastTime.getTime() - TimerThread.currentTime.getTime());
		Manager.query("getgraph");
	}*/
}

/* Copy Of Previous Code
 *
 *
 import java.sql.Timestamp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;


public class GameUpdater extends Thread
{
	public boolean running=true;
	public static int sleepInterval=10000;
	public static int i=0;
	public static Canvas splashImg;
	public static String splashText;
	public static boolean gettingFirstData;
	public static Shell shell=null;
	public void run()
	{
	//	System.out.println("TimerThread.java : In run()");
		try
		{
			while(!Application.rootShell.isDisposed())
			{
				Application.display.asyncExec(new Runnable() {
				public void run()
				{
					if(running)
					{
						reUpdateGame();
					}
				}});
				try
				{
					Thread.sleep(sleepInterval);
				}
				catch(InterruptedException e)  //so that when game ends doesn't throw exception
				{
					e.printStackTrace();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void reUpdateGame()
	{
//		Manager.resetco();

//		i++;
//		if(i%Manager.gi.graphUpdateFactor==0)
//		getNewerGraph();
		//if(i==0)
//		Manager.query("update");

		/*
		Manager.query("getcompanyinfo");
		Manager.query("getholdings");
		Manager.query("getbids");
		Manager.query("gettopbids");
		Manager.query("getrankings");
		*/
/*	}
*
*	public static void firstTimeUpdate()
*	{
*
		boolean doit=true;
		while(doit)
		{
			doit=false;

			shell = new Shell(SWT.ON_TOP|SWT.NO_TRIM);
			GridLayout gd=new GridLayout(1,true);
			gd.horizontalSpacing=0;
			gd.verticalSpacing=0;
			gd.marginHeight=0;
			gd.marginWidth=0;
			shell.setLayout(gd);
			splashImg=new Canvas(shell,SWT.NONE);
			final Image m = Application.loadImage("splash.gif");
			splashText="Welcome to Dalal Street!";
			splashImg.addPaintListener (new PaintListener () {
				public void paintControl (PaintEvent e) {
					e.gc.drawImage (m, 0, 0);
					e.gc.setForeground(new Color(Application.display,0,0,0));
					e.gc.drawText(splashText, 5, 338,true);
				}
			});

			GridData gdData = new GridData(SWT.FILL, SWT.FILL,false,false, 1, 1);
			splashImg.setLayoutData(gdData);
			gdData.heightHint=354;
			gdData.widthHint=500;

			ProgressBar bar = new ProgressBar (shell, SWT.SMOOTH);
			bar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));

			shell.pack();
			Rectangle splashRect = shell.getBounds();
			Rectangle displayRect = Application.display.getBounds();
			int x = (displayRect.width - splashRect.width) / 2;
			int y = (displayRect.height - splashRect.height) / 2;
			shell.setLocation(x, y);
			shell.open();

			int k=0,max=bar.getMaximum()/4;
			gettingFirstData=true;
			LoginBox.buttonLogin.setSelection(false);
			System.out.println("GameUpdater.java before update : Debug Logout :" + Manager.gi.user.getString());
			bar.setSelection(max*k);k++;splashText="Logging in...";splashImg.redraw();splashImg.update();
			Manager.getCompanyInfo();
			Manager.getMFinfo();

			bar.setSelection(max*k);k++;splashText="Loading your Portfolio...";splashImg.redraw();splashImg.update();
			Manager.getFAQ();

			bar.setSelection(max*k);k++;splashText="Loading Market Status...";splashImg.redraw();splashImg.update();
////		Manager.getTopbids();
////		Manager.getRecentTransactions();

			bar.setSelection(max*k);k++;splashText="Loading Share Prices...";splashImg.redraw();splashImg.update();
////			Manager.query("getallgraph"); if(!Manager.keepLoggingIn) break;
////			Manager.getBids();
////			Manager.getHoldings();

			bar.setSelection(max*k);splashText="Initializing Game...";splashImg.redraw();splashImg.update();
			gettingFirstData=false;

		shell.close();
		}
		gettingFirstData=false;
	}

/*	public void getNewerGraph()
	{
		Manager.resetco();
		for(int i=1;i<=30;i++)
		{
			CO_Company a = new CO_Company();
			a.toTime = new Timestamp(1);//TimerThread.currentTime - TimerThread.currentTime;
			a.fromTime = new Timestamp(TradingBox.graphManager.graph.lastTime.getTime() - TimerThread.currentTime.getTime());
			//if(i==3) System.out.println("\nGameUpdater.java : for comp3 :"+TradingBox.graphManager.graph.lastTime.toString());
			Manager.co.company.put(new Integer(i),a.getString());
		}
		Manager.co.toTime=new Timestamp(1);//TimerThread.currentTime - TimerThread.currentTime.getTime();
		Manager.co.fromTime=new Timestamp(HomeBox.graphManager.graph.lastTime.getTime() - TimerThread.currentTime.getTime());
		Manager.query("getgraph");
	}*/
//}

/* Copy Of Previous Code
 *
 *
 import java.sql.Timestamp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;


public class GameUpdater extends Thread
{
	public boolean running=true;
	public static int sleepInterval=10000;
	public static int i=0;
	public static Canvas splashImg;
	public static String splashText;
	public static boolean gettingFirstData;
	public static Shell shell=null;
	public void run()
	{
	//	System.out.println("TimerThread.java : In run()");
		try
		{
			while(!Application.rootShell.isDisposed())
			{
				Application.display.asyncExec(new Runnable() {
				public void run()
				{
					if(running)
					{
						reUpdateGame();
					}
				}});
				try
				{
					Thread.sleep(sleepInterval);
				}
				catch(InterruptedException e)  //so that when game ends doesn't throw exception
				{
					e.printStackTrace();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void reUpdateGame()
	{
//		Manager.resetco();

//		i++;
//		if(i%Manager.gi.graphUpdateFactor==0)
//		getNewerGraph();
		//if(i==0)
//		Manager.query("update");

		/*
		Manager.query("getcompanyinfo");
		Manager.query("getholdings");
		Manager.query("getbids");
		Manager.query("gettopbids");
		Manager.query("getrankings");
		*/
/*	}
*
*	public static void firstTimeUpdate()
*	{
*
		boolean doit=true;
		while(doit)
		{
			doit=false;
//			Manager.keepLoggingIn=true;
//			Manager.resetco();
			/*
			Manager.query("getcompanyinfo");
			Manager.query("getholdings");
			Manager.query("getbids");
			Manager.query("gettopbids");
			Manager.query("getrankings");
			*/
			//WindowManager.status.setText("Logging in...");
/*
*			shell = new Shell(SWT.ON_TOP|SWT.NO_TRIM);
*			GridLayout gd=new GridLayout(1,true);
*			gd.horizontalSpacing=0;
*			gd.verticalSpacing=0;
*			gd.marginHeight=0;
			gd.marginWidth=0;
			shell.setLayout(gd);
			//shell.setBounds(200, 200, 0, 0);
		//	System.out.println("First time update");
			splashImg=new Canvas(shell,SWT.NONE);
			final Image m = Application.loadImage("splash.gif");
			splashText="Welcome to Dalal Street!";
			splashImg.addPaintListener (new PaintListener () {
				public void paintControl (PaintEvent e) {
					e.gc.drawImage (m, 0, 0);
					e.gc.setForeground(new Color(Application.display,0,0,0));
					e.gc.drawText(splashText, 5, 338,true);
				}
			});

			GridData gdData = new GridData(SWT.FILL, SWT.FILL,false,false, 1, 1);
			splashImg.setLayoutData(gdData);
			gdData.heightHint=354;
			gdData.widthHint=500;

			ProgressBar bar = new ProgressBar (shell, SWT.SMOOTH);
			bar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));

			shell.pack();
			Rectangle splashRect = shell.getBounds();
			Rectangle displayRect = Application.display.getBounds();
			int x = (displayRect.width - splashRect.width) / 2;
			int y = (displayRect.height - splashRect.height) / 2;
			shell.setLocation(x, y);
			shell.open();

			int k=0,max=bar.getMaximum()/4;
			gettingFirstData=true;
			LoginBox.buttonLogin.setSelection(false);
			bar.setSelection(max*k);k++;splashText="Logging in...";splashImg.redraw();splashImg.update();
			System.out.println("GameUpdater.java before update : Debug Logout :" + Manager.gi.user.getString());

//			Manager.query("update");
			//if(!Manager.keepLoggingIn) break;
			System.out.println("GameUpdater.java after update: Debug Logout :" + Manager.gi.user.getString());
		//	System.out.println("GameUpdater.java : ranking size : "+Manager.gi.rankings.size());
			bar.setSelection(max*k);k++;splashText="Loading your Portfolio...";splashImg.redraw();splashImg.update();
//			Manager.query("getfaqs"); if(!Manager.keepLoggingIn) break;
			Manager.getFAQ();
			bar.setSelection(max*k);k++;splashText="Loading Market Status...";splashImg.redraw();splashImg.update();
//			Manager.query("getnews"); if(!Manager.keepLoggingIn) break;
			Manager.getCompanyInfo();
			Manager.getMFinfo();
			bar.setSelection(max*k);k++;splashText="Loading Share Prices...";splashImg.redraw();splashImg.update();
//			Manager.query("getallgraph"); if(!Manager.keepLoggingIn) break;
////			Manager.getBids();
////			Manager.getHoldings();
////			Manager.getTopbids();

			bar.setSelection(max*k);splashText="Initializing Game...";splashImg.redraw();splashImg.update();
			gettingFirstData=false;
//			WindowManager.drawGUI();
			{

				//CompleteNewsTable.news.newEntry(row, 1);
				//WindowManager.lb.pack();
				//WindowManager.lb.m2 = new Image(root.display , m.getImageData().scaledTo(677,root.display.getClientArea().height));
				//WindowManager.lb.setBackgroundImage(WindowManager.lb.m2);

//				HomeBox.graphManager.putNewGraph(Manager.gi.priceMap);
//				TradingBox.graphManager.putNewGraph((new CO_Company((String)Manager.gi.company.get(new Integer(Manager.currentTradingCompId)))).priceMap);
//				TradingBox.graphManager.update();

//				TradingBox.updateCompanyTable();
//				Mutualfunds.updateMFTable();


//				CompanyTable.updateTable();
//				Ticker.getNewsString();

//				PortfolioBox.updateHoldings();
							//HeaderBox.updateHeader(); -- included in networth update
//				UpdateNetworth.update();
//				FrontendSell.resetSell();
//				FrontendBuy.resetBuy();

//				Portfolio.updateBids();

//				Top5Buy.updateTopBids();
//				Top5Sell.updateTopBids();
				//System.out.println("GameUpdater.java : ranking size : "+Manager.gi.rankings.size());
//				Top10Rankers.updateRankings();

//				CompanyNewsTable.updateCompanyNews();
//				CompleteNewsTable.updateDailyNews();
//				Profile.updateProfileList();
//				HelpBox.updateHelp();
			}

		shell.close();
//		Manager.query("update");
		//System.out.println("GameUpdater.java : Finished Update()"+Thread.currentThread().getName());
		//Finished drawing GUI, start updating values
//		Application.updaterThread.running=true;
//		Ticker.running=true;
		}
		gettingFirstData=false;
	}

/*	public void getNewerGraph()
	{
		Manager.resetco();
		for(int i=1;i<=30;i++)
		{
			CO_Company a = new CO_Company();
			a.toTime = new Timestamp(1);//TimerThread.currentTime - TimerThread.currentTime;
			a.fromTime = new Timestamp(TradingBox.graphManager.graph.lastTime.getTime() - TimerThread.currentTime.getTime());
			//if(i==3) System.out.println("\nGameUpdater.java : for comp3 :"+TradingBox.graphManager.graph.lastTime.toString());
			Manager.co.company.put(new Integer(i),a.getString());
		}
		Manager.co.toTime=new Timestamp(1);//TimerThread.currentTime - TimerThread.currentTime.getTime();
		Manager.co.fromTime=new Timestamp(HomeBox.graphManager.graph.lastTime.getTime() - TimerThread.currentTime.getTime());
		Manager.query("getgraph");
	}*/
/*
	}
/*
 *
 *
*/
