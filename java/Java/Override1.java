/*A java program to show method overriding*/

class A{
	int a = 111;
	void callme(){
		System.out.println("This is callme() in A");
	}
}

class B extends A{
	int a = 222;
	void callme(){
		System.out.println("This is callme() in B");
	}
}

class C extends B{
	int a = 333;
	void callme(){
		System.out.println("This is callme() in C");
	}
}

class Override1{
	public static void main(String args[]){
		A a = new A();//creating variable a of object type A
		B b = new B();//creating variable b of object type B
		C c = new C();//creating variable c of object type C
		
		A r;//creating a reference variable r of type A
		
		r = a;//now r refers to object type A
		r.callme();
		System.out.println("The value of r.a in A is " + r.a);
		
		r = b;//now r refers to object type B
		r.callme();
		System.out.println("The value of r.a in B is " + r.a);
		
		r = c;//now r refers to object type C
		r.callme();
		System.out.println("The value of r.a in C is " + r.a);
		/*Hence which callme() is called depends on the object type the variable refers to and not the variable instance type*/
		/*But the variable a remains the same... So .. dynamic dispatch is only followed in methods and not in instance variables*/
	}
}
