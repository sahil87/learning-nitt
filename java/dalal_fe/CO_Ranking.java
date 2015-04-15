
public class CO_Ranking {

	public String UserName;
	public int netWorth;
	public String getString()
	{
		return (UserName + CO_CO.delim[2] + netWorth + CO_CO.delim[2]);
	}
	public CO_Ranking()//default constructor
	{
		UserName="";
		netWorth=-1;
	}

	public CO_Ranking(String s)
	{

		netWorth=-1;
		UserName="";
		String str[]=CO_CO.getSplit(s,CO_CO.delim[2]);
		UserName=str[0];
		netWorth=Integer.parseInt(str[1]);
		}
	}
