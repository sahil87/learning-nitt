#include <iostream>
#include "stack_mine.h"
using namespace std;

int main()
{
  stack *s;
  s = new stack();
  int i;
  s->push(i=2);
  cout<<endl<<s->size();
  s->push(i=57);
  s->push(89);
  cout<<s->pop();
  cout<<endl<<s->size();
  cout<<s->pop();
  cout<<s->pop();
  cout<<s->pop();
  cout<<s->pop();
  cout<<endl<<s->size();
  cout<<s->pop();
  s->push(89);
  cout<<endl<<s->size();
  cout<<s->pop();
  
  return 0;
}

