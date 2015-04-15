#include<stdio.h>

int main()
{	int a=6,b=7;
	while(a!=0||b!=0)
	{
		printf("\n\nEnter a and b:");
		scanf("%2d %3d",&a,&b);	
		printf("a: %1d   b:%-12dyahoo",a,b);
	}
	return 0;
}
