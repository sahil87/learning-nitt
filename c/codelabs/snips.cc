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

using namespace std;

#include<cstdio>
#include<string>
#include<iostream>
#include<vector>
#include<algorithm>

int main(){
//1) To find no of primes between a and b
	//int a,b; cin>>a>>b; cout << COUNT(FOR(i,a,b+1), !EXISTS(FOR(j,2,i), i%j==0));
//2) To find all permutations of a string
//	string a,b; cin >> a; b=a;
//	next_permutation(a.begin(),a.end());
//	while(b!=a)	{cout<<a<<endl;next_permutation(a.begin(),a.end());}
//3) Print the no of ways you can place n-queens on a nxn chess board
/*	int n; cin >> n;
	int a[n]; REP(i,n)	a[i]=i;
	int ans=0;
	do{
		ans+= !EXISTS(REP(i,n),EXISTS(REP(j,i),abs(i-j)==abs(a[i]-a[j])));
	}while(next_permutation(a,a+n));
	cout << ans;
	return 0;*/
//4) Use of GI
/*	LET(a,GI);
	cout << a;
	string b;
	LET()b.pb(GI);
	cout<<b;*/
}
