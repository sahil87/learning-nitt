/* Program to print composite nos from 1 to 100 
.......  also using user input*/

class Prime
{	public static void main(String arg[])
	throws java.io.IOException
	{	char ch='y';
		int linebreakcounter;
		
		while(ch=='y')
		{	linebreakcounter=0;
			System.out.print("The prime nos are ...");
			outer: for(int i=1; i<=100;i++)
			{	
				inner: for(int j=2; ;j++)
				{	if(j>=i)	continue outer;
					if(i%j == 0)	break inner;
				}
				
				if(linebreakcounter%10==0)	System.out.println();
				linebreakcounter++;
				System.out.print(i+" ");
			}
		System.out.print("\nDo you want to run all this gibberish again?(y/n) : ");
		ch = (char)System.in.read();
		}
	}
}