using namespace std;
extern int glo;
#include<iostream>

class stack
{
private:
	int a;
	int b;
public:
	int pop();	
	stack();
	~stack();

};



stack::stack()
{
	a=30;
	b=10;
}

stack::~stack()
{
}

int stack::pop()
{
	cout<<"I am in pop() in second file"<<endl<<glo;
	cout<<a+b<<endl;
	return a+b;
}
