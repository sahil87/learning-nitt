 /* To display and move numbers of a matrix on the screen */
/*using GOTOXY(x,y)*/
#include<stdio.h>
#include<conio.h>
#include<time.h>
#include<stdlib.h>

#define ROW 4
#define COL 4
#define SIZE 4

int row=ROW,col=COL;
int s=SIZE;
int r,c,i;
int a[6][6],moves=0;

void main()
{	char ch='0';
	/*To show the array - */
	void boundry();/*to enter the boundries*/
	void display();/*to enter the numbers*/
	int cover();
	/*To change the array - */
	void randomise();
	void enter(char);/*to accept the input*/
	char check();
	/*Beginning nonsense*/
	void accept();
	void intro();

	for (r=0;r<row;r++) for (c=0;c<col;c++)
	a[r][c]=((r*col)+(c+1));

	clrscr();
	delay(1000);
	intro();
	clrscr();
	accept();
	ch='r';

	while(ch!=27)
	{       enter(ch);
		display(); cover();
		ch=check();
	}
}

void randomise()
{       void boundry();
	void enter();
	void display();
	int cover();

	int z=0,t;
	for (r=0;r<row;r++) for (c=0;c<col;c++)
	a[r][c]=((r*col)+(c+1));

	randomize();
	while (z<=4000)	z=random(5000);
	for(i=1;i<=2*z;i++)       /*even no of times so that it is always solvable*/
	{	c=random(col);/*one change creates one ghapla.. n 2 ghaplas cancel each other*/
		r=random(row);
   /* (1) */	if( (r+c)==0 || (r+c+2)==(row+col) ) i--;
		else
		{	t=a[r][c];
			a[r][c]=a[0][0];
			a[0][0]=t;
		}
	}

/* (1) :- r+c=0 nt allowed coz it will change with itself
	  r+c+2=(row*col) (last element) nt allowed coz it wud mean simply
	  making a move by sliding a no. if it is exchanged with one
	  of its neighbours */
/*So    change the last one here....*/

	for(i=row-1;i>r;i--)
	{	t=a[i][col-1];
		a[i][col-1]=a[i-1][col-1];
		a[i-1][col-1]=t;
	}
	for(i=col-1;i>c;i--)
	{	t=a[r][i];
		a[r][i]=a[r][i-1];
		a[r][i-1]=t;
	}

	moves=0;
	clrscr();
	gotoxy(5,30);
	boundry();
	display();	cover();
}

void enter (char ch)
{       void randomise();
	static x,y;
	int x1,y1;

	if(ch=='r')
	{  	randomise();
		for (r=0;r<row;r++) for(c=0;c<col;c++)
			if(a[r][c]==row*col)
			{	x=c; y=r;
			}
	}


	x1=x;	y1=y;   /*old x n y stored*/
	switch (ch)
	{	case '4':x+=1; if(x==col) x--;else moves++;	break;
		case '5':y-=1; if(y==-1) y++; else moves++;	break;
		case '6':x-=1; if(x==-1) x++; else moves++;	break;
		case '8':y+=1; if(y==row) y--;else moves++;	break;
	}

	i=a[y1][x1];
	a[y1][x1]=a[y][x];
	a[y][x]=i;

}

char check()
{       void win();

	gotoxy(1,25);

	for (r=0;r<row;r++) for(c=0;c<col;c++)
		if(a[r][c]!=(r*col+c+1))
			return(getch());

	win();
	return 0;
}

void win()
{	void enter(char);

	gotoxy(1,s*row+3);
	printf("You have won the game!!!!");
	printf("You completed the game in %d moves...",moves);
	getch();

	printf("\nPress any key to reset all the nos...");
	getch();
	enter('r');
}

int cover()
{	for (r=0;r<row;r++) for(c=0;c<col;c++)
	if(a[r][c]==row*col)
	{	gotoxy(s*c+2,s*r+2);
		for(i=1;i<s;i++) printf("%c",32);
		return(0);
	}
	return(0);
}





void display ()/*to display numbers*/
{
	for (r=0 ;r<row; r++)
	{	for (c=0 ;c<col; c++)
		{       gotoxy(s*c+2,s*r+2);
			for(i=1;i<=s-1;i++)	printf("%c",32);
			for(i=1;i<=s-1;i++)	printf("%c",8);
			printf("%d",a[r][c] );
		}
	}
	gotoxy(1,s*row+2);
	printf("Moves :%d",moves);
}


void boundry()/*boundries*/
{
	/*horizontal205*/
	for (r=0 ;r<=row; r++)
	{	for (c=0 ;c< col; c++)
		{	gotoxy(s*c+2,s*r+1);
			for(i=1;i<s;i++) printf("%c",205);
		}
	}
	/*vertical186*/
	for (c=0 ;c<=col; c++)
	{	for (r=0 ;r< row; r++)
		{	for(i=1;i<s;i++)
			{	gotoxy(s*c+1,s*r+1+i);
				printf("%c",186);
			}
		}
	}
	/*middles206*/
	for (r=1 ;r<row; r++)
	{	for (c=1 ;c< col; c++)
		{	gotoxy(s*c+1,s*r+1);
			printf("%c",206);
		}
	}
	/*top middles203*/
		for (c=1 ;c< col; c++)
		{	gotoxy(s*c+1,1);
		printf("%c",203);
		}
	/*bottom middles202*/
		for (c=1 ;c< col; c++)
		{	gotoxy(s*c+1,s*row+1);
		printf("%c",202);
		}
	/*left middles204*/
		for (r=1 ;r< row; r++)
		{	gotoxy(1,s*r+1);
		printf("%c",204);
		}
	/*right middles185*/
		for (r=1 ;r< row; r++)
		{	gotoxy(s*col+1,s*r+1);
		printf("%c",185);
		}
	/*topleft201*/
		gotoxy(1,1);			printf("%c",201);
	/*topright187*/
		gotoxy(s*col+1,1);		printf("%c",187);
	/*bottomleft200*/
		gotoxy(1,s*row+1);		printf("%c",200);
	/*bottomright188*/
		gotoxy(s*col+1,s*row+1);	printf("%c",188);

}

void accept()
{       int coolway();
	gotoxy(1,5);
	printf("What no of rows do you want?(3 to 6)\n[Default:3] ");
	row = coolway();
	printf("\n\nWhat no of colums do you want?(3 to 6)\n[Default:3] ");
	col = coolway();
	delay(700);
}

int coolway()
{       char inpc;
	int wait=9,inp;
	fflush(stdin);
	for(r=1;r<=12;r++)	printf("a");
	for(i=1;i<=wait*10-1;i++)
	{       for(r=1;r<=12;r++)	printf("%c",8);
		printf("(%d/%d secs) :",(i/10+1),wait);
		delay(100);
		if(kbhit()!=0)
		{
/*to get single character input- and still be able to press enter*/
			inpc=getche();
			inp=inpc-48;

			if(inp<3||inp>6)
			{	printf("\nOut of bounds... using default value");
				return(3);
			}
			else
				return(inp);
		}
	}
	printf("\nTime out!.. using default value");
	return(3);
}

void intro()
{	int design();
	design();
	textcolor(15);/*necessary coz if one decides to exit the star pattern
before it completes the rest of the prog shows up in color*/
	clrscr();
	printf("\nObjective\t:To arrange all the nos in ascending order");
	printf("\nControls\t:MAKE SURE THE NUM LOCK IS ON");
	printf("\n\t\t Play using the numpad");
	printf("\n\t\t 8 : up");
	printf("\n\t\t 5 : down");
	printf("\n\t\t 4 : left");
	printf("\n\t\t 6 : right");
	printf("\n\t\t r : randomize");
	printf("\n\t\t escape : exit");
	printf("\n\n\nHavent learnt file handling yet...");
	printf("\nSo cant store highscores...");
	printf("\nWould be incorporated soon ;)");
	printf("\n\n\nPress any key to continue");
	fflush(stdin);
	getch();

}

int design()
{	int line,i,j,c;
	int dikha(char [],int);
	clrscr();
	textcolor(15);
	randomize();
	line = 15;

	fflush(stdin);
       /*star pattern - */
	for (i=1;i<=line;i++)
	{	 for (j=1;j<=line-i;j++)
			printf("  ");
		 for (j=1;j<=(2*i-1);j=j+1)
			{textcolor(/* (((j+9==16)||(j+9==32))?2:j+9)*/ ((c=random(15))!=0)?c:1 );
			 cprintf("* ");
			}
		 if(kbhit()!=0) return(0);
		 delay (5*(line - i));
		 printf("\n");
	}
	delay(100);
	gotoxy(1,1);

	for (i=1;i<=line;i++)
	{	for (j=1;j<=line-i;j++)
		{	textcolor( ((c=random(15))!=0)?c:1 );
			cprintf("* ");
		}
		gotoxy(((line-i)+(2*i-1))*2,i);
		for (j=1;j<=line-i;j++)
		{       textcolor( ((c=random(15))!=0)?c:1 );
			cprintf(" *");
		}
		if(kbhit()!=0) return(0);
		delay (5*(line - i));
		printf("\n");
	}
	delay(200);
	gotoxy(1,1);
	/*removing the stars*/
	for(i=1;i<=2*line-1;i++)
	{       for(j=1;j<=line/2;j++)
		{	gotoxy(2*i-1,j);
			printf("  ");
		}
		delay(2*line-i);
		if(kbhit()!=0) return(0);
	}
	delay(250);

	for(i=1;i<=2*line;i++)
	{       for(j=line/2+1;j<=line;j++)
		{	gotoxy(((2*line-1)-i)*2+1,j);
			printf("  ");
		}
		delay(2*line-i);
		if(kbhit()!=0) return(0);
	}

	delay(500);
	/*text*/
	gotoxy(1,1);
	dikha("This is a game by:",10);
	gotoxy(10,2);
	dikha("S A H I L   A H U J A",50);
	gotoxy(10,3);
	dikha("First Year",50);
	gotoxy(10,4);
	dikha("1 0 4 0 3 5 5",50);

	/*wait for 4 secs or exit when user presses a key*/
	for(i=1;kbhit()==0&&i<=40;i++)
		delay(100);

	return(0);
}

int dikha(char s[],int wait)
{       int i;
	textcolor(15);
	for(i=0;s[i]!='\0';i++)
	{	cprintf("%c",s[i]);
		if(kbhit()!=0) return(0);
		delay(wait);
	}
	return(0);
}