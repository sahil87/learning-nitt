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
  node *q,*p;
  p=new node;
  p->previous=p;
  p->next=p;
  int i=1;
  char ans='y';
  while(1)
    {
      cout << "Enter the value of node " << i <<":";
      cin >> p->info;
      
      cout << "Do you wish to add one more statement?(y/n)";
      cin >> ans;
      if('y'!=ans) break;

      q=new node;
      q->previous=p;
      q->next=p->next;
      p->next=q;
      p=q;
      i++;
    }
  cout<<"Hi how are you???\n";
  displaynodes(p);
  return 0;
}

int displaynodes(node *p)
{
  node *pstart;
  pstart=p;//note:: this is in place of pstart=new node;
  cout << p->info << "\n";
  p=p->next;
  while(pstart!=p)
    {
      cout << p->info << "\n";
      p=p->next;
    }
  cout << "Bye Bye!!";
  return 0;
}

