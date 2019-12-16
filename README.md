# Obligatory Assignment 1 - A MIPS Assembler
## Group members
* Emil Söderlind (id15esd)
* Filippa D Lidman (c17fdn)
* Amanda Ryman (bio16arn)
* Martin Hedberg (c17mhg)
## User manual
To run the assembler you need a input file, path name to hex output text file and path name to pretty print text file.

#### Running assembler
To run the assembler one runs the following in the src/ directory:

```console
foo@bar:~$ javac MIPSAssembler.java compiler/*.java
foo@bar:~$ java MIPSAssembler <Input file> <Pretty print path> <Hex output path>
```

#### Input file
The input file should contain the valid assembly code to be assembled. Example: 
```
# This is an example
label0:
label1: addi  $t1, $zero, 1   # A comment
	addi  $t2, $zero, 2
	addi  $t3, $zero, 3		
	addi  $t4, $zero, -4
```

The commands which the assembler supports is: add, sub, and, or, nor, slt, lw, sw, beq, addi, sll, j, jr and nop.

#### Hex output text file
The assembler will produce a text file containing the input file's commands in assembly hex format. Example: 
```
0x20090001
0x200a0002
0x200b0003
0x200cfffc
```

#### Pretty print text file
The assembler will produce a text file containing the input file's commands both in hex format and it's orignal format. Including labels/commands addresses. Example: 
```
                        # This is an example
                        label0:
0x00000000  0x20090001  label1: addi  $t1, $zero, 1   # A comment
0x00000004  0x200a0002          addi  $t2, $zero, 2
0x00000008  0x200b0003          addi  $t3, $zero, 3
0x0000000c  0x200cfffc          addi  $t4, $zero, -4

Symbols
label0   0x00000000    label1   0x00000000    
```
