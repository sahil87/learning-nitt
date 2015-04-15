#include <iostream>
#define REP(i,n) for(i=0;i<_n;++i)
int main()
{
	int N,P;
	cin>>P>>N;
	vector<int> val[4](P);
	REP(i,4) REP(j,P) cin>>val[i](j);
	REP(i,4) sort(val[i].begin(),val[i].end());
	


	return 0;
}
