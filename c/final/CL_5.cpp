#include<iostream>
#include<fstream>
#include<cstring>
using namespace std;
int main(int argc,char *argv[])
{
  if(argc!=2)
{
cout<<"usage ; output<filename>\n";
return 1;
}
ofstream out(argv[1]);
if(!out)
{
cout<<"cannot opehn output file\n";
return 1;
}
char str[800];
int i=0,j;
cout<<"write strings to disk.enter ! to stop\n";

do{

cin>>str;
out<<str<<" " ;

}while(strcmp(str,"EOL"));
out.close();
cout<<"here is output"<<endl;
ifstream in(argv[1]);

int count=0;
int flag=0;
char str1[800];

do
{
 in>>str1;
for(j=0;j<strlen(str1);j++)
  {
    if((str1[j]>=65 && str1[j]<=90)||(str1[j]>=97 && str1[j]<=122))
     {
       flag=1;
       continue;}
   else
{  
flag=0;
   break;}
  }
cout<<str1<<" ";
  
if(flag==1)
i++;


}while(strcmp(str1,"EOL"));

cout<<"number of words are"<<i;
return 0;
}
