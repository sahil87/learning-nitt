#include <iostream>
using namespace std;

struct node
{	
  int info;
  struct node *next;
  struct node *previous;
};


int main()
{
  int displaynodes(node *p1);
  node *p;
  p=new node;//why is this line important?? gives segmentation fault without it
  p->previous=NULL;
  int i=1;
  char ans='y';
  while(1)
    {
      p->next=NULL;
      cout << "Enter the value of node " << i <<":";
      cin >> p->info;
      
      cout << "Do you wish to add one more statement?(y/n)";
      cin >> ans;
      if('y'!=ans) break;

      p->next=new node;
      p->next->previous=p;
      p=p->next;
      i++;
    }
  cout<<"Hi how are you???\n";
  displaynodes(p);
  return 0;
}

int displaynodes(node *p)
{
  while(NULL!=p->previous) 
    p=p->previous;
  while(NULL != p)
    {
      cout << p->info << "\n";
      p=p->next;
    }
  cout << "Bye Bye!!";
  return 0;
}
