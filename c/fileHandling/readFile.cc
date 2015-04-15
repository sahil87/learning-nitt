#include<iostream>
#include<fstream>
using namespace std;
main()
{
	fstream in("test.txt");
	if(!in)
		cout<<"Cannot open file test.txt.";
	char ch;
	fstream out("test2.txt");
	if(!out)
		cout<<"Cannot open file test2.txt.";
	while(in.get(ch))
		out.put(ch);
	
		//cout<<ch;
}
