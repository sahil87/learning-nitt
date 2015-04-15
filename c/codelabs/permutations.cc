#include<algorithm>
#include<vector>
#include<string>
#include<cstdio>
#include<iostream>
//#include<cstring>
using namespace std;
#define FOR(i,n) for(int i=0;i<n;++i)
string st;

void ROTATE(int SIZE) {
	char c = st[st.size()-1];
	FOR(i,SIZE-1)	st[st.size()-i-1]=st[st.size()-i-2];
	st[st.size()-SIZE]=c;
}

void RECURSE(int SIZE){
	if(SIZE==0){	cout<<st<<endl; return ; }
	FOR(i,SIZE){
		ROTATE(SIZE);RECURSE(SIZE-1);
		
	}
}

int main(){
	cin>>st;
	RECURSE(st.size());
	return 0;
}

