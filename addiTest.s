addi  $t1, $zero, 1
ori $t2, t1, 1
ori $t2, t1, 0
srl $t2, $t1, 1
sra $t3, $t1, 1
sw $t1, 1($t1)
lw $t5, 1($t1)
addi  $t2, $zero, 2
addi  $t3, $zero, 3
addi  $t4, $zero, -4