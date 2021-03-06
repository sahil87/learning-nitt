/** To merge sort array **/
#define FOR(i,a,n) for(int i=a;i<n;++i)
#define REP(i,n)   FOR(i,0,n)
#define SWAP(a,b)  {int _=b;b=a;a=_;}
using namespace std;
#include <iostream>
int n1,n2,temp,a,b;
void merge(int p,int q,int r);
int A[] = {6,34,3,12,45,67,423,34,1,2,456,232};
int L[6];int R[6];
int main() {
	
	REP(i,12)
		cout<<A[i]<<" ";
	cout<<endl;
	merge(0,5,11);
	REP(i,12)
		cout<<A[i]<<" ";
	cout<<endl;
}

void merge(int p,int q,int r) {
	if(r>p) {
		merge(p,p+(q-p)/2,q);
		merge(q+1,q+(r-q)/2,r);
		n1=q-p+1;
		n2=r-q;
		REP(i,n1)	L[i]=A[p+i];
		//cout<<"L:"<<n1<<" r: "<<r<<" q: "<<q<<" p: "<<p<<" ";
		//REP(i,6)	cout<<L[i]<<" ";
		REP(i,n2)	R[i]=A[q+i+1];
		//cout<<"R:"<<n2<<" ";
		//REP(i,6)	cout<<R[i]<<" ";
		a=b=0;
		FOR(i,p,r+1)	{
			if((L[a]<R[b] && a<n1) || b==n2) {
				A[i]=L[a];
				a++;
			}
			else {
				A[i]=R[b];
				b++;
			}
		}
		REP(i,12)	cout<<A[i]<<" "; cout<<endl;
	}
}
