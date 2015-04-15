/******************************************************************/
////
////	Class Description	:	Window Manager, to place the different
////							components of the Application in place
////	Components			:	Header, LeftPane, TabFolder, TickerBar,
////	Created By			:	Abhishek Jain
////	Created on			:	2007-10-06
////	Last Modification 	:
////	Last Modified By 	:
////	Last Modified on	:
////
/******************************************************************/

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class WindowManager {

	//// Basic Components of the Application
	static Composite compositeHeader;
	static Composite compositeLeftPane;
	static Composite compositeTabsFolder;
	public static Composite compositeTickerBar;

	//// Left Pane Components
	public static StyledText email,networth,mcapital,scapital,wcapital,rank,college;

	//// Tab Folders Components
	static CTabFolder tabFolder;
	static CTabItem loginTab;
	static CTabItem homeTab;
	static CTabItem tradingTab;
	static CTabItem mutualFundsTab;
	static CTabItem portfolioTab;
	static CTabItem informationTab;
	static CTabItem newsTab;
	static CTabItem settingsTab;
	static CTabItem helpTab;

	//// Theme Components
	public static Font bigSystemFont;
	public static Font mediumSystemFont;
	public static Font smallSystemFont;

	public static StyledText name;
	public static Button buttonLogout,buttonRefresh;

	public WindowManager(Shell rootShell)
	{
	//// Initialize Theme
		new ThemeManager();
		//// Initialize Fonts
			Font systemFont = Application.display.getSystemFont();
			FontData[] fontData = systemFont.getFontData();
			fontData[0].setHeight(fontData[0].getHeight()-1);
			smallSystemFont = new Font(Application.display, fontData);
			fontData[0].setHeight(fontData[0].getHeight()+1);

			for (int i = 0; i < fontData.length; i++) {
				fontData[i].setHeight(fontData[i].getHeight()+1);
			}
			mediumSystemFont = new Font(Application.display, fontData);

			fontData[0].setHeight(fontData[0].getHeight()+15);
			bigSystemFont = new Font(Application.display, fontData);
			fontData[0].setHeight(fontData[0].getHeight()-15);

			for (int i = 0; i < fontData.length; i++) {
				fontData[i].setHeight(fontData[i].getHeight()+3);
			}
			Font bigSystemFont = new Font(Application.display, fontData);

	//// Initialize Shell Properties
		rootShell.setText("Dalal Street : Pragyan 2008");
		rootShell.setLayout(new GridLayout(2,false));

	//// Initialize Components
		/*********************Header Starts Here*****************************/
		compositeHeader = new Composite(rootShell, SWT.NONE);
		GridData headerGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		headerGridData.horizontalSpan=2;
		headerGridData.grabExcessVerticalSpace=false;
		compositeHeader.setLayoutData(headerGridData);
		compositeHeader.setLayout(new GridLayout(3,false));
		Image header1 = Application.loadImage("header.jpg");
		compositeHeader.setBackgroundImage(header1);
		compositeHeader.setBackgroundMode(SWT.INHERIT_FORCE);

		//// Initialize Header Components
			GridData gridDataHeader = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
			gridDataHeader.grabExcessVerticalSpace=false;
			gridDataHeader.horizontalAlignment=SWT.BEGINNING;

			name=new StyledText(compositeHeader,SWT.WRAP|SWT.BOLD);
			name.setEditable(false);
			name.setText("Welcome,                          ");
			name.setEnabled(true);
			name.setLayoutData(gridDataHeader);

			buttonRefresh = new Button(compositeHeader, SWT.PUSH);
			buttonRefresh.setLayoutData(new GridData(SWT.RIGHT,SWT.NONE,false,false,1,1));
			buttonRefresh.setImage(Application.loadImage("refresh.png"));
			buttonRefresh.setText(" &Refresh ");
			buttonRefresh.setEnabled(false);
			buttonRefresh.setVisible(false);

			buttonLogout = new Button(compositeHeader, SWT.PUSH);
			buttonLogout.setLayoutData(new GridData(SWT.RIGHT,SWT.NONE,false,false,1,1));
			buttonLogout.setImage(Application.loadImage("logout.png"));
			buttonLogout.setText(" &Logout ");
			buttonLogout.setEnabled(false);
			buttonLogout.setVisible(false);

			    buttonLogout.addSelectionListener(new SelectionAdapter(){
		         	public void widgetSelected(SelectionEvent e)
		         	{
		         		MessageBox m = new MessageBox(Application.rootShell,SWT.OK|SWT.CANCEL|SWT.ICON_QUESTION);
		         			m.setMessage("Are You Sure You Want To Logout?");
		         			m.setText("Logout");
		         			if ( m.open()==SWT.OK)
		         			{
		         				Manager.logout();
		         			}
		         	}
				});
				buttonRefresh.addSelectionListener(new SelectionAdapter(){
		         	public void widgetSelected(SelectionEvent e)
		         	{
		         		//root.display.asyncExec(new Runnable(){public void run(){
		         		//	Manager.query("update");
		         		//}
		         		//});
		         	}

				});
		/*********************Header Ends Here*****************************/

		/*********************Left Pane Start Here*****************************/
		compositeLeftPane = new Composite(rootShell, SWT.BORDER);
		GridData LeftGridData = new GridData(GridData.FILL_VERTICAL);
		LeftGridData.grabExcessVerticalSpace = true;
		compositeLeftPane.setLayoutData(LeftGridData);
		compositeLeftPane.setLayout(new GridLayout(1,true));

		compositeLeftPane.setBackgroundImage(header1);
		compositeLeftPane.setBackgroundMode(SWT.INHERIT_FORCE);

		//// Load Back Ground Image for the Tab.
		Composite collegeLogo = new Composite(compositeLeftPane,SWT.NONE);
		collegeLogo.setLayoutData(new GridData(GridData.FILL_BOTH));
		Image m = Application.loadImage("dalalleft.jpg");
//		int w=compositeLeftPane.co
		//m = new Image(Application.display , m.getImageData().scaledTo(75,120 ));
		collegeLogo.setBackgroundImage(m);
/*
		Composite pragyanLogo = new Composite(compositeLeftPane,SWT.NONE);
		pragyanLogo.setLayoutData(new GridData(GridData.FILL_BOTH));
		Image m1 = Application.loadImage("pragyan_logo.png");
		//m1 = new Image(Application.display , m1.getImageData().scaledTo(110,200 ));
		pragyanLogo.setBackgroundImage(m1);
*/
		//// Initialize Left Pane Compoents

			rank=new StyledText(compositeLeftPane,SWT.WRAP|SWT.BOLD);
			rank.setLayoutData(new GridData(SWT.FILL,SWT.DEFAULT,true,false,1,1));
			rank.setEditable(false);
			rank.setText("Rank : \n1");
			rank.setEnabled(true);
			rank.setVisible(false);

			networth=new StyledText(compositeLeftPane,SWT.WRAP|SWT.BOLD);
			networth.setLayoutData(new GridData(SWT.FILL,SWT.DEFAULT,true,false,1,1));
			networth.setEditable(false);
			networth.setText("\nNetworth : \n200000.00");
			networth.setEnabled(true);
			networth.setVisible(false);

			wcapital=new StyledText(compositeLeftPane,SWT.WRAP|SWT.BOLD);
			wcapital.setLayoutData(new GridData(SWT.FILL,SWT.DEFAULT,true,false,1,1));
			wcapital.setEditable(false);
			wcapital.setText("Withdrawable Capital : \n200000.00");
			wcapital.setEnabled(true);
			wcapital.setVisible(false);

			scapital=new StyledText(compositeLeftPane,SWT.WRAP|SWT.BOLD);
			scapital.setLayoutData(new GridData(SWT.FILL,SWT.DEFAULT,true,false,1,1));
			scapital.setEditable(false);
			scapital.setText("Share Capital : \n0.00");
			scapital.setEnabled(true);
			scapital.setVisible(false);

			mcapital=new StyledText(compositeLeftPane,SWT.WRAP|SWT.BOLD);
			mcapital.setLayoutData(new GridData(SWT.FILL,SWT.DEFAULT,true,false,1,1));
			mcapital.setEditable(false);
			mcapital.setText("Mutual Fund Capital : \n0.00");
			mcapital.setEnabled(true);
			mcapital.setVisible(false);
/*
			Composite temenosLogo = new Composite(compositeLeftPane,SWT.NONE);
			temenosLogo.setLayoutData(new GridData(GridData.FILL_BOTH));
			Image m2 = Application.loadImage("temenos_logo.gif");
			//m2 = new Image(Application.display , m2.getImageData().scaledTo(110,100 ));
			temenosLogo.setBackgroundImage(m2);
*/
		/*********************Left Pane Ends Here*****************************/


		/*********************Tabs Start Here*****************************/
		compositeTabsFolder = new Composite(rootShell, SWT.FILL);
		GridData tabGridData = new GridData(GridData.FILL_BOTH);
		tabGridData.grabExcessVerticalSpace = true;
		compositeTabsFolder.setLayoutData(tabGridData);
		compositeTabsFolder.setLayout(new FillLayout());
		tabFolder = new CTabFolder(compositeTabsFolder, SWT.BORDER);
		tabFolder.setSelectionBackground(ThemeManager.currentTheme.colorGradient,ThemeManager.currentTheme.colorPercent, true);
		tabFolder.setSelectionForeground(ThemeManager.currentTheme.colorTabForeground);
		tabFolder.setFont(bigSystemFont);
		tabFolder.setSimple(false); //For curved tabs
		tabFolder.setBackground(ThemeManager.currentTheme.colorTabBackground);

		//// Initialize Tabs Here
		drawFirst();
		tabFolder.setSelection(loginTab);
		/*********************Tabs End Here*****************************/


		/*********************Ticker Starts Here*****************************/
		compositeTickerBar = new Composite(Application.rootShell, SWT.FILL);
			GridData gridDataCompositeTickerBar = new GridData(SWT.DEFAULT,Application.display.getSystemFont().getFontData()[0].getHeight()+10);
			gridDataCompositeTickerBar.horizontalSpan=2;
			gridDataCompositeTickerBar.horizontalAlignment=SWT.FILL;
		compositeTickerBar.setLayoutData(gridDataCompositeTickerBar);
		compositeTickerBar.setLayout(new FillLayout());
		/*********************Ticker Ends Here*****************************/

	}

	public static void drawFirst() {
		// TODO Auto-generated method stub
		/*********************Login Tab Starts Here*****************************/
		if(loginTab==null || loginTab.isShowing()==false)
		{
			/*********************Login Tab Start Here*****************************/
			loginTab = new CTabItem(tabFolder, SWT.NONE);
			loginTab.setText	(" &Login   ");
			Composite compositeLogin=new Composite(tabFolder,SWT.NONE);
			new LoginBox(compositeLogin,SWT.FILL);
			loginTab.setControl(compositeLogin);
			loginTab.setImage(Application.loadImage("login.png"));
			/*********************Login Tab Ends Here*****************************/

			/*********************Settings Tab Start Here*****************************/
			settingsTab = new CTabItem(tabFolder, SWT.NONE);
			settingsTab.setText	(" &Settings   ");
			Composite compositeSettings=new Composite(tabFolder,SWT.NONE);
			new SettingsBox(compositeSettings);
			settingsTab.setControl(compositeSettings);
			settingsTab.setImage(Application.loadImage("settings.png"));
			/*********************Settings Tab Ends Here*****************************/

			/*********************Help Tab Start Here*****************************/
			helpTab = new CTabItem(tabFolder, SWT.NONE);
			helpTab.setText		(" H&elp   ");
			Composite compositeHelp=new Composite(tabFolder,SWT.NONE);
			new HelpBox(compositeHelp);
			helpTab.setControl(compositeHelp);
			helpTab.setImage(Application.loadImage("help.png"));
			/*********************Help Tab Ends Here*****************************/
		}
		tabFolder.setSelection(loginTab);
	}
	public static void drawGUI()
	{
		helpTab.dispose();
		settingsTab.dispose();
		//HeaderBox.updateHeader();
		//ticker.update();
		//System.out.println("Inside UpdateS");
		//Populate more tabs

		/*********************Home Tab Start Here*****************************/
		homeTab = new CTabItem(tabFolder, SWT.NONE);
		homeTab.setText	(" &Home   ");
		Composite compositeHome=new Composite(tabFolder,SWT.NONE);
		new HomeBox(compositeHome,SWT.FILL);
		homeTab.setControl(compositeHome);
		homeTab.setImage(Application.loadImage("home.png"));
		/*********************Home Tab Ends Here*****************************/


		/*********************Trading Tab Start Here*****************************/
		tradingTab = new CTabItem(tabFolder, SWT.NONE);
		tradingTab.setText	(" &Trading   ");
		Composite compositeTrading=new Composite(tabFolder,SWT.NONE);
		new TradingBox(compositeTrading,SWT.FILL);
		tradingTab.setControl(compositeTrading);
		tradingTab.setImage(Application.loadImage("trading.png"));
		/*********************Trading Tab Ends Here*****************************/

		/*********************Mutual Funds Tab Start Here*****************************/
		mutualFundsTab = new CTabItem(tabFolder, SWT.NONE);
		mutualFundsTab.setText	(" Mutual Funds   ");
		Composite compositeMutualFunds=new Composite(tabFolder,SWT.NONE);
		new Mutualfunds(compositeMutualFunds,SWT.FILL);
		mutualFundsTab.setControl(compositeMutualFunds);
		/*********************Mutual Funds Tab Ends Here*****************************/

		/*********************Portfolio Tab Start Here*****************************/
		portfolioTab = new CTabItem(tabFolder, SWT.NONE);
		portfolioTab.setText	(" Portfolio   ");
		Composite compositePortfolio=new Composite(tabFolder,SWT.NONE);
		new PortfolioTab(compositePortfolio,SWT.FILL);
		portfolioTab.setControl(compositePortfolio);
		portfolioTab.setImage(Application.loadImage("portfolio.png"));
		/*********************Portfolio Tab Ends Here*****************************/

		/*********************Information Tab Start Here*****************************/
		informationTab = new CTabItem(tabFolder, SWT.NONE);
		informationTab.setText	(" &Details   ");
		Composite compositeInformation=new Composite(tabFolder,SWT.NONE);
		new InformationBox(compositeInformation,SWT.FILL);
		informationTab.setControl(compositeInformation);
		/*********************Information Tab Ends Here*****************************/

		/*********************News Tab Start Here*****************************/
		newsTab = new CTabItem(tabFolder, SWT.NONE);
		newsTab.setText	(" &News   ");
		Composite compositeNews=new Composite(tabFolder,SWT.NONE);
		new NewsTab(compositeNews,SWT.FILL);
		newsTab.setControl(compositeNews);
		/*********************News Tab Ends Here*****************************/

		loginTab.dispose();

		//Redraw the previous tabs
		/*********************Settings Tab Start Here*****************************/
		settingsTab = new CTabItem(tabFolder, SWT.NONE);
		settingsTab.setText	(" &Settings   ");
		Composite compositeSettings=new Composite(tabFolder,SWT.NONE);
		new SettingsBox(compositeSettings);
		settingsTab.setControl(compositeSettings);
		settingsTab.setImage(Application.loadImage("settings.png"));
		/*********************Settings Tab Ends Here*****************************/

		/*********************Help Tab Start Here*****************************/
		helpTab = new CTabItem(tabFolder, SWT.NONE);
		helpTab.setText		(" H&elp   ");
		Composite compositeHelp=new Composite(tabFolder,SWT.NONE);
		new HelpBox(compositeHelp);
		helpTab.setControl(compositeHelp);
		helpTab.setImage(Application.loadImage("help.png"));
		/*********************Help Tab Ends Here*****************************/

		updateHeader();
		updateLeftPane();
		MoneyManager.updatedata();
//		Application.updaterThread.running=false;
		TimerThread.running=false;
//		Ticker.prices="";
//		WindowManager.ticker.tickerClear();
//		Ticker.running=false;
//		WindowManager.ticker.tickerReDraw();
//		WindowManager.ticker.removePaintListener(WindowManager.ticker.paintListener);
		//GameUpdater.firstTimeUpdate();
	}
	public static void redrawGUI()
	{
		if(homeTab!=null && !homeTab.isDisposed())
		{
			tradingTab.dispose();
			mutualFundsTab.dispose();
			portfolioTab.dispose();
			informationTab.dispose();
			newsTab.dispose();
			helpTab.dispose();
			settingsTab.dispose();

			//Populate more tabs
			/*********************Login Tab Start Here*****************************/
			loginTab = new CTabItem(tabFolder, SWT.NONE);
			loginTab.setText	(" &Login   ");
			Composite compositeLogin=new Composite(tabFolder,SWT.NONE);
			new LoginBox(compositeLogin,SWT.FILL);
			loginTab.setControl(compositeLogin);
			loginTab.setImage(Application.loadImage("login.png"));
			/*********************Login Tab Ends Here*****************************/

			homeTab.dispose();

			//Redraw the previous tabs
			/*********************Settings Tab Starts Here*****************************/
			settingsTab = new CTabItem(tabFolder, SWT.NONE);
			settingsTab.setText	(" &Settings   ");
			settingsTab.setImage(Application.loadImage("settings.png"));
			Composite compositeSettings=new Composite(tabFolder,SWT.FILL);
			new SettingsBox(compositeSettings);
			settingsTab.setControl(compositeSettings);
			/*********************Settings Tab Ends Here*****************************/

			/*********************Help Tab Starts Here*****************************/
			helpTab = new CTabItem(tabFolder, SWT.NONE);
			helpTab.setText		(" &Help   ");
			helpTab.setImage(Application.loadImage("help.png"));
			Composite compositeHelp=new Composite(tabFolder,SWT.FILL);
			new HelpBox(compositeHelp);
			helpTab.setControl(compositeHelp);
			/*********************Help Tab Ends Here*****************************/

			reupdateHeader();
			reupdateLeftPane();
//			Application.updaterThread.running=false;
			TimerThread.running=false;
//			Ticker.prices="";
//			WindowManager.ticker.tickerClear();
//			Ticker.running=false;
//			WindowManager.ticker.tickerReDraw();
//			WindowManager.ticker.removePaintListener(WindowManager.ticker.paintListener);
		}
	}

	public static void updateHeader()
	{

		name.setText("Welcome , "+Manager.gi.user.userName);
		/*StyleRange style1 = new StyleRange();
		style1.start = 0;		style1.length = 7;		style1.fontStyle = SWT.BOLD;
		name.setStyleRange(style1);
*/
		name.setVisible(true);
		buttonLogout.setEnabled(true);
		buttonLogout.setVisible(true);
		buttonRefresh.setEnabled(true);
		buttonRefresh.setVisible(true);

	}
	public static void reupdateHeader()
	{
		name.setText("Welcome, ");

		buttonLogout.setEnabled(false);
		buttonLogout.setVisible(false);
		buttonRefresh.setEnabled(false);
		buttonRefresh.setVisible(false);
	}

	public static void updateLeftPane()
	{
		rank.setText("Rank : \n"+Manager.gi.user.ranking);
		rank.setVisible(true);

		networth.setText("Networth : \n"+CO_CO.formatMoney(Manager.gi.user.netWorth));
		networth.setVisible(true);

		wcapital.setText("Withdrawable Capital : \n"+CO_CO.formatMoney(Manager.gi.user.cashWithdrawal));
		wcapital.setVisible(true);

		scapital.setText("Share Capital : \n"+CO_CO.formatMoney(Manager.gi.user.cashShare));
		scapital.setVisible(true);

		mcapital.setText("Mutual Fund Capital : \n"+CO_CO.formatMoney(Manager.gi.user.cashMF));
		mcapital.setVisible(true);
	}
	public static void reupdateLeftPane()
	{
		rank.setText("Rank : \n1");
		rank.setVisible(false);

		networth.setText("\nNetworth : \n200000.00");
		networth.setVisible(false);

		wcapital.setText("Withdrawable Capital : \n200000.00");
		wcapital.setVisible(false);

		scapital.setText("Share Capital : \n0.00");
		scapital.setVisible(false);

		mcapital.setText("Mutual Fund Capital : \n0.00");
		mcapital.setVisible(false);
	}


}

