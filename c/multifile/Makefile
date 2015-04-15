SHELL = /bin/sh
CC = g++

a : firstA.o secondA.o
	$(CC) -o a firstA.o secondA.o
firstA.o : firstA.cpp firstA.cpp
	   $(CC) -c firstA.c -o firstA.o
secondA.o : secondA.cpp
	   $(CC) -c secondA.c -o secondA.o
clean :
	   rm firstA.o secondA.o

