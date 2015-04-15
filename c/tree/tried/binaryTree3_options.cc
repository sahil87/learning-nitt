#include <iostream>
using namespace std;

struct tree
{
  int info;//or char
  struct tree *childLeft,*childRight;
  struct tree *parent;
};

tree *p,*q;//DISPLAY p and q GLOBALLY IDIOT!!!!!

void inorder(tree *);
void postorder(tree *);
void preorder(tree *);

int main()
{
  int selectNode();
  tree *root;
  p=new tree;
  root=p;
  root->parent=NULL;

  char ans='y';
  while('y'==ans)
    {
      cout << "Enter the value of current node:";
      cin >> p->info;
 
      cout << "Add one more element in the tree?(y/n) :";
      cin >> ans;
      if('n'==ans) break;
      p=root;
      selectNode();
    }
  int answer=1;
  while(answer)
  {
    cout<<"\nEnter your choice";
    cout<<"\n0. Exit\n1. Inorder\n2. Preorder\n3. Postorder\n";
    cin>>answer;
    switch(answer)
    {
      case 1:	inorder(root);	        break;
      case 2:	preorder(root); 	        break;
      case 3:	postorder(root);	break;
    }
   }
return 0;
}
void inorder(tree* current)
{
  if(current->childLeft==NULL && current->childRight==NULL)
  { cout<<current->info<<" "; return;}
  if(current->childLeft!=NULL)  inorder(current->childLeft);
  cout<<current->info<<" ";
  if(current->childRight!=NULL) inorder(current->childRight);
}

void postorder(tree* current)
{
  if(current->childLeft==NULL && current->childRight==NULL)
  { cout<<current->info<<" "; return;}
  if(current->childLeft!=NULL)  postorder(current->childLeft);
  if(current->childRight!=NULL) postorder(current->childRight);
  cout<<current->info<<" ";
}

void preorder(tree* current)
{
  if(current->childLeft==NULL && current->childRight==NULL)
  { cout<<current->info<<" "; return;}
  cout<<current->info<<" ";
  if(current->childLeft!=NULL)  preorder(current->childLeft);
  if(current->childRight!=NULL) preorder(current->childRight);
}

int selectNode()
{
  int displayNode();
  char ans='q';
  cout<<"current node:\n";
  displayNode();
  while(ans!='l'&&ans!='r')
    {
      cout << "left(l) right(r) node:";
      cin >> ans;
    }
  if('l'==ans)
    {
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
    }
  if('r'==ans)
    {
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
