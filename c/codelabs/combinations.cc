#include<algorithm>
#include<vector>
#include<string>
#include<cstdio>
#include<iostream>
using namespace std;
#define FOR(i,n) for(int i=0;i<n;++i)
string st,op;
int size,stsize;
void RECURSE(int n){
	if(n==size)	{cout<<op<<endl;return;}
	FOR(i,stsize) {
		op[n]=st[i];
		RECURSE(1+n);
	}
}

int main(){
	cin>>st;
	cin>>size;
	stsize=st.size();
	op.resize(size);
	sort(st.begin(),st.end());
	RECURSE(0);
	return 0;
}
