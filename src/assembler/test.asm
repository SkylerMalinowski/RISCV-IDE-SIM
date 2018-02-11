# Basic Error Checking Section
msg:	.string "Hello World\n"
.extern CONST_VAR 4 
.global _start
_start:
	ori s0, t11, 0xAB48
	sw , 16(a0)
	addi t3, zero, -020.6
	fmv.d fs0, fa2
return:
	jr ra # Return.

# Invalid Syntax Behavior Checking Section
msg:	.string "Hello World\n" # Hello World
..extern CONST_VAR 4  	  # Constant Variable
.global __start
_start::
	ori s0, t11, 00xXAb48
	sw , 16(a0a0)
	addi t3, zeroo, -+020.6
	fmv..d fss0, fa22
return:
	jr ra # Return ## Address
