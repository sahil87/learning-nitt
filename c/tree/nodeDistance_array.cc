#include <iostream>
using namespace std;

void displayReln();
int pre[]= {2,3,7,8,10,100,6,9};
int in[] = {7,3,8,100,10,2,6,9};
int* ina, * inb;
int* prea, * preb;

int
main ()
{
	cout<<"inorder  - ";for(int i=0;i<8;i++) cout<<in[i]<<"  ";
	cout<<"\npreorder - ";for(int i=0;i<8;i++) cout<<pre[i]<<"  ";
	int tofind;cout<<"\nTo find the distance of which node from root?";cin>>tofind;
    inb=in;while(*inb!=tofind) inb++;
	preb=pre;while(*preb!=tofind) preb++;
	ina=in;while(*ina!=pre[0]) ina++;
	prea=pre;
	cout<<*pre<<"  ";
	displayReln();
	return 0;
}

void displayReln()
{
	prea++;
	//to check if prea lies on the same side of ina in which inb is
	int * temp=in;while(*temp!=*prea) temp++;
	if(ina>inb){	if(ina<temp) { displayReln(); return; } }
	else if(ina<inb){ if(ina>temp) { displayReln(); return; } }
	//if reaches here then on same side
	if(ina>inb){	while(*ina!=*prea) ina--; cout<<*ina<<"  "; displayReln(); return;}
	else if(ina<inb){	while(*ina!=*prea) ina++; cout<<*ina<<"  "; displayReln(); return;}
}
