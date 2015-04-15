#include<iostream>
class a
{
public:
	int arr[5];
	a operator+(a t2);
	void show();
};
a a::operator+(a t2)
{
	a temp;
	for(int i=0;i<=4;i++)
		temp.arr[i]=this->arr[i] + t2.arr[i];
	return temp;
}
void a::show()
{
	for(int i=0;i<=4;i++) std::cout<<arr[i]<<std::endl;
}
class b
{
public:
	int arr[10];
};

main()
{
	a a1,a2,a3;
	for(int i=0;i<=4;i++) a1.arr[i]=i;
	for(int i=0;i<=4;i++) a2.arr[i]=i*2;
	a3=a1+a2;
	a3.show();
}
