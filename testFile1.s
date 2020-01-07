# Test program for assignment 1 - a MIPS Assembler

	nor $t1, $zero, $zero
	sub $t1, $zero, $t1
	add $t2, $t1, $t1
	add $t3, $t2, $t1 #woop
	and $t4, $t2, $t3
	or  $t4, $t2, $t1
	slt $t5, $t1, $t2
	add $t5, $t3, $t1
	sw  $t2, 4($t5)
	lw  $t4, 4($t5)
	nop

label:	nop
	nop
	nop
	sub $t4, $t3, $t3
	nop
	beq $t4, $zero, label
