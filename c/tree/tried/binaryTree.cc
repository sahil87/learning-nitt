#include <iostream>
using namespace std;

struct tree
{
  int info;
  struct tree *childLeft,*childRight;
  struct tree *parent;
};

int main()
{
  int selectNode(tree *p);
  int displayNode(tree *p);
  tree *p,*root;
  p=new tree;
  root=p;
  root->parent=NULL;

  char ans='y';
  while('y'==ans)
    {
      cout << "Enter the value of current node:";
      cin >> p->info;
      p->childLeft=NULL;
      p->childRight=NULL;
      cout<<root->info;
      cout << "Add one more element in the tree?(y/n) :";
      cin >> ans;
      if('n'==ans) break;
      p=root;      
      selectNode(&p);
    }
return 0;
}

int selectNode(tree **p)
{
  int displayNode(tree *p);
  char ans;
  tree *q;
  q=new tree;
  cout<<"current node:\n";  
  displayNode(p);
  for(;;)
    {
      cout << "Select left(l) or right(r) node:";
      cin >> ans;
      if(ans=='l'||ans=='r') break;
    }
  if('l'==ans)
    {
      if(NULL==p->childLeft)
	{ 
	  p->childLeft=q;
	  q->parent=p;
	  p=q;
	  return 0;
	} 
      else
	{
	  p=p->childLeft; 
	  selectNode(p);
	  return 0;
	}
    }
  if('r'==ans)
    {
      if(NULL==p->childRight)
	{
	  p->childRight=q;;
	  q->parent=p;
	  p=q;
	  return 0;
	} 
      else
	{
	  p=p->childRight;
	  selectNode(p);
	  return 0;
	}
    }
} 

int displayNode(tree *p)
{
  if(NULL==p->parent) cout<<"\t\tROOT\n";
  else cout<<"\t\t"<<p->parent->info<<"\n";
  cout<<"\t\t"<<p->info<<"\n";  
  if(NULL==p->childLeft) cout<<"\tNULL";
  else cout<<"\t"<<p->childLeft->info;
  if(NULL==p->parent) cout<<"\t\tNULL\n";
  else cout<<"\t\t"<<p->childRight->info<<"\n";
  return 0;
}
