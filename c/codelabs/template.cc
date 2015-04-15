#define LET(x,a) typeof(a) x(a)
#define FOR(i,a,b) for(LET(i,a);i!=(b);++i)
#define REP(i,n) FOR(i,0,n)
#define EACH(it,v) FOR(it,(v).begin(),(v).end())
#define sz size()
#define pb push_back
#define cs c_str()

#define GI ({int t;scanf("%d",&t);t;})
#define COUNT(f,x) ({int _=0;f _+=(x);_;})
#define EXISTS(f,x) ({int _=0;f if(x) {_=1;break;}_;})
#define ALL(f,x) (!EXISTS(f,!(x)))
#define MIN(f,x) ({LL _=LINF;f _<?=(x);_;})
#define MAX(f,x) (-MIN(f,-(x)))

#include<cstdio>
#include<algorithm>
#include<string>
#include<vector>
#include<iostream>

typedef long long LL;
using namespace std;

int main(){
	
	return 0;
}
