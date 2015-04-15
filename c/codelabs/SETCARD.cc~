/*
Set Cardinality : SETCARD :: 100
Given a sequence, we are to identify the no of elements in it.
A sequence can be nested with sub-seqs ( all are non-empty sequences)
Repeated elems count .

Constraints :
Each line consists of not more than 10000 characters.

Input Format : 1st line contains N the no of inputs. Followed by N lines of sequences.
Ouput Format : Output N lines of integers with the cardinality of the sequence.
Sample Input : (There are no spaces inbetween)
3
{{1,2},{3,{3,4}}}
{1,{2,{3,{4}}},4}
{3}

Sample Output:
5
5
1

Source : SELF
*/

#define LET(x,a) typeof(a) x(a)
#define FOR(i,a,b) for(LET(i,a);i!=(b);++i)
#define REP(i,n) FOR(i,0,n)
#define EACH(it,v) FOR(it,(v).begin(),(v).end())
#define COUNT(f,x) ({int _=0;f _+=(x);_;})

#include<string>
#include<vector>
#include<iostream>

typedef long long LL;
using namespace std;
// no of comas + 1
int main(){
	int N; cin >> N;
	string S;
	REP(i,N){
		cin >> S;
		//int ans=0;
		//EACH(it,S)	if(*it==',') ans++;
		cout << COUNT(REP(i,S.length()),(S[i] == ',')) + 1 << endl;
		//cout << ans+1 <<endl;
	}
	return 0;
}
