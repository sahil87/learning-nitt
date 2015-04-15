#include<iostream>
#include<fstream>
using namespace std;
struct arbit{
int num[5];
float f[6];
char c[7];
};
int main()
{arbit obj;
cout<<"\n enter 5 numbers;";
for(int i=0;i<5;i++)
{
cin>>obj.num[i];
}
cout<<"\n enter 6 float:";
for(int j=0;j<6;j++)
{
cin>>obj.f[j];
}
cout<<"\n enter 7 chars:";
for(int k=0;k<7;k++)
{
cin>>obj.c[k];
}

ofstream fout;
fout.open("prob",ios::out|ios::binary);
if(!fout)
{
cout<<"\n aborting!!";
return 1;
}
fout.write((char *)&obj,sizeof(arbit));
fout.close();
cout<<"\n reading chars:";
ifstream fin;
fin.open("prob",ios::binary|ios::in);
if(!fin)
{cout<<"\n aborting!!";
return 1;
}
fin.read((char*)&obj,sizeof(arbit));
for(int i=0;i<6;i++)
{
cout<<"\n"<<obj.c[i];
}
cout<<endl<<"reading number";

for(int i=0;i<5;i++)
{
cout<<"\n"<<obj.num[i];
}
getchar();
return 0;
}
