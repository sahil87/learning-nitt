template<class SType> class stack
{
	SType arr[10];
	int tos;
public:
	stack();
	void push(SType);
	SType pop();
};

template<class SType> stack<SType>::stack()
{
	tos=0;
}

template<class SType> void stack<SType>::push(SType s)
{
	arr[tos]=s;
	tos++;
}
