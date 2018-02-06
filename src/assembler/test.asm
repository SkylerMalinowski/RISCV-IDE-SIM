# testing comment
msg:	.string "Hello World\n"
.extern CONST_VAR 4 

.global _start
_start:
	ori s0, t11, 0xAB48
	sw , 16(a0)
	addi t3, zero, -020
	fmv.d fs0, fa2

return:
	jr ra # Return.
