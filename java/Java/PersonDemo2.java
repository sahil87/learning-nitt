class Person2
{	char startingletter;
	int weightinkg;
	int heightinfeet;
	
	void weightintoheight()
	{	System.out.println(startingletter + "'s product of weight and height is " + weightinkg*heightinfeet);
	}
}

class PersonDemo2
{	public static void main(String args[])
	{	Person sagar;
		sagar = new Person();
		Person pooja = new Person();
		
		sagar.startingletter='s';
		sagar.weightinkg=61;
		sagar.heightinfeet=6;
		
		pooja.startingletter='p';
		pooja.weightinkg=42;
		pooja.heightinfeet=5;

		sagar.weightintoheight();
		pooja.weightintoheight();
	}
}


