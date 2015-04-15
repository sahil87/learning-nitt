/*A java program to show method overriding*/

class A{
	void callme(){
		System.out.println("This is callme() in A");
	}
}

class B extends A{
	void callme(){
		System.out.println("This is callme() in B");
	}
}

class C extends B{
	void callme(){
		System.out.println("This is callme() in C");
	}
}

class Override{
	public static void main(String args[]){
		A a = new A();//creating variable a of object type A
		B b = new B();//creating variable b of object type B
		C c = new C();//creating variable c of object type C
		
		A r;//creating a reference variable r of type A
		
		r = a;//now r refers to object type A
		r.callme();
		
		r = b;//now r refers to object type B
		r.callme();
		
		r = c;//now r refers to object type C
		r.callme();
		/*Hence which callme() is called depends on the object type the variable refers to and not the variable instance type*/
	}
}
