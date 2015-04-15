#include <iostream>
using namespace std;
#define MAX 1000

class stack
{
 protected:
  struct node
  {
    tree* info;
    node *prev;
    node *next;
  } *listptr;//the active pointer in this class
  int sz;

 public:

  stack();//constructor
  ~stack();//destructor
  void push(tree*);
  tree* pop();
  int size();
};

stack::stack()
{
  listptr=new node;
  listptr->prev=NULL;
  // listptr->info=NULL;
  listptr->next=NULL;
  sz=0;
}

int stack::size()
{
  return sz;
}

void stack::push(tree* i)
{
  if(sz>=MAX)
    {
      cout<<"ERROR: Reached maximum stack size";
      return;
    }
  listptr->info=i;
  node *a=listptr;
  listptr=new node;
  a->next=listptr;
  listptr->prev=a;
  //listptr->info=NULL;
  listptr->next=NULL;
  sz++;
}

tree* stack::pop()
{
  if(listptr->prev==NULL)
    {
      cout<<endl<<"ERROR: The list is empty";
      return 0;
    }
  listptr=listptr->prev;
  delete listptr->next;
  tree *a=listptr->info;
  //listptr->info=NULL;
  listptr->next=NULL;
  sz--;
  return a;
}

stack::~stack()
{
  if(listptr->prev==NULL) return;
  listptr=listptr->prev;
  for(;listptr->prev!=NULL;listptr=listptr->prev)
    delete listptr->next;
  delete listptr;
  delete &sz;
}

