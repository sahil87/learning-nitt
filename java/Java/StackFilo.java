/*Java program to demonstrate working of classes using First in Last out stack*/

class Stack
{	int stck[] = new int[10];
//position of first
	int pof;
	
	void push(int value)
	{	if(pof==9)
		System.out.println("Stack overflow");
		else
		stck[++pof]=value;
	}
	
	int pop()
	{	if(pof<0)
		{	System.out.println("Stack underflow");
			return 0;
		}
		else
		return stck[pof--];
	}
//Constructor
	Stack()
	{	pof = -1;
	}
}

class StackFilo
{	public static void main(String args[])
	{	Stack mystack1= new Stack();
		
		for(int i=10;i<20;i++)
		{	mystack1.push(2*i);
		}

		System.out.println("Stack in mystack1 :");

		for(int i=0;i<10;i++)
		{	System.out.println("Stack "+i+":"+mystack1.pop());
		}
	}
}