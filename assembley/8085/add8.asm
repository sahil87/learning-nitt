
;Add two 8-bit numbers

	jmp start
;data

var1:	db 8,10

;code
start:	lda var1
	mov b,a
	lda var1+1
	add b
	hlt