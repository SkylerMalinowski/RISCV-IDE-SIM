# Basic Error Checking Section  # Nested comment

#.text main
#main:
	add x1, x2, x3          # r-type
	subi x4, x5, 0xAF4B.7B  # i-type
	sw sp, 4(x7)            # s-type
	bne x8, x9, loop        # b-type
	lui x10, 0b1000111010   # u-type
	