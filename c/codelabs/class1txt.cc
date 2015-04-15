#define LET(x,a) typeof(a) x(a)
#define FOR(i,a,b) for(LET(i,a);i!=(b);++i)
#define REP(i,n) FOR(i,0,n)
#define EACH(it,v) FOR(it,(v).begin(),(v).end())
#define sz size()
#define pb push_back
#define cs c_str()

#define GI ({int t;scanf("%d",&t);t;})
#define COUNT(f,x) ({int _=0;f _+=(x);_;})
#define EXISTS(f,x) ({int _=0;f if(x) {_=1;break;}_;})
#define ALL(f,x) (!EXISTS(f,!(x)))
#define MIN(f,x) ({LL _=LINF;f _<?=(x);_;})
#define MAX(f,x) (-MIN(f,-(x)))

eg: Print the no of primes between a and b (a,b >= 2)
cout << COUNT(FOR(n,a,b), !EXISTS(FOR(i,2,n), n%i == 0)) << endl;

containers, iterators;
vector, string, set, map, queue, deque
algorithm, pair

1) Macro preprocessing 
2) Containers
3) Iterators
4) Blocks eval to values
5) TODO: add reference materials url;

Writing bruteforcers ::
	Generators and filters. 
	Run time & memory - 10^8
	
	1) n-queens problem::
Print the no of ways you can place n-queens on a nxn chess board.

Solution: (WARNING:untested code follows)
int a[n]; REP(i,n) a[i] = i;
int ans = 0;
do {
	ans += ! ( EXISTS(REP(i,n), EXISTS(REP(j,i), 
				abs(i-j) == abs(a[i]-a[j]) )));
} while(next_permutation(a,a+n));

	Time ?
	Memory?
	
	2) Palindromes::
Find the number of binary (0/1 digits) palindromes of length n which donot contain "100" as its substring;

Solution: (WARNING:untested code follows)
string S; REP(i,n) S += '*';
ans = go(0,n-1);
int go(int i, int j) {
	if(S.find("100") != string::npos) return 0;
	if(i > j) return 1;
	S[i] = S[j] = '0';
	int r = go(i+1,j-1);
	S[i] = S[j] = '1';
	r += go(i+1,j-1);
	S[i] = S[j] = '*';
	return r;
}

	Time?
	Memory?

	3) Nim game ::
(Multi-player games)
Two players pick coins from a pile intially containing n coins.
At a players turn he can either choose 1,3 or 8 top most coins from the pile;
When a player cannot make a pick in his turn (no coins left), he loses. 
Given n, tell me if both players play intelligently (play for a win), who will win?
(The guy who plays first or he who plays second?)

Solution: (WARNING:untested code follows)
bool first(int n) { 
	return n <= 0 ? n<0 : !first(n-1) || !first(n-3) || !first(n-8);
}
	
	4) K lie problem ::
Step 0: Person A thinks of a number in the range 1 to n.
Step 1: B asks A if the number is within the subset S 
Step 2: A replies yes/no. He can choose to lie! He can lie atmost K times;
Step 3: If B couldnot uniquely identify the number A couldve thought, he goes back to Step 1 asking a new question.

Now the problem you are required to answer is ::
What is the maximal number of times B has to ask questions (Step 1), if B always plays intelligently trying to
minimise the number of questions he asks.
i.e., assuming the worst number A couldve chosen. Which is same as assuming, A keeps changing his number in mind, and
only pretends to have thought of the number before. Ofcourse, his final number must obey all the lie constraints subject to the answers that he gave ...

Minimax game :: A maximises, B minimises; Optimal path of the game movement;
Solution will be given next class;


Structure of a backtrack:
function() {
	filtration checks
	if(endpoint-reached) { print(); return; }
	for every possible trainsition {
		effect the transition
		recursive call function()
		nullify/revert the transition
	}
}

Common generators:


G##1 ::(WARNING:untested code follows)
int A[n], used[n]; REP(i,n) used[i] = 0;
go(0);
void go(int i) {
	if(i == n) { print_array(A); return ; }
	REP(p,n) if(!used[p]) {
		used[p] = 1;
		A[i] = p;
		go(i+1);
		used[p] = 0;
	}
}

G##2 ::(WARNING:untested code follows)
int A[K];
go(K,N);
void go(int k, int n) {
	if(!k || !n) { if(!n && !k) print_array(A); return; }
	go(k,n-1);
	A[index] = max;
	go(k-1,n-1);
}

Generator??
//////////////////////////////////////////
Tricks using generators?
next_generator(f) {
	
}
	5) Solid number ::
Let us call solid number as a number whose digits donot repeat & it doesnot contain a zero digit.
Given a solid number N, print the smallest solid number > N;

Lengthy soln:
Find next-permutation of digits in N;
if(found) { print; return; }
Find next-combination of digits in N chosen against 1..9;
if(found) { print; return; }

////////////////////////////////////////////
Dynamic programming?
