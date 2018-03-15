# Basic Error Checking Section  # Nested comment

.text
main:
	add x1, x2, x3           # r-type
	addi x4, x5, -0xAF4B.7B  # i-type
	sw x6, 4(x7)             # s-type
	bne x8, x9, loop         # b-type
	lui x10, 0b1000111010    # u-type

.data
	msg: .string "Hello World"
	term: .word -1 4 0x3FFB
	