#include <iostream>
using namespace std;

struct node
{	
  int info;
  struct node *next;
};


int main()
{
  int displaynodes(node *root);
  node *p,*root;
  p=new node;
  root=p;
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
      p=p->next;
      i++;
    }
  cout<<"Hi how are you???\n";
  displaynodes(root);
  return 0;
}

int displaynodes(node *p)
{
  while(NULL != p)
    {
      cout << p->info << "\n";
      p=p->next;
    }
  cout << "Bye Bye!!";
  return 0;
}
