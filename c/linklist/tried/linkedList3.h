#include <iostream>
using namespace std;

class list
{
protected:
  struct node
  {	
    int info;
    struct node *next;
    struct node *prev;
  } *linkptr;
  int sizenum;
  node *rootnode;
  
public:
  list();
  //  ~list();  
  void push(int i);
  int pop();
  void insert(int i);
  int remove();
  int read();
  void next();
  void previous();
  void root();
};

  list::list()
{
  linkptr=NULL;
  sizenum=0;
}

int list::remove()
{
  if(sizenum==0)
    {
      cout<<"List Empty";
      return 0;
    }
  linkptr=linkptr->prev;
  int a=linkptr->next->info;
  if(sizenum!=1)
    {
      linkptr->next=linkptr->next->next;
      linkptr->next->next->prev=linkptr;
      delete linkptr->next;
    }
  else
    {
      delete linkptr;
    }
  sizenum--;
  return a;
}

void list::insert(int i)
{
  if(sizenum==0)
    {
      rootnode=new node;
      rootnode->info=i;
      rootnode->next=rootnode;
      rootnode->prev=rootnode;
      linkptr=rootnode;
    }
  else  
    {
      node *a;
      a=new node;
      a->info=i;
      a->prev=linkptr;
      a->next=linkptr->next;
      linkptr->next=a;
      linkptr=a;
    }
  sizenum++;
}

void list::push(int i)
{
  if(sizenum==0)
    {
      rootnode=new node;
      rootnode->info=i;
      rootnode->next=rootnode;
      rootnode->prev=rootnode;
      linkptr=rootnode;
    }
  else  
    {
      node *a;
      a=new node;
      linkptr=rootnode->prev;
      a->info=i;
      a->next=rootnode;
      a->prev=linkptr;
      linkptr->next=a;
      linkptr=a;
    }
  sizenum++;
}

int list::pop()
{
  if(sizenum==0)
    {
      cout<<"List Empty";
      return 0;
    }
  linkptr=rootnode->prev->prev;
  int a=linkptr->next->info;
  if(sizenum!=1)
    {
      delete linkptr->next;
      linkptr->next=rootnode;
      rootnode->prev=linkptr;
    }
  else
    {
      delete linkptr;
    }
  sizenum--;
  return a;
}

void list::next()
{
  if(sizenum==0)
    {
      cout<<endl<<"List Empty";
      return ;
    }  
  linkptr=linkptr->next;
} 


void list::previous()
{
  if(sizenum==0)
    {
      cout<<endl<<"List Empty";
      return ;
    }  
  linkptr=linkptr->prev;
}

 void list::root()
{
  if(sizenum==0)
    {
      cout<<endl<<"List Empty";
      return ;
    }  
  linkptr=rootnode;
} 

 int list::read()
{
  if(sizenum==0)
    {
      cout<<endl<<"List Empty";
      return 0;
    }  
  return linkptr->info;
}
  
