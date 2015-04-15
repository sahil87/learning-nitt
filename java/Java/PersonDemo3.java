class Person3
{	char startingletter;
	int weightinkg;
	double heightinfeet;
	
	int weightintoheight()
	{	return((int) (weightinkg*heightinfeet));
	}
}

class PersonDemo3
{	public static void main(String args[])
	{	Person3 sagar;
		sagar = new Person3();
		Person3 pooja = new Person3();
		
		sagar.startingletter='s';
		sagar.weightinkg=61;
		sagar.heightinfeet=5.5;
		
		pooja.startingletter='p';
		pooja.weightinkg=42;
		pooja.heightinfeet=5.3;

		System.out.println(sagar.startingletter + "'s product of weight and height is " + sagar.weightintoheight());
		System.out.println(pooja.startingletter + "'s product of weight and height is " + pooja.weightintoheight());
	}
}


