
;To solve an eqn y = ab + c/2
;org 4100h
		jmp start
;data
answer: ds 2
first:	db 20
second:	db 20
third: db 5

start:	LDA first
	MOV L,A
	XRA A
	MOV H,A
	MOV D,A
	LDA second
	MOV E,A
	JMP MULTIPLY
MULTIPLY:	DCR A	
	CPI 00
	JZ ADD
	DAD D
	JMP MULTIPLY
ADD:	XRA A
	LDA third
	RAR
	MOV E,A
	DAD D
	SHLD answer
	HLT
	