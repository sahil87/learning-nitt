#include <iostream>
#include <cstdlib>
using namespace std;

struct tree
{
  int info;//or char
  struct tree *childLeft,*childRight;
  struct tree *parent;
};

tree *p,*q;//DISPLAY p and q GLOBALLY IDIOT!!!!!

int main()
{
  int selectNode();
  p=new tree;
  tree *root;
  root=p;
  root->parent=NULL;

  char ans='y';
  while(1)
    {
      cout << "Enter the value of current node:";
      cin >> p->info;
 
      cout << "Add one more element in the tree?(y/n) :";
      cin >> ans;
      if('y'!=ans) break;
      p=root;
      selectNode();
    }
return 0;
}

int selectNode()
{
  int displayNode();
  char ans='?';
  cout<<"current node:\n";
  displayNode();
  while(ans!='l'&&ans!='r'&&ans!='p'&&ans!='q')
    {
      cout << "left(l) right(r) or parent(p) node:";
      cin >> ans;
    }
  switch(ans)
    {
    case 'l': 
      if(NULL==p->childLeft)
	{ 
	  q=new tree;
	  q->childLeft=NULL;
	  q->childRight=NULL;
	  p->childLeft=q;
	  q->parent=p;
	  p=q;
	} 
      else
	{
	  p=p->childLeft;
	  selectNode();
	}
      break;
    case 'r':
      if(NULL==p->childRight)
	{
	  q=new tree;
	  q->childLeft=NULL;
	  q->childRight=NULL;
	  p->childRight=q;
	  q->parent=p;
	  p=q;
	} 
      else
	{
	  p=p->childRight;
	  selectNode();
	}
      break;
    case 'p':
      if(p->parent!=NULL) p=p->parent;
      selectNode();
      break;
    case 'q':
      exit(0);
    }
  return 0;
}

int displayNode()
{
  if(NULL==p->parent) cout<<"\t\tROOT\n";
  else cout<<"\t\t"<<p->parent->info<<"\n";
  cout<<"\t\t"<<p->info<<"\n";
  if(NULL==p->childLeft) cout<<"\tNULL";
  else cout<<"\t"<<p->childLeft->info;
  if(NULL==p->childRight) cout<<"\t\tNULL\n";
  else cout<<"\t\t"<<p->childRight->info<<"\n";
  return 0;
}
