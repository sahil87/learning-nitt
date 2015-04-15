/*
ByteLandian Gold Coins : GCOINS :: 500

In byte-land, their currency consist of coins on which a number (N) is written. You can exchange that coin to N dollars, but not vice-versa. [N<=10^9] Now u have got another choice, u can exchange the coin into 3 coins on which N/2, N/3 & N/4 are written. This is integer division., ie 3/2 = 1; Grow Rich in byteland.

Input Format :
one integer N, which represent the number written on the one coin given to u initially.

SampleInput:
12

OutputFormat :
one integer which is the maximum amount of dollars that can be made out of the coin.

Sample Output:
13
[13=12/2+12/3+12/4]
Advice :
Use long long int of gcc.
eg: long long int a; This holds 64-bit value;
Of course works only in gcc/g++ compilers of GNU Linux.

Source : University Of Purdue Programming Contest. By Tomek Czajka.
*/

#define FOR(i,a,b) for(LET(i,a);i!=(b);++i)
#define REP(i,n) FOR(i,0,n)

#include<iostream>
#include<vector>
typedef long long LL;
using namespace std;

LL go(LL N){
	if(N==0)	return 0;
	return( max((go(N/2)+go(N/3)+go(N/4)),N) );
}

int main(){
	LL N; cin >> N;
	cout << go(N);;
	return 0;
}
