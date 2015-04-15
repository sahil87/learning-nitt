#include<iostream>
#include<fstream>
#include<string>
using namespace std;

int main()
{
	ifstream in("test3.txt",ios::out);
	if(!in)
	{
		cout<<"Unable to open file";
	}
	string a;
	int count=0,count2=0;
	while(in>>a)
	{
		cout << a<<endl;
		char ch2=' ',ch=' ';
		int i=0;
		while(ch2=a[i++])
		{
			count2++;
			cout<<ch2;
		}
		//int i=0;
		//while(ch2=a[i++]) cout<<ch2; 
		count++;
	}
	std::cout<<endl<<"No of words is "<<count;
	std::cout<<endl<<"No of letters is "<<count2<<endl;
}
