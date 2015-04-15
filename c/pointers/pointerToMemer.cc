#include<iostream>
class a
{	
public:
	int z;
	int b();
};
int a::b()
{
	return 25;
}

main()
{
	a a1;
	a1.z=90;
	int (a::*data)();
	data = &a::b;
	std::cout<<(a1.*data)();
	return 0;
}
