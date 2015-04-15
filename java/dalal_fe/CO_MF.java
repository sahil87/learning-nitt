import java.util.TreeMap;



/**
 * Now the company is (to be) stored in hashmap as its getString() output only.
 * (Earlier the company object was stored -- the problem with that - it has to be type-casted
 * back to company form)
 * To use the company, we have to create a new company using the CO_Company(String) Constructor
 * all throughout the game.
 */


public class CO_MF
{

	public int MFId;
	public String MFName,MFInfo;
	public String MFType,MFRiskType;
	public int NAV;
	public int EntryLoad,ExitLoad;
	public int MFDayHigh,MFDayLow;
	public boolean change;
//	public Timestamp fromTime;
//	public Timestamp toTime;
	public TreeMap priceMap;//Timestamp time;Integer Price
//	public boolean priceMapHistoryLimitReached;

	public String getString()
	{
		String res="";
			res+=MFId;
		res+=CO_CO.delim[3];
			res+=MFName;
		res+=CO_CO.delim[3];
			res+=MFInfo;
		res+=CO_CO.delim[3];
			res+=MFType;
		res+=CO_CO.delim[3];
			res+=MFRiskType;
		res+=CO_CO.delim[3];
			res+=NAV;
		res+=CO_CO.delim[3];
			res+=EntryLoad;
		res+=CO_CO.delim[3];
			res+=ExitLoad;
		res+=CO_CO.delim[3];
			res+=MFDayHigh;
		res+=CO_CO.delim[3];
			res+=MFDayLow;
		res+=CO_CO.delim[3];
			res+=change;
		res+=CO_CO.delim[3];
		res+=" "+CO_CO.delim[3];
		return res;
	}


	public CO_MF()
	{
		MFId = -1;
		MFName = new String("");
		MFInfo = new String("");
		MFType = new String("");
		MFRiskType = new String("");
		NAV = -1;
		EntryLoad = -1;
		ExitLoad = -1;
		MFDayHigh = -1;
		MFDayLow = -1;
		change = true;
		priceMap = new TreeMap();//Date time;int Price
//		fromTime=new Timestamp(0);
//		toTime=new Timestamp(0);
//		priceMapHistoryLimitReached = false;
	}
	public CO_MF(String str)
	{
		int i=0;
		MFId = -1;
		MFName = new String("");
		MFInfo = new String("");
		MFType = new String("");
		MFRiskType = new String("");
		NAV = -1;
		EntryLoad = -1;
		ExitLoad = -1;
		MFDayHigh = -1;
		MFDayLow = -1;
		change = true;
		priceMap = new TreeMap();//Date time;int Price

		String[] fields=CO_CO.getSplit(str,CO_CO.delim[3]);

		i=0;

		if(!fields[i++].equals(""))
			MFId=Integer.parseInt(fields[i-1]);
		if(!fields[i++].equals(""))
			MFName=fields[i-1];
		if(!fields[i++].equals(""))
			MFInfo=fields[i-1];
		if(!fields[i++].equals(""))
			MFType=fields[i-1];
		if(!fields[i++].equals(""))
			MFRiskType=fields[i-1];
		if(!fields[i++].equals(""))
			NAV=Integer.parseInt(fields[i-1]);
		if(!fields[i++].equals(""))
			EntryLoad=Integer.parseInt(fields[i-1]);
		if(!fields[i++].equals(""))
			ExitLoad=Integer.parseInt(fields[i-1]);
		if(!fields[i++].equals(""))
			MFDayHigh=Integer.parseInt(fields[i-1]);
		if(!fields[i++].equals(""))
			MFDayLow=Integer.parseInt(fields[i-1]);
		if(fields[i++].length()!=0){
			if(fields[i-1].equals("true"))
				change=true;
			else
				change=false;
		}
	}
}
