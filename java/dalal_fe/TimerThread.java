import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class TimerThread extends Thread
	{
		public static boolean running=true;
		private static long diff=0;
		public static Timestamp currentTime;
		private static int timeZoneOff;
		private static Calendar off;
		public TimerThread()
		{


		}
		public void setTime(Timestamp login)
		{
			diff=login.getTime()-System.currentTimeMillis();
			currentTime=new Timestamp(System.currentTimeMillis()+diff);
			off =Calendar.getInstance();
			off.clear();
			timeZoneOff = 1000*60*(30+60*5);
		}
		public void run()
		{
			//System.out.println("TimerThread.java : In run()");
			try
			{
				while(!Application.rootShell.isDisposed())
				{
					//System.out.println("asdasD");

					try
		    	 	{
						if(!Application.rootShell.isDisposed())
						{

							Application.display.asyncExec(new Runnable() {
						public void run()
						{

							if(running)
							{
								final Date now = new Date(System.currentTimeMillis()+diff+off.getTimeInMillis()+timeZoneOff);
								//final  DateFormat df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
//								System.out.println("Time is: "+currentTime.toString()+" and diff="+diff);
								currentTime.setTime(now.getTime());
								//WindowManager.timer.setText("Server Time : "+df.format(now));
							}
						}});
						}
						Thread.sleep(1000);
					}
		    	 	catch (Exception e)
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
}
