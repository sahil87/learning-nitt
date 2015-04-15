#!/usr/bin/python
#A program to make the user guess a number

num = 23
repeat = True

while repeat:
	guess = int(raw_input('Enter your guess : '))
	
	if guess == num:
		print 'Cool! you guessed the right number.'
		repeat = False
	elif guess > num:
		print 'You guessed a little higher'
	else:
		print 'You guessed a little lower'
else:
	print '\nExitting program.'
		
