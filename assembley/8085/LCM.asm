;To get the LCM of two numbers
	jmp start
;data
answer: ds 1
stack:  ds 20
first:	db 18
second:	db 27



start:	LXI SP,stack+19
	LDA first
	MOV B,A
	LDA second
	MOV C,A
	CMP B
	JC swapbc
retswapbc:	MVI D, 00H
repeat:		JMP next
retnext:	JMP check
retcheck:	JMP repeat

swapbc: MOV D,C
	MOV C,B
	MOV B,D
	JMP retswapbc

next:	MOV A,D
	ADD C
	MOV D,A
	CPI 128
	JC retnext
	MVI D,00
	JMP done

check:	MOV E,D
	MOV A,E
sub:	SUB B
	JZ done
	CPI 128
	JC sub
	JMP retcheck

done:	MOV A,D
	STA answer
	HLT
	