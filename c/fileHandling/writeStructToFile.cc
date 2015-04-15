#include<iostream>
#include<fstream>
using namespace std;

struct trial
{
	int a;
	float b;
	char s[5];

};
main()
{
	trial temp,temp2;
	temp.a=90;
	temp2.a=80;
	
	fstream out("trial.txt",ios::out|ios::binary);
	if(!out)
	{
		cout<<"Unable to open trial.txt";
		return 1;
	}
	out.write((char *) &temp,sizeof temp);
	out.close();
	
	ifstream in("trial.txt",ios::in|ios::binary);
	in.read((char *) &temp2,sizeof temp2);
	in.close();
	
	cout<<temp2.a;
	return 0;
}
