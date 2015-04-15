#include<iostream>
#include<vector>
#include<string>
#include<cstdio>
using namespace std;
#define FOR(i,a) for(int i=0;i<a;i++)
//vector<bool> arr;
string st;
void recurse(int SIZE)
{
	if(SIZE==0) { cout<<st<<endl; return; }
	FOR(i,SIZE)
	{
		recurse(SIZE-1);
		char c = st[st.size()-SIZE];
		for(int j=stSIZE;j>=0;j--)
			st[st.size()-j]=st[st.size()-j+1];
		st[SIZE]=c;
		
	}
}
int main()
{	
	cin>>st;
	int SIZE=st.size();
	//arr.resize(SIZE,false);
	recurse(SIZE);
	return 0;
}
