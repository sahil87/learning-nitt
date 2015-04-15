#include<iostream>
#include"tree.h"
using namespace std;

int main()
{
  tree *tr;
  tr=new tree;
  tr->write(1);
  tr->left();
  tr->write(2);
  tr->right();
  tr->write(3);
  tr->root();
  cout<<tr->read();
  tr->left();
  cout<<tr->read();
  tr->right();
  cout<<tr->read();
}
