#include <iostream>
#define REP(i,_n) for(int i=0;i<_n;++i)
typedef long long int LL;
using namespace std;

#define MAX (int)1e6
LL D[3][MAX+2];
int val[MAX+2];

int main(){
  int N;cin>>N;ithy
  REP(i,N) cin>>val[i];
  for(int i=1;i<=N+1;++i){
    D[0][i]=max(D[0][i-1],max(D[1][i-1],D[2][i-1]));
    D[1][i]=D[0][i-1]+val[i-1];
    D[2][i]=D[1][i-1]+val[i-1];
  }
  
  cout<<D[0][N+1]<<endl;
  return 0;
}
