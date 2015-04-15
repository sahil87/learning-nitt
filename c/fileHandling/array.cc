#include<iostream>
#include<fstream>
#include<string>
using namespace std;
main()
{
	fstream out("array.txt");
	if(!out)
	{	cout<<"Error opening file array.txt";
		return 1;
	}
	int arr[5];
	for(int i=0;i<5;i++) arr[i]=i*35;
	out<<arr[2]<<endl<<arr[3];
	out.close();
	
	//out.open("array.txt");
	ifstream in("array.txt");
	if(!in)
	{	cout<<"Error opening file array.txt";
		return 1;
	}
	//int a,b=40;
	int count=0;
	string a,b;
	while(in>>a)
		count++;
	//in>>a
	cout<<count;
	//cout<<a<<" "<<b;
	
}
