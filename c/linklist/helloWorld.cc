#include <iostream>
using namespace std;

struct node
{	
  int info;
  struct node *next;
};


int main()
{
  int displaynodes(node *p1);
  node *p,*p1;
  p=new node;
  p->next=NULL;
  p1=p;
  int i=4,ans=1;
  while(ans==1)
    {
      i++;
      p -> next = new node;
      p=p -> next;      
      p->info = i;
      p -> next=NULL;
      
      cout << "Do you wish to add one more statement?";
      cin >> ans;
    }
  cout<<"Hi how are you???\n";
  displaynodes(p1);
  return 0;
}

int displaynodes(node *p)
{
  while(NULL != p->next)
    {
      cout << p->info << "\n";
      p=p->next;
    }
  cout << "Bye Bye!!";
  return 0;
}
