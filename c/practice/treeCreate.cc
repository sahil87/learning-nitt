#include <iostream>
#include <vector>
using namespace std;


struct treeNode
{
	int val;
	struct treeNode *parent,*ch1,*ch2;
}*root,*p;

void search(int value);
void preorder_stacks(treeNode *);

int main()
{
	root = new treeNode;
	root->parent=NULL;
	root->ch2=NULL;
	root->ch1=NULL;
	p=root;
	cout<<"root value : ";
	cin>>p->val;
	int ans=1,value;
	while(ans==1)
	{
		cout<<"Enter the value of the new node";
		cin>>value;
		
		search(value);
		p->val=value;
		p->ch1=NULL;p->ch2=NULL;
		cout<<"Do you want to continue?";
		cin>>ans;
	}
	cout<<"Preorder: ";
	preorder_stacks(root);	
}
void preorder_stacks(treeNode *p)
{
	vector<treeNode> s;
	while(1)
	{
		cout << p->val;
		if(p->ch2!=NULL) s.push_back(*p->ch2);
		if(p->ch1!=NULL) p=p->ch1;
		else
		{
			if(s.size()==0) break;
			p=&s.at(s.size()-1);
			s.pop_back();
		}
	}
}

void search(int value)
{
	p=root;
	while(1)
	{
		if(value < p->val )
		{
			if(p->ch1==NULL)
			{
				p->ch1=new treeNode;
				p=p->ch1;
				break;
			}
			p=p->ch1;
			continue;
		}
		else
		{
			if(p->ch2==NULL)
			{
				p->ch2=new treeNode;
				p=p->ch2;
				break;
			}
			p=p->ch2;
			continue;
		}				
		
	}
	return; 
}
