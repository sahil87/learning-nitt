#include <curses.h>//includes stdio.h
#include <iostream>
int main(void)
{
        initscr();
        printw("Press any key.");
        int a=getch();
        std::cout << a;
        getch();
        endwin();
        return 0;
}
