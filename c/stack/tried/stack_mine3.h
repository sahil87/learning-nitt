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
  int topno;

 public:

  stack();//constructor
  ~stack();//destructor
  void push(int);
  int pop();
  int top();
};

int stack::top()
{
  return topno;
}

stack::stack()
{
  //listptr = new node;
  listptr=NULL;
  topno = -1;
}

void stack::push(int i)
{
   if(topno!=-1)
    {  
      node *a;
      a = new node;
      a->info=i;
      a->prev=listptr;
      a->next=NULL;
      listptr->next=a;
      listptr=a;
    }
   else
    {
      listptr=new node;
      listptr->prev=NULL;
      listptr->next=NULL;
      listptr->info=i;
    }
   topno++;
}

int stack::pop()
{
  if(topno==-1)
    {
      cout<<endl<<"ERROR: The list is empty";
      return 0;
    }

  int a=listptr->info;
  if(topno!=0)
    {
      listptr=listptr->prev;
      delete listptr->next;
      listptr->next=NULL;
    }
  else
    {
      listptr=NULL;
    }
  topno--;
  
  return a;
}

stack::~stack()
{
  node *a,*b;
  if(listptr==NULL) return;
  for(a=listptr,b=a->prev;a!=NULL;a=b,b=a->prev)
    delete a;
}

