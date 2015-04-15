import java.sql.Timestamp;


public class CO_User
{
	public int userId;
	public String userName,userEmail,userPassword,status;
	public boolean loggedin;
	public int cashTotal,cashWithdrawal,cashShare,cashMF;
	public int netWorth;
	public int ranking;
	public Timestamp loginTime;

	public CO_User()
	{
		userId=-1;
		userName = new String("");
		userEmail = new String("");
		userPassword = new String("");
		status = new String("");
		loggedin = false;
		cashTotal=-1;
		cashWithdrawal=-1;
		cashShare=-1;
		cashMF=-1;
		netWorth=-1;
		ranking=-1;
		loginTime = new Timestamp(0);
	}

	public CO_User(String str)
	{
		int i;
		/******************************/
		userId=-1;
		userName = new String("");
		userEmail = new String("");
		userPassword = new String("");
		status = new String("");
		loggedin = false;
		cashTotal=-1;
		cashWithdrawal=-1;
		cashShare=-1;
		cashMF=-1;
		netWorth=-1;
		ranking=-1;
		loginTime = new Timestamp(0);

		/**************/
		String[] fields=CO_CO.getSplit(str,CO_CO.delim[6]);
		i=0;

		if(!fields[i++].equals(""))
			userId=Integer.parseInt(fields[i-1]);
		if(!fields[i++].equals(""))
			userName=fields[i-1];;
		if(!fields[i++].equals(""))
			userEmail = fields[i-1];
		if(!fields[i++].equals(""))
			userPassword=fields[i-1];

		if(fields[i++].length()!=0)
			status=fields[i-1];

		if(fields[i++].length()!=0){
			if(fields[i-1].equals("true"))
				loggedin=true;
			else
				loggedin=false;
		}
		if(fields[i++].length()!=0)
			cashTotal=Integer.parseInt(fields[i-1]);
		if(fields[i++].length()!=0)
			cashWithdrawal=Integer.parseInt(fields[i-1]);
		if(fields[i++].length()!=0)
			cashShare=Integer.parseInt(fields[i-1]);
		if(fields[i++].length()!=0)
			cashMF=Integer.parseInt(fields[i-1]);
		if(fields[i++].length()!=0)
			netWorth=Integer.parseInt(fields[i-1]);
		if(fields[i++].length()!=0)
			ranking=Integer.parseInt(fields[i-1]);
		if(fields[i++].length()!=0)
			if(!fields[i-1].equals("0"))
				loginTime=new Timestamp(Long.parseLong(fields[i-1]));
		cashTotal=cashMF+cashShare+cashWithdrawal;
	}

	public String getString()
	{
		String res="";
			res+=userId;
		res+=CO_CO.delim[6];
			res+=userName;
		res+=CO_CO.delim[6];
			res+=userEmail;
		res+=CO_CO.delim[6];
			res+=userPassword;
		res+=CO_CO.delim[6];

			res+=status;
		res+=CO_CO.delim[6];
			res+=loggedin;
		res+=CO_CO.delim[6];

			res+=cashTotal;
		res+=CO_CO.delim[6];
			res+=cashWithdrawal;
		res+=CO_CO.delim[6];
			res+=cashShare;
		res+=CO_CO.delim[6];
			res+=cashMF;
		res+=CO_CO.delim[6];

			res+=netWorth;
		res+=CO_CO.delim[6];
			res+=ranking;
		res+=CO_CO.delim[6];
			res+=loginTime.getTime();
		res+=CO_CO.delim[6];

		res+=" "+CO_CO.delim[6];
		return res;
	}


}