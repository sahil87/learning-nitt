#include <iostream>
#include <cstdlib>//for exit(int errorCode);
#include <curses.h>

using namespace std;
#define MAX 1000

struct tree
{
  int info;//or char
  struct tree *childLeft,*childRight;
  struct tree *parent;
};

tree *p,*q;//DISPLAY p and q GLOBALLY IDIOT!!!!!
//p is the active pointer
void inorder(tree *);
void postorder(tree *);
void preorder(tree *);
void inorder_stacks(tree *);
void heightOfTree(tree *);
int height=0,sub=0;
int selectNode();
int displayNode();

int main()
{
  initscr();
  p=new tree;
  tree *root;
  root=p;
  root->parent=NULL;
  printw("Enter value of root node :");
  scanw("%d",&root->info);
  int errorCode=0;
  while(errorCode==0)
    {
      displayNode();
      errorCode=selectNode();
     // p=root;
    }
  int answer=1;
  while(answer)
  {
    printw("\nEnter your choice");
    printw("\n0. Exit\n1. Inorder\n2. Inorder using stacks\n3. Preorder\n4. Postorder\n5. Height of tree\n");
    answer=getch();
    switch(answer)
    {
     // case 0:   endwin();exit(0);                break;
      case 1:	inorder(root);	        break;
      case 2:   inorder_stacks(root);   break;
      case 3:	preorder(root);         break;
      case 4:	postorder(root);        break;
      case 5:   heightOfTree(root);
                printw("\nHeight of tree :%d",height+1);
	        break;
    }
  }
  endwin();
return 0;
}

void heightOfTree(tree* current)
{
  if(NULL!=current->childLeft)
  {
    sub==0?height++:sub--;
    heightOfTree(current->childLeft);
    sub++;
  }
  if(NULL!=current->childRight)
  {
    sub==0?height++:sub--;
    heightOfTree(current->childRight);
    sub++;
  }
}
#include "stackoftree_mine.h"

void inorder_stacks(tree* current)
{
  stack* s=new stack;
  enum d { UPL,DOWN } direction = DOWN;
  while(1)
  {
    if(current->childLeft!=NULL && direction!=UPL)
    {
      s->push(current); 
      current=current->childLeft;
    }
    else
    {
      cout<<current->info<<" ";
      if(current->childRight!=NULL)
      {
	current=current->childRight;
	direction=DOWN;
      }
      else
      {
	if(s->size()==0) break;
	current=s->pop();
	direction=UPL;
      }
    }
  }
  return;
}

int selectNode()
{
  char ans='?';
  while(1)
  {
    printw("left(1/l) right(3/r) parent(5/p) quit(0/q):");
 //   cin >> ans;
    ans = getch();
    switch(ans)
    {
      case '1':
      case 'l': 
	if(NULL==p->childLeft)
	  { 
	    q=new tree;
	    q->childLeft=NULL;
	    q->childRight=NULL;
	    printw("Enter value of left node :");scanw("%d",&q->info);
	    p->childLeft=q;
	    q->parent=p;
	    p=q;
	  } 
	else
	    p=p->childLeft;
	break;
      case '3':
      case 'r':
	if(NULL==p->childRight)
	  {
	    q=new tree;
	    q->childLeft=NULL;
	    q->childRight=NULL;
	    printw("Enter value of right node :");scanw("%d",&q->info);
	    p->childRight=q;
	    q->parent=p;
	    p=q;
	  } 
	else
	    p=p->childRight;
	break;
      case '5':
      case 'p':
	if(p->parent!=NULL) p=p->parent;
	break;
      case '0':
      case 'q':
	return 1;
      default:
	continue;
    }
    return 0;
  }
}

int displayNode()
{
  if(NULL==p->parent) printw("\t\tROOT\n");
  else printw("\t\t%d\n",p->parent->info);
  printw("\t\t%d\n",p->info);
  if(NULL==p->childLeft) printw("\tNULL");
  else printw("\t%d",p->childLeft->info);
  if(NULL==p->childRight) printw("\t\tNULL\n");
  else printw("\t\t%d\n",p->childRight->info);
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
  { printw("%d ",current->info); return;}
  if(current->childLeft!=NULL)  postorder(current->childLeft);
  if(current->childRight!=NULL) postorder(current->childRight);
  printw("%d ",current->info);
}

void preorder(tree* current)
{
  if(current->childLeft==NULL && current->childRight==NULL)
  { cout<<current->info<<" "; return;}
  cout<<current->info<<" ";
  if(current->childLeft!=NULL)  preorder(current->childLeft);
  if(current->childRight!=NULL) preorder(current->childRight);
}

