#include<stdio.h>
#include<conio.h>
#include<dos.h>

#include<time.h>
#include<stdlib.h>

#define ROW 25
#define LINES COL
#define COL 80
#define SQUARES 5

int r,c,i,z;
char a[ROW][COL];
char value[ROW][COL];
int counter=0,lens;/*length of s*/

int rang[ROW][COL];
struct linktype
	{       int onoff;
		int speed;
		int length;
		int pf;/*position of first*/
	};
struct blastingsquare
	{	int onoff;
		int xcen;/*xcenter*/
		int ycen;
		int size;
	};
typedef struct linktype SL;
SL lin[LINES];
typedef struct blastingsquare SB;
SB sq[SQUARES];

char *s="SAHIL ";
int length(char *s)
{	int i;
	for(i=1;*(s+i)!=0;i++);
	return (i);
}

int main()
{       void display();
	void initialize();
	void changematrix();
	void changesquares();
	int intro();
	int length(char *s);

	fflush(stdin);
	textbackground(0);
	clrscr();
	initialize();
	randomize();
	delay(0);
	intro();
	initialize();
	lens=length(s);
	if(kbhit()!=0)	getch();
   /*	instead of fflush(stdin);*/


	while(1)
	{       if(kbhit()!=0)	return 0;
		changematrix();
		changesquares();
		display();
//		delay(2);
	}
}

void initialize()
{     	for(r=0;r<ROW;r++)
		for(c=0;c<COL;c++)
		{	a[r][c]=32;
			value[r][c]=32;
		}

	for(i=0;i<LINES;i++)
	{    	if(lin[i].speed==0) lin[i].speed=2;   /*coz if speed 0 then later % creates trouble*/
	      /*	if(lin[i].length==0) lin[i].length=10;*/
	}
}

void display()
{
	void putsqcolor(int xcen,int ycen,int size);


	/*To randomise the colors*/
	for(c=0;c<COL;c++)
	{       /*colors of the first row*/
		if(a[1][c]==32 && a[0][c]!=32)
		{       i=random(2);
			if(i==0)	rang[0][c]=2;
			else            rang[0][c]=10;
		}
		/*colors of the others... copy*/
		for(r=1;r<ROW;r++)
		if(a[r][c]!=32)
			rang[r][c]=rang[r-1][c];
	}

	/*to display with those colours*/
	for(r=0;r<ROW-1;r++)
	{      gotoxy(1,r+1);
	       for(c=0;c<COL;c++)
	       {        if(value[r][c]==32)
			{	textcolor((a[r+1][c]==32)?15:rang[r][c]);
				cprintf("%c",a[r][c]);
			}
			else
			{	textcolor(10);
				cprintf("%c",value[r][c]);
			}
	       }
	}
}

void changesquares()
{       void change(int xc,int xy,int size);
	/*make the square matrix space*/
	for(r=0;r<=ROW-1;r++)
		for(c=0;c<=COL-1;c++)
			value[r][c]=32;

	for(i=0;i<SQUARES;i++)
	{    	if( sq[i].onoff != 1 )
			sq[i].onoff=random(300);
		else
		{       /*for the whole square to be displayed - */
		     /*	r=max(max(sq[i].xcen,COL-sq[i].xcen),
			      max(sq[i].ycen,ROW-sq[i].ycen) );*/
			/*to display till the top touches the corners - */
			if(sq[i].ycen>=ROW-sq[i].ycen)	r=sq[i].ycen;
			else r=ROW-sq[i].ycen;
	       /*	z= max(sq[i].ycen,ROW-sq[i].ycen) ;   */
			if(sq[i].size>r||sq[i].xcen==0)/*initial condition*/
			{	sq[i].onoff=0;
				sq[i].xcen=random(COL);
				sq[i].ycen=random(ROW);
				sq[i].size=0;
			}
			else
			{     change(sq[i].xcen,sq[i].ycen,sq[i].size);
			     /* if(counter%2==0) */	sq[i].size++;
			     /* if(sq[i].size==r-7)	delay(50);*/
				if(sq[i].size==2)	delay(50);
			}
		}
	}

}

void change(int xc,int yc,int size)
{
	/*then assign the required values with conditions*/
	for(z=0 ;z<=2*size; z++)
	{       /*top*/
		if( (xc-size+z)>=0 && (xc-size+z)<=COL-1 &&
		    (yc-size)>=0   &&	(yc-size)<=ROW-1 && z!=2*size)
			value[yc-size][xc-size+z]=*(s+(z%lens));
		/*left*/
		if( (xc-size)>=0   && (xc-size)<=COL-1 &&
		    (yc-size+z)>=0 &&	(yc-size+z)<=ROW-1&& z!=2*size)
			value[yc-size+z][xc-size]=*(s+(z%lens));

		/*right*/
		if( (xc+size)>=0   && (xc+size)<=COL-1 &&
		    (yc-size+z)>=0 &&	(yc-size+z)<=ROW-1)
			value[yc-size+z][xc+size]=*(s+(z%lens));
		/*bottom*/
		if( (xc-size+z)>=0 && (xc-size+z)<=COL-1 &&
		    (yc+size)>=0   &&	(yc+size)<=ROW-1)
			value[yc+size][xc-size+z]=*(s+(z%lens));

	}
}

void changematrix()
{       counter++;


	/*first drag it down... drag above change coz we are only creating
	the top row... so we need to make space for it*/
	/*copy every link down*/
	for(i=0;i<LINES;i++)
	{ if( (lin[i].onoff==1)&&(counter%(lin[i].speed)==0))
	  {
		for(r=ROW-1;r>=1;r--)
			a[r][i]=a[r-1][i];
		/*fill the top of the dragged link with space*/
		if(lin[i].pf>0)
			a[lin[i].pf-1][i]=32;
	  }
	}

	/*then change the links*/
	for(i=0;i<LINES;i++)
	{
		/*check onoff, if off then getting on is 50% chance
		  and keep increasing posn of first till it reaches
		  bottom of screen made sure it doesnt get on till then*/
		if(lin[i].onoff!=1)
			lin[i].onoff=random(25);

		/*if on then check if its has completed its length*/
		/*lin[i].pf>(ROW) used instead of ROW-1 coz space is put at a
		row one before posn of first.. so the last space doesnt get
		removed if we dont make pf also the one after ROW-1
		(the last row)*/
		else if (lin[i].pf>(ROW)||lin[i].length==0)/*initial case condition*/
		{       /*1) Turn it off*/
			lin[i].onoff=0;
			/*2) Give random length again*/
			do     z=random(30); 	while(z<10);
			lin[i].length=z;
			/*3) Make position of first above screen*/
			lin[i].pf=-z;/* -z or -z+1 ???*/
			/*4) Give it a new speed*/
			lin[i].speed=random(4)+1;
			/*+1 coz if speed is 0 the % operator acts up...*/
			while((lin[i].speed==lin[i-1].speed)||(lin[i].speed==lin[i+1].speed))
			{	lin[i].speed=random(4)+1;
			}/* to make sure the adjacent ones dont have the same speeds*/
		}

		/*if it has fully crossed the first row... the pf still needs
		  to be increased*/
		else if(lin[i].pf>0/*lin[i].length*/ && counter%lin[i].speed==0)
				lin[i].pf++;
		/*if its on and continuing then give its coressponding
		  column a random character*/
		else if(counter%(lin[i].speed)==0)
			{
				a[0][i]=random(104)+64;
				lin[i].pf++;
			}

	}
	/*fill all remaining columns with space*/
	for(i=0;i<COL;i++)
	{       if( lin[i].pf<=0 )/*to see if*/
			continue; /*the last one has gotten out*/
		else
			a[0][i]=32;
	}
}

int intro()
{
	int intro1(char *,int len,int irow,int icol);
	int intro2(char *,int len,int irow,int icol,int delay1,int delay2);
	int length(char *);

	char *s1="T h e  M a t r i x";
	char *s2="                      ";
	char *s3="By:";
	char *s4="S A H I L  A H U J A";
	char *s5="1 0 4 0 3 5 5";
	char *s6="First Year-NIT Trichy";

	intro1(s1,length(s1),8,24);    		if (kbhit()!=0) return 0;
	intro2(s2,0,9,42,0,4000);               if (kbhit()!=0) return 0;
	intro2(s2,length(s1),9,24,25,200);      if (kbhit()!=0) return 0;

	intro2(s3,length(s3),12,25,45,1000);  	if (kbhit()!=0) return 0;
	intro2(s4,length(s4),13,25,45,500);  	if (kbhit()!=0) return 0;
	intro2(s5,length(s5),14,25,45,0);  	if (kbhit()!=0) return 0;
	intro2(s6,length(s6),15,25,45,3000);  	if (kbhit()!=0) return 0;

	intro1(s2,length(s2),8,25);    		if (kbhit()!=0) return 0;

	return 0;
}

int intro2(char *s1,int len,int irow,int icol,int delay1,int delay2)
{
	gotoxy(icol,irow);

	for(i=0;i<len;i++)
	{       gotoxy(icol+i+1,irow);
		delay(delay1);
		textcolor(2);
		cprintf("%c",*(s1+i));
		textcolor(10);
		cprintf("%c",219);
		if (kbhit()!=0) return 0;
	}

	textcolor(10);
	for(r=0;r<((delay2/700)/2);r++)
	{       gotoxy(icol+i+1,irow);
		cprintf("%c",219);
		gotoxy(80,25);
		delay(700);
		gotoxy(icol+i+1,irow);
		cprintf("%c",32);
		gotoxy(80,25);
		delay(700);

		if (kbhit()!=0) return 0;

	}
	gotoxy(icol+i+1,irow); cprintf("%c",32);

	return 0;
}



int intro1(char *s1,int len,int irow,int icol)
{       int intro1change(int irow,int icol,int letter);
	for(i=1;i<=len;i++)
	{       if(*(s1+i-1)==32)	counter=0;
		intro1change(irow,icol+i-1,*(s1+i-1));

		if (kbhit()!=0) return 0;
	}
	return 0;
}
int intro1change(int irow,int icol,int letter)
{       void intro1display(int icol);
	textcolor(2);

	/*first row*/
	while(1)
	{	if(letter!=32)
		{	if(a[irow][icol]==letter)
			{	a[0][icol]=32;
				z=1;
				for(r=1;r<=ROW-1;r++)
					if(r!=irow&&a[r][icol]!=32)
					{	z=0;
						break;
					}
				if(z==1)	return 0;
			}
			else
				a[0][icol]=((z=random(91)+32)==letter?random(2)+letter:z);
		}
		else
		{       if(counter<=30)
				a[0][icol]=random(91)+32;
			else
				a[0][icol]=32;
			if(counter>30+ROW||kbhit()!=0)	return 0;
			counter++;
		}
		intro1display(icol);

		/*drag down*/
		for(r=ROW-1;r>=1;r--)
		{	a[r][icol]=a[r-1][icol];
			if(a[irow+1][icol]==letter&&letter!=32)
			{       z=a[irow][icol];
				a[irow][icol]=a[irow+1][icol];
				a[irow+1][icol]=z;
			}
		}
	}
}
void intro1display(int icol)
{     	delay(1);
	for(r=0;r<ROW-1;r++)
	{       gotoxy(icol+1,r+1);
		cprintf("%c",a[r][icol]);
	}
}
