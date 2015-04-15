import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
/**
 * The class whose resultant objects give the complete graph.
 * By complete graph we mean the the graph + zoom in/zoom out + dnd + scale + graphbackground
 * i.e. the complete package
 * @author Sahil Ahuja
 */
public class SliderMain extends Canvas //implements Runnable
{
	/** Contains the main graph
	 * Only the actual graph.*/
	private Image gim;
	private GC gcgim;
	public Timestamp timeMin,timeMax,timeRange,origtimeMin,origtimeRange;
	int priceMin,priceMax,priceRange,origpriceMin,origpriceRange;
	/** Used by GraphManager if more data is required from the server
	 */
	public Timestamp firstTime,lastTime;
	/**A variable <b>Calculated by client</b> that tells that the
	 * server has no more values to send
	 */
	public boolean dataNotAvailable=false;
	final  DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT);
	/**
	 * PriceMap pm
	 * Using a TreeMap for price map ensures that all (date/price) entries are sorted by their keys (date)
	 * So to get the firt or last date we can simply use pm.firstKey() and pm.lastKey()
	 */
	public TreeMap pm;

	int lx1,ly1,lx2,ly2,lmid;
	int rx1,ry1,rx2,ry2,rmid;
	public int origin;
	TreeMap Tmap;
	public SliderMain(Composite parent,TreeMap tmap)
	{
		super(parent,SWT.FILL | SWT.DOUBLE_BUFFERED | SWT.INHERIT_FORCE);
		//setBackground(this.getDisplay().getSystemColor(SWT.COLOR_GRAY));
		setBackground(new Color(getDisplay(),32,0,0));

		//this.setSize(380, 240);
		this.setSize(410, 20);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.minimumHeight=20;
		data.minimumWidth=410;
		setLayoutData(data);

		lx1 = 50;
		lx2 = lx1;
		ly1 = 0;
		ly2 = 40;
		Tmap = tmap;
		rx1 = 70;
		rx2 = rx1;
		ry1 = 0;
		ry2 = 40;
		origin = 0;

		timeMin = new Timestamp(TimerThread.currentTime.getTime()-15*60000);
		timeMax = new Timestamp(TimerThread.currentTime.getTime());
		System.out.println(timeMin.toString() + ":" + timeMax.toString());
		addPaintListener(drawListener);


//		pm=priceMap;
//		firstTime=new Timestamp(TimerThread.currentTime.getTime());
//		lastTime=new Timestamp(TimerThread.currentTime.getTime());
		/**
		 * To zoom in and out of graph simply have to change timeRange,timeMin and priceRange,priceMin
		 * without calling decideRanges();
		 *
		 * And to move around have to change timeMin and priceMin
		 */
//		decideRanges();
	}

	PaintListener drawListener = new PaintListener()
	{
		public void paintControl(PaintEvent pe)
		{
			Display disp = pe.display;
			createGraph(disp);
			GC gc = pe.gc;
			gc.setBackground(new Color(disp,43,42,83));
			gc.setFont(WindowManager.smallSystemFont);
			//gc.fillGradientRectangle(0, 0, 380, 240,true);
			//gc.fillGradientRectangle(0, 0, 800, 600,true);

			//int xo = 55;/**xoffset*/
			int xo = 50;/**xoffset*/
			int yo = 10;/**yoffset.*/
			gc.setForeground(new Color(disp,98,94,221));
			gc.setForeground(new Color(disp,255,255,255));
			//gc.drawRectangle(xo-1, yo-1, 301, 19);
			gc.drawImage(gim, xo, yo);

/*
			for(int i=0;i<=5;i++)
			{
				timeMax.setTime((timeMin.getTime()+timeRange.getTime()*i/5));
				//gc.drawText((timeMax.toString().substring(11, 16)), xo+60*i-18, yo+200+3,true);
				gc.drawText(df.format(timeMax), xo+60*i-18, yo+200+3,true);
			}
			int len;
			for(int i=0;i<=10;i++)
			{
				if(priceRange>10000)
				{
					priceMax=priceMin+priceRange*i/10;
					len=Integer.valueOf(priceMax).toString().length()-2;
					if(len<=0)len=1;
					if(priceMax<100 && priceMax>-100) priceMax=0; //coz in paises
					gc.drawText(Integer.valueOf(priceMax).toString().substring(0,len), xo-8-len*8, yo+200-9-20*i,true);
				}
				else
				{
					priceMax=priceMin+priceRange*i/10;
					len=Integer.valueOf(priceMax).toString().length();
					if(len<=2)len=3;
					if(priceMax<0 && len <= 3) len=4;
					gc.drawText(CO_CO.formatMoney(priceMax), xo-8-len*8, yo+200-9-20*i,true);
				}
			}*/
		}
	};
	/**
	 * Create "Graph" // field work
	 */
	private void createGraph(Display disp)
	{
		gim = new Image(disp,250,40);
		gcgim=new GC(gim);
		gcgim.setBackground(new Color(disp,176,213,230));
		gcgim.fillRectangle(0,0,250,40);
		//Manager.gi.sessionid = 1;
		gcgim.setLineWidth(2);
		//Timestamp diff = new Timestamp(timeMin.getTime() - TimerThread.currentTime.getTime());

		//lx1 = (int)((timeMin.getTime() - TimerThread.currentTime.getTime()+Manager.gi.sessionid*3)*310 /Manager.gi.sessionid*3*3600000);
		Set set = Tmap.entrySet();
		gcgim.setForeground(new Color(disp,255,255,255));
		Iterator it = set.iterator();
		Map.Entry me;
		//System.out.println("Hi");
		if(it.hasNext())
		{
			me = (Map.Entry)it.next();
			int x1 = ((Integer)me.getKey()).intValue();
			int y1 = ((Integer)me.getValue()).intValue();

			while(it.hasNext() && x1 < origin)
			{
				me = (Map.Entry)it.next();
				x1 = ((Integer)me.getKey()).intValue();
				y1 = ((Integer)me.getValue()).intValue();
			}
			float scale = (float)0.2;
			y1 *= scale;
			while(it.hasNext())
			{
				me = (Map.Entry)it.next();
				int x = ((Integer)me.getKey()).intValue();
				int y = ((Integer)me.getValue()).intValue();
				if(x>origin+250)break;
				y*=scale;
				gcgim.drawLine(x1-origin, y1, x-origin, y);
				x1 = x;
				y1 = y;
			}
		}
		gcgim.setForeground(new Color(disp,255,0,0));
		gcgim.setLineWidth(5);
		gcgim.drawLine(lx1, ly1, lx1, ly2);
		lmid = (ly1+ly2)/2;
		gcgim.drawPolygon(new int[]{lx1,lmid+4,lx1,lmid-4,lx1-4,lmid});
		gcgim.drawPolygon(new int[]{lx1,lmid+4,lx1,lmid-4,lx1+4,lmid});
		//rx1 = (int)((timeMax.getTime() - TimerThread.currentTime.getTime()+Manager.gi.sessionid*3)*310 /Manager.gi.sessionid*3*3600000);
		gcgim.drawLine(rx1, ry1, rx1, ry2);
		rmid = (ry1+ry2)/2;
		gcgim.drawPolygon(new int[]{rx1,rmid+4,rx1,rmid-4,rx1+4,rmid});
		gcgim.drawPolygon(new int[]{rx1,rmid+4,rx1,rmid-4,rx1-4,rmid});

		gcgim.drawText(""+origin, 0, 10);

		//System.out.println("Hi");
//		System.out.println(""+lx1+" "+rx1);

		//Make the graph
		/** Scale factor y = 200 / priceRange
		 * Scale factor x = 300 / timeRange
		 */
		/*int pr = priceRange; 	/** price range
		int tr = (int) timeRange.getTime();/** time range
		if(pm.keySet().size()<2)
		{
			gcgim.drawText("DATA UNAVAILABLE", 0, 0);
			dataNotAvailable=true;
		}
		else
		{
			Set set = pm.entrySet();
			Iterator iter = set.iterator();
			Map.Entry me;
			if(iter.hasNext())
			{
				me = (Map.Entry)iter.next();
				firstTime = (Timestamp) me.getKey();
				long t1 = firstTime.getTime();
				long p1 = ((Integer) me.getValue()).intValue();
				int x1 = (int) (((t1-timeMin.getTime())*300)/tr);
				int y1 = (int) (((p1-priceMin)*200)/pr);
				//System.out.println(((Timestamp) me.getKey()).toString().subSequence(11, 16) + " : " + (Integer) me.getValue());
				//System.out.println("x :"+x1+"y :"+y1 );
				while(iter.hasNext())
				{
					me = (Map.Entry)iter.next();
					long t = ((Timestamp) me.getKey()).getTime();
					long p = ((Integer) me.getValue()).intValue();
					int x = (int) (((t-timeMin.getTime())*300)/tr);
					int y = (int) (((p - priceMin)*200)/pr);
					gcgim.drawLine(x1,199-y1,x,199-y);
					x1=x;
					y1=y;
					//System.out.println(((Timestamp) me.getKey()).toString() + " : " + (Integer) me.getValue());
					//System.out.println("x :"+x+"y :"+y );
				}
				//timeMin = new Timestamp(((Timestamp) pm.firstKey()).getTime());//new Timestamp(((Timestamp) me.getKey()).getTime());
				//timeMax = new Timestamp(((Timestamp) pm.lastKey()).getTime());
				//lastTime = new Timestamp(((Timestamp) pm.lastKey()).getTime());//(Timestamp) me.getKey();
			}
		}*/
		gcgim.dispose();
	}
	/**
	 * Get all the ranges. The min max time and also the min max price
	 * The ranges are priceMin, priceMax, ctimeMin, ctimeMax.
	 */
	public void decideRanges()
	{
		//pm = GraphManager.priceMap;
		Set set = pm.entrySet();
		Iterator iter = set.iterator();

		Map.Entry me;
		if(iter.hasNext())
		{
			me = (Map.Entry)iter.next();
			timeMin = new Timestamp(((Timestamp) pm.firstKey()).getTime());//new Timestamp(((Timestamp) me.getKey()).getTime());
			timeMax = new Timestamp(((Timestamp) pm.lastKey()).getTime());//new Timestamp(((Timestamp) me.getKey()).getTime());

			priceMin = ((Integer) me.getValue()).intValue();
			priceMax = ((Integer) me.getValue()).intValue();
			while(iter.hasNext())
			{
				me = (Map.Entry)iter.next();
//				if(((Timestamp) me.getKey()).compareTo(timeMax) > 0)
//					timeMax = new Timestamp(((Timestamp) me.getKey()).getTime());
//				if(((Timestamp) me.getKey()).compareTo(timeMin) < 0)
//					timeMin = new Timestamp(((Timestamp) me.getKey()).getTime());
				if(((Integer) me.getValue()).intValue()>priceMax)
					priceMax = ((Integer) me.getValue()).intValue();
				if(((Integer) me.getValue()).intValue()<priceMin)
					priceMin = ((Integer) me.getValue()).intValue();
			}
		}
		else  //exception handling
		{
			timeMin= new Timestamp(Calendar.getInstance().getTimeInMillis()-3600000);
			timeMax= new Timestamp(Calendar.getInstance().getTimeInMillis());
			priceMin=10;
			priceMax=1000;
		}
	//	System.out.println("timeMin :" + timeMin.toString() + "\ttimeMax :" + timeMax.toString());
	//	System.out.println("priceMin :" + priceMin + "\tpriceMax :" + priceMax);

		priceRange=priceMax-priceMin;
		//price ranges :
		int iMax=1,iMin,iRange=1;
		while(iMax<priceMax)	iMax*=10;
		while(iRange<priceRange) iRange*=10;
		iRange/=10; if(iRange<10)	iRange = 10;
		while(priceMax<iMax)	iMax-=iRange;
		iMax+=iRange; //top limit is set (iMax)
		iMin=iMax;
		while(priceMin<iMin)	iMin-=iRange;//bottom limit is set (iMin)
		if(iMin==iMax)	iMin-=iRange;
		priceMin = iMin;priceMax = iMax;
		priceRange = iMax - iMin;
		if(priceRange<400)	priceRange=400;
	//	System.out.println("priceMin :"+priceMin+"\tpriceMax :"+priceMax+"\tpriceRange :"+priceRange);

		/**ctimeRange.setTimeInMillis never works for Calendar. You never get the right time.
		 * So always do Calendar.add(HOUR, hour) then min, then sec
		 * or add the cleared reference time
		 *
		 * Start labelling the scale from 1st itself among the 10 divisions
		 * i.e. label 1,3,5,7,9 label no.
		 * coz it may be like 5 10 15 20 25
		 * otherwise it ll be 7.5 12.5 ...
		 */
		Calendar ctimeMax,ctimeMin,ctimeRange,ctimeMaxh;
		ctimeRange = Calendar.getInstance();
		ctimeMax = Calendar.getInstance();
		ctimeMin = Calendar.getInstance();
		ctimeMaxh = Calendar.getInstance();
		timeRange=new Timestamp(timeMax.getTime()-timeMin.getTime());
	//	System.out.println("timeRange :"+timeRange.getTime());

		ctimeMax.setTimeInMillis(timeMax.getTime());
		ctimeMin.setTimeInMillis(timeMin.getTime());
		ctimeRange.clear();
		ctimeRange.setTimeInMillis(ctimeRange.getTimeInMillis()+timeRange.getTime());
	//	System.out.println("ctime :" +ctimeRange.getTime());
		if( (ctimeRange.get(Calendar.HOUR) == 0) && (ctimeRange.get(Calendar.MINUTE) <= 10 ))
		{
			ctimeMin.set(Calendar.SECOND, 0);
			ctimeMin.set(Calendar.MILLISECOND, 0);
			ctimeMax.setTimeInMillis(ctimeMin.getTimeInMillis());
			ctimeMax.add(Calendar.MINUTE, 10);
		//	System.out.println("Minute < 10");
		}
		else if( (ctimeRange.get(Calendar.HOUR) == 0) && (ctimeRange.get(Calendar.MINUTE) <= 25 ))
		{
			ctimeMin.set(Calendar.SECOND, 0);
			ctimeMin.set(Calendar.MILLISECOND, 0);
			ctimeMin.set(Calendar.MINUTE,(ctimeMin.get(Calendar.MINUTE)) - (ctimeMin.get(Calendar.MINUTE)%5) + 5);
			ctimeMax.setTimeInMillis(ctimeMin.getTimeInMillis());
			ctimeMax.add(Calendar.MINUTE, 25);
	//		System.out.println("Minute < 25");
		}
		else if( (ctimeRange.get(Calendar.HOUR) <= 2))
		{
			ctimeMin.set(Calendar.SECOND, 0);
			ctimeMin.set(Calendar.MILLISECOND, 0);
			ctimeMin.set(Calendar.MINUTE,(ctimeMin.get(Calendar.MINUTE)) - (ctimeMin.get(Calendar.MINUTE)%10) +10);
			ctimeMaxh.setTimeInMillis(ctimeMin.getTimeInMillis());
			ctimeMax.setTimeInMillis(timeMax.getTime());
			while(ctimeMaxh.before(ctimeMax))
				ctimeMaxh.add(Calendar.MINUTE, 10);
			ctimeMax.setTimeInMillis(ctimeMaxh.getTimeInMillis());
		//	System.out.println("Hour <= 2");
		}
		else if( (ctimeRange.get(Calendar.HOUR) > 2))
		{
			ctimeMin.set(Calendar.MINUTE, 0);
			ctimeMin.set(Calendar.SECOND, 0);
			ctimeMin.set(Calendar.MILLISECOND, 0);
			ctimeMin.set(Calendar.HOUR,(ctimeMin.get(Calendar.HOUR))+1);
			ctimeMaxh.setTimeInMillis(ctimeMin.getTimeInMillis());
			ctimeMax.setTimeInMillis(timeMax.getTime());
			while(ctimeMaxh.before(ctimeMax))
				ctimeMaxh.add(Calendar.HOUR, 1);
			ctimeMax.setTimeInMillis(ctimeMaxh.getTimeInMillis());
		//	System.out.println("Hour > 2");
		}
	//	System.out.println("Time Min : "+ timeMin.toString() +"\tTime Max :"+timeMax.toString());
	//	System.out.println("Scaled Min : "+ ctimeMin.getTime() +"\tScaled Max :"+ctimeMax.getTime());
		ctimeMaxh.clear();

		ctimeRange.setTimeInMillis(ctimeMax.getTimeInMillis()-ctimeMin.getTimeInMillis());
		timeRange.setTime(ctimeRange.getTimeInMillis());
	//	System.out.println(timeRange.getTime());
		timeMin.setTime(ctimeMin.getTimeInMillis());
		timeMax.setTime(ctimeMax.getTimeInMillis());

		origtimeRange = new Timestamp(timeRange.getTime());
		origtimeMin = new Timestamp(timeMin.getTime());
		origpriceRange = priceRange;
		origpriceMin = priceMin;
	}
	public void putNewPriceMap(TreeMap priceMap) {
		pm=priceMap;
	}
}
