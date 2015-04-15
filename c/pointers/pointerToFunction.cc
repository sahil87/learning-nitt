#include<iostream>
main()
{
	int inv();
	int (*a)();
	a=&inv;//or a=inv; both work
	a();
}
int inv()
{
	std::cout<<"Hi";
}
