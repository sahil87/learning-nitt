/*program to demonstrate scope of a variable
within a method*/

class Scope
{	public static void main(String arg[])
	{	int x=100;
		System.out.println("x : "+x);
		{int y=200;
		System.out.println("y : "+y);
		x=y+x;
		}
	//	System.out.println("x,y : " + x + "," + y);
		System.out.println("x : " + x );
	}
}	