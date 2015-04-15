#include<iostream>
#include<fstream>
#include<cstring>
using namespace std;
int main()
{
 ifstream in;
 int i,z;
char str[80];
  in.open("sumit12.csv");

  if(!in)
{
cout<<"cannot open the file";
return 1;
}
do{
   in>>str;
   cout<<str;}while(strcmp(str,"EOL"));
in.close();
return 0;
}
