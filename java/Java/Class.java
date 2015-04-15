                                                                                                                                                                                                                                                          
import javax.swing.*;
import java.io.*;
import java.text.*;

class sine
{
	private double angle;
	
	public void setAngle(double angle)
	{
		this.angle=angle;
	}

	public double getAngle()
	{
		return angle;
	}

	public void setAngleDegrees(double angle)
	{
		this.angle = angle / 180 * Math.PI;
	}

	public double getAngleDegrees()
	{
		return (angle / Math.PI * 180);
	}

	public double sineTerm(int n)
	{
		if(n<=0)
			return -2;

		int sign = (n%2 == 0) ? -1 : 1;
		
		return sign * Math.pow(angle, 2*n -1) / factorial (2*n - 1);
	}

	public double factorial(int number)
	{
		if(number <0)
			return -1;
		else if (number==0)
			return 1;
			
		int count;
		double result=1;
		for(count=number; count>0; count--)
		{
			result = result * count;
		}

		return result;
	}

	public String sineUpto(int terms)
	{
		if(terms<=0)
			return "Terms must be greater than 0";

		int count;
		DecimalFormat df = new DecimalFormat( "0.0000000000");
		double result=0;

		for(count=1; count<=terms; count++)
		{
			result += sineTerm(count);
		}
			
		return df.format(result);
	}

	public String sinePrecision(int precision)
	{
		if(precision<0)
			return "Precision must be non-negative";
		
		int count;
		double limit = Math.pow(10, (-1) * (precision+1));
		double term;
		double result=0;

		String pattern = "0.";
		for(count=0; count<precision; count++)
		{
			pattern = pattern + "0";
		}

		DecimalFormat df = new DecimalFormat(pattern);

		count=1;

		while(true)
		{
			term = sineTerm(count);
			
			if(term * Math.pow(-1,(count+1)) < limit)
				break;
			
			result = result + term;
			count++;
	
		}
		
		return df.format(result);
	}



	
	static public void main(String args[]) throws IOException
	{
		int choice;
		BufferedReader bufReader = new BufferedReader( new InputStreamReader (System.in));
		
		while(true)
		{
			choice = Integer.parseInt( JOptionPane.showInputDialog(null,"Enter ur option :\n"+
							"1. Sine Upto N Terms\n"+
							"2. Sine Upto Precision N\n"+
							"3. Exit\n\n"	));
			perform(choice);
		}
	}

	static private void perform(int choice) throws IOException
	{		
		switch(choice)
		{
			case 1: calcNTerms();
				break;
			
			case 2: calcPrecision();
				break;
			
			case 3:	System.exit(0);
				break;

			default: System.out.println("\nWrong Choice.!!\n");
				break;
		}
	}

	static private void calcNTerms() throws IOException
	{
		double angle;
		int terms;
		sine num = new sine();

		
		angle = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter the angle :"));
		terms = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the no. of terms :"));
		


		num.setAngleDegrees(angle);
		System.out.println("Sine of " + angle + " upto " + terms + " terms is " + num.sineUpto(terms));

	}

	static private void calcPrecision() throws IOException
	{
		double angle;
		int precision;
		sine num = new sine();

		angle = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter the angle :"));		
		precision = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the precision :"));

		num.setAngleDegrees(angle);
		System.out.println("Sine of " + angle + " with precision " + precision + " is " + num.sinePrecision(precision));
		
	}
}