#include <iostream>
using namespace std;

int main()
{
  extern int a; // if not use extern .. then wont work with add1.cpp will show garbage
  cout << a;
}
