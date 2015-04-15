/**
 * In the bid hashmap, the key isn't equal to the company id
 * The company Id has to be found out from ((company) bid.get("key")).compId;
 */
public class CO_Bid
{
	public int bidId;
	public int compId;
	public int noOfShares;
	public int price; //buying or selling
	public String bidType;//buying or selling

	public CO_Bid()
	{
		bidId=-1;
		compId=-1;
		noOfShares=-1;
		price=-1;
		bidType="";
	}

	public CO_Bid(String str)
	{
		int i=0;
		bidId=-1;
		compId=-1;
		noOfShares=-1;
		price=-1;
		bidType="";

		String[] fields=CO_CO.getSplit(str,CO_CO.delim[6]);

		i=0;

		if(!fields[i].equals(""))
			bidId=Integer.parseInt(fields[i++]);
		if(!fields[i].equals(""))
			compId=Integer.parseInt(fields[i++]);
		if(!fields[i].equals(""))
			noOfShares=Integer.parseInt(fields[i++]);
		if(!fields[i].equals(""))
			price=Integer.parseInt(fields[i++]);
		if(fields[i].length()!=0)
			bidType=fields[i++];
	}

	public String getString()
	{
		String res="";
			res+=bidId;
		res+=CO_CO.delim[6];
			res+=compId;
		res+=CO_CO.delim[6];
			res+=noOfShares;
		res+=CO_CO.delim[6];
			res+=price;
		res+=CO_CO.delim[6];
			res+=bidType;
		res+=CO_CO.delim[6];
		return res;
	}


}

