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
  tree *p,*root;
  p=new tree;
  root=p;
  root->parent=NULL;

  int ans='y';
  while('y'==ans)
    {
      cout << "Enter the value of current node:";
      cin >> p->info;
      p->childLeft=NULL;
      p->childRight=NULL;
      
      cout << "Add one more element in the tree?(y/n) :"<<ans;
      cin >> ans;
      if('n'==ans) break;
      p=root;      
      selectNode(p);
    }
return 0;
}

int selectNode(tree *p)
{
  int displayNode(tree *p);
  int ans;
  tree *q;
  q=new tree;
  cout<<"current node:\n";  
  displayNode(p);
  while(1) 
    {
      cout << "Select left(l) or right(r) node:";
      cin >> ans;
      //ans='l';
      if(ans=='l'||ans=='r') break;
    }

  if('l'==ans)
    {
      if(NULL==p->childLeft)
	{ //cout<<"top:"<<p->childLeft;cin>>a;
	  p->childLeft=q;
	  q->parent=p;
	  p=q;
	  return 0;
	} 
      else
	{//cout<<"bottom:"<<p->childLeft;cin>>a;
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
