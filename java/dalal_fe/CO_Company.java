import java.util.TreeMap;



/**
 * Now the company is (to be) stored in hashmap as its getString() output only.
 * (Earlier the company object was stored -- the problem with that - it has to be type-casted
 * back to company form)
 * To use the company, we have to create a new company using the CO_Company(String) Constructor
 * all throughout the game.
 */


public class CO_Company
{

	public int compId;
	public String compName,compInfo;
	public String sector;
	public int marketPrice;
	public int dayHigh;
	public int dayLow;
	public int noOfShares;
	public boolean change;
//	public Timestamp fromTime;
//	public Timestamp toTime;
	public TreeMap priceMap;//Timestamp time;Integer Price
//	public boolean priceMapHistoryLimitReached;


	public CO_Company()
	{
		compId = -1;
		compName = new String("");
		compInfo = new String("");
		sector = new String("");
		marketPrice = -1;
		noOfShares = -1;
		change = true;
		priceMap = new TreeMap();//Date time;int Price
//		fromTime=new Timestamp(0);
//		toTime=new Timestamp(0);
//		priceMapHistoryLimitReached = false;
	}

	public String getString()
	{
		String res="";
			res+=compId;
		res+=CO_CO.delim[3];
			res+=compName;
		res+=CO_CO.delim[3];
			res+=compInfo;
		res+=CO_CO.delim[3];
			res+=sector;
		res+=CO_CO.delim[3];
			res+=marketPrice;
		res+=CO_CO.delim[3];
			res+=dayHigh;
		res+=CO_CO.delim[3];
			res+=dayLow;
		res+=CO_CO.delim[3];
			res+=noOfShares;
		res+=CO_CO.delim[3];
			res+=change;
		res+=CO_CO.delim[3];
			res+=CO_CO.getTreeMapString(priceMap);
		res+=CO_CO.delim[3];
		res+=" "+CO_CO.delim[3];
		return res;
	}

	public CO_Company(String str)
	{
		int i=0;
		compId = -1;
		compName = new String("");
		compInfo = new String("");
		sector = new String("");
		marketPrice = -1;
		noOfShares = -1;
		change = true;
		priceMap = new TreeMap();//Date time;int Price

		String[] fields=CO_CO.getSplit(str,CO_CO.delim[3]);

		i=0;

		if(!fields[i++].equals(""))
			compId=Integer.parseInt(fields[i-1]);
		if(!fields[i++].equals(""))
			compName=fields[i-1];
		if(!fields[i++].equals(""))
			compInfo=fields[i-1];
		if(!fields[i++].equals(""))
			sector=fields[i-1];
		if(!fields[i++].equals(""))
			marketPrice=Integer.parseInt(fields[i-1]);
		if(!fields[i++].equals(""))
			dayHigh=Integer.parseInt(fields[i-1]);
		if(!fields[i++].equals(""))
			dayLow=Integer.parseInt(fields[i-1]);
		if(!fields[i++].equals(""))
			noOfShares=Integer.parseInt(fields[i-1]);
		if(fields[i++].length()!=0){
			if(fields[i-1].equals("true"))
				change=true;
			else
				change=false;
		if(!fields[i].equals("") && !fields[i].equals(" "))
			CO_CO.setPriceMap(priceMap,fields[i]);

		}
	}
}