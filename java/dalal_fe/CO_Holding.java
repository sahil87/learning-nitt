
//Delim list : |#^;= Use non printable chars \00 to \31
// Separate delims for diff classes
/**
 * In the holdings hashmap, the company id is equal to the key
 * So you get company no 2 by (Company) holding.get(new Integer(2))
 */

public class CO_Holding
{
	public int compId;
	public int noOfShares;
	public int purchasedPrice;
	public String holdingType;

	public CO_Holding()
	{
		compId=-1;
		noOfShares=-1;
		purchasedPrice=-1;
		holdingType="";
	}

	public CO_Holding(String str)
	{
		int i;
		compId=-1;
		noOfShares=-1;
		purchasedPrice=-1;
		String[] fields=CO_CO.getSplit(str,CO_CO.delim[6]);

		i=0;

		if(!fields[i++].equals(""))
			compId=Integer.parseInt(fields[i-1]);
		if(!fields[i++].equals(""))
			noOfShares=Integer.parseInt(fields[i-1]);
		if(!fields[i++].equals(""))
			purchasedPrice=Integer.parseInt(fields[i-1]);
		if(!fields[i++].equals(""))
			holdingType=fields[i-1];
	}

	public String getString()
	{
		String res="";
			res+=compId;
		res+=CO_CO.delim[6];
			res+=noOfShares;
		res+=CO_CO.delim[6];
			res+=purchasedPrice;
		res+=CO_CO.delim[6];
			res+=holdingType;
		res+=CO_CO.delim[6];
		return res;
	}

}
