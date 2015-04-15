#include <iostream>
using namespace std;
#define MAX 5
//on making the list empty... there is still one node that points to null..
//so when on one element is added.. its put to the same node.. so no new node
//gets created... so .. no of nodes and elements are related as-
// elements- 0 1 2 3 4..
// nodes   - 1 1 2 3 4..
class linklist
{
 protected:
  struct node
  {
    int info;
    node *prev;
    node *next;
  } *listptr,*rootptr;//the active pointer in this class
  int sz;

 public:

  linklist();//constructor
  ~linklist();//destructor
  int size();
  void push(int);
  int pop();
  void root();
  void last();
  int read();
  void insert(int i);//means insert before
  int remove();
  void prev();
  void next();
};
linklist::linklist()
{
  listptr=new node;
  listptr->prev=NULL;//prev=NULL shows that the linklist is empty coz it may be at root but still not empty
  listptr->next=NULL;
  rootptr=listptr;
  sz=0;
}
linklist::~linklist()
{
  this->last();
  if(listptr->prev==NULL) return;
  while(listptr!=rootptr)
  {
    listptr=listptr->prev;
    delete listptr->next;
  }
  delete listptr,rootptr;
  delete &sz;
}
int linklist::size()
{
  return sz;
}
void linklist::push(int i)
{
  if(sz>=MAX)
  {
    cout<<"ERROR: Reached maximum linklist size\n";
    return;
  }
  if(listptr->prev!=NULL)
  {
    this->last();
    node *a=listptr;
    listptr=new node;
    listptr->prev=a;
    a->next=listptr;
  }
 // else
  //  listptr->prev=rootptr;//here listptr=rootptr

  listptr->info=i;
  listptr->next=rootptr;
  rootptr->prev=listptr;
  sz++;
}
int linklist::pop()
{
  if(listptr->prev==NULL)
  {
    cout<<endl<<"ERROR: The list is empty";
    return 0;
  }
  this->last();
  int a=listptr->info;
  if(listptr!=rootptr)
  {
    listptr=listptr->prev;
    delete listptr->next;
    listptr->next=rootptr;
    rootptr->prev=listptr;
  }
  else
  {
    listptr->next=NULL;
    listptr->prev=NULL;
  }
  sz--;
  return a;
}
void linklist::root()
{
  listptr=rootptr;
}
void linklist::last()
{
  if(listptr->prev==NULL)
  {
    cout<<endl<<"ERROR: The list is empty";
    return;
  }
  listptr=rootptr->prev;
}
int linklist::read()
{
  if(listptr->prev==NULL)
  {
    cout<<endl<<"ERROR: The list is empty";
    return 0;
  }
  return listptr->info;
}
void linklist::insert(int i)
{
  if(listptr!=rootptr)
  {
    node *tempptr=rootptr;
    rootptr=listptr;
    this->push(i);
    rootptr=tempptr;
  }
  else if(listptr->prev==NULL) this->push(i);
  else //listptr==rootptr and listptr->prev!=NULL
  {
    this->push(i);
    rootptr=listptr;
    listptr=rootptr->next;
  }
  return;
}
int linklist::remove()
{
  //checks taken care of by pop()
  if(listptr!=rootptr)
  {
    node *tempptr=rootptr;
    rootptr=listptr->next;
    this->pop();
    rootptr=tempptr;
  }
  else  this->pop();
}
void linklist::prev()
{
  if(listptr->prev==NULL)
  {  
	 cout<<"ERROR: No element in list";
	 return;
  }
  listptr=listptr->prev;
}
void linklist::next()
{
  if(listptr->prev==NULL)
  {  
	 cout<<"ERROR: No element in list";
	 return;
  }
  listptr=listptr->next;
}
