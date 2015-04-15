#include <iostream>
using namespace std;

//To complete destructor and a way to know if the tree is empty

class tree
{
  protected:
    struct node	
    {
      int info;//or char
      struct node *childLeft,*childRight;
      struct node *parent;
    } *rootptr,*treeptr;
   // int init;//root initialized or not//not implemented yet
    void deleteTree(tree);

  public:

    tree();
    //~tree();
    void root();
    void left();
    void right();
    void parent();
    int read();
    void write(int);
};
tree::tree()
{
  treeptr=new node;
  treeptr->parent=NULL;
  treeptr->childLeft=NULL;
  treeptr->childRight=NULL;
  rootptr=treeptr;
 // this->init=1;//init yet to be implemented
}
/*tree::~tree()
{
  tree a=rootptr;
  tree b=rootptr;
  if(a->childLeft!=NULL)
  {
    b=a;
    a=a->childLeft;
  }
  else if(
  delete &init;
}*/
void tree::parent()
{
  if(treeptr->parent!=NULL)
    treeptr=treeptr->parent;
  else
    cout<<"ERROR: Reached top of tree"<<endl;
}
void tree::left()
{
  if(treeptr->childLeft!=NULL)
    treeptr=treeptr->childLeft;
  else
  {
    node *newTree = new node;
    newTree->childLeft=NULL;
    newTree->childRight=NULL;
    newTree->parent=treeptr;
    treeptr->childLeft=newTree;
    treeptr=newTree;
  }
}
void tree::right()
{
  if(treeptr->childRight!=NULL)
    treeptr=treeptr->childRight;
  else
  {
    node *newTree = new node;
    newTree->childLeft=NULL;
    newTree->childRight=NULL;
    newTree->parent=treeptr;
    treeptr->childRight=newTree;
    treeptr=newTree;
  }
}
void tree::root()
{
  treeptr=rootptr;
}
int tree::read()
{
 // if(this->init!=0)
    return treeptr->info;
 /* else
    cout<<"ERROR: no node initialized";
    return 0;*/
}
void tree::write(int a)
{
  //if(this->init!=0)
    treeptr->info=a;
}



