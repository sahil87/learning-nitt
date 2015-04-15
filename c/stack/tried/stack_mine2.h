#include <iostream>
using namespace std;

class stack
{
 protected:
  struct node
  {
    int info;
    node *prev;
    node *next;
  } *listptr;//the active pointer in this class

 public:

  stack();//constructor
  ~stack();//destructor
  void push(int);
  int pop();
};

stack::stack()
{
  listptr=new node;
}
void stack::push(int i)
{
  node *a;
  a = new node;
  a->prev=listptr;
  a->info=i;
  a->next=NULL;
  listptr->next=a;
  listptr=a;
}
int stack::pop()
{
  if(listptr==NULL)
    {
      cout<<endl<<"ERROR: The list is empty";
      return 0;
    }
  int a=listptr->info;
  listptr=listptr->prev;
  delete listptr->next;
  listptr->next=NULL;
  return a;
}

stack::~stack()
{
  node *a,*b;
  if(listptr==NULL) return;
  for(a=listptr,b=a->prev;a!=NULL;a=b,b=a->prev)
    delete a;
}

