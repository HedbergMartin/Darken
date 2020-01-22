package datapath;

public class Control {
	
	private int output = 0;
	public void perform(int instruction, int funct) {
    	switch (instruction) {
			case 0:
				if (funct == 8) {
					output = 0b10000000000;
				} else {
					output = 0b01000100010;
				}
				break;
			case 2: //Jump
				output = 0b00100000000;
				break;
			case 4: //Beq
				output = 0b00000000101;
				break;
			case 8: //Addi
				output = 0b00010100000;
				break;
			case 13: //Ori
				output = 0b00010100000;
				break;
			case 35: //LW
				output = 0b00011110000;
				break;
			case 43: //SW
				output = 0b00010001000;
				break;
			default:
				System.out.println("Control error: " + Integer.toBinaryString(instruction));
				break;
		}
    }
    
    public boolean isJumpReg() {
    	return getBit(output, 10);
    }

    public boolean isRegDst() {
		return getBit(output, 9);
	}

    public boolean isJump() {
		return getBit(output, 8);
	}

	public boolean isALUSrc() {
		return getBit(output, 7);
	}

	public boolean isMemtoReg() {
		return getBit(output, 6);
	}

	public boolean isRegWrite() {
		return getBit(output, 5);
	}

	public boolean isMemRead() {
		return getBit(output, 4);
	}

	public boolean isMemWrite() {
		return getBit(output, 3);
	}

	public boolean isBranch() {
		return getBit(output, 2);
	}

	public boolean isALUOp1() {
		return getBit(output, 1);
	}

	public boolean isALUOP0() {
		return getBit(output, 0);
	}
	
	private boolean getBit(int value, int position) {
	   return ((value >> position) & 1) == 1;
	}
}
