class Person
{	char startingletter;
	int weightinkg;
	int heightinfeet;
}

class PersonDemo1
{	public static void main(String args[])
	{	int a,b;
		Person sagar;
		sagar = new Person();
		Person pooja = new Person();
		
		sagar.startingletter='s';
		sagar.weightinkg=61;
		sagar.heightinfeet=6;
		
		pooja.startingletter='p';
		pooja.weightinkg=41;
		pooja.heightinfeet=5;

		a=sagar.weightinkg * sagar.heightinfeet;
		System.out.println("Product of sagar's height and weight is " + a);
	}
}
