/* The most used debugging statement
for (int i = 0; i < vl.size (); i++)
		cout << "Element " << i << " is " << vl.at(i) << endl;
*/

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector < char >vl, svl;
char arr[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g' };
struct edge
{
	char e1, e2;		//edges
	int wt;			//weight
	bool operator==(edge a);
} ed;

bool edge::operator== (edge a)
{
	return (a.e1 == e1 && a.e2 == e2 && a.wt ==wt);
}
char ed1[] = { 'a', 'a', 'b', 'c', 'c', 'd', 'e', 'e', 'f', 'b' };
char ed2[] = { 'b', 'g', 'c', 'd', 'e', 'e', 'f', 'g', 'g', 'g' };
int wt[] =   {  1,   2,  20,  4,   11,  5,   6,   10,  7  ,  3  };
 
vector < edge > el, sel;	//selected edge list or solution edge list

bool
cmp (edge a, edge b)
{
	return a.wt < b.wt;
}

int
main ()
{
	for (int i = 0; i < 7; vl.push_back (arr[i]), ++i) ;
	for (int i = 0; i < 10; ++i)
	{
		ed.e1 = ed1[i];
		ed.e2 = ed2[i];
		ed.wt = wt[i];
		el.push_back (ed);
	};

	//solution code starts
	sort (el.begin (), el.end (), cmp);
	sel.push_back (el.at (0));
	el.erase (el.begin ());
	svl.push_back (sel.at (0).e1);
	svl.push_back (sel.at (0).e2);
	vl.erase (find (vl.begin (), vl.end (), sel.at (0).e1));
	vl.erase (find (vl.begin (), vl.end (), sel.at (0).e2));
	vector < edge > sel_wtlt;	//making weight list
	vector<char>::iterator resultint;
	vector<edge>::iterator result;
	while (vl.size () != 0)
	{
		//make weight list -- list of the weights of all the edges connecting vertices of sel
		sel_wtlt.clear();

		for (int j = 0; j < el.size (); ++j)
		{
			cout<<"j:"<<j<<"\n";
			cout<<"el.at (j).e1"<<el.at (j).e1<<"  el.at (j).e2"<<el.at (j).e2<<endl;
			if ( ( (find(svl.begin(),svl.end(),el.at (j).e1) != svl.end())&&
				   (find(svl.begin(),svl.end(),el.at (j).e2) == svl.end())
				  )||
				 ( (find(svl.begin(),svl.end(),el.at (j).e2) != svl.end())&&
				   (find(svl.begin(),svl.end(),el.at (j).e1) == svl.end())
				 )
			)
			{
				cout<<"in first if j="<<j<<endl;
				if (sel_wtlt.empty()||(result=find(sel_wtlt.begin(), sel_wtlt.end(),el.at(j) ))==sel_wtlt.end())
				{
					sel_wtlt.push_back (el.at(j));cout<<"Pushed in sel_wtlt"<<el.at(j).wt<<endl;
				}
			}
		}
		for (int i = 0; i < sel_wtlt.size (); i++)
			cout << "Element of sel_wtlt" << i << " is " << sel_wtlt.at (i).wt << endl;
		sort(sel_wtlt.begin(),sel_wtlt.end(),cmp);
			result=find(el.begin(),el.end(),sel_wtlt.at(0));
			sel.push_back (*result);
			el.erase (result);
		
			if((resultint=find(svl.begin(),svl.end(),sel.at(sel.size()-1).e1))==svl.end())
				svl.push_back (sel.at(sel.size()-1).e1);
			else
				svl.push_back (sel.at(sel.size()-1).e2);
//binary_search wont work if the elements being searched are not in ascending order		
			
			resultint = find (vl.begin (), vl.end (), sel_wtlt.at (0).e1);
			if(resultint!=vl.end())
				vl.erase (find (vl.begin (), vl.end (), sel_wtlt.at (0).e1));
			else
				vl.erase (find (vl.begin (), vl.end (), sel_wtlt.at (0).e2));
	}
	for (int i = 0; i < vl.size (); i++)
		cout << "Element " << i << " is " << vl.at (i) << endl;
	for (int i = 0; i < sel.size (); i++)
		cout << "Element " << i << " is " << sel.at (i).wt << endl;
	return 0;
}
