/*A program to get the hexadecimal value
of integers....*/

class HexValue
{	public static void main(String arg[])
	{	byte b;
		char hex[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		
		b = 0x0f;
		for(b=-5;b<=5;b++)
		System.out.println("The hex value of "+b+" is 0x"+hex[(b >> 4)& 0x0f]+hex[b & 0x0f]);
	}
}