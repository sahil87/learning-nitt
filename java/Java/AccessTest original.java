class Test1
{	int a;
        public int b;//public scope is default anyway
        private int c;
        public static int d;
        
        Test1()//constructor... (this one with no arguments)
        {	a=11;
                b=12;
                c=13;
        }
	
	void test1method()//to see if this method is accessible by main();
	{	System.out.println("\nNow in Test1method");
	}
}

class AccessTest
{	AccessTest accesstest1;
	int yahoo = 234;
        public static void main(String args[])
        {	Test1 mytest = new Test1();
                System.out.println("\nInside main()...........");
		
		//System.out.println("The value of yahoo is " + yahoo);
		//this causes error coz yahoo is not a static variable....
		
		System.out.println("The value of a is " + mytest.a);
                System.out.println("The value of b is " + mytest.b);
                
		//System.out.println("The value of c is " + mytest.c);
		//this causes errors because c is private varaiable and can be  
		//accessed only thru methods of the same object
                
		System.out.println("The value of Test1.d is " + Test1.d);
                System.out.println("The value of mytest.d is " + mytest.d);
		//..shows we can access d directly as mytest.d (in place of Test1.d) coz d is static
                
		mytest.test1method();//this IS accessible in main()
		//accesstest1.main1();//but not this. WHY???
		/*WHY DOES THIS (previous line) NOT WORK??? other instance methods ARE easily accessible
		by main (for eg mytest.test1method())... then why not this(accesstest1.main1())?
		AND why has main1() to be declared as static to access it from main()??*/
		//System.out.println("The value of accesstest.yahoo is " + accesstest1.yahoo);
		/*Similarly this too.... other variables (non-static) like mytest.a can be referred
		to from main but not accesstest1.yahoo .... WHY*/
                
		//this.main1();    
		//gives error coz this and super keywords cannot be refered to in a static method
                
		main1();
        }
        
        static void main1()	/*-----have to declare main1() as static so that it is accessible 
			by the static function main() WHY?...I mean.. why is it not accessible as 
			accesstest1.main1() just like other methods in other declared objects?*/
        {	System.out.println("\nInside main1()..........");
                System.out.println("The value of d is " + Test1.d);	
		//static variables have only one instance..
                //they can be directly accessed by classname.var-name instead of 
		//refering to an object of that class type
        }
        
        //increasing the scope of mytest (earlier its scope was only in main())
        Test1 mytest = new Test1();
        
        static  //static functions are run just once (when that class is run the first time...
		//even if there is no object of that class type)
        {	System.out.println("inside static : The value of d is " + Test1.d);
                //the next line causes error-	
		//System.out.println("inside static : The value of d is " + mytest.d);
		//because instance variables CANT be accessed by static methods...*/
        }
}