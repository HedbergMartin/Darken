# Obligatory Assignment 2 - A MIPS Simulator
## Group members
* Emil Söderlind (id15esd)
* Filippa D Lidman (c17fdn)
* Amanda Ryman (bio16arn)
* Martin Hedberg (c17mhg)
## User manual

#### Running simulator
To run the simulator one runs the following in the src/ directory:

```console
foo@bar:~$ javac program/MIPSsimulator.java listeners/*.java gui/*.java 
            datapath/*.java compiler/*.java
foo@bar:~$ java program.MIPSsimulator
```

#### GUI overview

##### Open file
In the upper left corner of the GUI one can import a valid valid assembly code file to be imported.

The input file should contain the valid assembly code to be assembled. Example: 
```
# This is an example
label0:
label1: addi  $t1, $zero, 1   # A comment
	addi  $t2, $zero, 2
	addi  $t3, $zero, 3		
	addi  $t4, $zero, -4
```

##### Register panel
To the left in the GUI one can observe the full content of the MIPS processors registers.

##### Program panel
To the right in the GUI one can observe the currently imported assembly code to be executed by the simulator. Including the current executed row, instruction etc.

##### Memory panel
In the lower part of the GUI one can observe the full content of the MIPS processors memory.

##### Available buttons
In the upper middle of the GUI one can performe one clock cycle of execution, run the whole program, reset the program execution and switch between displaying register content in decimal/hex-format.

#### Supported commands
The commands which the assembler supports is: add, sub, and, or, nor, slt, lw, sw, beq, addi, sll, j, jr and nop.
