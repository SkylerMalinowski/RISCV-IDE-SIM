.text
addi s0 zero 20
# do
while:
	# t0 = t0 + 5
	addi t0 t0 5
	# while (t0 != s0)
	bne t0 s0 while
	# exit loop
	jal x0 end

end:
	# a0 = t0 + s0
	add a0 t0 s0

.data
	msg: .asciiz "Hello World"
