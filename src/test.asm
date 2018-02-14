# Basic Error Checking Section

loop:
	add x1, x2, x3         # r-type
	subi x4, x5, 0xAF4B77  # i-type
	sw x6, 4(x7)           # s-type
	bne x8, x9, loop       # b-type
	lui x10, 0b1000111010  # u-type
