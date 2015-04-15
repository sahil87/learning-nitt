import java.util.TreeMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * There are two methods to tell the class that no more data is available.
 * <ol>
 * <li>dataNotAvailable in GraphMain --> Client check </li>
 * <li>priceMapHistoryLimitReached in GraphManager --> Server check</li>
 * </ol>
 * @author Sahil
 */
public class GraphManager extends ColorfulTab{

	/** A server variable that tells the GraphManager that
	 * the server has no more price values to send and the client can stop requesting
	 * it graph price values.
	 */

	public GraphMain graph;
	public SliderMain slider;
	int X;
	boolean leftSliderToolActive = false;
	boolean rightSliderToolActive = false;
	boolean shiftSliderToolActive = false;
	boolean dragActive = false;
	int diffLeft,diffRight,diff;

	public GraphManager(Composite parent, int style, String title) {
		super(parent, style, title);
		Composite comp = new Composite(tabComp,SWT.NONE);
		//comp.setToolTipText("Drag and scale the graph for a better perspective");
		this.setLayout(new FillLayout());
		GridLayout grid = new GridLayout();
		grid.makeColumnsEqualWidth=false;
		grid.numColumns=1;
		comp.setLayout(grid);
		TreeMap tmap = new TreeMap();
	//	for(int i = 0;i<350;i++)tmap.put(i, 200-i*i/1000);
		//System.out.println(tmap.size());
		slider = new SliderMain(comp,tmap);
		graph = new GraphMain(comp,tmap,slider);
		graph.redraw();
		final Cursor MoveLeftRight = new Cursor(getDisplay(),SWT.CURSOR_SIZEWE);
		final Cursor Arrow = new Cursor(getDisplay(),SWT.CURSOR_ARROW);
		final Cursor Hand = new Cursor(getDisplay(),SWT.CURSOR_SIZEALL);
		final Listener MouseMoveOnSlider= new Listener ()
		{
			public void handleEvent(Event event)
			{
				if(!dragActive)
				X = event.x;
				if((event.x > slider.lx1-8+50 && event.x < slider.lx1+8+50) &&
				   (event.y < 40+10 && event.y > 0+10))
				{
					slider.setCursor(MoveLeftRight);
					leftSliderToolActive = true;
					rightSliderToolActive = false;
					shiftSliderToolActive = false;
					if(dragActive)
					{
						slider.lx1 = event.x - 50;
						if(slider.lx1 <= 0)
							slider.lx1 = 0;
						if(slider.lx1 >= slider.rx1 - 10)
							slider.lx1 = slider.rx1 - 10;
						slider.redraw();
					}
				}
				else if	((event.x > slider.rx1-8+50 && event.x < slider.rx1+8+50) &&
				   		(event.y < 40+10 && event.y > 0+10))
				{
					slider.setCursor(MoveLeftRight);
					rightSliderToolActive = true;
					leftSliderToolActive = false;
					shiftSliderToolActive = false;
					if(dragActive)
					{
						slider.rx1 = event.x - 50;
						if(slider.rx1 <= slider.lx1 + 10)
							slider.rx1 = slider.lx1 + 10;
						if(slider.rx1 >= 250)
							slider.rx1 = 250;
						slider.redraw();

					}
				}

				else
					if((event.x > slider.lx1+8+50 && event.x < slider.rx1-8+50) &&
						(event.y < 40+10 && event.y > 0+10) )
				{
					slider.setCursor(Hand);
					shiftSliderToolActive = true;
					leftSliderToolActive = false;
					rightSliderToolActive = false;
					if(!dragActive)
					{
						diffLeft = slider.lx1 - ( event.x - 50 );
						diffRight = slider.rx1 - ( event.x - 50 );
						diff = slider.rx1 - slider.lx1;

					}
					else
					{
						slider.rx1 = event.x - 50 + diffRight;
						slider.lx1 = event.x - 50 + diffLeft;
						if(slider.lx1 <= 0)
						{
							slider.lx1 = 0;
							slider.rx1 = slider.lx1 + diff;
						}
						if(slider.rx1 >= 250)
						{
							slider.rx1 = 250;
							slider.lx1 = slider.rx1 - diff;
						}
						slider.redraw();

					}
				}
				else
				{
					slider.setCursor(Arrow);
					leftSliderToolActive = false;
					rightSliderToolActive = false;
					shiftSliderToolActive = false;
					if(dragActive)
					{
						int t = event.x;
						if(t>=50 && t<=300)
						slider.origin-= t-X;
						if(slider.origin<0)slider.origin = 0;
						//here set else if(slider.origin > maxpixel)set slider.origin = maxpixel ;do
						slider.redraw();
						X = event.x;
					}
				}
				//System.out.println(event.x+":"+event.y+","+leftSliderToolActive + ","+rightSliderToolActive + ","+shiftSliderToolActive + ","+dragActive);
				graph.start = slider.lx1;
				graph.end  = slider.rx1;
				graph.isdraw = true;
				graph.redraw();
			}

		};
		slider.addListener(SWT.MouseMove, MouseMoveOnSlider);

		slider.addListener(SWT.DragDetect, new Listener () {
			public void handleEvent (Event event) {
				dragActive = true;
				if(leftSliderToolActive)
				{
//					System.out.println("in drag : "+event.x);
					slider.lx1 = event.x - 50 ;
					if(slider.lx1 <= 0)
						slider.lx1 = 0;
					if(slider.lx1 >= slider.rx1 - 10)
						slider.lx1 = slider.rx1 - 10;
					slider.redraw();
				}
				else if(rightSliderToolActive)
				{
					slider.rx1 = event.x - 50;
					if(slider.rx1 <= slider.lx1 + 10)
						slider.rx1 = slider.lx1 + 10;
					if(slider.rx1 >= 250)
						slider.rx1 = 250;
					slider.redraw();
				}
				else if(shiftSliderToolActive)
				{
					slider.rx1 = event.x - 50 + diffRight;
					slider.lx1 = event.x - 50 + diffLeft;
					if(slider.lx1 <= 0)
					{
						slider.lx1 = 0;
						slider.rx1 = slider.lx1 + diff;
					}
					if(slider.rx1 >= 250)
					{
						slider.rx1 = 250;
						slider.lx1 = slider.rx1 - diff;
					}
					slider.redraw();
				}
				else
				{
						int t = event.x;
				}
			}
		});

		slider.addListener(SWT.MouseUp, new Listener () {
			public void handleEvent (Event event) {
				dragActive = false;

			}
		});
		final Listener MouseMoveOnGraph= new Listener ()
		{
			public void handleEvent(Event event)
			{
				graph.px = event.x;
				graph.isdraw = false;
				if(graph.px >= 60 && graph.px<=310 && graph.py>=25 && graph.py<=225)
					graph.redraw();
			}
		};
		graph.addListener(SWT.MouseMove, MouseMoveOnGraph);
	}

	/**Used if only new values added in the graph (through upadation)
	 */
	public void updateGraph()
	{
		/*graph.redraw();

		if(!graph.pm.isEmpty())
		{
			graph.firstTime = (Timestamp) graph.pm.firstKey();
			graph.lastTime = (Timestamp) graph.pm.lastKey();
		}


		if(graph.pm.keySet().size()<2)	graph.dataNotAvailable = true;
		if(graph.pm.keySet().size()==earlierCount)	graph.dataNotAvailable = true;
	//	System.out.println("GraphManager.java : updateGraph() "+graph.firstTime.toString()+" "+graph.timeMin.toString()+" "+graph.dataNotAvailable+" "+ priceMapHistoryLimitReached);
		if(graph.firstTime.getTime()>graph.timeMin.getTime() && (!(graph.dataNotAvailable)) && !priceMapHistoryLimitReached)
		{
			//Means not enough data available....so :
			getMoreHistoryGraph();
		}*/
	}
	/**Used for replacing graph with the given priceMap
	 */
	public void putNewGraph(TreeMap priceMap)
	{
/*		graph.putNewPriceMap(priceMap);
		graph.decideRanges();
		graph.dataNotAvailable = false;
		priceMapHistoryLimitReached = false;
		updateGraph();
	}
	public void getMoreHistoryGraph()
	{
		if(!sensexGraph)
		{
			Manager.resetco();
			CO_Company a = new CO_Company();

			a.toTime = new Timestamp( graph.firstTime.getTime()  - TimerThread.currentTime.getTime());
			a.fromTime = new Timestamp(graph.firstTime.getTime() - 1000*60*60  - TimerThread.currentTime.getTime());
			//System.out.println("GraphManager.java : getMoreHistoryGraph a.toTime"+a.toTime.toString()+"a.fromTime"+a.fromTime.toString());
			Manager.co.company.put(new Integer(Manager.currentTradingCompId),a.getString());
			//System.out.println("\nGraphManager.java : for comp3 :"+graph.firstTime.toString());
			earlierCount=graph.pm.keySet().size();
			Manager.query("getgraph");
			if((new CO_Company((String) Manager.gi.company.get(new Integer(Manager.currentTradingCompId)))).priceMapHistoryLimitReached)	setPriceMapHistoryLimitReached();
		}
		else
		{
			Manager.resetco();
			Manager.co.toTime = new Timestamp(graph.firstTime.getTime()  - TimerThread.currentTime.getTime());
			Manager.co.fromTime = new Timestamp(graph.firstTime.getTime() - 1000*60*60  - TimerThread.currentTime.getTime());
			//System.out.println("GraphManager.java : getMoreHistoryGraph toTime"+toTime.toString()+"a.fromTime"+a.fromTime.toString());

			earlierCount=graph.pm.keySet().size();
			Manager.query("getgraph");
			if(Manager.gi.priceMapHistoryLimitReached)	setPriceMapHistoryLimitReached();
		}*/
	}
	public void setPriceMapHistoryLimitReached()
	{
		//System.out.println("GraphManager.java : Setting price limit history reached");
	/*	priceMapHistoryLimitReached = true;*/
	}
}
