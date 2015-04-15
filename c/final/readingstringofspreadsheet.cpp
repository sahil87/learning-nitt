#include<iostream>
#include<fstream>
#include<cstring>
using namespace std;
int main()
{
 ifstream in;
 int i,z;
 char str[80];
 in.open("sumit4.csv",ios::binary|ios::in);

  if(!in)
{
cout<<"cannot open the file";
return 1;
}

  while(in)
    {
   in>>str;
   cout<<str;
 }
in.close();
return 0;
}
