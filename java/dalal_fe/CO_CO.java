
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * CO_CO stands for CO_CommunicationObject
 *
 *  * co		1
//	bid			7

//	company		4
//  MF			4
//	holding		7
//	news		5
//	ranking		6
//	user		7

HashMap		9,10
Arraylist	8
TreeMap		11,12
Array 		13
 *
 *
 */

public class CO_CO
{

/*	public String type;
	public String returnStatus;
	public String todayFortune;
	public boolean error;
	public Timestamp time;
	public Timestamp fromTime;
	public Timestamp toTime;
	public TreeMap priceMap;//Timestamp time;int Price
	public boolean priceMapHistoryLimitReached;
	public int version;
	public ArrayList news;//Daily, company and sector
	public int graphUpdateFactor;
	public int updateInterval;
*/
	public static char delim[]={'\u1000','\u1001','\u1002','~','\u1004','\u1005','\u1006','\u1007','|','`','\u1010','\u1011','\u1012'};

	public String action;	//1
	public CO_User user;		//2
	public HashMap user_Holding;	//3
	public HashMap user_Bid;		//4

	public HashMap topBids;			//5
	public ArrayList deleteBidId;	//6
	public ArrayList rankings;		//7
	public ArrayList faqs;			//8
	public ArrayList ans;			//9

	public HashMap company;			//10
	public HashMap MF;				//11

	public String companyMD5;		//12
	public String MFMD5;			//13

	public String adminMsg;			//14
	public CO_Bid bid;				//15
	public CO_Holding holding;      //16

	public String recent_trans;     //17

	public int fromtime;			//18
	public int totime;				//19



	public CO_CO(String str)
	{
		String[] fields = getSplit(str, delim[0]);
		int i=0,j=1;
		user= new CO_User();
		company= new HashMap();
		MF = new HashMap();
		topBids= new HashMap();
		user_Bid = new HashMap();;
		user_Holding = new HashMap();;
		faqs=new ArrayList();
		ans=new ArrayList();
		rankings=new ArrayList();
		deleteBidId = new ArrayList();
		companyMD5 = new String();
		MFMD5 = new String();
		adminMsg = new String();
		bid = new CO_Bid();
		holding = new CO_Holding();
		recent_trans = new String();
try{
		if(fields[0].charAt(i++) == '1')
			action = new String(fields[j++]);
		if(fields[0].charAt(i++) == '1')
			user = new CO_User(fields[j++]);

		if(fields[0].charAt(i++) == '1')
			setHashMap(user_Holding, fields[j++]);
		if(fields[0].charAt(i++) == '1')
			setHashMap(user_Bid, fields[j++]);
		if(fields[0].charAt(i++) == '1')
			setHashMap(topBids, fields[j++]);
		if(fields[0].charAt(i++) == '1')
			setArrayList(deleteBidId, fields[j++]);
		if(fields[0].charAt(i++) == '1')
			setArrayList(rankings, fields[j++]);
		if(fields[0].charAt(i++) == '1')
			setArrayList(faqs, fields[j++]);
		if(fields[0].charAt(i++) == '1')
			setArrayList(ans, fields[j++]);
		if(fields[0].charAt(i++) == '1')
			setHashMap(company, fields[j++]);
		if(fields[0].charAt(i++) == '1')
			setHashMap(MF, fields[j++]);
		if(fields[0].charAt(i++) == '1')
			companyMD5 = fields[j++];
		if(fields[0].charAt(i++) == '1')
			MFMD5 = fields[j++];
		if(fields[0].charAt(i++) == '1')
			adminMsg = fields[j++];
		if(fields[0].charAt(i++) == '1')
			bid = new CO_Bid(fields[j++]);
		if(fields[0].charAt(i++) == '1')
			holding = new CO_Holding(fields[j++]);

		if(fields[0].charAt(i++) == '1')
			recent_trans = new String(fields[j++]);

		if(fields[0].charAt(i++) == '1')
			fromtime = Integer.parseInt(fields[j++]);
		if(fields[0].charAt(i++) == '1')
			totime = Integer.parseInt(fields[j++]);
}catch(Exception e){}

	}

	public CO_CO()
	{
		user= new CO_User();
		company= new HashMap();
		MF = new HashMap();
		topBids= new HashMap();
		faqs=new ArrayList();
		ans=new ArrayList();
		rankings=new ArrayList();
		deleteBidId = new ArrayList();
		user_Bid = new HashMap();;
		user_Holding = new HashMap();;
		companyMD5 = new String();
		MFMD5 = new String();
		adminMsg = new String();
		bid = new CO_Bid();
		holding = new CO_Holding();
	}

	public String getString(String str)
	{
		String res="";
		int i = 0;

		res+=delim[0];
		try
		{
		if(str.charAt(i++) == '1')
			res+=action + delim[0];
		if(str.charAt(i++) == '1')
			res+=user.getString() + delim[0];
		if(str.charAt(i++) == '1')
			res+=getHashMapString(user_Holding) + delim[0];
		if(str.charAt(i++) == '1')
			res+=getHashMapString(user_Bid) + delim[0];
		if(str.charAt(i++) == '1')
			res+=getHashMapString(topBids) + delim[0];
		if(str.charAt(i++) == '1')
			res+=getArrayListString(deleteBidId) + delim[0];
		if(str.charAt(i++) == '1')
			res+=getArrayListString(rankings) + delim[0];
		if(str.charAt(i++) == '1')
			res+=getArrayListString(faqs) + delim[0];
		if(str.charAt(i++) == '1')
			res+=getArrayListString(ans) + delim[0];
		if(str.charAt(i++) == '1')
			res+=getHashMapString(company) + delim[0];
		if(str.charAt(i++) == '1')
			res+=getHashMapString(MF) + delim[0];
		if(str.charAt(i++) == '1')
			res+=companyMD5 + delim[0];
		if(str.charAt(i++) == '1')
			res+=MFMD5 + delim[0];
		if(str.charAt(i++) == '1')
			res+=adminMsg + delim[0];
		if(str.charAt(i++) == '1')
			res+=bid.getString() + delim[0];
		if(str.charAt(i++) == '1')
			res+=holding.getString() + delim[0];

		if(str.charAt(i++) == '1')
			res+=action + delim[0];


		if(str.charAt(i++) == '1')
			res+=fromtime;
		if(str.charAt(i++) == '1')
			res+=totime;

		}
		catch(Exception e)
		{
			System.out.println("In CO_CO Catch"+e.getMessage());
		}
		return res;

	}

	public static String formatMoney(int paise) {
		String output="";
		int rupeesPart = paise/100;
		int paisePart = paise%100;
		if(paisePart<0)
		{
			paisePart*=-1;
			if(rupeesPart<0)	rupeesPart*=-1;
			output+="-";
		}
		output+=rupeesPart+".";
		if(paisePart<10) output+=0;
		output+=paisePart;
		return output;
	}
	public static String[] getSplit(String big,char small)
	{
		String[] a;
		int count,p;
		p=0;
		count=0;
		boolean set;
		set = false;
		for(int i=0;i<big.length();i++)
		{
			set=false;
			if(small==big.charAt(i)){	count++;set=true; }
		}
		if(set)		a = new String[count];
		else 		a = new String[count+1];
		a[0]="";
		for(int i=0;i<big.length();i++)
		{
			if(big.charAt(i)==small)
			{	p++; if(p!=count) a[p]="";else {if(set)break;else a[p]="";}	}
			else
				a[p]+=big.charAt(i);
		}
		return a;
	}
	public static String getHashMapString(HashMap x) {

		Set set = x.entrySet();
		Iterator i = set.iterator();//		 Get an iterator
		String xString="";
		while(i.hasNext())
		{
			Map.Entry me = (Map.Entry)i.next();
			xString+=me.getKey()+""+CO_CO.delim[8];
			xString+=((String)me.getValue())+CO_CO.delim[9];
		}
		return xString;
	}
	public static void setHashMap(HashMap x,String string) {
		String[] fields=CO_CO.getSplit(string,CO_CO.delim[9]);
		for(int i=0;i<fields.length;i++)
		{
			String[] key_value=CO_CO.getSplit(fields[i],CO_CO.delim[8]);
			x.put(new Integer(key_value[0]),new String(key_value[1]));
		}
	}
	public static String getArrayListString(ArrayList a) {
		String str="";
		for(Iterator iter = a.iterator(); iter.hasNext();)
			str+=(String)iter.next()+CO_CO.delim[7];
		return str;
	}
	public static void setArrayList(ArrayList a,String string) {
		String[] fields=CO_CO.getSplit(string,CO_CO.delim[7]);
		for(int i=0;i<fields.length;i++)
			a.add(fields[i]);
	}
	public static String getTreeMapString(TreeMap priceMap)
	{
		String a = "";
		Set set = priceMap.entrySet();
		Iterator iter = set.iterator();
		Map.Entry me = null;
		while(iter.hasNext())
		{
			me = (Map.Entry)iter.next();
			a+=""+((Timestamp)me.getKey()).getTime()+CO_CO.delim[10]+((Integer) me.getValue())+CO_CO.delim[11];
		}
		return a;
	}
	public static void setPriceMap(TreeMap priceMap,String a)
	{
		String[] fields=CO_CO.getSplit(a,CO_CO.delim[11]);
		for(int i=0;i<fields.length;i++){
			String[] k=CO_CO.getSplit(fields[i],CO_CO.delim[10]);
			//priceMap.put(new Timestamp(Long.parseLong(k[0])),new Integer(k[1]));
		}
	}
}