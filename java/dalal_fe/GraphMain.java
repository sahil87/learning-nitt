import java.sql.Timestamp;
import java.text.DateFormat;
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
public class GraphMain extends Canvas //implements Runnable
{
	/** Contains the main graph
	 * Only the actual graph.*/
	public int start,end;
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
	SliderMain Slider;
	Set set;
	boolean isdraw;
	int px,py;
	public GraphMain(Composite parent,TreeMap priceMap,SliderMain slider)
	{
		super(parent,SWT.FILL | SWT.DOUBLE_BUFFERED | SWT.INHERIT_FORCE);
		start = slider.lx1;end = slider.rx1;
		setBackground(new Color(getDisplay(),16,16,32));
		addPaintListener(drawListener);
		this.setSize(410, 240);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.minimumHeight=240;
		data.minimumWidth=410;
		setLayoutData(data);
		pm = priceMap;
		set = pm.entrySet();
		//		timeMin = new Timestamp(TimerThread.currentTime.getTime());
//		timeMax = new Timestamp(TimerThread.currentTime.getTime());;

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
		Slider = slider;
	}

	PaintListener drawListener = new PaintListener()
	{
		public void paintControl(PaintEvent pe)
		{
			Display disp = pe.display;
			//if(isdraw)
			createGraph(disp);
			GC gc = pe.gc;
			gc.setBackground(new Color(disp,43,42,83));
			gc.setFont(WindowManager.smallSystemFont);
			//gc.fillGradientRectangle(0, 0, 380, 240,true);
			//gc.fillGradientRectangle(0, 0, 800, 600,true);
			//int xo = 55;/**xoffset*/
			int xo = 60;/**xoffset*/
			int yo = 25;/**yoffset.*/
			gc.setForeground(new Color(disp,98,94,221));
			gc.setForeground(new Color(disp,255,255,255));
			//gc.drawRectangle(xo-1, yo-1, 301, 201);
			gc.drawRectangle(xo-1, yo-1, 251, 201);


			gc.drawImage(gim, xo, yo);


			/*for(int i=0;i<=5;i++)
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
		//pm = GraphManager.priceMap;
		gim = new Image(disp,250,200);
		gcgim=new GC(gim);
		gcgim.setBackground(new Color(disp,13,13,21));
		gcgim.fillRectangle(0,0,250,200);
		gcgim.setForeground(new Color(disp,61,58,136));
		for(int i=1;i<10;i++)	gcgim.drawLine(0, 20*i, 250, 20*i);
		for(int i=1;i<10;i++)	gcgim.drawLine(25*i, 0, 25*i, 200);
		gcgim.setForeground(new Color(disp,90,249,46));
		float scale = (float)0.0;
		Iterator it = set.iterator();
		Map.Entry me;
		if(!pm.isEmpty())
		{
			me = (Map.Entry)it.next();
			int x1 = ((Integer)me.getKey()).intValue();
			int y1 = ((Integer)me.getValue()).intValue();

			while(it.hasNext() && x1 < start + Slider.origin)
			{
				me = (Map.Entry)it.next();
				x1 = ((Integer)me.getKey()).intValue();
				y1 = ((Integer)me.getValue()).intValue();
			}

			scale = (float)(end-start)/250;
			while(it.hasNext())
			{
				me = (Map.Entry)it.next();
				int x = ((Integer)me.getKey()).intValue();
				int y = ((Integer)me.getValue()).intValue();
				if(x>end + Slider.origin)break;
				gcgim.drawLine((int)((x1-start-Slider.origin)/scale), y1, (int)((x-start-Slider.origin)/scale), y);
				gcgim.setForeground(new Color(disp,255,255,255));
				gcgim.drawOval((int)((x-1-start-Slider.origin)/scale),y-1,1,1);
				gcgim.setForeground(new Color(disp,90,249,46));
				x1 = x;
				y1 = y;
			}
		}
		it = set.iterator();

		if(it.hasNext())
		{
			me = (Map.Entry)it.next();
			int prevx = ((Integer)(me.getKey())).intValue();
			int prevy = ((Integer)(me.getValue())).intValue(),x = 0,y = 0;
			px-=60;
			while(it.hasNext())
			{
				me = (Map.Entry)it.next();
				x = ((Integer)(me.getKey())).intValue();
				y = ((Integer)(me.getValue())).intValue();
				if(x>=(px*scale)+Slider.origin+start)break;
				prevx = x;
				prevy = y;
			}
			float m = (float)(prevy-y)/(prevx-x);
			float c = y - m*x;

			py = (int)((px*scale+Slider.origin+start)*m + c);
//			System.out.println(""+prevx+" "+prevy+" "+x+" "+y+" "+m+" "+c);
//			System.out.println(""+px+" "+py);
			gcgim.drawLine(px,200,px,0);
			gcgim.drawLine(0,py,250,py);
		}
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
}
